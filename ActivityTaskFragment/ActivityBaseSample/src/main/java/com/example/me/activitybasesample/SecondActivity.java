package com.example.me.activitybasesample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Administrator on 2015/12/7.
 */
public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
         TextView tvs= (TextView) findViewById(R.id.tvsecond);
        tvs.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(SecondActivity.this,MainActivity.class);
                startActivity(intent);
               // finish();
            }
        });
        TextView tvf= (TextView) findViewById(R.id.tvfinish);
        tvf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
