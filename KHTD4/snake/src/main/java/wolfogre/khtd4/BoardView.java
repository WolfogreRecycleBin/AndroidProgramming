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
	BlockView[][] blocks;
	SnakeLogic snakeLogic;
	int [][] mat;

	public BoardView(Context context, AttributeSet attrs) {
		super(context, attrs);
		snakeLogic = new SnakeLogic(row, col);
		blocks = new BlockView[row][col];
		for(int i = 0; i < row; ++i)
			for(int j = 0; j < col; ++j)
			{
				blocks[i][j] = new BlockView(context, attrs);
				blocks[i][j].setShape( j * colSize / col, i * rowSize / row,  colSize / col, rowSize / row);
			}
		new Thread(this).start();
	}

	public void onDraw(Canvas canvas){
		Paint paint = new Paint();
		paint.setColor(Color.GRAY);
		canvas.drawRect(0, 0, rowSize, colSize, paint);
		for(int i = 0; i < row; ++i)
			for(int j = 0; j < col; ++j)
			{
				blocks[i][j].draw(canvas);
			}
	}

	@Override
	public void run() {
		while (!Thread.currentThread().isInterrupted())
		{
			try
			{
				if(snakeLogic.move())
					mat = snakeLogic.toMatrix();
				for(int i = 0; i < row; ++i)
					for(int j = 0; j < col; ++j)
					{
						if(mat[i][j] == 0)
							blocks[i][j].setVisible(false);
						if(mat[i][j] == 1){
							blocks[i][j].setColor(0,0,0);
							blocks[i][j].setVisible(true);
						}
						if(mat[i][j] == 3){
							blocks[i][j].setColor(255,0,0);
							blocks[i][j].setVisible(true);
						}
					}
				Thread.sleep(1000);
			}
			catch (InterruptedException e)
			{
				Thread.currentThread().interrupt();
			}
			this.postInvalidate();
		}
	}

	public void restart(){
		snakeLogic = new SnakeLogic(row, col);
	}
}
