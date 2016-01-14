package com.example.me.websample;

import java.io.IOException;
import java.util.List;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
	ListView mlistView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mlistView= (ListView) findViewById(R.id.lv);

		// 菜单项数组
				String[] items = { "下载图片", "下载html", "下载解析xml和json","get方式上传","post方式上传"};
		// 将菜单项数组设置为ListView的列表项展示
				mlistView.setAdapter(new ArrayAdapter<String>(this,
						android.R.layout.simple_list_item_1, items));
		       mlistView.setTextFilterEnabled(true);
		       mlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
				   @Override
				   public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					   Intent intent = new Intent();
					   Uri uri ;
					   String data;
					   ComponentName cn ;
					   switch (position) {
						   //
						   case 0:
							   intent.setClass(MainActivity.this,GetPictureActivity.class);
							   startActivity(intent);
							   break;

						   case 1:
							   intent.setClass(MainActivity.this, GetHtmlActivity.class);
							   startActivity(intent);
							   break;
						   //
						   case 2:
							   intent.setClass(MainActivity.this, GetNewsActivity.class);
							   startActivity(intent);
							   break;

						   case 3:
							   intent.setClass(MainActivity.this, GetMethodTestActivity.class);
							   startActivity(intent);
							   break;
						   case 4:
							   intent.setClass(MainActivity.this, PostMethodTestActivity.class);
							   startActivity(intent);
							   break;

						   default:
							   break;
					   }
				   }
			   });
			}
			



}
