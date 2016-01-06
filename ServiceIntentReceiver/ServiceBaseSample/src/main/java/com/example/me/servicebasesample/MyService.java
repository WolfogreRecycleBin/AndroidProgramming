package com.example.me.servicebasesample;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MyService extends Service {
    public static final String ACTION="com.example.me.servicebasesample.MYSERVICE_ACTION";
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        System.out.println("------>on Bind");
        return null;
       // throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        System.out.println("------>on unBind");
        return super.onUnbind(intent);
    }


    @Override
    public void onCreate(){
        super.onCreate();
        System.out.println("------>on created");
    }

    @Override
    public int onStartCommand(Intent intent,int flags,int startId){
        System.out.println("------>on startcommand");
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        System.out.println("------>on destroy");
    }

}
