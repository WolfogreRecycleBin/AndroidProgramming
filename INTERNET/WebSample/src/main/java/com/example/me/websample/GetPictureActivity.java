package com.example.me.websample;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class GetPictureActivity extends AppCompatActivity {
	private EditText edt;
	private ImageView imv;
	private Button btn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_getpicture);
		edt=(EditText)findViewById(R.id.editText1);		
		imv=(ImageView)findViewById(R.id.imageView1);
		btn=(Button)findViewById(R.id.button1);
		btn.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				  ConnectivityManager connMgr = (ConnectivityManager) 
				            getSystemService(Context.CONNECTIVITY_SERVICE);
				        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
				        if (networkInfo != null && networkInfo.isConnected()) {
				            new DownloadWebpictureTask().execute(edt.getText().toString());
				        } else {
				        	Toast.makeText(getApplicationContext(), "No network connection available", Toast.LENGTH_SHORT).show();
				        }
			}			
		});
	}

	private Bitmap loadImage(String path){
		try{
			
			Bitmap bitmap= InternetTools.getImage(path);
					return bitmap;	      
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	
	 private class DownloadWebpictureTask extends AsyncTask<String, Void, Bitmap> {
	        @Override
	        protected Bitmap doInBackground(String... urls) {
	              
	            // params comes from the execute() call: params[0] is the url.
	            	return loadImage(urls[0]);
	        }
	        // onPostExecute displays the results of the AsyncTask.
	        @Override
	        protected void onPostExecute(Bitmap result) {
				if(result!=null) {
					imv.setImageBitmap(result);
				}
	       }
	    }



}
