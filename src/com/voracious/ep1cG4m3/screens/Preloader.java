package com.voracious.ep1cG4m3.screens;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import com.voracious.ep1cG4m3.framework.Drawable;
import com.voracious.ep1cG4m3.framework.Screen;
import com.voracious.ep1cG4m3.utils.Point;
import com.voracious.ep1cG4m3.utils.ScreenResultEvent;

public class Preloader extends Screen {
	private static final long serialVersionUID = 1333992802820959184L;
	private MediaTracker tracker;
	private Drawable bg;

	public Preloader(int id, ScreenResultEvent listener) {
		super(id, listener);
		tracker = new MediaTracker(this);
		bg = new Drawable();
		String file[] = {"/loadImage.png"};
		BufferedImage temp = loadImages(file)[0];
		bg.setImage(temp);
		bg.setVisible(true);
		bg.setLocation(new Point(0, 0));
	}
	
	public BufferedImage[] loadImages(String[] filePaths){
		BufferedImage result[] = new BufferedImage[filePaths.length];
		
		for(int i=0; i<filePaths.length; i++){
			Image temp = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource(filePaths[i]));
			tracker.addImage(temp, i);
	        try {
	            tracker.waitForID(i);
	            result[i] = new BufferedImage(temp.getWidth(null), temp.getHeight(null), BufferedImage.TYPE_INT_ARGB);
	            result[i].getGraphics().drawImage(temp, 0, 0, null);
	        } catch (InterruptedException e) { e.printStackTrace(); }
		}
		
		return result;
	}

	@Override
	public void start() {
	}

	@Override
	public void stop() {
		
	}
	
	@Override
	public void paint(Graphics g){
		Graphics2D g2 = (Graphics2D)g;
		bg.draw(g2);
		
		Toolkit.getDefaultToolkit().sync();
		g.dispose();
	}
}
