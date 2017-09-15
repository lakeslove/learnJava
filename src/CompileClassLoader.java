

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
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
public class CompileClassLoader extends ClassLoader
{
	// 读取一个文件的内容
	private byte[] getBytes(String filename)
		throws IOException
	{
		File file = new File(filename);
		long len = file.length();
		byte[] raw = new byte[(int)len];
		try(
			FileInputStream fin = new FileInputStream(file))
		{
			// 一次读取class文件的全部二进制数据
			int r = fin.read(raw);
			if(r != len)
			throw new IOException("无法读取全部文件："
				+ r + " != " + len);
			return raw;
		}
	}
	// 定义编译指定Java文件的方法
	private boolean compile(String javaFile)
		throws IOException
	{
		System.out.println("CompileClassLoader:正在编译 "
			+ javaFile + "...");
		// 调用系统的javac命令
		Process p = Runtime.getRuntime().exec("javac " + javaFile);
		try
		{
			// 其他线程都等待这个线程完成
			p.waitFor();
			BufferedReader bf = new BufferedReader(new InputStreamReader(p.getErrorStream()));
			String line = "";
			while((line = bf.readLine())!=null){
				System.out.println(new String(line.getBytes("GBK")));
			}
		}
		catch(InterruptedException ie)
		{
			System.out.println(ie);
		}
		// 获取javac线程的退出值
		int ret = p.exitValue();
		// 返回编译是否成功
		return ret == 0;
	}
	// 重写ClassLoader的findClass方法
	@Override
	protected Class<?> findClass(String className)
		throws ClassNotFoundException
	{
		System.out.println(className);
		Class clazz = null;
		// 将包路径中的点（.）替换成斜线（/）;
		className = className.replace("1" , "");
		String fileName = className.replace("." , "/");
		String javaFilename = "src/"+fileName + ".java";
		String classFilename = "bin/"+fileName + ".class";
		File javaFile = new File(javaFilename);
		System.out.println(javaFile.getAbsolutePath());
		File classFile = new File(classFilename);
		// 当指定Java源文件存在，且class文件不存在、或者Java源文件
		// 的修改时间比class文件修改时间更晚，重新编译
		if(javaFile.exists() && (!classFile.exists()
			|| javaFile.lastModified() > classFile.lastModified()))
		{
			try
			{
				// 如果编译失败，或者该Class文件不存在
				if(!compile(javaFilename) || !classFile.exists())
				{
					throw new ClassNotFoundException(
						"ClassNotFoundExcetpion:" + javaFilename);
				}
			}
			catch (IOException ex)
			{
				ex.printStackTrace();
			}
		}
		// 如果class文件存在，系统负责将该文件转换成Class对象
		if (classFile.exists())
		{
			try
			{
				// 将class文件的二进制数据读入数组
				byte[] raw = getBytes(classFilename);
				// 调用ClassLoader的defineClass方法将二进制数据转换成Class对象
				clazz = defineClass(className,raw,0,raw.length);
			}
			catch(IOException ie)
			{
				ie.printStackTrace();
			}
		}
		// 如果clazz为null，表明加载失败，则抛出异常
		if(clazz == null)
		{
			throw new ClassNotFoundException(className);
		}
		return clazz;
	}

	// 定义一个主方法
//	public static void main(String[] args) throws Exception
//	{
//		// 如果运行该程序时没有参数，即没有目标类
//		args = new String[]{"Hello","java疯狂讲义w"};
//		// 第一个参数是需要运行的类
//		String progClass = args[0];
//		// 剩下的参数将作为运行目标类时的参数，
//		// 将这些参数复制到一个新数组中
//		String[] progArgs = new String[args.length-1];
//		System.arraycopy(args , 1 , progArgs
//			, 0 , progArgs.length);
//		CompileClassLoader ccl = new CompileClassLoader();
//		// 加载需要运行的类
//		Class<?> clazz = ccl.loadClass(progClass);
//		// 获取需要运行的类的主方法
//		Method main = clazz.getMethod("main" , (new String[0]).getClass());
//		Object[] argsArray = {progArgs};
//		main.invoke(null,argsArray);
//	}
	public static void main(String[] args) throws Exception
	{
		args = new String[]{"Hello","java疯狂讲义w"};
		String progClass = args[0];
		String[] progArgs = new String[args.length-1];
		System.arraycopy(args , 1 , progArgs, 0 , progArgs.length);
		URL[] urls = {new URL("file:")};
		Class<?> clazz = (new URLClassLoader(urls)).loadClass(progClass);
		// 获取需要运行的类的主方法
		Method main = clazz.getMethod("main" , (new String[0]).getClass());
		Object[] argsArray = {progArgs};
		main.invoke(clazz,argsArray);
	}
}
