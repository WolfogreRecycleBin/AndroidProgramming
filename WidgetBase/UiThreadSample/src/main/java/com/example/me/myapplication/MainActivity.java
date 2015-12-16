package com.example.me.myapplication;

import android.app.Activity;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class MainActivity extends AppCompatActivity {

    private ImageView iv,iv2;
    private  Bitmap mBit;

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            if(msg.what == 1&& mBit!=null) {
                iv.setImageBitmap(mBit);
                //iv.setImageResource(R.drawable.image1);
            }
            super.handleMessage(msg);
        }
    };


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bt=(Button)this.findViewById(R.id.button1);
        iv=(ImageView)this.findViewById(R.id.imageView1);
        iv2=(ImageView)this.findViewById(R.id.imageView2);

        bt.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v) {

               // loadImage1();
               //loadImage2();
               // loadImage3();
               // loadImage4();
              loadImage5();
            }
        });
    }

    //loadImage1 is a fault sample
    private void loadImage1(){
        new Thread(new Runnable() {
            public void run() {
                Bitmap b = loadImageFromNetwork();
                iv.setImageBitmap(b);

            }
        }).start();
    }

//view.post(runnable)
    private void loadImage2(){
        new Thread(new Runnable() {
            public void run() {
                final Bitmap bitmap = loadImageFromNetwork();
                //final Bitmap bitmap = loadImageFromRawResource(R.raw.image1);
                iv.post(new Runnable() {
                    public void run() {
                        iv.setImageBitmap(bitmap);
                        iv2.setImageBitmap(bitmap);
                        // iv.setImageResource(R.drawable.image1);
                    }
                });
            }
        }).start();
    }

    //AsyncTask method
    private void loadImage3(){
        new DownloadImageTask().execute();

    }

    //handler method
    private void loadImage4(){
        new Thread(new Runnable() {
            public void run() {
                Message msg = mHandler.obtainMessage();
                mBit = loadImageFromNetwork();
                msg.what = 1;
                msg.sendToTarget();
            }
        }).start();
    }

        //runonuithread
        private void loadImage5(){
            new Thread(new Runnable() {
                public void run() {
                    final Bitmap bitmap = loadImageFromNetwork();
                    MainActivity.this.runOnUiThread(new Runnable() // 工作线程刷新UI
                    { //这部分代码将在UI线程执行，实际上是runOnUiThread post Runnable到UI线程执行了
                        @Override
                        public void run() {
                            iv.setImageBitmap(bitmap);
                            iv2.setImageBitmap(bitmap);

                        }
                    });
                                    }
            }).start();
        }
    //
    private Bitmap loadImageFromNetwork(){
        //图片的链接地址
        String url="http://img5.imgtn.bdimg.com/it/u=1561037318,1220192463&fm=21&gp=0.jpg";
        try{
            URL imgURL = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) imgURL.openConnection();
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            //下载之
            Bitmap bmp = BitmapFactory.decodeStream(bis);
            bis.close();
            is.close();
            return bmp;
        }catch(IOException e){
            return null;
        }

    }

    private Bitmap loadImageFromRawResource(int resid){
        //图片的链接地址
        try{

            InputStream is = getResources().openRawResource(resid);
            BufferedInputStream bis = new BufferedInputStream(is);
            //下载之
            Bitmap bmp = BitmapFactory.decodeStream(bis);
            bis.close();
            is.close();
            return bmp;
        }catch(Resources.NotFoundException e){
            return null;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    private Bitmap loadImageFromDrawable(int resid){
        //图片的链接地址
        try{
            InputStream is = getResources().openRawResource(resid);
            BufferedInputStream bis = new BufferedInputStream(is);
            //下载之
            Bitmap bmp = BitmapFactory.decodeStream(bis);
            bis.close();
            is.close();
            return bmp;
        }catch(IOException e){
            return null;
        }
    }
//AsyncTask
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        /** The system calls this to perform work in a worker thread and
         * delivers it the parameters given to AsyncTask.execute() */
        protected Bitmap doInBackground(String... urls) {
            return loadImageFromNetwork();
        }//String... urls 可变参数，即参数个数可变 ,例如return loadImageFromNetwork(urls[0]);

        /** The system calls this to perform work in the UI thread and delivers
         * the result from doInBackground() */
        protected void onPostExecute(Bitmap result) {
            iv.setImageBitmap(result);
        }


    }
}
