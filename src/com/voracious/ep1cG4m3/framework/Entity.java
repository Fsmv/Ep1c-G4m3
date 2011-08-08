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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
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
	private static Point dimensions;
	private boolean falling;
	
	private boolean WkeyDown = false;
	private boolean AkeyDown = false;
	private boolean DkeyDown = false;
	
	public static ArrayList<Tile> tiles;
	public static final int G = 2;
	public static final int TERM_VEL = 10;
	public static BufferedImage sourceImage;
	
	/**
	 * Initializes animations list, acceleration and speed. Runs the default constructor of Drawable.
	 * 
	 * @see Drawable
	 */
	
	public Entity(int width, int height){
		super(calculateFrames(width, height), true);
		myAnimations = new HashMap<String, Animation>();
		dPoint = new Point(0, 0);
		aPoint = new Point(0, 0);
		dimensions = new Point(width, height);
		falling = false;
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
	
	public void jump(){
		if(!falling){
			setVelocity(getVelocity().getX(), getVelocity().getY()-23);
			falling = true;
		}
	}
	
	/**
	 * Causes the Entity to accelerate, move, and to display the next animation frame.
	 * 
	 * @see Animation
	 */
	
	public void update(){
		accelerate();
		
		Point dim = getDimensions();
		Point loc = getLocation();
		Point vel = getVelocity();
		Point acc = getAccelleration();
		
		if(WkeyDown)
			jump();
		if(AkeyDown)
			setVelocity(-4, vel.getY());
		if(DkeyDown)
			setVelocity(4, vel.getY());
		
		if(!DkeyDown && !AkeyDown)
			setVelocity(0, vel.getY());
		
		vel = getVelocity();
		
		
		
		Rectangle nextFrame = getBounds();
		
		boolean onGround = false;
		
		for(int i=0; i<tiles.size(); i++){
			nextFrame.setLocation((int)(loc.getX() + vel.getX()), (int)(loc.getY()));
			if(tiles.get(i).hitTest(nextFrame)){
				if(vel.getX() > 0)
					setLocation(new Point((int)(tiles.get(i).getLocation().getX()-dim.getX()), (int)(loc.getY())));
				else if(vel.getX() < 0)
					setLocation(new Point((int)(tiles.get(i).getLocation().getX()+TileFactory.TILE_SIZE), (int)(loc.getY())));
				setVelocity(0, vel.getY());
				vel = getVelocity();
				loc = getLocation();
			}
			
			nextFrame.setLocation((int)(loc.getX()), (int)(loc.getY()+vel.getY()));
			if(falling){
				if(tiles.get(i).hitTest(nextFrame)){
					if(vel.getY() > 0){
						setAccelleration(acc.getX(), 0);
						setVelocity(vel.getX(), 0);
						setLocation(new Point((int)(loc.getX()), (int)(tiles.get(i).getLocation().getY()-dim.getY())));
						falling = false;
					}else if(vel.getY() < 0){
						setVelocity(vel.getX(), 0);
						setLocation(new Point((int)(loc.getX()), (int)(tiles.get(i).getLocation().getY()+TileFactory.TILE_SIZE)));
					}
					loc = getLocation();
					vel = getVelocity();
					acc = getAccelleration();
				}
			}
			nextFrame.setLocation((int)(loc.getX()+vel.getX()), (int)(loc.getY()+vel.getY()+1));
			if(tiles.get(i).hitTest(nextFrame)){
				onGround = true;
			}
		}
		
		if(!onGround){
			falling = true;
			setAccelleration(acc.getX(), G);
			acc = getAccelleration();
		}
		
		if(falling && vel.getY() > TERM_VEL){
			setVelocity(vel.getX(), TERM_VEL);
			setAccelleration(acc.getX(), 0);
			vel = getVelocity();
			acc = getAccelleration();
		}
		
		move();
		
		if(currentAnimation != "")
			this.setImage(myAnimations.get(currentAnimation).getNextFrame());
	}
	
	public static void setLevel(ArrayList<Tile> levelTiles){
		tiles = levelTiles;
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
	
	public static void setDimensions(Point dim){
		dimensions = dim;
	}
	
	public static void setAnimationSource(BufferedImage source){
		sourceImage = source;
	}
	
	public static BufferedImage calculateFrames(int width, int height){
		if(sourceImage != null)
			return (sourceImage.getSubimage((0%(sourceImage.getWidth()/(width)))*(width), (0/(sourceImage.getWidth()/(width)))*(width), width, height));
		else{
			System.out.println("null");
			return null;
		}
	}

	public Point getAccelleration(){
		return aPoint;
	}
	
	public void keyPressed(KeyEvent e){
		if(e.getKeyCode() == KeyEvent.VK_W){
			WkeyDown = true;
		}else if(e.getKeyCode() == KeyEvent.VK_A){
			AkeyDown = true;
		}else if(e.getKeyCode() == KeyEvent.VK_D){
			DkeyDown = true;
		}
	}
	
	public void keyReleased(KeyEvent e){
		if(e.getKeyCode() == KeyEvent.VK_W){
			WkeyDown = false;
		}else if(e.getKeyCode() == KeyEvent.VK_A){
			AkeyDown = false;
		}else if(e.getKeyCode() == KeyEvent.VK_D){
			DkeyDown = false;
		}
	}
	
	public Point getVelocity(){
		return dPoint;
	}
	
	public static Point getDimensions(){
		return dimensions;
	}
}
