import java.nio.file.Path;
import java.nio.file.Paths;

public class Hello {
	int testInt = 1;
	Integer testInteger = 1;
	String testString = "test1_w";
	public static void main(String[] args) {
		String localVarible = "localV";
		System.out.println(localVarible);
		System.out.println(Paths.get(".").toAbsolutePath());
	}
}
