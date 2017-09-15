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
public class MyProxyFactory
{
	// Ϊָ��target���ɶ�̬�������
	@SuppressWarnings("unchecked")
	public static <T> T getProxy(Class<T> clazz)throws Exception{
		// ����һ��MyInvokationHandler����
		// ΪMyInvokationHandler����target����
//		T target = clazz.newInstance();
		MyInvokationHandler handler =new MyInvokationHandler();
		// ������������һ����̬����
		return (T)Proxy.newProxyInstance(clazz.getClassLoader()
			, new Class[]{clazz}, handler);
	}
}

