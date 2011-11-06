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

package com.voracious.ep1cG4m3.entities;

import java.awt.Point;
import java.io.File;
import java.util.ArrayList;

import com.voracious.ep1cG4m3.framework.Drawable;
import com.voracious.ep1cG4m3.framework.Entity;

/**
 * Represents the player. A user should be able to control the actions of this object.
 * 
 * @author Voracious Softworks
 */

public class Player extends Entity {
	private static final long serialVersionUID = 7613641952651564431L;

	public static final int BULLET_SPEED = 15;
	
	private Drawable gun;
	private ArrayList<Entity> bullets;
	private double gunRotation;
	
	/**
	 * Initialize a new player object
	 */
	
	public Player(){
		super(new File("entities/player/"));
		this.setAnimation("standing");
		gun = new Drawable(this.getResource("gun"));
		bullets = new ArrayList<Entity>();
	}
	
	/**
	 * Rotate the player's gun
	 * 
	 * @param location point the rotate to face. (Usually the mouse pointer location)
	 */
	
	public void pointAt(Point.Double location){
		Point.Double axis = new Point.Double(10.0, (double) gun.getIconHeight()/2);
		double rads = Math.atan((location.y-axis.y)/(location.x-axis.x));
		setGunRotation(rads);
		gun.rotate(rads, axis);
	}
	
	/**
	 * Cause the player to fire a bullet
	 */
	
	public void shoot(){
		Entity temp = new Entity(this.getResource("bullet"));
		temp.setVelocity(new Point.Double(BULLET_SPEED*Math.cos(getGunRotation()), BULLET_SPEED*Math.sin(getGunRotation())));
		bullets.add(temp);
	}
	
	/**
	 * @return the gunRotation
	 */
	
	public double getGunRotation() {
		return gunRotation;
	}

	/**
	 * @param gunRotation the gunRotation to set
	 */
	
	private void setGunRotation(double gunRotation) {
		this.gunRotation = gunRotation;
	}
}
