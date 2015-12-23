package com.example.me.myapplication;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class ToolbarActivity extends AppCompatActivity {

    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar);
        mToolbar = (Toolbar) findViewById(R.id.my_toolbar);

        mToolbar.setLogo(R.drawable.mic);
        mToolbar.setTitle(R.string.app_name);
        mToolbar.setSubtitle(R.string.app_name);


      //  mToolbar.setNavigationIcon(R.drawable.mic);

        setSupportActionBar(mToolbar);

        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

//        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                switch (item.getItemId()) {
//                    case R.id.mic:
//                        return true;
//
//                    default:
//                        return false;
//                }
//            }
//        });
        //mToolbar.inflateMenu(R.menu.toolbar_menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.mic:
                Toast.makeText(getApplication().getApplicationContext(), "aaa", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.toolbar_menu, menu);

        return true;
    }
}
