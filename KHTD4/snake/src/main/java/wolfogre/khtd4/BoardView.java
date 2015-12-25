package wolfogre.khtd4;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;


/**
 * Created by Jason Song(wolfogre@outlook.com) on 12/25/2015.
 */
public class BoardView extends View implements Runnable {
	final int row = 20;
	final int col = 20;
	final int rowSize = 800;
	final int colSize = 800;
	BlockView block;

	public BoardView(Context context, AttributeSet attrs) {
		super(context, attrs);
		block = new BlockView(context, attrs);
		block.setLocation(100, 100);
		block.setSize(30, 30);
		new Thread(this).start();
	}

	public void onDraw(Canvas canvas){
		Paint paint = new Paint();
		paint.setColor(Color.GRAY);
		canvas.drawRect(0, 0, rowSize, colSize, paint);
		block.draw(canvas);
	}

	@Override
	public void run() {
		boolean visible = false;
		while (!Thread.currentThread().isInterrupted())
		{
			try
			{
				block.setVisible(visible = !visible);
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
