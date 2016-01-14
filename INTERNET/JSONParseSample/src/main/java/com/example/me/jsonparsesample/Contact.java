package com.example.me.jsonparsesample;

import android.util.Log;

/**
 * Created by Administrator on 2016/1/10.
 */
public class Contact {
    private int age;
    private String name;
    private String[] phones;
    static  final String FIELD_NAME="name";
    static  final String FIELD_AGE="age";
    static  final String FIELD_PHONES="phones";

    public Contact()
    {
        age=20;
        name="somebody";
        phones=new String[]{"1234567890"};
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String[] getPhones()
    {
        return phones;
    }

    public void setPhones(String[] telephones)
    {
        this.phones = telephones;
    }

    public Contact(int age, String name, String[] telephones)
    {
        super();
        this.age = age;
        this.name = name;
        this.phones = telephones;
    }

    @Override
    public String toString()
    {
       String str="Contact [age=" + age + ", name=" + name + ", telephone1=" + phones[0]
               + "]";
        Log.d("Cotact.tostring", str);
        return str;
    }
}
