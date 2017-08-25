package cn.game.ball;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import cn.game.util.Constant;

public class Block {
	
	private Point allocation;
	private Point location[];
	private Point size;
	private boolean exist[];
	
	public Block() {
		allocation = new Point(4,5);
		size = new Point(52,25);
		location = new Point[Constant.num];
		for(int i = 0; i < allocation.y; i++) {
			for(int j = 0; j < allocation.x; j++) {
				location[i * allocation.x + j] = new Point((Constant.GAME_HEIGHT / allocation.x - size.x) / 4 + j * (Constant.GAME_WIDTH / allocation.x), (i * 2 + 5) * size.y);
//				System.out.println(location[i * allocation.x + j]);
			}
		}
	
		exist = new boolean[Constant.num];
		for(int i = 0; i < Constant.num; i++) {
			exist[i] = true;
		}
	}
	
	public Rectangle getRect(int x, int y) {
		return new Rectangle(x, y,Constant.blockWidth,Constant.blockHeight);
		
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.gray);
		for(int i = 0; i < Constant.num; i++) {
			if(exist[i] == true) {
				g.fillRect(location[i].x, location[i].y, size.x, size.y);
			}
		}
	}

	public boolean[] getExist() {
		return exist;
	}

	public void setExist(int index, boolean boo) {
		exist[index] = boo;
	}

	public Point[] getLocation() {
		return location;
	}

	public void setLocation(Point[] location) {
		this.location = location;
	}
	
}
