package learnJava;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class LearnHttp {
	
	public static void main(String[] arg) throws JsonGenerationException, JsonMappingException, IOException {  
		 String exampleUrl = "http://openam.example.com:8080/openam/json/users/?_action=create";
		String param = "AQIC5wM2LY4Sfcw22XCZ3VAcUKPgCudM1rknLXD4cb1MylM.*AAJTSQACMDEAAlNLABQtODQ5NDA2MTgxMTM5NjYwMjc5MgACUzEAAA..*";
//		String data = "{'username': 'user8','userpassword': 'password','employeeNumber':'employeeNumbertest'}";
		Map<String, String> data = new HashMap<String, String>();
		data.put("username", "user16");
		data.put("userpassword", "password");
		data.put("employeeNumber", "employeeNumbertest");
		
		String tmp = requestAmPost(exampleUrl, param,getJsonString(data));
		System.out.println(tmp);
    }  
	public static String getJsonString(Object data) throws JsonGenerationException, JsonMappingException, IOException{
		ObjectMapper objm = new ObjectMapper();
		String supplierJson = objm.writeValueAsString(data);
		return supplierJson;
	}
	public static String requestAmPost(String exampleURL,String param,String data) {
		// public static String sendPost(String url) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(exampleURL);
			HttpURLConnection conn = (HttpURLConnection)realUrl.openConnection();
			conn.setRequestMethod("POST");
			// 打开和URL之间的连接
//			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("iPlanetDirectoryPro",param);
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Cookie","iPlanetDirectoryPro="+param);
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(data);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("向example发送 POST 请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}


}
