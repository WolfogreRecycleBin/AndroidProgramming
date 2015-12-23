package com.shu.wolfogre.kt4_2_2;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Jason Song(wolfogre@outlook.com) on 12/23/2015.
 */

public class ClockView extends View {
	public ClockView(Context context, AttributeSet as)
	{
		super(context,as);
	}
	public void onDraw(Canvas canvas){
		Paint paint = new Paint();
		paint.setColor(Color.BLACK);
		canvas.drawCircle(400,400,400,paint);
	}
}
