package learnIO;

import java.util.Map;
import java.util.Set;

public class TestString {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		Map<String,String> test = new HashMap<>();
//		
//		test.put("a", "avule");
//		System.out.println(test.get(null));
//		String test = null;
//		System.out.println("abd"+test);
		System.out.println(System.getenv("TSS_KZ_LOGS"));
		Map<String, String> map = System.getenv();
		Set<String> set = map.keySet();
		for (String key : set) {
			System.out.println(key + " " + map.get(key));
		}
	}

}
