package com.example.me.receiversample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // 定义一个Action常量
    private static final String MY_ACTION = "com.example.me.receiversample.action.MY_ACTION";
    // 定义一个Button对象
    private Button btn;
    TextView tv;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置当前布局视图
        setContentView(R.layout.activity_main);
        btn = (Button)findViewById(R.id.bt);
        tv= (TextView) findViewById(R.id.tv);
        // 为按钮设置单击监听器
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 实例化Intent对象
                Intent intent = new Intent();
                // 设置Intent action属性
                intent.setAction(MY_ACTION);
                // 为Intent添加附加信息
                intent.putExtra("msg",tv.getText());
                // 发出广播
                sendBroadcast(intent);
            }
        });
    }
}
