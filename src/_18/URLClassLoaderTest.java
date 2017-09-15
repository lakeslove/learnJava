package _18;

import java.sql.*;
import java.util.*;
import java.net.*;
/**
 * Description:
 * <br/>网站: <a href="http://www.crazyit.org">疯狂Java联盟</a>
 * <br/>Copyright (C), 2001-2016, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author Yeeku.H.Lee kongyeeku@163.com
 * @version 1.0
 */
public class URLClassLoaderTest
{
	private static Connection conn;
	// 定义一个获取数据库连接方法
	public static Connection getConn(String url ,
		String user , String pass) throws Exception
	{
		if (conn == null)
		{
			// 创建一个URL数组
			URL[] urls = {new URL(
				"file:mysql-connector-java-5.1.30-bin.jar")};
			// 以默认的ClassLoader作为父ClassLoader，创建URLClassLoader
			URLClassLoader myClassLoader = new URLClassLoader(urls);
			// 加载MySQL的JDBC驱动，并创建默认实例
			Driver driver = (Driver)myClassLoader.
				loadClass("com.mysql.jdbc.Driver").newInstance();
			// 创建一个设置JDBC连接属性的Properties对象
			Properties props = new Properties();
			// 至少需要为该对象传入user和password两个属性
			props.setProperty("user" , user);
			props.setProperty("password" , pass);
			// 调用Driver对象的connect方法来取得数据库连接
			conn = driver.connect(url , props);
		}
		return conn;
	}
	public static void main(String[] args)throws Exception
	{
		Connection temconn = getConn("jdbc:mysql://localhost:3306/mybatis"
				, "root" , "password");
			try{
				String sql = "INSERT INTO `mybatis`.`users` (`NAME`, `age`) VALUES ('java8', '2')";
				java.sql.PreparedStatement stmt = temconn.prepareStatement(sql);
				stmt.execute();
				stmt.close();
				String sql2 = "select * from `mybatis`.`users`";
				java.sql.PreparedStatement stmt2 = temconn.prepareStatement(sql2);
				ResultSet rs = stmt2.executeQuery();
				ResultSetMetaData m=rs.getMetaData();
				//显示列,表格的表头
				int columns=m.getColumnCount();
				for(int i=1;i<=columns;i++)
				   {
				    System.out.print(m.getColumnName(i));
				    System.out.print("\t\t");
				   }
				System.out.println();
				   //显示表格内容
				   while(rs.next())
				   {
				    for(int i=1;i<=columns;i++)
				    {
				     System.out.print(rs.getString(i));
				     System.out.print("\t\t");
				    }
				    System.out.println();
				   }
				stmt2.close();
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				temconn.close();
			}
	}
}

