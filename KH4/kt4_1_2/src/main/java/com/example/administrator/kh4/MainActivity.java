package com.example.administrator.kh4;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

	TextView tv;
	int whoGotIt = 0;
	int speed = 10;
	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			if(msg.what == 1) {
				tv.setLeft(tv.getLeft() + speed);
			}
			super.handleMessage(msg);
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		tv = (TextView)this.findViewById(R.id.text);
		Button bt1 =(Button)this.findViewById(R.id.button1);
		Button bt2 =(Button)this.findViewById(R.id.button2);
		Button bt3 =(Button)this.findViewById(R.id.button3);

		bt1.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v) {
				new Thread(new Runnable() {
					public void run() {
						if(whoGotIt != 0){
							whoGotIt = 0;
							return;
						}
						whoGotIt = 1;
						while(whoGotIt == 1){
							if(tv.getLeft() > 1000 || tv.getLeft() < 0)
								speed = - speed;
							tv.post(new Runnable() {
								public void run() {
									tv.setLeft(tv.getLeft() + speed);
								}
							});
							try {
								Thread.sleep(100);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				}).start();
			}
		});

		bt2.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v) {
				new Thread(new Runnable() {
					public void run() {
						if(whoGotIt != 0){
							whoGotIt = 0;
							return;
						}
						whoGotIt = 2;
						while(whoGotIt == 2){
							if(tv.getLeft() > 1000 || tv.getLeft() < 0)
								speed = - speed;
							MainActivity.this.runOnUiThread(new Runnable()
							{
								public void run() {
									tv.setLeft(tv.getLeft() + speed);
								}
							});
							try {
								Thread.sleep(100);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				}).start();

			}
		});

		bt3.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				new Thread(new Runnable() {
					public void run() {
						if(whoGotIt != 0){
							whoGotIt = 0;
							return;
						}
						whoGotIt = 3;
						while(whoGotIt == 3) {
							if (tv.getLeft() > 1000 || tv.getLeft() < 0)
								speed = -speed;
							Message msg = mHandler.obtainMessage();
							msg.what = 1;
							msg.sendToTarget();
							try {
								Thread.sleep(100);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				}).start();
			}
		});
	}

}
