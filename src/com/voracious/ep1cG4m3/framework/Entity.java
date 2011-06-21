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

/**
 * Framework class that creates the general model for the enemies and characters.
 * 
 * @author Voracious Softworks
 * @version 6/20/2011
 */

import java.util.HashMap;
import java.util.Map;
import java.awt.image.BufferedImage;
import com.voracious.ep1cG4m3.utils.Point;
import com.voracious.ep1cG4m3.utils.Animation;

public class Entity extends Drawable {
	private Map<String, Animation> myAnimations;
	private String currentAnimation;
	private Point dPoint;
	private Point aPoint;
	
	public Entity(){
		super();
		myAnimations = new HashMap<String, Animation>();
		dPoint = new Point(0, 0);
		aPoint = new Point(0, 0);
		currentAnimation = "";
	}
	
	public void addAnimation(String animationName, Animation animation){
		myAnimations.put(animationName, animation);
	}
	
	public void setAnimation(String name){
		currentAnimation = name;
	}
	
	public Map<String, Animation> getAnimations(){
		return myAnimations;
	}
	
	public String getCurrentAnimation(){
		return currentAnimation;
	}
	
	@Override
	public void setImage(BufferedImage image){
		super.setImage(image);
	}
	
	public void accelerate(){
		dPoint.set(dPoint.getX()+aPoint.getX(),dPoint.getY()+aPoint.getY());	
	}
	
	public void move(){
		Point pos = this.getLocation();
		pos.set(pos.getX()+dPoint.getX(),pos.getY()+dPoint.getY());
	}
	
	public void update(){
		accelerate();
		move();
	}
	
	public void setAccelleration(double accX, double accY){
		dPoint.set(accX,accY);
	}
	
	public void setVelocity(double dx, double dy){
		dPoint.set(dx,dy);
	}
	
}
