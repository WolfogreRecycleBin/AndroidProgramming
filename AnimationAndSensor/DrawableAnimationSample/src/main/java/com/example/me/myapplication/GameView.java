package com.example.me.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.view.KeyEvent;
import android.view.View;

public class GameView extends View
{

	private AnimationDrawable	frameAnimation	= null;
	Context						mContext		= null;
	

	Drawable				mBitAnimation				= null;
	public GameView(Context context)
	{
		super(context);
		
		mContext = context;
		

		frameAnimation = new AnimationDrawable();
		

		for (int i = 1; i <= 15; i++)
		{
			int id = getResources().getIdentifier("a" + i, "drawable", mContext.getPackageName());
			mBitAnimation = getResources().getDrawable(id);

			frameAnimation.addFrame(mBitAnimation, 500);
		}
		

		frameAnimation.setOneShot( false );  
		

		this.setBackgroundDrawable(frameAnimation);
		frameAnimation.start();
	}
	
	public void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);
		
	}
	

}

