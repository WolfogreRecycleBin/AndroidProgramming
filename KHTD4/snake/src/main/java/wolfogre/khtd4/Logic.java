package wolfogre.khtd4;

import android.graphics.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Jason Song(wolfogre@outlook.com) on 12/25/2015.
 */
public class Logic {
	int row;
	int col;
	Point food;
	List snack;
	Random random;

	public Logic(int row, int col){
		this.row = row;
		this.col = col;
		random = new Random();
		food = new Point(random.nextInt(row), random.nextInt(col));
		snack = new ArrayList();
		snack.add(new Point(row / 2, col / 2));
		snack.add(new Point(row / 2 + 1, col / 2));
		snack.add(new Point(row / 2 + 2, col / 2));
		snack.add(new Point(row / 2 + 3, col / 2));
		snack.add(new Point(row / 2 + 4, col / 2));
	}

	public int[][] toMatrix(){
		int[][] mat = new int [row][col];
		mat[food.y][food.x] = 3;
		for(Object o : snack){
			Point p = (Point)o;
			mat[p.y][p.x] = 1;
		}
		return mat;
	}
}
