package learnJava;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import learnJava.Test;

public class AnnotationLearn {
	
	public static void main(String[] args) throws NoSuchMethodException, SecurityException, ClassNotFoundException{
//		Test test = new Test();
		Annotation[] aArray = Class.forName("learnJava.Test").getMethod("info").getAnnotations();
//		Annotation[] aArray = Class.forName("learnJava.Test").getMethod("info").getDeclaredAnnotationsByType();
//		System.out.println(aArray.length);
		for(Method m : Class.forName("learnJava.Test").getMethods()){
			System.out.println(m);
			if (m.isAnnotationPresent(MyTag.class)){
				try{
					m.invoke(null);
					
				}catch(Exception ex){
//					System.out.println("澶辫触锛�"+ex.getCause());
				}
			}
			
		}
		for(Annotation tag : aArray){
			System.out.println(tag);
			if (tag instanceof MyTag){
				System.out.println("This is :"+ tag);
				System.out.println(((MyTag)tag).name());
				System.out.println(((MyTag)tag).age());
			}
		}
	}

}
