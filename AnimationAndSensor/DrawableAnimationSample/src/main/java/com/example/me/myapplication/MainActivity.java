package com.example.me.myapplication;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ImageView scanView;
    private AnimationDrawable scanAnimation;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(new GameView(this));
        scanView=new ImageView(this);
        scanView.setBackgroundResource(R.drawable.frameanimation);
        scanAnimation = (AnimationDrawable) scanView.getBackground();
        setContentView(scanView);
    }

    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(true);
        scanAnimation.start();
    }

}
