

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
 * <br/>��վ: <a href="http://www.crazyit.org">���Java����</a>
 * <br/>Copyright (C), 2001-2016, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author Yeeku.H.Lee kongyeeku@163.com
 * @version 1.0
 */
public class CompileClassLoader extends ClassLoader
{
	// ��ȡһ���ļ�������
	private byte[] getBytes(String filename)
		throws IOException
	{
		File file = new File(filename);
		long len = file.length();
		byte[] raw = new byte[(int)len];
		try(
			FileInputStream fin = new FileInputStream(file))
		{
			// һ�ζ�ȡclass�ļ���ȫ������������
			int r = fin.read(raw);
			if(r != len)
			throw new IOException("�޷���ȡȫ���ļ���"
				+ r + " != " + len);
			return raw;
		}
	}
	// �������ָ��Java�ļ��ķ���
	private boolean compile(String javaFile)
		throws IOException
	{
		System.out.println("CompileClassLoader:���ڱ��� "
			+ javaFile + "...");
		// ����ϵͳ��javac����
		Process p = Runtime.getRuntime().exec("javac " + javaFile);
		try
		{
			// �����̶߳��ȴ�����߳����
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
		// ��ȡjavac�̵߳��˳�ֵ
		int ret = p.exitValue();
		// ���ر����Ƿ�ɹ�
		return ret == 0;
	}
	// ��дClassLoader��findClass����
	@Override
	protected Class<?> findClass(String className)
		throws ClassNotFoundException
	{
		System.out.println(className);
		Class clazz = null;
		// ����·���еĵ㣨.���滻��б�ߣ�/��;
		className = className.replace("1" , "");
		String fileName = className.replace("." , "/");
		String javaFilename = "src/"+fileName + ".java";
		String classFilename = "bin/"+fileName + ".class";
		File javaFile = new File(javaFilename);
		System.out.println(javaFile.getAbsolutePath());
		File classFile = new File(classFilename);
		// ��ָ��JavaԴ�ļ����ڣ���class�ļ������ڡ�����JavaԴ�ļ�
		// ���޸�ʱ���class�ļ��޸�ʱ��������±���
		if(javaFile.exists() && (!classFile.exists()
			|| javaFile.lastModified() > classFile.lastModified()))
		{
			try
			{
				// �������ʧ�ܣ����߸�Class�ļ�������
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
		// ���class�ļ����ڣ�ϵͳ���𽫸��ļ�ת����Class����
		if (classFile.exists())
		{
			try
			{
				// ��class�ļ��Ķ��������ݶ�������
				byte[] raw = getBytes(classFilename);
				// ����ClassLoader��defineClass����������������ת����Class����
				clazz = defineClass(className,raw,0,raw.length);
			}
			catch(IOException ie)
			{
				ie.printStackTrace();
			}
		}
		// ���clazzΪnull����������ʧ�ܣ����׳��쳣
		if(clazz == null)
		{
			throw new ClassNotFoundException(className);
		}
		return clazz;
	}

	// ����һ��������
//	public static void main(String[] args) throws Exception
//	{
//		// ������иó���ʱû�в�������û��Ŀ����
//		args = new String[]{"Hello","java�����w"};
//		// ��һ����������Ҫ���е���
//		String progClass = args[0];
//		// ʣ�µĲ�������Ϊ����Ŀ����ʱ�Ĳ�����
//		// ����Щ�������Ƶ�һ����������
//		String[] progArgs = new String[args.length-1];
//		System.arraycopy(args , 1 , progArgs
//			, 0 , progArgs.length);
//		CompileClassLoader ccl = new CompileClassLoader();
//		// ������Ҫ���е���
//		Class<?> clazz = ccl.loadClass(progClass);
//		// ��ȡ��Ҫ���е����������
//		Method main = clazz.getMethod("main" , (new String[0]).getClass());
//		Object[] argsArray = {progArgs};
//		main.invoke(null,argsArray);
//	}
	public static void main(String[] args) throws Exception
	{
		args = new String[]{"Hello","java�����w"};
		String progClass = args[0];
		String[] progArgs = new String[args.length-1];
		System.arraycopy(args , 1 , progArgs, 0 , progArgs.length);
		URL[] urls = {new URL("file:")};
		Class<?> clazz = (new URLClassLoader(urls)).loadClass(progClass);
		// ��ȡ��Ҫ���е����������
		Method main = clazz.getMethod("main" , (new String[0]).getClass());
		Object[] argsArray = {progArgs};
		main.invoke(clazz,argsArray);
	}
}
