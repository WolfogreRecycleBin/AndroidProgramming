package com.example.me.actionbarsample;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SecondActivity extends AppCompatActivity {
    ActionBar mAb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        mAb=getSupportActionBar();//获取系统ActionBar对象
        mAb.setTitle("ActionBar例子");
        mAb.setSubtitle("SecondActivity");


        mAb.setDisplayShowHomeEnabled(true);
        mAb.setDisplayUseLogoEnabled(true);
        mAb.setLogo(R.drawable.ic_account_balance_black_18dp);

        mAb.setDisplayHomeAsUpEnabled(true);
    }
}
