import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class Eval extends ClassLoader{
	
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

	@Override
    protected Class<?> findClass(String tmpName)
        throws ClassNotFoundException
    {
		Class clazz = null;
        System.out.println(tmpName);
        String tmpEvalExcuteString = "public class EvalExcute {public void evalString() {%s}}";
		String evalExcuteString = String.format(tmpEvalExcuteString, tmpName);
		
//		if(compile("")){
//			
//		}
		// 将class文件的二进制数据读入数组
		byte[] raw = null;
		// 调用ClassLoader的defineClass方法将二进制数据转换成Class对象
		clazz = defineClass("EvalExcute",raw,0,raw.length);
        
        return clazz;
    }
	
	public static void main(String[] args) throws Exception {
		eval("System.out.println(\"12\");");
		
	}
	
	public static void eval(String string) throws Exception{
		Eval eval = new Eval();
        // 加载需要运行的类
        Class<?> clazz = eval.findClass(string);
        // 获取需要运行的类的主方法
        Method evalString = clazz.getMethod("evalString");
        evalString.invoke(clazz,new String[]{});
	}

}
