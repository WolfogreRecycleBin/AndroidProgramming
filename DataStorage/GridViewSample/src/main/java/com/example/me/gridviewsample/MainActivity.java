package com.example.me.gridviewsample;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends AppCompatActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ListView listview=(ListView) findViewById(R.id.listview01);
        //listview.setAdapter(new ImageAdapter(this));
        //取得GridView对象
        GridView gridview = (GridView) findViewById(R.id.gridview);
        //添加元素给gridview
        gridview.setAdapter(new ImageAdapter(this));

        // 设置Gallery的背景
        gridview.setBackgroundResource(R.drawable.bg0);

        //事件监听
        gridview.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {
                Toast.makeText(MainActivity.this, "你选择了" + (position + 1) + " 号图片", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

