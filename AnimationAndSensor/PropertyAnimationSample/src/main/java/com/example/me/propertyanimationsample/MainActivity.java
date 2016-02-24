package com.example.me.propertyanimationsample;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.FloatEvaluator;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private Button btVal,btObj,btVpa,btAset,btKey;
    ValueAnimator valueAnima;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btVal = (Button) findViewById(R.id.btClick);
        btObj= (Button) findViewById(R.id.btObjectanim);
        btVpa= (Button) findViewById(R.id.btViewPropertyanim);
        btAset= (Button) findViewById(R.id.btAnimset);
        btKey= (Button) findViewById(R.id.btKeyframe);
    }

    public void btOnclick(View v){
        valueAnima = ValueAnimator.ofObject(new MyTypeEvaluator(), getWindowManager().getDefaultDisplay().getWidth()/2, getWindowManager().getDefaultDisplay().getWidth());

        valueAnima.addListener(new AnimatorListenerAdapter() {
            public void onAnimationStart(Animator animation) {
                btVal.setText("start");
            }

            public void onAnimationEnd(Animator animation) {
                btVal.setText("end");
                btVal.setX(45);
                btVal.setVisibility(View.GONE);
            }

            public void onAnimationRepeat(Animator animation) {
                btVal.setText("repeat");
            }
        });
        valueAnima.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                btVal.setX((float) animation.getAnimatedValue());
            }
        });

        valueAnima.setDuration(3000);
        valueAnima.setInterpolator(new AnticipateOvershootInterpolator());
        valueAnima.setRepeatCount(3);
        valueAnima.start();
    }


    public void btObjClick(View v){
        ObjectAnimator animR =ObjectAnimator.ofFloat(btObj, "rotation", 0f, 360f);
        animR.setDuration(3000);
        animR.setInterpolator(new AnticipateOvershootInterpolator());
        animR.setRepeatCount(3);
        animR.start();

    }

    public void btVpaClick(View v){
        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("x", 0f,50f);
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("y",0f,100f);
        ObjectAnimator.ofPropertyValuesHolder(btObj, pvhX,pvhY).start();

       btVpa.animate().rotationBy(180f).xBy(200f);
    }

    public void btAsetClick(View v){
        ObjectAnimator animR =ObjectAnimator.ofFloat(btAset, "rotation", 0f, 360f);
        animR.setDuration(1000);
        animR.setInterpolator(new AnticipateOvershootInterpolator());

        ObjectAnimator animX =ObjectAnimator.ofFloat(btAset, "x", 0f, 100f);
        animX.setDuration(1000);

        ObjectAnimator animY =ObjectAnimator.ofFloat(btAset, "y", 0f, 100f);
        animY.setDuration(1000);

        ObjectAnimator animA =ObjectAnimator.ofFloat(btAset, "alpha", 0f, 255f);
        animA.setDuration(6000);

        AnimatorSet as = new AnimatorSet();
        as.play(animR).before(animX);
        as.play(animX).with(animY);
        as.play(animA).after(animY);
        as.start();
    }

    public void btKeyClick(View v){
        Keyframe kf0 = Keyframe.ofFloat(0f, 0f);
        Keyframe kf1 = Keyframe.ofFloat(.5f, 360f);
        Keyframe kf2 = Keyframe.ofFloat(1f, 0f);
        PropertyValuesHolder pvhRotation = PropertyValuesHolder.ofKeyframe("rotation", kf0, kf1, kf2);
        ObjectAnimator rotationAnim = ObjectAnimator.ofPropertyValuesHolder(btKey, pvhRotation);
        rotationAnim.setDuration(5000);
        rotationAnim.start();
    }

    public class MyTypeEvaluator implements TypeEvaluator {
        public Object evaluate(float fraction, Object startValue, Object endValue) {
            float startFloat = ((Number) startValue).floatValue();
            return startFloat + fraction * (((Number) endValue).floatValue() - startFloat);
        }
    }


}
