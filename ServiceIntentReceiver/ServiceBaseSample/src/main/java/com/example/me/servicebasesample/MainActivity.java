package com.example.me.servicebasesample;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
Boolean mBound=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void bt1Click(View view){
        Intent it=new Intent(MyService.ACTION);
        it.setPackage("com.example.me.servicebasesample");
       // Intent it=new Intent(this,MyService.class);
        startService(it);
    }

    public void bt2Click(View view){
        Intent it=new Intent(MyService.ACTION);
        it.setPackage("com.example.me.servicebasesample");
       // Intent it=new Intent(this,MyService.class);
        stopService(it);
    }

    public void bt3Click(View view){
        Intent it=new Intent(MyService.ACTION);
        it.setPackage("com.example.me.servicebasesample");
        // Intent it=new Intent(this,MyService.class);
        bindService(it, conn, Context.BIND_AUTO_CREATE);
        mBound = true;
    }

    public void bt4Click(View view){
        if(  mBound) {
            unbindService(conn);
            mBound = false;
        }
    }

    ServiceConnection conn=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
}
