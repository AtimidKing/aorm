package com.king.test;

import com.king.other.Parser;
import com.king.sql.Sql;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;

/**
 * Created by king on 2017/6/11.
 */
public class SqlTest {
    @Before
    public void init() throws Exception{
        Sql sql=new Sql();
    }

    @Test
    public void getInsect() throws Exception {
        Sql sql=new Sql();
        Person person=new Person("y",16,"m");
        java.util.Set set= Parser.getFieldName(Person.class);
        System.out.println(sql.getInsect("Person",set));
    }

    @Test
    public void getUpdate() throws Exception {
        Sql sql=new Sql();
        Person person=new Person("y",16,"m");
        java.util.Set set= Parser.getFieldName(Person.class);
        java.util.Set set1=new HashSet();
        set1.add("name");
        System.out.println(sql.getUpdate("Person",set,set1));
    }

    @Test
    public void getDelete() throws Exception {
        Sql sql=new Sql();
        Person person=new Person("y",16,"m");
        java.util.Set set= Parser.getFieldName(Person.class);
        System.out.println(sql.getDelete("Person",set));
    }

    @Test
    public void getSelect() throws Exception {
        Sql sql=new Sql();
        Person person=new Person("y",16,"m");
        java.util.Set set= Parser.getFieldName(Person.class);
        System.out.println(sql.getSelect("Person",set));
    }

}