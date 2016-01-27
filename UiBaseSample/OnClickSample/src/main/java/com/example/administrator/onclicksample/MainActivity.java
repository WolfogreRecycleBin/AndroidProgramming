package com.example.administrator.onclicksample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView mTv;
    Button mBtn1,mBtn2,mBtn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTv= (TextView) findViewById(R.id.tv1);
        mBtn1= (Button) findViewById(R.id.bt1);
        mBtn2= (Button) findViewById(R.id.bt2);
        mBtn3= (Button) findViewById(R.id.bt3);
        mBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowToast("button1 be clicked");
                mTv.setText("button1 clicked");
            }
        });

        mBtn2.setOnClickListener(this);
    }


    void ShowToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    public void bt3OnClick(View v){
        ShowToast("button3 be clicked");
        mTv.setText("button3 clicked");
    }

    @Override
    public void onClick(View v) {
        ShowToast("button2 be clicked");
        mTv.setText("button2 clicked");
    }
}
