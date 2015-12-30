package com.example.me.sharedpreferencesample;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * Created by Administrator on 2015/12/28.
 */
public class SettingFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.mypreferences);
    }

}
