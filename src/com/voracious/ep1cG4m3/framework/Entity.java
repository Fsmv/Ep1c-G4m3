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

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.voracious.ep1cG4m3.utils.Animation;
import com.voracious.ep1cG4m3.utils.Art;

/**
 * Like Drawable except this class has methods to facilitate animation and motion.
 * 
 * @author Voracious Softworks
 * @version 6/20/2011
 * @see Drawable
 */

public class Entity extends Drawable {
	private static final long serialVersionUID = -1211928635812350349L;
	private Map<String, Animation> animations;
	private String currentAnimation;
	private Point.Double velocity;
	private Point.Double acceleration;

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

	public Entity(File resourceFolder){
		super();
		myAnimations = loadAnimations(resourceFolder);
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
	 * Cuts the animation frames out of the source image.
	 * 
	 * @param width image width
	 * @param height image height
	 * @return The first frame
	 */

	private HashMap<String, Animation> loadAnimations(File resourceFolder){
		if(resourceFolder.canRead() && resourceFolder.isDirectory()){
			BufferedImage frames = Art.loadImage(resourceFolder.getPath() + "/" + resourceFolder.getName() + ".png");

			int col = frames.getRGB(0, 0);
			System.out.println("width:  " + (col & 0xff0000)/0xffff);
			System.out.println("height: " + (col & 0x00ff00)/0xff);

			int frameSet = 1;
			for(int xx=1; xx<frames.getWidth(); xx++){
				int color = frames.getRGB(xx, 0) ^ 0xff000000;

				if(color == 0xffffff)
					break;

				int a = (color & 0xff0000)/0xffff;
				int b = (color & 0x00ff00)/0xff;
				int c = (color & 0x0000ff);

				if(xx%5 == 0){
					System.out.println("Number of frames: " + color); 
				}else if(xx%5 == 1){
					System.out.println("Frame set:        " + frameSet);
					String result = "";
					for(int i=0; i<4; i++){
						color = frames.getRGB(xx+i, 0) - 0xff000000;
						a = (color & 0xff0000)/0xffff;
						b = (color & 0x00ff00)/0xff;
						c = (color & 0x0000ff);

						if(a == 0){
							break;
						}else if(b == 0){
							result += Integer.toHexString(a);
							break;
						}else if(c == 0){
							result += Integer.toHexString((a*0x100)+b);
							break;
						}else{
							result += Integer.toHexString(color);
						}
					}
					xx += 3;
					frameSet++;
					System.out.println("Name:             " + hexToString(result));
				}
			}
			if(new File(resourceFolder.getPath(), "rsc.png").exists()){
				BufferedImage rsc = Art.loadImage(resourceFolder.getPath() + "/rsc.png");
			}
		}else{
			throw new IllegalArgumentException("resourceFolder is not a directory. Path = " + resourceFolder.getAbsolutePath());
		}
	}

	public static String hexToString(String hex){
		String result = "";
		for(int i=0; i<hex.length()-1; i+=2 ){
			String substr = hex.substring(i, (i + 2));
			int decimal = Integer.parseInt(substr, 16);
			result += (char)decimal;
		}
		return result;
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