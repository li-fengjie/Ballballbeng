package cn.game.ball;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Date;

import cn.game.util.Constant;
import cn.game.util.GameUtil;
import cn.game.util.MyFrame;

public class GameFrame extends MyFrame{
	Image bg = GameUtil.getImage("images/bg.jpg");
	Ball ba;
	Block bl = new Block();
	Board b = new Board("images/board.png",Constant.GAME_WIDTH - Constant.boardWidth,
			Constant.GAME_HEIGHT - Constant.boardHeight);
	boolean start = false;
	Date startTime, endTime, nowTime;
	
	public void paint(Graphics g) {
		
		g.drawImage(bg, 0, 0, null);
		b.draw(g);
		bl.draw(g);
		if(ba.isLive()) {
			if(!start) {
				ba.setX(b.getX() + Constant.boardWidth / 2);
				ba.setY(b.getY() - Constant.boardHeight);
			}
			ba.draw(g);
			boolean over = b.getRect().intersects(ba.getRect());
			if(over) {
				ba.setDegree(-1 * ba.getDegree());
			}
			for(int i = 0; i < Constant.num; i++) {
				boolean peng = bl.getRect(bl.getLocation()[i].x, bl.getLocation()[i].y).intersects(ba.getRect());
				if(bl.getExist()[i] == true && peng) {
					bl.setExist(i, false);
					ba.setDegree(-1 * ba.getDegree());
				}
			}
			boolean flag = false;
			for(int i = 0; i < Constant.num; i++) {
				if(bl.getExist()[i] == true) {
					flag = true;
				}
			}
			if(!flag) {
				endTime = new Date();
				start = false;
				ba.setLive(false);
//				Color c = g.getColor();
//				g.setColor(Color.red);
//				g.setFont(new Font("宋体",Font.BOLD,8));
//				g.drawString("恭喜你赢了！",5,260);
//				g.setColor(c);
			}
		}
		if(!ba.isLive() && endTime == null) {
			endTime = new Date();
			start = false;
		}
		if(start && endTime == null) {
			Color c = g.getColor();
			g.setColor(Color.pink);
			g.setFont(new Font("宋体",Font.BOLD,30));
			nowTime = new Date();
			int timeCost = (int)((nowTime.getTime()-startTime.getTime())/1000);
			g.drawString("时间：" + timeCost + "秒",290,80);
			g.setColor(c);
		}
		else if(endTime != null){
			Color c = g.getColor();
			g.setColor(Color.red);
			g.setFont(new Font("宋体",Font.BOLD,30));
			int timeCost = (int)((endTime.getTime()-startTime.getTime())/1000);
			g.drawString("时间：" + timeCost + "秒",290,80);
			g.setColor(c);
		}
		
		if(!start) {
			Color c = g.getColor();
			g.setColor(Color.pink);
			g.setFont(new Font("楷体",Font.BOLD,30));
			g.drawString("Ctrl + tab键  重新一局",60,109);
			g.drawString("按方向 上 键  加速",60,400);
			g.drawString("按方向 下 键  减速",60,450);
			g.drawString("按方向 左 键  左移",60,500);
			g.drawString("按方向 右 键  右移",60,550);
			g.drawString("按   空格键  开始",60,630);
			g.setColor(c);
		}
	}
	
	public static void main(String[] args) {
		new GameFrame().launchFrame();
	}
	
	public void launchFrame() {
		super.launchFrame();
		setTitle("Ballballbeng");
		addKeyListener(new KeyMonitor());
		
		ba = new Ball();
	}
	
	class KeyMonitor extends KeyAdapter{

		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_SPACE) {
				ba.setSpeed(Constant.intermediateBallSpeed);
				start = true;
				if(startTime == null)
					startTime = new Date();
			}
			if(e.getKeyCode() == 38) {
				ba.setSpeed(Constant.advancedBallSpeed);
			}
			if(e.getKeyCode() == 40) {
				ba.setSpeed(Constant.primaryBallSpeed);
			}
			if(e.getKeyCode() == KeyEvent.VK_CONTROL) {
				setVisible(false);
				new GameFrame().launchFrame();
			}
			b.addDirection(e);

		}
		
		public void keyReleased(KeyEvent e) {
			b.minusDirection(e);
		}
	}
}
