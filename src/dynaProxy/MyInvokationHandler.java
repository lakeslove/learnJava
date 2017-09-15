package dynaProxy;

import java.lang.reflect.*;
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
public class MyInvokationHandler implements InvocationHandler
{
	private static DogUtil du = new DogUtil();
	// ��Ҫ������Ķ���
//	private Object target;
//	public MyInvokationHandler(Object target)
//	{
//		this.target = target;
//	}
	// ִ�ж�̬�����������з���ʱ�����ᱻ�滻��ִ�����µ�invoke����
	public Object invoke(Object proxy, Method method, Object[] args)
		throws Exception
	{
		// ִ��DogUtil�����е�method1��
		du.method1();
		System.out.println("����һ��"+method.getName()+"����");
		// ��target��Ϊ������ִ��method����
//		Object result = method.invoke(target , args);
		// ִ��DogUtil�����е�method2��
		du.method2();
		return null;
	}
}

