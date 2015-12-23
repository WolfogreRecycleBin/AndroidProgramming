package com.shu.wolfogre.kt4_2_2;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Jason Song(wolfogre@outlook.com) on 12/23/2015.
 */

public class ClockView extends View implements Runnable{

	int mFormat;
	int mColor;
	int mFontSize = 120;

	public ClockView(Context context, AttributeSet as)
	{
		super(context,as);
		TypedArray a = context.obtainStyledAttributes(as, R.styleable.ClockView_atts);
		mFormat = a.getInteger(R.styleable.ClockView_atts_cvFormat, 24);
		mColor = a.getColor(R.styleable.ClockView_atts_cvColor, Color.BLACK);
		(new Thread(this)).start();
	}
	public void onDraw(Canvas canvas){
		Paint paint = new Paint();
		paint.setColor(Color.GRAY);
		canvas.drawRect(0, 0, 480, mFontSize + 20, paint);

		SimpleDateFormat formatter;
		if(mFormat == 12)
			formatter = new SimpleDateFormat ("hh:mm:ss ");
		else
			formatter = new SimpleDateFormat ("HH:mm:ss ");
		Date curDate = new Date(System.currentTimeMillis());
		String str = formatter.format(curDate);
		paint.setColor(mColor);
		paint.setTextSize(mFontSize);
		canvas.drawText(str,0, mFontSize, paint);
	}

	public void run(){
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
