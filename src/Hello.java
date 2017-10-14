import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.util.StringUtils;

public class Hello {
	int testInt = 1;
	Integer testInteger = 1;
	String testString;
	public static void main(String[] args) {
		Map<String,Object> test = new HashMap<>();
		test.put("1", 0);
		test.put("2", null);
		test.put("3", "");
		for(Entry<String,Object> entry : test.entrySet()){
			System.out.println(entry.getKey());
			Boolean expenseIdIsNull = StringUtils.isEmpty(entry.getValue());
			System.out.println(expenseIdIsNull);
		}
	}
}
