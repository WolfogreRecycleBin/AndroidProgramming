package com.example.me.actionbarsample;



import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ShareActionProvider;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ActionBar mAb;
    Button mBtBar,mBtSe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAb=getSupportActionBar();//获取系统ActionBar对象
        mAb.setTitle("ActionBar例子");
        mAb.setSubtitle("MainActivity");


        mAb.setDisplayShowHomeEnabled(true);
        mAb.setDisplayUseLogoEnabled(true);
        mAb.setLogo(R.drawable.ic_account_balance_black_18dp);

        mBtBar = (Button) findViewById(R.id.btshowbar);
        mBtBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAb.isShowing()) {
                    mAb.hide();
                    mBtBar.setText("show bar");
                } else {
                    mAb.show();
                    mBtBar.setText("hide bar");
                }
            }
        });

        mBtSe = (Button) findViewById(R.id.btsecond);
        mBtSe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(MainActivity.this,SecondActivity.class);
                startActivity(it);
                }
         });
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action1:
               Toast.makeText(getApplication().getApplicationContext(),"action1", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
