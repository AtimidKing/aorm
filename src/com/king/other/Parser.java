package com.king.other;


import java.lang.reflect.InvocationTargetException;
import java.util.*;


/**
 * Created by king on 2017/6/7.
 * 用于解析模型类的对象。
 */
public class Parser {

    /**
     * 解析模型类，获取模型类中的成员变量名和值
     *
     * @param pojo 用于解析的模型类。
     * @return 返回Map，key为成员变量名，value为成员变量对应的值。
     * 注意：值为空的成员变量，map中的value也为空。
     */
    public static <P> Map getFields(P pojo) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Map map = new HashMap();
        Class cls = pojo.getClass();
        //获取模型类中的成员变量。
        java.lang.reflect.Field[] fields = cls.getDeclaredFields();
        //解析字段
        for (int i = 0; i < fields.length; i++) {
            //获取成员变量名称
            String name = fields[i].getName();
            //获取注解
            com.king.annotation.Key key = fields[i].getAnnotation(com.king.annotation.Key.class);
            if (map.containsKey("key") && key != null) {
                map.put("key", name);
            }
            //将成员变量名称作为key，变量值作为value存入map中。
            map.put(name, getValue(name, pojo));


        }
        return map;
    }

    /**
     * 获取目标类的成员变量集合。
     *
     * @param cls 目标表类的Class对象。
     * @return 目标类的成员变量集合。
     *
     */

    public static Set getFieldName(Class cls) {
        Set<String> set = new HashSet();

        java.lang.reflect.Field[] fields = cls.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            //获取成员变量名称
            String name = fields[i].getName();
            //将成员变量名称添加的到set中。
            set.add(name);
        }
        return set;
    }

    /**
     * 注入值到目标对象的成语变量中。
     * @param cls 要注入的对象
     *@param map 包含成员对象的名称和值的map对象。
     * */
    public static<P> P setFields(Class cls,Map map) throws IllegalAccessException, InstantiationException {
        P pojo= ((P) cls.newInstance());
        for (int i = 0; i < map.size(); i++) {

        }

        return pojo;
    }

    /**
     * 获取的对象的成员变量的值。
     *
     * @param name 目标成员变量。
     * @param pojo 目标对象。
     * @return 返回成员变量的值。
     * getMethodName(String name):合成目标成员变量的get方法名称。
     */
    private static <P> Object getValue(String name, P pojo) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Class cls = pojo.getClass();
        java.lang.reflect.Method method = cls.getMethod(getMethodName(name));
        Object object = method.invoke(pojo);
        return object;
    }

    /**
     * 合成一个成员变量的get方法。
     *
     * @param name 成员变量的名称。
     * @return 成员变量的get方法名。
     */
    private static String getMethodName(String name) {
        String methodName = "get" + name.substring(0, 1).toUpperCase() + name.substring(1);
        return methodName;
    }


}
