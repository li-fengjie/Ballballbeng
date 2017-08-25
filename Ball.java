package cn.game.ball;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;

import cn.game.util.Constant;

public class Ball {
	private double x, y;
	private double degree = Math.PI / 4;
	private boolean live = true;
	private int speed;

	
	public Ball() {
	}
	
	
	public Rectangle getRect() {
		return new Rectangle((int)x,(int)y,Constant.ballWidth,Constant.ballHeight);
		
	}
	
	public void draw(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.pink);
		g.fillOval((int) x, (int) y, Constant.ballHeight, Constant.ballWidth);
		
		x -= speed * Math.cos(degree);
		y -= speed * Math.sin(degree);
	
		if(y < Constant.ballHeight) {
			degree = -degree;
		}
		if(x < 0 || x > Constant.GAME_WIDTH - Constant.ballWidth) {
			degree = Math.PI - degree;
		}
		if(y > Constant.GAME_HEIGHT - Constant.ballHeight) {
			live = false;
		}
		g.setColor(c);
	}
	
	public double getDegree() {
		return degree;
	}

	public void setDegree(double degree) {
		this.degree = degree;
	}

	public boolean isLive() {
		return live;
	}

	public void setLive(boolean live) {
		this.live = live;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
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
