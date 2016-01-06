package com.example.me.notificationsample;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class ResultActivity extends AppCompatActivity {
    private int noID=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Button bt1=(Button)findViewById(R.id.bt1);
        Button bt2=(Button)findViewById(R.id.bt2);
        Button bt3=(Button)findViewById(R.id.bt3);
        bt1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                NotificationCompat.Builder mBuilder =
                        new NotificationCompat.Builder(ResultActivity.this)
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setContentTitle("My notification")
                                .setContentText("Hello World!")
                                .setNumber(noID++);
                // Creates an explicit intent for an Activity in your app
                Intent resultIntent = new Intent(ResultActivity.this, MainActivity.class);

                // The stack builder object will contain an artificial back stack for the
                // started Activity.
                // This ensures that navigating backward from the Activity leads out of
                // your application to the Home screen.
                TaskStackBuilder stackBuilder = TaskStackBuilder.create(ResultActivity.this);
                // Adds the back stack for the Intent (but not the Intent itself)
                stackBuilder.addParentStack(ResultActivity.class);
                // Adds the Intent that starts the Activity to the top of the stack
                stackBuilder.addNextIntent(resultIntent);
                PendingIntent resultPendingIntent =
                        stackBuilder.getPendingIntent(
                                0,
                                PendingIntent.FLAG_UPDATE_CURRENT
                        );
                mBuilder.setContentIntent(resultPendingIntent);

                NotificationCompat.InboxStyle inboxStyle =
                        new NotificationCompat.InboxStyle();
                String[] events = new String[6];
                // Sets a title for the Inbox style big view
                inboxStyle.setBigContentTitle("Event tracker details:");
                // Moves events into the big view
                for (int i=0; i < events.length; i++) {
                    events[i]="rrrrrrrr";
                    inboxStyle.addLine(events[i]);
                }
                // Moves the big view style object into the notification object.
                mBuilder.setStyle(inboxStyle);

                NotificationManager mNotificationManager =
                        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                // mId allows you to update the notification later on.
                mNotificationManager.notify(noID, mBuilder.build());
            }

        });

        bt2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                final NotificationManager mNotifyManager =
                        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                final NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(ResultActivity.this);
                mBuilder.setContentTitle("Picture Download")
                        .setContentText("Download in progress")
                        .setSmallIcon(R.mipmap.ic_launcher);
                // Start a lengthy operation in a background thread
                new Thread(
                        new Runnable() {
                            @Override
                            public void run() {
                                int incr;
                                // Do the "lengthy" operation 20 times
                                for (incr = 0; incr <= 100; incr+=5) {
                                    // Sets the progress indicator to a max value, the
                                    // current completion percentage, and "determinate"
                                    // state
                                    mBuilder.setProgress(100, incr, false);
                                    // Displays the progress bar for the first time.
                                    mNotifyManager.notify(0, mBuilder.build());
                                    // Sleeps the thread, simulating an operation
                                    // that takes time
                                    try {
                                        // Sleep for 5 seconds
                                        Thread.sleep(1*1000);
                                    } catch (InterruptedException e) {
                                        Log.d("MainActivity", "sleep failure");
                                    }
                                }
                                // When the loop is finished, updates the notification
                                mBuilder.setContentText("Download complete")
                                        // Removes the progress bar
                                        .setProgress(0,0,false);
                                mNotifyManager.notify(noID++, mBuilder.build());
                            }
                        }
                        // Starts the thread by calling the run() method in its Runnable
                ).start();
            }

        });


        bt3.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                final NotificationManager mNotifyManager =
                        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                final NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(ResultActivity.this);
                mBuilder.setContentTitle("Picture Download")
                        .setContentText("Download in progress")
                        .setSmallIcon(R.mipmap.ic_launcher);
                // Start a lengthy operation in a background thread
                new Thread(
                        new Runnable() {
                            @Override
                            public void run() {
                                int incr;
                                // Do the "lengthy" operation 20 times
                                for (incr = 0; incr <= 100; incr+=5) {
                                    // Sets the progress indicator to a max value, the
                                    // current completion percentage, and "determinate"
                                    // state
                                    mBuilder.setProgress(0, 0, true);
                                    // Displays the progress bar for the first time.
                                    mNotifyManager.notify(0, mBuilder.build());
                                    // Sleeps the thread, simulating an operation
                                    // that takes time
                                    try {
                                        // Sleep for 5 seconds
                                        Thread.sleep(1*100);
                                    } catch (InterruptedException e) {
                                        Log.d("MainActivity", "sleep failure");
                                    }
                                }
                                // When the loop is finished, updates the notification
                                mBuilder.setContentText("Download complete")
                                        // Removes the progress bar
                                        .setProgress(0,0,false);
                                mNotifyManager.notify(noID++, mBuilder.build());
                            }
                        }
                        // Starts the thread by calling the run() method in its Runnable
                ).start();
            }

        });
    }
}
