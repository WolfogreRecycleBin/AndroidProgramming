package com.example.me.websample;

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
import android.widget.TextView;
import android.widget.Toast;

public class GetHtmlActivity extends Activity {
	private EditText edt;
	private TextView tv; 
	private Button btn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gethtml);
		edt=(EditText)findViewById(R.id.editText1);		
		tv=(TextView)findViewById(R.id.textView2);
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

	private String loadHtml(String path){
		try{
			//a debug connection string
			path="http://192.168.0.104:8080/ServerForAndroid/GetHtmlTest.jsp" ;
			String html= InternetTools.getHtml(path);
					return html;	      
			
		}catch(Exception e){
			e.printStackTrace();
			
		}
		return null;
	}
	
	
	 private class DownloadWebTask extends AsyncTask<String, Void, String> {
	        @Override
	        protected String doInBackground(String... urls) {
	              
	            // params comes from the execute() call: params[0] is the url.
	         
	            	return loadHtml(urls[0]);
	           
	        }
	        // onPostExecute displays the results of the AsyncTask.
	        @Override
	        protected void onPostExecute(String result) {
	            tv.setText(result);
	       }
	    }



}
