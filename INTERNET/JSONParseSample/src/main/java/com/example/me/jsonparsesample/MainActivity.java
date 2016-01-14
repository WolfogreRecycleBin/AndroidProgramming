package com.example.me.jsonparsesample;

import android.support.annotation.NonNull;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity {
Toolbar mToolbar;
    EditText nameEd,ageEd,phone1Ed,phone2Ed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar= (Toolbar) findViewById(R.id.my_toolbar);
        nameEd= (EditText) findViewById(R.id.edname);
        ageEd= (EditText) findViewById(R.id.edage);
        phone1Ed= (EditText) findViewById(R.id.edtp1);
        phone2Ed= (EditText) findViewById(R.id.edtp2);
        setSupportActionBar(mToolbar);
    }

    private JSONObject getContactJSONObject(Contact contact){
        try {
            // 首先最外层是{}，是创建一个对象
            JSONObject contactJsonObject = new JSONObject();
            contactJsonObject.put(Contact.FIELD_NAME, contact.getName());
            contactJsonObject.put(Contact.FIELD_AGE, contact.getAge());
            JSONArray phonearray = new JSONArray();
            phonearray.put(contact.getPhones()[0]).put("234531");
            contactJsonObject.put(Contact.FIELD_PHONES, phonearray);
            return  contactJsonObject;
        }catch (JSONException e){
            e.printStackTrace();
        }
        return  null;
    }

    private Contact getContactFromJSON(String str){
        Contact cat=new Contact();
        try {
            JSONTokener jsonParser = new JSONTokener(str);
            // 此时还未读取任何json文本，直接读取就是一个JSONObject对象。
            // 如果此时的读取位置在"name" : 了，那么nextValue就是"yuanzhifei89"（String）
            JSONObject contactJObject = (JSONObject) jsonParser.nextValue();
            // 接下来的就是JSON对象的操作了
            JSONArray jsa=contactJObject.getJSONArray(Contact.FIELD_PHONES);
            String[] ss=new String[jsa.length()];

            for(int i=0;i<=jsa.length()-1;i++){
                 ss[i]=jsa.optString(i);
            }
           cat.setName(contactJObject.getString(Contact.FIELD_NAME));
           cat.setAge(contactJObject.getInt(Contact.FIELD_AGE));
            cat.setPhones(ss);
            return cat;
        } catch (JSONException ex) {
            // 异常处理代码
        }
        return  null;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_add:
                Contact contact = new Contact();
                contact.toString();


               JSONObject jsb=getContactJSONObject(contact);
                Log.d("jsb.tostring", jsb.toString());
                Contact ca=getContactFromJSON(jsb.toString());
                nameEd.setText(ca.getName());
                ageEd.setText(Integer.toString(ca.getAge()));
                phone1Ed.setText(ca.getPhones()[0]);
                phone2Ed.setText(ca.getPhones()[1]);

                Toast.makeText(getApplication().getApplicationContext(), jsb.toString(), Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        MenuItem menuItem1 = menu.findItem(R.id.tool_spinner);


        return true;
    }
}
