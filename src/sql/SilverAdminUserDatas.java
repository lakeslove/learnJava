package sql;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;

public class SilverAdminUserDatas implements Runnable{
	
	private static LinkedBlockingQueue<Connection> conQueue = null;
	private static String[] date;
//	private static String[] date = {"2016-06-01","2016-06-02","2016-06-03"};
	private static int threadCounts = 1;
	private static String temDate =null;
	private static int threadNumber = 0;
	
	public void run() {
		try {
			insertFunction(temDate,threadCounts);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws InterruptedException{
		
//		if(args.length!=1){
//			System.out.println("请输入：2016-06-01或2016-06-02或2016-06-03");
//			return;
//		}
		date = new String[] {"2016-06-21"};
		createConnections(threadCounts);
		SilverAdminUserDatas st = new SilverAdminUserDatas();
//		long startTime = System.currentTimeMillis();
		System.out.println("---------------------导入数据开始时间"+(new SimpleDateFormat("HH:mm:ss")).format(new Date())+"--------------");
		for(String tem : date){
			temDate = tem;
			System.out.println("--------------------------------"+tem+"：开始--------------");
			for (int i = 0;i<threadCounts;i++){
				new Thread(st,"新线程"+i).start();
			}
//			insertFunction(temDate,threadCounts);
		 }
//		System.out.println("-------------------------------------导入是数据完毕--------------");
//		long endTime = System.currentTimeMillis();
//		long usedTime = (endTime-startTime)/1000;
//		System.out.println("-------------------------------------所用时间为"+usedTime+"seconds");
//		System.out.println("-------------------------------------所用时间为"+(usedTime/60)+"minites");
	}
	
//	@Override


	
	public static void insertFunction(String nowdate,int number) throws InterruptedException{
		int temNumber = ++threadNumber;
		System.out.println("新线程执行mysql--"+temNumber+"--");
		Connection temconn = conQueue.take();
		for(int i =4;i<200;i++){
			try{
				String sql ="INSERT INTO `SILVERAIDB`.`jp_admin_user` "
						+ "(`login_id`, `user_last_name`, `user_first_name`, "
						+ "`user_last_kana_name`, `user_first_kana_name`, `role_type`, "
						+ "`email`, `supplier_id`, `post_area_code`, `create_user_id`, "
						+ "`update_user_id`)"
						+ " VALUES"
						+"(?,?,?,?,?, ?, ?, ?, "
						+"?, ?,?);";
				String sql1 = "INSERT INTO `iedocnavi`.`answer` "
							+"(`user_id`, `event_type`, `event_time`, `schedule_type`, `answer_date`, `answer_type`,"
							+" `create_user_id`, `create_date`, `update_user_id`, `udapte_date`) VALUES "
							+"(?,?,?,?,?, ?, ?, ?, "
							+"?, ?);";
				java.sql.PreparedStatement stmt = temconn.prepareStatement(sql);
				stmt.setString(1,""+i);
				stmt.setString(2,"last"+i);
				stmt.setString(3,"first"+i);
				stmt.setString(4, "laka"+i);
				stmt.setString(5, "fika"+i);
				stmt.setString(6, ""+3);
				stmt.setString(7, "group@ippost.com.cn");
				stmt.setString(8, ""+getRandomNumber(10));
				stmt.setString(9, ""+getRandomNumber(10));
				stmt.setString(10, ""+i);
				stmt.setString(11, ""+i);
				stmt.execute();
				stmt.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		conQueue.put(temconn);
		System.out.println("已经结束mysql--"+temNumber+"--");
		System.out.println("---------------------导入数据结束时间"+(new SimpleDateFormat("HH:mm:ss")).format(new Date())+"--------------");
	}
	
	public static String getTime(String yy){
		SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
		return yy+" "+df.format(new Date());
	}
	
	public static int getRandomNumber(int range){
		int number = new Random().nextInt(range) + 1;
		return number;
	}
	
	public static void createConnections(int num){
		try {
			conQueue = getconQueue(num);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static LinkedBlockingQueue<Connection> getconQueue(int num) throws InterruptedException {
		LinkedBlockingQueue<Connection> temconQueue = new LinkedBlockingQueue<Connection>();
		for(int i=0; i<num; i++) {
			temconQueue.put(getCon());
		}
		return temconQueue;
	}
	
	public static Connection getCon(){
		Connection tmpconn = null;
		System.out.println("初始化时执行了public static Connection getCon()");
		try{
			Class.forName("com.mysql.jdbc.Driver");  
			String url = "jdbc:mysql://172.20.13.55:3306/SILVERAIDB?useUnicode=true&characterEncoding=utf8";// 数据库连接，oracle代表链接的是oracle数据库；thin:@MyDbComputerNameOrIP代表的是数据库所在的IP地址（可以保留thin:）；1521代表链接数据库的端口号；ORCL代表的是数据库名称
			String UserName = "silver";
			String Password = "passw0rd";
			tmpconn = DriverManager.getConnection(url, UserName, Password);
//			tmpconn.setAutoCommit(false);
		}catch(Exception e){
			e.printStackTrace();
		}
		return tmpconn;
	}




}
