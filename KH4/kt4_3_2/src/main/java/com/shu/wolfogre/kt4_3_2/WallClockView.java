package com.shu.wolfogre.kt4_3_2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.text.format.Time;
import android.util.AttributeSet;
import android.view.View;

import java.util.Calendar;

/**
 * Created by Jason Song(wolfogre@outlook.com) on 12/23/2015.
 */
public class WallClockView extends View implements Runnable {

	public WallClockView(Context context, AttributeSet attrs) {
		super(context, attrs);
		new Thread(this).start();
	}

	public void onDraw(Canvas canvas){
		int radius = 400;
		int textSize = 100;
		Paint paint = new Paint();

		//画表盘
		paint.setColor(Color.GRAY);
		canvas.drawCircle(radius, radius, radius, paint);

		//画刻度
		paint.setColor(Color.BLACK);
		paint.setTextSize(textSize);
		canvas.save();
		for(int i = 1; i <= 12; ++i){
			canvas.rotate(30,radius,radius);
			canvas.drawText("" + i, radius - paint.measureText("" + i)/2, textSize,paint);
		}
		canvas.restore();

		Calendar c = Calendar.getInstance();

		//画时针
		paint.setColor(Color.RED);
		paint.setStrokeWidth(30);
		canvas.save();
		canvas.rotate(360 / 12 * c.get(Calendar.HOUR), radius, radius);
		canvas.drawLine(radius, radius, radius, textSize + 80, paint);
		canvas.restore();

		//画分针
		paint.setColor(Color.GREEN);
		paint.setStrokeWidth(20);
		canvas.save();
		canvas.rotate(360 / 60 * c.get(Calendar.MINUTE), radius, radius);
		canvas.drawLine(radius, radius, radius, textSize + 40, paint);
		canvas.restore();

		//画秒针
		paint.setColor(Color.BLUE);
		paint.setStrokeWidth(10);
		canvas.save();
		canvas.rotate(360 / 60 * c.get(Calendar.SECOND), radius, radius);
		canvas.drawLine(radius, radius, radius, textSize, paint);
		canvas.restore();

		//画针轴
		paint.setColor(Color.DKGRAY);
		canvas.drawCircle(radius, radius, 30, paint);
	}

	@Override
	public void run() {
		while (!Thread.currentThread().isInterrupted())
		{
			try
			{
				Thread.sleep(1000);
			}
			catch (InterruptedException e)
			{
				Thread.currentThread().interrupt();
			}
			this.postInvalidate();
		}
	}
}
