import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Test {
	private static enum LogType{
		BATCH_LOG(1, "������־"),
		PATH_LOG(2, "��·��־"),
		RECEIPT_LOG(3, "�ص���־"),
		CAR_LOG(4, "������־"),
		DRIVER_LOG(5, "˾����־"),
		SETTLEMENT_LOG(6, "������־"),
		SYSTEM_SETTING(7, "ϵͳ����");
		
		LogType(Integer logTypeNumber,String logType){
			this.logTypeNumber = logTypeNumber;
			this.logType = logType;
		}
		
		static LogType getLogTypeByLogTypeNumber(Integer logTypeNumber){
			if(logTypeMap==null){
				logTypeMap = new HashMap<>();
				for(LogType type : LogType.values()){
					logTypeMap.put(type.logTypeNumber, type);
				}
			}
			return logTypeMap.get(logTypeNumber);
		}
		
		private static Map<Integer,LogType> logTypeMap;
		private Integer logTypeNumber;
		private String logType;
	}
	
	public static void main(String[] args) {
		List<String> list1 = new ArrayList<>();
		list1.add("1");
		list1.add("2");
		list1.add("3");
		list1.add("4");
		
		List<String> list2 = new ArrayList<>();
		list2.add("1");
		list2.add("2");
		list2.add("5");
		
		list1.removeAll(list2);
		for(String str : list1){
			System.out.println(str);
		}
	}
	
	
}
