package com.example.administrator.layoutinflatesample;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ActionMenuView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View layout = getLayoutInflater().inflate(R.layout.inflate_layout, (ViewGroup) findViewById(R.id.topLayout));
        TextView mergeTv=(TextView)layout.findViewById(R.id.mergetv);
        mergeTv.setText("mergetv ");
        mergeTv.setBackgroundColor(Color.BLUE);

        LinearLayout toplayout= (LinearLayout) findViewById(R.id.topLayout);
        TextView textView=new TextView(this);
         textView.setText("new Textview");
        textView.setBackgroundColor(0xffffff00);
        LinearLayout.LayoutParams p= new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,    //android:layout_width             
                LinearLayout.LayoutParams.WRAP_CONTENT//android:layout_height
                );
        toplayout.addView(textView, p);

	    View myLlayout = getLayoutInflater().inflate(R.layout.my_layout, (ViewGroup) findViewById(R.id.belowLayout));

	    Button button = new Button(this);
	    button.setText("动态生成的按钮");
	    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
			    LinearLayout.LayoutParams.WRAP_CONTENT,
			    LinearLayout.LayoutParams.WRAP_CONTENT
	    );
	    ((LinearLayout) findViewById(R.id.belowLayout)).addView(button, layoutParams);

    }
}
