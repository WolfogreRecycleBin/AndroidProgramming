package wolfogre.khtd4;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Jason Song(wolfogre@outlook.com) on 12/25/2015.
 */
public class BlockView extends View {
	RectF rect;
	boolean visible;
	int color;

	public BlockView(Context context, AttributeSet attrs) {
		super(context, attrs);
		visible = false;
	}

	public void setShape(float x, float y, float width, float height){
		rect = new RectF(x, y, x + width, y + height);
	}

	public void setColor(int r, int g, int b){
		this.color = Color.rgb(r, g, b);
	}

	public void setVisible(boolean value){
		visible = value;
	}

	@Override
	public void draw(Canvas canvas){
		if(!visible)
			return;
		super.draw(canvas);
		Paint paint = new Paint();
		paint.setColor(color);
		canvas.drawRect(rect, paint);
	}

}
