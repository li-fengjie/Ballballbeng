package cn.game.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.net.URL;

import javax.imageio.ImageIO;

/**
 * 开发中常用工具类
 * @author LiFen
 *
 */

public class GameUtil {
	
	private GameUtil() {} //工具类

	public static Image getImage(String path) {
		URL u = GameUtil.class.getClassLoader().getResource(path);
		BufferedImage img = null;
		try {
			img = ImageIO.read(u);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return img;
	}
}
