package com.example.me.activitdataexsample;

import android.app.Application;

/**
 * Created by Administrator on 2015/12/7.
 */
public class MyApplication extends Application {
     private String name;
    private int age;
    private  boolean pass;

    public String getName(){
        return  name;
    }
    public void setName(String name){
        this.name=name;
    }
}