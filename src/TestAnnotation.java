import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

@Retention(RetentionPolicy.RUNTIME)
@interface TssLogAnnotation {
	String showName();
}

class Test2 {
	@TssLogAnnotation(showName = "java")
	String name;
}

public class TestAnnotation {
	public static void main(String[] args) throws NoSuchFieldException, SecurityException{

		Class<?> clazz = new Test2().getClass();
		Field[] fields = clazz.getDeclaredFields();
		for(Field field : fields){
			System.out.println(field.getName());
			TssLogAnnotation tss = field.getAnnotation(TssLogAnnotation.class);
			System.out.println(tss.showName());
		}
	}

}


 