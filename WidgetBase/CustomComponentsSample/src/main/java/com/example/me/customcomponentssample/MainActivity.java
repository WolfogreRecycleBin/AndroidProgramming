package com.example.me.customcomponentssample;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static final int	REFRESH		= 0x000001;

    /* 声明GameView类对象 */
    private GameView			mGameView	= null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mGameView=(GameView)this.findViewById(R.id.gv);


        // 开启线程
        new Thread(new GameThread()).start();
        Button bt1= (Button) findViewById(R.id.bt1);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGameView.mY -= 3;
            }
        });

        Button bt2= (Button) findViewById(R.id.bt1);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGameView.mY+=3;
            }
        });
    }

    Handler myHandler	= new Handler()
    {
        //接收到消息后处理
        public void handleMessage(Message msg)
        {
            switch (msg.what)
            {
                case MainActivity.REFRESH:
                    mGameView.setLeft(mGameView.getLeft()+20);
                    mGameView.invalidate();
                    break;
            }
            super.handleMessage(msg);
        }
    };

    class GameThread implements Runnable
    {
        public void run()
        {
            while (!Thread.currentThread().isInterrupted())
            {
                Message message = new Message();
                message.what = MainActivity.REFRESH;

                //发送消息
                MainActivity.this.myHandler.sendMessage(message);
                try
                {

                    Thread.sleep(1000);
                }
                catch (InterruptedException e)
                {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
    /**
     * 当然可以将GameThread类这样写
     * 同样可以更新界面，并且不在需要
     * Handler在接受消息
     class GameThread implements Runnable
     {
     public void run()
     {
     while (!Thread.currentThread().isInterrupted())
     {
     try
     {
     Thread.sleep(100);
     }
     catch (InterruptedException e)
     {
     Thread.currentThread().interrupt();
     }
     //使用postInvalidate可以直接在线程中更新界面
     mGameView.postInvalidate();
     }
     }
     }
     */
}
