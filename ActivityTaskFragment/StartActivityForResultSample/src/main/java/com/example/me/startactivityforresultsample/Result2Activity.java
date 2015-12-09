package com.example.me.startactivityforresultsample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class Result2Activity extends AppCompatActivity {

    private CheckBox mCk;
    private static  final String RESULTSTRING_OK="It's OK From Result two";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result2);
        mCk= (CheckBox) findViewById(R.id.result2_ck);
        Button bt= (Button) findViewById(R.id.result2_bt);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCk.isChecked()) {
                    Bundle data = new Bundle();
                    data.putString(MainActivity.RESULT_KEY, RESULTSTRING_OK);
                    Intent intent = Result2Activity.this.getIntent();
                    intent.putExtras(data);
                    setResult(MainActivity.RESULT_OK, intent);
                }
                finish();
            }
        });
    }
}
