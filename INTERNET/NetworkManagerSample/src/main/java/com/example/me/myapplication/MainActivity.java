package com.example.me.myapplication;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String DEBUG_TAG = "NetworkStatusExample";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onButtonClick(View view){
        if( isOnline()){
            Toast.makeText(this,"network is availble",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this,"network is not availble",Toast.LENGTH_SHORT).show();
        }
    }
    public boolean isOnline() {
        /*
        TIPS:
        主要用到两个类
        ConnectivityManager: Answers queries about the state of network connectivity. It also notifies applications when network connectivity changes.
        NetworkInfo: Describes the status of a network interface of a given type (currently eitherMobile or Wi-Fi).
         */
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (null!=networkInfo) {
            boolean wifiConnected = networkInfo.getType() == ConnectivityManager.TYPE_WIFI;
            boolean mobileConnected = networkInfo.getType() == ConnectivityManager.TYPE_MOBILE;

            Log.d("isOnline", "Wifi connected: " + wifiConnected);
            Log.d("isOnline", "Mobile connected: " + mobileConnected);
        }
        return (networkInfo != null && networkInfo.isConnected());
    }
}
