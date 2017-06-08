package com.king.other;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


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
    public static <P> Map getFields(P pojo) throws Exception {
        Map map = new HashMap();
        Class cls = pojo.getClass();
        map.put("table",cls.getSimpleName());
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
     *
     * @param cls 要注入的对象。
     * @param map 包含成员对象的名称和值的map对象。
     */
    public static <P> P setFields(Class cls, Map map) throws Exception {
        //实例化目标对象。
        P pojo = ((P) cls.newInstance());
        //获取key数组。
        Object[] objects = map.keySet().toArray();
        //遍历map对象。
        for (int i = 0; i < objects.length; i++) {
            //获取目标对象的指定成员变量。
            java.lang.reflect.Field field = cls.getDeclaredField(objects[i].toString());
            //获取成员变量指定的成员变量的set方法。
            java.lang.reflect.Method method = cls.getDeclaredMethod(getMethodName(objects[i].toString(),1), field.getType());
            //执行目标方法，将对应的注入到目标对象的成员变量中。
            method.invoke(pojo, map.get(objects[i]));
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
    private static <P> Object getValue(String name, P pojo) throws Exception {
        Class cls = pojo.getClass();
        java.lang.reflect.Method method = cls.getMethod(getMethodName(name, 0));
        Object object = method.invoke(pojo);
        return object;
    }

    /**
     * 合成一个成员变量的get方法。
     *
     * @param name     成员变量的名称。
     * @param getORset 指定取得get或者set方法。0:get方法;1: set方法。
     * @return 成员变量的get方法名。
     */
    private static String getMethodName(String name, int getORset) throws Exception {
        String methodName = "";
        switch (getORset) {
            case 0:
                methodName = "get" + name.substring(0, 1).toUpperCase() + name.substring(1);
                return methodName;
            case 1:
                methodName = "set" + name.substring(0, 1).toUpperCase() + name.substring(1);
                return methodName;
            default:
                throw new java.lang.Exception("!!!!!!!!!!!");
        }
    }


}
