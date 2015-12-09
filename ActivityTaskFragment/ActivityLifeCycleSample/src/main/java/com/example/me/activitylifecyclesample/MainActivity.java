package com.example.me.activitylifecyclesample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button b1;
    private static final String TAG="main";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "onCreate------------------------------>");
        b1 = (Button) findViewById(R.id.Button01);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 显示方式声明Intent,直接启动SecondActivity
                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                startActivity(intent);
                //setContentView(R.layout.second);
            }
        });
    }

    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart------------------------------>");
    }


    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart------------------------------>");
    }


    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume------------------------------>");
    }


    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause------------------------------>");
    }


    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop------------------------------>");
    }


    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy------------------------------>");
    }


}
