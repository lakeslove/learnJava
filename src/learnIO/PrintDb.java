package learnIO;

public class PrintDb {
	public static void main(String[] args){
		System.out.println(getBodySql(281,300));
		System.out.println(getVitalSql(281,300));
	}
	
	private static String getVitalSql(Integer startId,Integer endId){
		StringBuffer sql = new StringBuffer(10000);
		String singleSql = "insert into WELLNESS.VITAL_PEDOMETER "
				+ "(WELLNESS_DATA_ID,FAST_STEP, FAST_STEP_TIME, RUNNING_STEP, "
				+ "DAILY_EXERCISE_QUANTITY, STEP_TIME, RUNNING_STEP_TIME, "
				+ "NORMAL_STEP_TIME, NORMAL_STEP, "
				+ "CREATE_USER_ID, CREATE_DATE, "
				+ "UPDATE_USER_ID, UPDATE_DATE)"
				+ " values(%s, %s, %s, %s, %s, %s, %s, %s, %s, "
				+ "'MA10000001', '2016-10-10 00:00:00', 'MA10000001', '2016-10-10 00:00:00');";
		for(int i=startId;i<endId;i+=2){
			int zaobu = (134-i/10+i*12);
			int xingzou = (1111+i*25);
			double EXliang = (i+i/10.0);
			int buxingTime = (25+i/25+i*9);
			int xingzouTime = (316+i*14);
			int putongBuxingTime = (334-i/3+i*7);
			int putongBuxing = (334-i/3+i*7)*10/7;
			String temp = singleSql.format(
					singleSql, i+"",
					zaobu+"",(217-i/10+i*5)+"",
					xingzou+"",EXliang+"",
					buxingTime+"",xingzouTime+"",
					putongBuxingTime+"",putongBuxing+"");
			sql.append(temp+"\n");
		}
		return sql.toString();
	}
//	private static String getVitalSql(Integer startId,Integer endId){
//		StringBuffer sql = new StringBuffer(10000);
//		String singleSql = "insert into WELLNESS.VITAL_PEDOMETER "
//				+ "(WELLNESS_DATA_ID, STEP, FAST_STEP, FAST_STEP_TIME, RUNNING_STEP, "
//				+ "DAILY_EXERCISE_QUANTITY, STEP_TIME, RUNNING_STEP_TIME, "
//				+ "NORMAL_STEP_TIME, NORMAL_STEP, "
//				+ "CREATE_USER_ID, CREATE_DATE, "
//				+ "UPDATE_USER_ID, UPDATE_DATE)"
//				+ " values(%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, "
//				+ "'MA10000001', '2016-10-10 00:00:00', 'MA10000001', '2016-10-10 00:00:00');";
//		for(int i=startId;i<endId;i+=2){
//			int zaobu = (134-i/10+i*12);
//			int xingzou = (1111+i*25);
//			double EXliang = (i+i/10.0);
//			int buxingTime = (25+i/25+i*9);
//			int xingzouTime = (316+i*14);
//			int putongBuxingTime = (334-i/3+i*7);
//			int putongBuxing = (334-i/3+i*7)*10/7;
//			String temp = singleSql.format(
//					singleSql, i+"",(zaobu+xingzou)+"",
//					zaobu+"",(217-i/10+i*5)+"",
//					xingzou+"",EXliang+"",
//					buxingTime+"",xingzouTime+"",
//					putongBuxingTime+"",putongBuxing+"");
//			sql.append(temp+"\n");
//		}
//		return sql.toString();
//	}
	private static String getBodySql(Integer startId,Integer endId){
		StringBuffer sql = new StringBuffer(10000);
		String singleSql = "insert into WELLNESS.BODY_COMPOSITION_METER "
				+ "(WELLNESS_DATA_ID, BODY_FAT_PERCENTAGE, "
				+ "VISCERAL_FAT_LEVEL, BODYAGE, BMI, SMP_FULL_BODY,"
				+ " CREATE_USER_ID, CREATE_DATE, UPDATE_USER_ID, UPDATE_DATE) "
				+ "values("
				+ "%s, %s, 24.1, 11, 11.1, 21.1,"
				+ " 'MA10000001', '2016-10-10 00:00:00', 'MA10000001', '2016-10-10 00:00:00');";
		for(int i=startId;i<=endId;i+=2){
			String temp = singleSql.format(singleSql, i+"",(20.1+i/10)+"");
			sql.append(temp+"\n");
		}
		return sql.toString();
	}

	
//	private static String getBodySql(Integer startId,Integer endId){
//		StringBuffer sql = new StringBuffer(10000);
//		String singleSql = "insert into WELLNESS.BODY_COMPOSITION_METER "
//				+ "(WELLNESS_DATA_ID, WEIGHT, BODY_FAT_PERCENTAGE, "
//				+ "VISCERAL_FAT_LEVEL, BODYAGE, BMI, SMP_FULL_BODY,"
//				+ " CREATE_USER_ID, CREATE_DATE, UPDATE_USER_ID, UPDATE_DATE) "
//				+ "values("
//				+ "%s, %s, %s, 24.1, 11, 11.1, 21.1,"
//				+ " 'MA10000001', '2016-10-10 00:00:00', 'MA10000001', '2016-10-10 00:00:00');";
//		for(int i=startId;i<=endId;i+=2){
//			String temp = singleSql.format(singleSql, i+"",(100.22+i/10)+"",(20.1+i/10)+"");
//			sql.append(temp+"\n");
//		}
//		return sql.toString();
//	}

}
