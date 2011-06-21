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
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>. 
 */

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import com.voracious.ep1cG4m3.utils.Point;

/**
 * Super class all classes that will be drawn must extend.
 * 
 * @author Voracious Softworks
 */

public class Drawable {
	private BufferedImage myImage;
	private boolean isVisible;
	private Point myLocation;
	
	/**
	 * Default Constructor, allows creating it then setting the values later.
	 */
	
	public Drawable(){
		myImage = (BufferedImage) null;
		isVisible = false;
		myLocation = new Point();
	}
	
	/**
	 * Constructor to initialize the image. It defaults to invisible and the location defaults to (-1, -1).
	 * 
	 * @param image Display image for the class
	 */
	
	public Drawable(BufferedImage image){
		myImage = image;
		isVisible = false;
		myLocation = new Point();
	}
	
	/**
	 * Allows initialization of both image and visibility. Location defaults to (-1, -1).
	 * 
	 * @param image Display image for the class
	 * @param visible Whether the image should be visible or not 
	 */
	
	public Drawable(BufferedImage image, boolean visible){
		myImage = image;
		isVisible = visible;
		myLocation = new Point();
	}
	
	/**
	 * Allows initialization of image, visibility and location.
	 * 
	 * @param image Display image for the class
	 * @param visible Whether the image should be visible or not
	 * @param point location
	 */
	
	public Drawable(BufferedImage image, boolean visible, Point point){
		myImage = image;
		isVisible = visible;
		myLocation = point;
	}
	
	/**
	 * If the image is set to be visible draw it to the screen
	 * 
	 * @param page Object to be drawn to
	 */
	
	public void draw(Graphics2D page){
		if(isVisible)
			page.drawImage(myImage, (int)myLocation.getX(), (int)myLocation.getY(), (ImageObserver)null);
	}
	
	/**
	 * Set the image after initialization
	 * 
	 * @param image Display image for the class
	 */
	
	public void setImage(BufferedImage image){
		myImage = image;
	}
	
	/**
	 * Set the image visibility after initialization
	 * 
	 * @param visible Whether the image should be visible or not
	 */
	
	public void setVisible(boolean visible){
		isVisible = visible;
	}
	
	/**
	 * Supplies the image that it is set to display
	 * 
	 * @return Current image.
	 */
	
	public BufferedImage getImage(){
		return myImage;
	}
	
	/**
	 * Supplies the location the image will be displayed at.
	 * 
	 * @return Current location.
	 */
	
	public Point getLocation(){
		return myLocation;
	}
	
	/**
	 * Supplies the visibility status.
	 * 
	 * @return visibility
	 */
	
	public boolean isVisible(){
		return isVisible;
	}
}
