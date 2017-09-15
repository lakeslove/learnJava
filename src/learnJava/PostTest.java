package learnJava;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
//import org.apache.commons.lang.StringUtils;


public class PostTest {
	public static void main(String[] arg) throws JsonGenerationException, JsonMappingException, IOException {  
		 String exampleUrl = "http://openam.example.com:8080/openam/json/users/?_action=create";
		String param = "AQIC5wM2LY4Sfcw22XCZ3VAcUKPgCudM1rknLXD4cb1MylM.*AAJTSQACMDEAAlNLABQtODQ5NDA2MTgxMTM5NjYwMjc5MgACUzEAAA..*";
//		String data = "{'username': 'user8','userpassword': 'password','employeeNumber':'employeeNumbertest'}";
		Map<String, String> data = new HashMap<String, String>();
		data.put("username", "user19");
		data.put("userpassword", "password");
		data.put("employeeNumber", "employeeNumbertest");
		
		String tmp = requestAmPost(exampleUrl, param,getJsonString(data),"POST",null);
		System.out.println(tmp);
   } 
	public static String getJsonString(Object data) throws JsonGenerationException, JsonMappingException, IOException{
		ObjectMapper objm = new ObjectMapper();
		String supplierJson = objm.writeValueAsString(data);
		return supplierJson;
	}
	public static String requestAmPost(String exampleURL,String param,String data,String method,Map<String,String> headerMap) {
		String tokenId = param;
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(exampleURL);
			HttpURLConnection conn = (HttpURLConnection)realUrl.openConnection();
			conn.setRequestMethod("POST");
//			URLConnection conn = realUrl.openConnection();
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Cookie","iPlanetDirectoryPro="+tokenId);
			if(headerMap!=null){
				for( Map.Entry<String, String> entry : headerMap.entrySet()){
					conn.setRequestProperty(entry.getKey(), entry.getValue());
				}
			}
			 
			if("POST".equals(method)||"PUT".equals(method)){
				conn.setDoOutput(true);
				conn.setDoInput(true);
				out = new PrintWriter(conn.getOutputStream());
				if(data!=null){
					out.print(data);
				}
				out.flush();
			}
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));  
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送 POST 请求出现异常！" + e);
			e.printStackTrace();
		}
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
