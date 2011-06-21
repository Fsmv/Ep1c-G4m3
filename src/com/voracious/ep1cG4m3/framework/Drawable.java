package com.voracious.ep1cG4m3.framework;

/*  
 *  Ep1c G4m3 -- A parody platformer
 * 
 *  Copyright (C) 2011  Voracious Softworks
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.

 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>. 
 */

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import com.voracious.ep1cG4m3.utils.Point;

public class Drawable {
	private BufferedImage myImage;
	private boolean isVisible;
	private Point myLocation;
	
	public Drawable(){
		myImage = (BufferedImage) null;
		isVisible = false;
		myLocation = new Point();
	}
	
	public Drawable(BufferedImage image){
		myImage = image;
		isVisible = false;
		myLocation = new Point();
	}
	
	public Drawable(BufferedImage image, boolean visible){
		myImage = image;
		isVisible = visible;
		myLocation = new Point();
	}
	
	public Drawable(BufferedImage image, boolean visible, Point point){
		myImage = image;
		isVisible = visible;
		myLocation = point;
	}
	
	public void draw(Graphics2D page){
		if(isVisible)
			page.drawImage(myImage, (int)myLocation.getX(), (int)myLocation.getY(), (ImageObserver)null);
	}
	
	public void setImage(BufferedImage image){
		myImage = image;
	}
	
	public void setVisible(boolean visible){
		isVisible = visible;
	}
	
	public BufferedImage getImage(){
		return myImage;
	}
	
	public Point getLocation(){
		return myLocation;
	}
	
	public boolean isVisible(){
		return isVisible;
	}
}
