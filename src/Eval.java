import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class Eval extends ClassLoader{
	
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
		// ��class�ļ��Ķ��������ݶ�������
		byte[] raw = null;
		// ����ClassLoader��defineClass����������������ת����Class����
		clazz = defineClass("EvalExcute",raw,0,raw.length);
        
        return clazz;
    }
	
	public static void main(String[] args) throws Exception {
		eval("System.out.println(\"12\");");
		
	}
	
	public static void eval(String string) throws Exception{
		Eval eval = new Eval();
        // ������Ҫ���е���
        Class<?> clazz = eval.findClass(string);
        // ��ȡ��Ҫ���е����������
        Method evalString = clazz.getMethod("evalString");
        evalString.invoke(clazz,new String[]{});
	}

}
