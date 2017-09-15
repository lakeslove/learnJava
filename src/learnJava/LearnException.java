package learnJava;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class LearnException {
	public static void main(String[] args)
	{
		
		
//		try{
//			System.out.println(testError.getString());
//		}catch(Throwable e){
//			System.out.println("java.lang.Throwable");
//			throw e;
//		}
		
		try{
			testError2.getString();
//			System.out.println(testError.getString());
		}catch(Exception e){
			System.out.println("java.lang.Exception");
			throw e;
		}
		
		
		
//		firstMethod();
	}
//	public static void firstMethod()
//	{
//		secondMethod();
//	}
//	public static void secondMethod()
//	{
//		thirdMethod();
//	}
//	public static void thirdMethod()
//	{
//		throw new SelfException("自定义异常信息");
//	}
}
class SelfException extends RuntimeException
{
	SelfException(){}
	SelfException(String msg)
	{
		super(msg);
	}
}

class testError{
	static {
		String filePath = LearnException.class.getClassLoader().getResource("test_message_generic.properties").getPath();
		Properties props = new Properties();
		try {
			props.load(new FileInputStream(filePath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
//			log.error(e);
		} catch (IOException e) {
			e.printStackTrace();
//			log.error(e);
		}
	}
	public static String getString(){
		return "调用了testError.class";
	}
}

class testError2{
	public static String getString(){
		String[] test = new String[]{"1"};
		return "调用了testError.class"+test[2];
	}
}
