package sql;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;

public class InputDatas implements Runnable{
	
	private static LinkedBlockingQueue<Connection> conQueue = null;
	private static String[] date;
//	private static String[] date = {"2016-06-01","2016-06-02","2016-06-03"};
	private static int threadCounts = 20;
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
		if(args.length!=1){
			System.out.println("请输入：2016-06-01或2016-06-02或2016-06-03");
			return;
		}
		date = args;
		createConnections(threadCounts);
		InputDatas st = new InputDatas();
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
		for(int i =(500000/number);i>0;i--){
			try{
				String sql = "INSERT INTO `iedocnavi`.`answer` "
							+"(`user_id`, `event_type`, `event_time`, `schedule_type`, `answer_date`, `answer_type`,"
							+" `create_user_id`, `create_date`, `update_user_id`, `udapte_date`) VALUES "
							+"(?,?,?,?,?, ?, ?, ?, "
							+"?, ?);";
				java.sql.PreparedStatement stmt = temconn.prepareStatement(sql);
				String dateTime = getTime(nowdate);
				String userId = ""+getRandomNumber(100000);
				stmt.setString(1,userId);
				stmt.setString(2, ""+getRandomNumber(14));
				int et1 =getRandomNumber(24);
				int et2 =getRandomNumber(60);
				String set1 = et1+"";
				String set2 = et2+"";
				if(et1<10){
					set1 =0+""+et1;
				}
				if(et2<10){
					set2 =0+""+et2;
				}
				stmt.setString(3,set1+set2);
				stmt.setString(4, ""+getRandomNumber(2));
				stmt.setString(5, dateTime);
				stmt.setString(6, ""+getRandomNumber(5));
				stmt.setString(7, userId);
				stmt.setString(8, dateTime);
				stmt.setString(9, userId);
				stmt.setString(10, dateTime);
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
			String url = "jdbc:mysql://172.20.15.73:3306/iedocnavi?characterEncoding=UTF-8";// 数据库连接，oracle代表链接的是oracle数据库；thin:@MyDbComputerNameOrIP代表的是数据库所在的IP地址（可以保留thin:）；1521代表链接数据库的端口号；ORCL代表的是数据库名称
			String UserName = "root";
			String Password = "password";
			tmpconn = DriverManager.getConnection(url, UserName, Password);
//			tmpconn.setAutoCommit(false);
		}catch(Exception e){
			e.printStackTrace();
		}
		return tmpconn;
	}




}
