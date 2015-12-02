package com.example.administrator.kh2_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    boolean isNew;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.findViewById(R.id.button).setOnClickListener(this);
        this.findViewById(R.id.button2).setOnClickListener(this);
        this.findViewById(R.id.button3).setOnClickListener(this);
        this.findViewById(R.id.button4).setOnClickListener(this);
        this.findViewById(R.id.button5).setOnClickListener(this);
        this.findViewById(R.id.button6).setOnClickListener(this);
        this.findViewById(R.id.button7).setOnClickListener(this);
        this.findViewById(R.id.button8).setOnClickListener(this);
        this.findViewById(R.id.button9).setOnClickListener(this);
        this.findViewById(R.id.button10).setOnClickListener(this);
        this.findViewById(R.id.button11).setOnClickListener(this);
        this.findViewById(R.id.button12).setOnClickListener(this);
        this.findViewById(R.id.button13).setOnClickListener(this);
        this.findViewById(R.id.button14).setOnClickListener(this);
        this.findViewById(R.id.button15).setOnClickListener(this);
        this.findViewById(R.id.button16).setOnClickListener(this);
        this.findViewById(R.id.button17).setOnClickListener(this);
        this.findViewById(R.id.button18).setOnClickListener(this);
        this.findViewById(R.id.button19).setOnClickListener(this);
        isNew = true;
    }

    public void onClick(View v) {
        TextView textView = (TextView) (findViewById(R.id.textView));
        Button button = (Button) v;
        String input =  button.getText().toString();
        if(isNew){
            textView.setText("");
            isNew = false;
        }
        switch (input) {
            case "C":{
                textView.setText("");
                break;
            }
            case "＋/－":{
                //TODO:
                break;
            }
            case "←":{
                String str = textView.getText().toString();
                textView.setText(str.substring(0, str.length() - 1));
                break;
            }
            case "=":{
                isNew = true;
                textView.setText(textView.getText() + "\n我不知道\n");
                break;
            }
            default:
            {
                textView.setText(textView.getText() + input);
                break;
            }
        }

    }
}
