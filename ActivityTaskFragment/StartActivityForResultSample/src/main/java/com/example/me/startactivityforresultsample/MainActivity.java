package com.example.me.startactivityforresultsample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView mTv1,mTv2;
    private Button mBt1,mBt2;
    private static final int REQUEST_CODE1=99;
    private static final int REQUEST_CODE2=100;
    public static final int RESULT_OK=1;
    public static final String RESULT_KEY="result";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTv1= (TextView) findViewById(R.id.tv1);
        mTv2= (TextView) findViewById(R.id.tv2);
        mBt1= (Button) findViewById(R.id.btmain1);

        mBt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(MainActivity.this,Result1Activity.class);
                startActivityForResult(it,REQUEST_CODE1);
            }
        });

        mBt2= (Button) findViewById(R.id.btmain2);
        mBt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this, Result2Activity.class);
                startActivityForResult(it, REQUEST_CODE2);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case REQUEST_CODE1:
                if(resultCode==RESULT_OK){
                    mTv1.setText(data.getStringExtra(RESULT_KEY));
                }else{
                    mTv1.setText("It is not ok from RESULTACTIVITY1");
                }
                break ;
            case REQUEST_CODE2:
                if(resultCode==RESULT_OK){
                    mTv2.setText(data.getStringExtra(RESULT_KEY));
                }else{
                    mTv2.setText("It is not ok from RESULTACTIVITY2");
                }
                break;
        }

    }


}
