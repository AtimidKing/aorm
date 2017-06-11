package com.king.test;

import com.king.annotation.Key;

/**
 * Created by king on 2017/6/8.
 */
public class Person {
    Person(String name,int age,String sex){
        this.name=name;
        this.age=age;
        this.sex=sex;
    }
    public Person(){}

    @Key
    private String name;
    private int age;
    private String sex;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
