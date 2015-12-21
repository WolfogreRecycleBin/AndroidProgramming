package com.shu.wolfogre.kh4_1_3;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//使TextView有滚动条，参考http://www.linuxidc.com/Linux/2012-02/55499.htm
		TextView tv =(TextView)findViewById(R.id.textView);
		tv.setMovementMethod(ScrollingMovementMethod.getInstance());

		Button bt = (Button)findViewById(R.id.button);
		bt.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				new DownloadHtmlTask().execute();
			}
		});
	}
	private class DownloadHtmlTask extends AsyncTask<String, Void, String> {

		protected String doInBackground(String... urls) {
			try{
				URL htmlURL = new URL("http://www.baidu.com");
				HttpURLConnection conn = (HttpURLConnection) htmlURL.openConnection();
				conn.setConnectTimeout(5 * 1000);
				conn.setRequestMethod("GET");
				InputStream inStream =  conn.getInputStream();
				ByteArrayOutputStream outStream = new ByteArrayOutputStream();
				byte[]  buffer = new byte[1204];
				int len;
				while ((len = inStream.read(buffer)) != -1){
					outStream.write(buffer,0,len);
				}
				inStream.close();
				byte[] bytes = outStream.toByteArray();
				return new String(bytes);
				//参考:http://www.blogjava.net/yxhxj2006/archive/2012/07/19/383495.html
			}catch(IOException e){
				return e.getMessage();
			}
		}

		protected void onPostExecute(String result) {
			((TextView)findViewById(R.id.textView)).setText(result);
		}
	}
}
