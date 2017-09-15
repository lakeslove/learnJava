package learnIO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.util.Arrays;

public class IOTest1 {
	public static void main(String[] args) throws IOException{
		String inputSqlfileName = "sqlModify/input/WELLNESS_DATA_201612021559.sql";
		String outputSqlfileName = "sqlModify/output/write.txt";
		
		
		File sql = new File(outputSqlfileName);
		if(!sql.exists()){
			sql.createNewFile();
		}
		RandomAccessFile raf = new RandomAccessFile(sql, "rw");
		raf.seek(raf.length());
//		String[] writeStringArray = new String[]{"������1","������2","������3"};
//		String writeString = Arrays.toString(writeStringArray);
//		raf.write(("\n"+writeString.substring(1, writeString.length()-1)).getBytes());
//		raf.close();
		BufferedReader in = new BufferedReader(new FileReader(inputSqlfileName));
		String line;
		while((line=in.readLine())!=null){
			raf.write((line+"\n").getBytes());
		}
		raf.close();
		in.close();
		
//		StringBuffer sb1 = new StringBuffer("����");
//		String s = sb1.toString();
//		System.out.println(s);//�������
//		File file = new File("");
//		System.out.println(file.getAbsolutePath());
//		String fileName = "src/learnIO/Stream.java";
//		FileInputStream in = new FileInputStream(fileName);
//	    byte[] bbuf = new byte[1024]; 
//	    StringBuffer sb = new StringBuffer(in.available());
//        
//        //���ڱ���ʵ�ʶ�ȡ���ֽ���           
//        int hasRead = 0;  
//        //ʹ��ѭ�����ظ���ȡ����  
//        while( (hasRead = in.read(bbuf)) > 0){  
//             sb.append(new String(bbuf,0,hasRead));
//        }  
//		String content = sb.toString();
//		content=content.replaceAll("\r\n", "<br>");
//		System.out.println(content+"\n"+"���ַ���: "+content.length());
//		System.out.println("file.getParent() : "+file.getParent());
////		System.out.println("file.renameTo() : "+file.renameTo(new File("tests")));
//		System.out.println("file.renameTo() : "+file.length());
//		(new File("a/b/c")).mkdirs();
//		Runtime rt = Runtime.getRuntime();
//		try {
//			rt.exec("").waitFor();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		String[] nameList = file.list((cxdir,name) -> name.endsWith(".java") || (new File("./src/"+name)).isDirectory());
//		for(String temName : nameList){
//			System.out.println(temName);
//		}
	}

}
