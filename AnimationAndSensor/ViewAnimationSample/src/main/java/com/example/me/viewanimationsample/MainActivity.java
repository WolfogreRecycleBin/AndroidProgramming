package com.example.me.viewanimationsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ImageView imv;
    private Animation xiaAnimation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imv= (ImageView) findViewById(R.id.imv);

        xiaAnimation= AnimationUtils.loadAnimation(this, R.anim.animxia);
        imv.startAnimation(xiaAnimation);
    }

    public void playAnim(View v){
        imv.startAnimation(xiaAnimation);
    }
}
