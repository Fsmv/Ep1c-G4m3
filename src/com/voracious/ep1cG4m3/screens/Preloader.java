package com.voracious.ep1cG4m3.screens;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import com.voracious.ep1cG4m3.framework.Screen;
import com.voracious.ep1cG4m3.utils.ScreenResultEvent;

public class Preloader extends Screen {
	private static final long serialVersionUID = 1333992802820959184L;
	private MediaTracker tracker;
	private Image bg;

	public Preloader(int id, ScreenResultEvent listener) {
		super(id, listener);
		tracker = new MediaTracker(this);
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
		Image bg = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/loadImage.png"));
		tracker.addImage(bg, -1);
	}

	@Override
	public void stop() {
		
	}
	
	@Override
	public void paint(Graphics g){
		try{
			tracker.waitForID(-1);
			g.drawImage(bg, 0, 0, null);
		}catch (InterruptedException e) { e.printStackTrace();}
	}
}
