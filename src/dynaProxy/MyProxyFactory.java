package dynaProxy;

import java.lang.reflect.*;
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
public class MyProxyFactory
{
	// 为指定target生成动态代理对象
	@SuppressWarnings("unchecked")
	public static <T> T getProxy(Class<T> clazz)throws Exception{
		// 创建一个MyInvokationHandler对象
		// 为MyInvokationHandler设置target对象
//		T target = clazz.newInstance();
		MyInvokationHandler handler =new MyInvokationHandler();
		// 创建、并返回一个动态代理
		return (T)Proxy.newProxyInstance(clazz.getClassLoader()
			, new Class[]{clazz}, handler);
	}
}

