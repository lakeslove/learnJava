package learnJava;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.regex.Pattern;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class javas {
	public static void main(String[] args) throws Exception {
		// System.out.println(getJsonString(new Object[]{args,"12"}));
		// System.out.println(String.valueOf(1));
		// getString(1);
//		System.out.println(dateFomate(new Date()));
		Runnable r = () -> {
			for (int i = 0; i < 100; i++) {
				System.out.println(i);
			}
		};
		
		new Thread(r,"线程1").start();
		new Thread(r,"线程2").start();
		r = null;
		System.gc();	
		System.runFinalization();
	}
	@Override
	public void finalize(){
		System.out.println("开始垃圾回收");
	}
	
	public static String dateFomate(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		return sdf.format(date);
	}
	
	public static boolean isNumByRegex(String s){
		Pattern p = Pattern.compile("[0-9]+");
		return p.matcher(s).matches();
	}
	
	public static void test(){
		int row = 5;
		for(int i = 1 ; i<=row ; i++){
			for(int j =0;j<row-i;j++){
				System.out.print(" ");
			}
			for(int j =0;j<2*i-1;j++){
				System.out.print("#");
			}
			System.out.println();
		}
	}
	
	public static void testSpeed(){
		long count = (long) 1e11;
		long value = 65332L;
		
		long startTime2 = System.currentTimeMillis();
		for(long i=0l;i<count;i++){
			if((value&1)==0){}
		}
		long endTime2 = System.currentTimeMillis();
		System.out.println(endTime2-startTime2);
		
		long startTime3 = System.currentTimeMillis();
		for(long i=count;i>0;i--){
			if((value&1)==0){}
		}
		long endTime3 = System.currentTimeMillis();
		System.out.println(endTime3-startTime3);
		
		long startTime = System.currentTimeMillis();
		for(long i=0l;i<count;i++){
			if(value%2==0){}
		}
		long endTime = System.currentTimeMillis();
		System.out.println(endTime-startTime);
		
		long startTime1 = System.currentTimeMillis();
		for(long i=count;i>0;i--){
			if(value%2==0){}
		}
		long endTime1 = System.currentTimeMillis();
		System.out.println(endTime1-startTime1);
	}

	public static void getString(Integer... test) {
		System.out.println(Arrays.toString(test));
	}

	public static String getJsonString(Object data) throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper objm = new ObjectMapper();
		String supplierJson = objm.writeValueAsString(data);
		return supplierJson;
	}
}
