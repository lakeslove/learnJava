
public class Test {
	public static void main(String[] args) {
		String url = "/transtask/save";
		String path = "/transtask/save";
		System.out.println(url.substring(0,path.length()).equalsIgnoreCase(path));
	}
}
