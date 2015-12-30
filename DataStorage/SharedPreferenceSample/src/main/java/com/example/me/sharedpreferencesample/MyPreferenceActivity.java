package com.example.me.sharedpreferencesample;

import android.preference.PreferenceActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

public class MyPreferenceActivity extends PreferenceActivity{

    final static String ACTION_PREFS_ONE = "cn.edu.shu.cs.android.settingspreference.ACTION";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
		/*
		 getFragmentManager().beginTransaction()
         .replace(android.R.id.content, new SettingFragment())
         .commit();
        */
    }

    @Override
    public void onBuildHeaders(List<PreferenceActivity.Header> target) {
        loadHeadersFromResource(R.xml.mypreferenceheader, target);
    }


}
