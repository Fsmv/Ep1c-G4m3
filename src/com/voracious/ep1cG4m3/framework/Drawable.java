package com.voracious.ep1cG4m3.framework;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.ImageObserver;

import com.voracious.ep1cG4m3.utils.Location;

public class Drawable {
	private Image myImage;
	private boolean isVisible;
	private Location myLocation;
	
	public Drawable(){
		myImage = (Image) null;
		isVisible = false;
		myLocation = new Location();
	}
	
	public Drawable(Image image){
		myImage = image;
		isVisible = false;
		myLocation = new Location();
	}
	
	public Drawable(Image image, boolean visible){
		myImage = image;
		isVisible = visible;
		myLocation = new Location();
	}
	
	public Drawable(Image image, boolean visible, Location location){
		myImage = image;
		isVisible = visible;
		myLocation = location;
	}
	
	public void draw(Graphics2D page){
		if(isVisible)
			page.drawImage(myImage, (int)myLocation.getX(), (int)myLocation.getY(), (ImageObserver)null);
	}
	
	public void setImage(Image image){
		myImage = image;
	}
	
	public void setVisible(boolean visible){
		isVisible = visible;
	}
	
	public Image getImage(){
		return myImage;
	}
	
	public Location getLocation(){
		return myLocation;
	}
	
	public boolean isVisible(){
		return isVisible;
	}
}
