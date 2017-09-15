package learnJava;

public class Test {
	
	@MyTag(name="name1",age="23")
	public static void info(){
		System.out.println("根据注解MyTag调用Test里的方法info成功");
		
	}

}
