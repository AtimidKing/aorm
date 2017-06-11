package com.king.test;

import com.king.other.Parser;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * Created by king on 2017/6/8.
 */
public class ParserTest {

    @Test
    public void getFields() throws Exception {
        Person person = new Person("y", 8, "m");
        java.util.Map map = new HashMap();
        map.put("name", "y");
        map.put("age", 8);
        map.put("sex", "m");
        assertEquals(Parser.getFields(person), map);
    }

    @Test
    public void getFieldName() throws Exception {
        Set set = new HashSet();
        set.add("name");
        set.add("age");
        set.add("sex");
        assertEquals(Parser.getFieldName(Person.class), set);
    }

    @Test
    public void setFields() throws Exception {
//        Person person=new Person("y",8,"m");
        Person person = new Person();
        person.setName("y");
        person.setAge(8);
        person.setSex("m");
        java.util.Map map = new HashMap();
        map.put("name", "y");
        map.put("age", 8);
        map.put("sex", "m");
//        assertEquals(Parser.setFields(Person.class, map), person);
        Person person1=Parser.setFields(Person.class,map);
        assertEquals(person1.getName(),"y");
        assertEquals(person1.getAge(),8);
        assertEquals(person1.getSex(),"m");
        person.equals(person1);
        System.out.println(person.toString());
    }
    @Test
    public void stringEqual(){
        String a="ya";
        String b="ya";
        Object object="ya";

        a.equals(b);
        assertEquals(a.equals(b),true);
        assertEquals(a==b,true);
        assertEquals(a.equals(object),true);
        assertEquals(a,b);

    }

    @Test
    public void getAnnotation(){
       java.util.Map map= Parser.getAnnotation(Person.class);
       java.util.Map map1=new HashMap();
       map1.put("Key","name");
       assertEquals(map,map1);
    }
}

