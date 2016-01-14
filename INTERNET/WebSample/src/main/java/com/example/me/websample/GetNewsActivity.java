package com.example.me.websample;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class GetNewsActivity extends Activity {
	private EditText edt;
	private ListView lv;
	private Button btn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_getnews);
		edt=(EditText)findViewById(R.id.editText1);		
		lv=(ListView)findViewById(R.id.ListView);
		btn=(Button)findViewById(R.id.button1);
		
		btn.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				  ConnectivityManager connMgr = (ConnectivityManager) 
				            getSystemService(Context.CONNECTIVITY_SERVICE);
				        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
				        if (networkInfo != null && networkInfo.isConnected()) {
				            new DownloadWebTask().execute(edt.getText().toString());
				        } else {
				        	Toast.makeText(getApplicationContext(), "No network connection available", Toast.LENGTH_SHORT).show();
				        }
			}			
		});
	}

	private List<News> loadNews(String path){
		try{
			
			return InternetTools.getNews(path);
			
		}catch(Exception e){
			e.printStackTrace();
			
		}
		return null;
	}
	
	
	 private class DownloadWebTask extends AsyncTask<String, Void, List<News>> {
	        @Override
	        protected List<News> doInBackground(String... urls) {
	              
	            // params comes from the execute() call: params[0] is the url.
	         
	            	return loadNews(urls[0]);
	           
	        }
	        // onPostExecute displays the results of the AsyncTask.
	        @Override
	        protected void onPostExecute(List<News> result) {
	          if(result!=null){
	        	List<HashMap<String,Object>> data=new ArrayList<HashMap<String,Object>>();
	            for(News news:result){
	            	HashMap<String,Object> item=new HashMap<String,Object>();
	            	item.put("id",news.getId());
	            	item.put("title",news.getTitle());
	            	item.put("timelength","timelength"+news.getId());
	            	data.add(item);
	            }
	            SimpleAdapter adapter=new SimpleAdapter(GetNewsActivity.this,data,android.R.layout.simple_list_item_2,
	            		new String[]{"title","timelength"},new int[]{android.R.id.text1,android.R.id.text2});
	            lv.setAdapter(adapter);
	          }
	       }
	    }



}
