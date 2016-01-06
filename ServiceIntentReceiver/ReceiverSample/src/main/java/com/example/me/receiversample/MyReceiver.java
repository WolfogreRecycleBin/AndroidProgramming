package com.example.me.receiversample;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;


public class MyReceiver extends BroadcastReceiver{
	
	@Override
	public void onReceive(Context cxt, Intent intent) {

		String msg = intent.getStringExtra("msg");

		Toast.makeText(cxt,"土豆土豆，我是地瓜", Toast.LENGTH_LONG).show();

		NotificationCompat.Builder mBuilder =
				new NotificationCompat.Builder(cxt)
						.setSmallIcon(R.mipmap.ic_launcher)
						.setContentTitle("My notification")
						.setContentText(msg);

		NotificationManager mNotificationManager =
				(NotificationManager) cxt.getSystemService(Context.NOTIFICATION_SERVICE);
// mId allows you to update the notification later on.
		mNotificationManager.notify(1, mBuilder.build());
	}
}
