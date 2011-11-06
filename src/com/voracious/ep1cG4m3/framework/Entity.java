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

package com.voracious.ep1cG4m3.framework;

import java.util.HashMap;
import java.util.Map;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;

import com.voracious.ep1cG4m3.utils.Animation;

/**
 * Like Drawable except this class has methods to facilitate animation and motion.
 * 
 * @author Voracious Softworks
 * @version 6/20/2011
 * @see Drawable
 */

public class Entity extends Drawable {
	private static final long serialVersionUID = -1211928635812350349L;
	private Map<String, Animation> myAnimations;
	private String currentAnimation;
	private Point.Double velocity;
	private Point.Double acceleration;

	private static BufferedImage sourceImage;
	
	/**
	 * Initializes animations list, acceleration and speed. Runs the default constructor of Drawable.
	 * 
	 * @see Drawable
	 */
	
	public Entity(){
		super();
		myAnimations = new HashMap<String, Animation>();
		velocity = new Point.Double(0, 0);
		acceleration = new Point.Double(0, 0);
		currentAnimation = "";
	}
	
	/**
	 * Initializes Entity with a width and height used to run calculateFrames
	 * 
	 * @param width image width
	 * @param height image height
	 */
	
	public Entity(int width, int height){
		super();
		this.setImage(calculateFrames(width, height));
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
	 * Causes the Entity to accelerate, move, and to display the next animation frame.
	 * 
	 * @see Animation
	 * @param dt delta time, Amount of time elapsed since last update in milliseconds
	 */
	
	public void update(long dt){
		Point loc = getLocation();
		Point.Double vel = getVelocity();
		Point.Double acc = getAcceleration();
		
		vel.setLocation(vel.getX()+(acc.getX()*dt), vel.getY()+(acc.getY()*dt));
		loc.setLocation(loc.getX()+(vel.getX()*dt), loc.getY()+(vel.getY()*dt));
		
		this.setLocation(loc);
		this.setVelocity(vel);
		
		if(getCurrentAnimation() != null && getCurrentAnimation() != ""){
			this.setImage(getAnimations().get(getCurrentAnimation()).getImage());
		}
	}
	
	/**
	 * @param image the image to set
	 */
	
	public static void setAnimationSource(Image image){
		sourceImage = (BufferedImage) image;
	}
	
	/**
	 * Cuts the animation frames out of the source image.
	 * 
	 * @param width image width
	 * @param height image height
	 * @return The first frame
	 */
	
	public static BufferedImage calculateFrames(int width, int height){
		if(sourceImage != null)
			//TODO: Put a for loop here so this calculates all the frames and returns an array
			return (sourceImage.getSubimage((0%(sourceImage.getWidth()/(width)))*(width), (0/(sourceImage.getWidth()/(width)))*(width), width, height));
		else{
			System.out.println("null");
			return null;
		}
	}
	
	/**
	 * @param velocity velocity to set
	 */
	
	public void setVelocity(Point.Double velocity){
		this.velocity = velocity;
	}
	
	/** 
	 * @return velocity
	 */
	
	public Point.Double getVelocity(){
		return velocity;
	}
	
	/**
	 * @param acceleration acceleration to set
	 */
	
	public void setAcceleration(Point.Double acceleration){
		this.acceleration = acceleration;
	}
	
	/**
	 * @return acceleration
	 */
	
	public Point.Double getAcceleration(){
		return acceleration;
	}
	
	/**
	 * Rotates the image by [rads] radians
	 * 
	 * @param rads radians to rotate to
	 * @param p point to rotate around
	 */
	
	public void rotate(double rads, Point p){
		//TODO: Write method
	}
}