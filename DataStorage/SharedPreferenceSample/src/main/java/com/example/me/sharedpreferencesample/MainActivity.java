package com.example.me.sharedpreferencesample;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText myEditText;
    private Button b1;
    private static final String TEMP_SMS = "temp_sms";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myEditText = (EditText) findViewById(R.id.EditText01);
        b1 = (Button) findViewById(R.id.Button01);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(MainActivity.this,MyPreferenceActivity.class);
                startActivity(it);
            }
        });

        SharedPreferences pre = getSharedPreferences(TEMP_SMS, MODE_WORLD_READABLE);
        String content = pre.getString("sms_content", "");
        myEditText.setText(content);
    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences.Editor editor = getSharedPreferences(TEMP_SMS, MODE_WORLD_WRITEABLE).edit();
        editor.putString("sms_content", myEditText.getText().toString());
        editor.commit();
    }
}
