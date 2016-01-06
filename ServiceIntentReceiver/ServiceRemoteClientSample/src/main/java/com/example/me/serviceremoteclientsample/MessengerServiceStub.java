package com.example.me.serviceremoteclientsample;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.widget.Toast;

public class MessengerServiceStub  {
    /** Command to the service to display a message */
    static final int MSG_SAY_HELLO = 1;
    public static final String ACTION="com.example.me.serviceremotesample_ACTION";


}
