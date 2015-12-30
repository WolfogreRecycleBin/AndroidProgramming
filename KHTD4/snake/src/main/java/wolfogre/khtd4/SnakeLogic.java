package wolfogre.khtd4;

import android.graphics.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Jason Song(wolfogre@outlook.com) on 12/25/2015.
 */
public class SnakeLogic {
	int raw;
	int col;
	Point food;
	List snack;
	Random random;
	public enum Direction{UP, DOWN, LEFT, RITHT}
	Direction direction;
	boolean isAlive;

	public SnakeLogic(int raw, int col){
		this.raw = raw;
		this.col = col;
		random = new Random();
		food = new Point(random.nextInt(raw), random.nextInt(col));
		snack = new ArrayList();
		snack.add(new Point(raw / 2, col / 2));
		direction = Direction.UP;
		isAlive = true;
	}

	public int[][] toMatrix(){
		int[][] mat = new int [raw][col];
		mat[food.y][food.x] = 3;
		for(Object o : snack){
			Point p = (Point)o;
			mat[p.y][p.x] = 1;
		}
		return mat;
	}

	public boolean move(){
		if(!isAlive)
			return false;
		switch (direction){
			case UP:
				if(((Point)snack.get(0)).y - 1 < 0)
					return isAlive = false;
				snack.add(0,new Point(((Point)snack.get(0)).x, ((Point)snack.get(0)).y - 1));
				break;
			case DOWN:
				if(((Point)snack.get(0)).y + 1 > raw - 1)
					return isAlive = false;
				snack.add(0,new Point(((Point)snack.get(0)).x, ((Point)snack.get(0)).y + 1));
				break;
			case LEFT:
				if(((Point)snack.get(0)).x - 1 < 0)
					return isAlive = false;
				snack.add(0,new Point(((Point)snack.get(0)).x - 1, ((Point)snack.get(0)).y));
				break;
			case RITHT:
				if(((Point)snack.get(0)).x + 1 > col - 1)
					return isAlive = false;
				snack.add(0,new Point(((Point)snack.get(0)).x + 1, ((Point)snack.get(0)).y));
				break;
		}
		snack.remove(snack.size() - 1);
		return true;
	}

	public void setDirection(Direction direction){
		this.direction = direction;
	}
}
