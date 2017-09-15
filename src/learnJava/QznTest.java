package learnJava;

public class QznTest {
	public static void main(String[] args){
		if(args.length==0){
			args = new String[]{"xxx.csv"};
		}
		System.out.println(args[0]);
		System.out.println(checkCsvType(args[0]));
	}
	public static boolean checkCsvType(String fileName) {
		int index = fileName.lastIndexOf(".");
		String extensionName = null;
		extensionName = fileName.substring(index + 1);
		if (extensionName.equalsIgnoreCase("csv")) {
			return true;
		}
		return false;
	}
}
