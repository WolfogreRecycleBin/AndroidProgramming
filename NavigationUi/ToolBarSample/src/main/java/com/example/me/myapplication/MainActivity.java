package com.example.me.myapplication;

import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.my_toolbar);

        mToolbar.setLogo(R.drawable.mic);
        mToolbar.setTitle(R.string.app_name);
        mToolbar.setSubtitle(R.string.app_name);
        setSupportActionBar(mToolbar);


        mToolbar.setNavigationIcon(R.drawable.mic);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.mic:
                Intent it=new Intent(MainActivity.this,ToolbarActivity.class);
                startActivity(it);
               // Toast.makeText(getApplication().getApplicationContext(),"aaa",Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        MenuItem menuItem1 = menu.findItem(R.id.action_share);
        ShareActionProvider sap=(ShareActionProvider) MenuItemCompat.getActionProvider(menuItem1);
        sap.setShareIntent(getDefaultIntent());

        return true;
    }
    private Intent getDefaultIntent(){
        Intent intent=new Intent(Intent.ACTION_SEND);
        intent.setType("image/*");
        return intent;
    }
}
