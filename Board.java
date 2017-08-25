package cn.game.ball;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import cn.game.util.Constant;
import cn.game.util.GameUtil;

public class Board {
	Image img;
	private double x, y;
	private boolean left, right;
	
	public void draw(Graphics g) {
		g.drawImage(img, (int) x, (int) y, null);
		move();
	}
	
	public Rectangle getRect() {
		return new Rectangle((int)x,(int)y,Constant.boardWidth,Constant.boardHeight);
		
	}
	
	public void move() {
		if (left && x > 0) {
			x -= Constant.boardSpeed;
			
		}
		if (right && x < Constant.GAME_WIDTH - Constant.boardWidth) {
			x += Constant.boardSpeed;
		}
	}
	
	public void addDirection(KeyEvent e) {
		switch (e.getKeyCode()) {
		case 37:
			left = true;
			break;
		case 39:
			right = true;
			break;
		default:
			break;
		}
	}
	public void minusDirection(KeyEvent e) {
		switch (e.getKeyCode()) {
		case 37:
			left = false;
			break;
		case 39:
			right = false;
			break;
		default:
			break;
		}
	}
	
	public Board(String path,double x, double y) {
		super();
		this.img = GameUtil.getImage(path);
		this.x = x;
		this.y = y;
	}
	
	public Board() {
		
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	
}
