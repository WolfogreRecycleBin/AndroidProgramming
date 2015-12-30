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
		/*
		Context 类的方法，该方法需要提供文件名来指定创建或者打开的xml 文件。
		mode 参数用来指定xml 文件的访问性。共有4 种取值
			MODE_PRIVATE 0 默认值，只允许app 内部访问，创建和装载标识
			MODE_WORLD_READABLE 1 ，允许其他app 读，创建和装载标识
			MODE_WORLD_WRITEABLE 2，允许其他app 写 ，创建和装载标识
			MODE_MULTI_PROCESS 4，允许多个进程读写该对象，装载标识，注意2.3
			以前不需要设置这种特性，系统默认允许多进程读写特性，2.3 以后需要明
			确设置该标识才能支持多进程读写。
		 */

        SharedPreferences pre = getSharedPreferences(TEMP_SMS, MODE_WORLD_READABLE);
        String content = pre.getString("sms_content", "");
        myEditText.setText(content);
	    /*
	    这段代码要呈现的功能是，在文本框内输入一些位置，退出APP，再打开APP文字还在
	     */
    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences.Editor editor = getSharedPreferences(TEMP_SMS, MODE_WORLD_WRITEABLE).edit();
	    //生成的文件名为temp_sms.xml
        editor.putString("sms_content", myEditText.getText().toString());
        editor.commit();
    }
}
