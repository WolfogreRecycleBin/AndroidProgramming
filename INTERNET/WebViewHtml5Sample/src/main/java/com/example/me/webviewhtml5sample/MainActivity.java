package com.example.me.webviewhtml5sample;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.webkit.WebView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private WebView wv;

    @SuppressLint("JavascriptInterface")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wv=(WebView)this.findViewById(R.id.webview);
        wv.getSettings().setJavaScriptEnabled(true);
        wv.getSettings().setSaveFormData(false);
        wv.addJavascriptInterface(new SharpJavaScript(), "sharp");
        wv.getSettings().setDefaultTextEncodingName("UTF-8") ;
        wv.loadUrl("file:///android_asset/phone.html");
    }

    public class SharpJavaScript {
        public void contactlist(){
            try{
                String json=buildJson(getContacts());
                wv.loadUrl("javascript:show('"+json+"')");
            }catch(Exception e){
                e.printStackTrace();
            }
        }

        public void call(String phone){
            Intent intent=new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
            try {
                MainActivity.this.startActivity(intent);
            }catch (Exception O){

            }
        }

        public List<Contact> getContacts(){
            List<Contact> contacts=new ArrayList<Contact>();
            contacts.add(new Contact(1,"tom","66133"));
            contacts.add(new Contact(2,"Mary","66313"));
            contacts.add(new Contact(3,"Jone","66432"));
            return contacts;
        }

        public String buildJson(List<Contact> contacts) throws Exception{
            JSONArray array=new JSONArray();
            for(Contact contact:contacts){
                JSONObject jb=new JSONObject();
                jb.put("id",contact.getId());
                jb.put("name",contact.getName());
                jb.put("phone",contact.getPhone());
                array.put(jb);
            }
            return array.toString();
        }
    }


}
