package com.example.me.customcomponentssample;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class GameView extends View
{
	int  miCount = 0;
	
	public int mX,mY;
	public int mcolor1,mcolor2,mcolor3,mcolor4;
	

	public GameView(Context context, AttributeSet as)
	{
		super(context,as);		
		TypedArray a = context.obtainStyledAttributes(as,
		          R.styleable.GV_atts);
		        mX = a.getInteger(R.styleable.GV_atts_gx, 22);
		        mY = a.getInteger(R.styleable.GV_atts_gy, (320-80)/2);	
		        mcolor1=a.getColor(R.styleable.GV_atts_gcolor1, Color.BLUE);
		        mcolor2=a.getColor(R.styleable.GV_atts_gcolor2, Color.GREEN);
		        mcolor3=a.getColor(R.styleable.GV_atts_gcolor3, Color.RED);
		        mcolor4=a.getColor(R.styleable.GV_atts_gcolor4, Color.YELLOW);
		        //
		        //mcolor4 = as.getAttributeResourceValue("cn.edu.shu.cs.android.experiment06.game_myview", "gcolor4", Color.YELLOW);  
	}
	//
	//
	public void onDraw(Canvas canvas)
	{
		if (miCount < 100)
		{
			miCount++;
		}
		else 
		{
			miCount = 0;
		}

		Paint mPaint = new Paint();   
        switch (miCount%4)
		{
		case 0:
			mPaint.setColor(mcolor1);  	
			break;
		case 1:
			mPaint.setColor(mcolor2);  	
			break;
		case 2:
			mPaint.setColor(mcolor3);  	
			break;
		case 3:
			mPaint.setColor(mcolor4);  	
			break;
		default:
			mPaint.setColor(Color.WHITE);  	
			break;
		} 
        //
        canvas.drawRect(mX, mY, mX+80, mY+40, mPaint); 
	}

	
	
}

