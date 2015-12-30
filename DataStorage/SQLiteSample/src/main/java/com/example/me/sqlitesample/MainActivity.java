package com.example.me.sqlitesample;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity {
    Button bt;
    ListView ls;
    MatchDbHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt= (Button) findViewById(R.id.bt);

        dbHelper=new MatchDbHelper(MainActivity.this,1);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues cv=new ContentValues();
                cv.put(MatchDbContract.MatchsTable.COLUMN_NAME_MATCH_NAME,"世界锦标赛");
                dbHelper.insert(MatchDbContract.MatchsTable.TABLE_NAME, cv);
                refreshListView();
            }
        });
        ls= (ListView) findViewById(R.id.list);
        refreshListView();
    }

    private void refreshListView(){
        Cursor cs=dbHelper.query(MatchDbContract.MatchsTable.TABLE_NAME);
        if (cs!=null) {
            SimpleCursorAdapter sca = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, cs, new String[]{MatchDbContract.MatchsTable.COLUMN_NAME_MATCH_NAME}, new int[]{android.R.id.text1});
            ls.setAdapter(sca);
        }
    }
}

