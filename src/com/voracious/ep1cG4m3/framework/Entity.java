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

import java.util.HashMap;
import java.util.Map;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

import com.voracious.ep1cG4m3.utils.Animation;

/**
 * Framework class that creates the general model for the enemies and characters.
 * 
 * @author Voracious Softworks
 * @version 6/20/2011
 * @see Drawable
 */

public class Entity extends Drawable {
	private Map<String, Animation> myAnimations;
	private String currentAnimation;
	private Point dPoint;
	private Point aPoint;
	
	public static final int WIDTH = 25;
	public static final int HEIGHT = 50;
	public static BufferedImage sourceImage;
	
	/**
	 * Initializes animations list, acceleration and speed. Runs the default constructor of Drawable.
	 * 
	 * @see Drawable
	 */
	
	public Entity(){
		super(calculateFrames(), true);
		myAnimations = new HashMap<String, Animation>();
		dPoint = new Point(0, 0);
		aPoint = new Point(0, 0);
		currentAnimation = "";
	}
	
	/**
	 * Runs update() before drawing.
	 * 
	 * @see Drawable
	 */
	
	@Override
	public void draw(Graphics2D p){
		update();
		super.draw(p);
	}
	
	/**
	 * Adds an animation to the list of animations for this entity.
	 * 
	 * @param animationName name to identify the animation with
	 * @param animation animation object to be added
	 * @see Animation
	 */
	
	public void addAnimation(String animationName, Animation animation){
		myAnimations.put(animationName, animation);
	}
	
	/**
	 * Change the currently playing animation.
	 * 
	 * @param name animation name to change to
	 * @see Animation
	 */
	
	public void setAnimation(String name){
		currentAnimation = name;
	}
	
	/**
	 * Supplies the list of animations.
	 * 
	 * @return list of animations
	 */
	
	public Map<String, Animation> getAnimations(){
		return myAnimations;
	}
	
	/**
	 * Supplies the current animation.
	 * 
	 * @return current animation name
	 * @see Animation
	 */
	
	public String getCurrentAnimation(){
		return currentAnimation;
	}
	
	/**
	 * Changes the speed of the Entity based on the acceleration.
	 */
	
	private void accelerate(){
		dPoint.setLocation(dPoint.getX()+aPoint.getX(), dPoint.getY()+aPoint.getY());	
	}
	
	/**
	 * Changes the location of the entity based on the speed.
	 */
	
	private void move(){
		Point pos = this.getLocation();
		pos.setLocation(pos.getX()+dPoint.getX(), pos.getY()+dPoint.getY());
	}
	
	/**
	 * Causes the Entity to accelerate, move, and to display the next animation frame.
	 * 
	 * @see Animation
	 */
	
	public void update(){
		accelerate();
		move();
		if(currentAnimation != "")
			this.setImage(myAnimations.get(currentAnimation).getNextFrame());
	}
	
	/**
	 * Allows setting the acceleration.
	 * 
	 * @param accX x component of acceleration
	 * @param accY y component of acceleration
	 */
	
	public void setAccelleration(double accX, double accY){
		aPoint.setLocation(accX, accY);
	}
	
	/**
	 * Allows setting the speed.
	 * 
	 * @param dx delta x, the x component of velocity
	 * @param dy delta y, the y component of velocity
	 */
	
	public void setVelocity(double dx, double dy){
		dPoint.setLocation(dx, dy);
	}
	
	public static void setAnimationSource(BufferedImage source){
		sourceImage = source;
	}
	
	public static BufferedImage calculateFrames(){
		if(sourceImage != null)
			return (sourceImage.getSubimage((0%(sourceImage.getWidth()/(WIDTH)))*(WIDTH), (0/(sourceImage.getWidth()/(WIDTH)))*(WIDTH), (WIDTH), (HEIGHT)));
		else{
			System.out.println("null");
			return null;
		}
	}

	public Point getAccelleration(){
		return aPoint;
	}
	
	public Point getVelocity(){
		return dPoint;
	}
}
