package _18;


import java.io.File;
import java.lang.reflect.*;
import java.util.*;
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
class Test
{
	public void replace(String str, List<String> list){}
}
public class MethodParameterTest
{
	public static void main(String[] args)throws Exception
    {
        // 获取Tesy的类
        Class<Test> clazz = Test.class;
        // 获取Test类的带两个参数的replace()方法
        Method replace = clazz.getMethod("replace"
            , String.class, List.class);
        // 获取指定方法的参数个数
        System.out.println("replace方法参数个数：" + replace.getParameterCount());
        // 获取replace的所有参数信息
        Parameter[] parameters = replace.getParameters();
        System.out.println((new File("")).getAbsolutePath());
        int index = 1;
        // 遍历所有参数
        for (Parameter p : parameters){
        	System.out.println("---第" + index++ + "个参数信息---");
            System.out.println("参数名：" + p.getName());
            System.out.println("形参类型：" + p.getType());
            System.out.println("泛型类型：" + p.getParameterizedType());
            if (p.isNamePresent())
            {
                System.out.println("---第" + index++ + "个参数信息---");
                System.out.println("参数名：" + p.getName());
                System.out.println("形参类型：" + p.getType());
                System.out.println("泛型类型：" + p.getParameterizedType());
            }
        }
    }
}
