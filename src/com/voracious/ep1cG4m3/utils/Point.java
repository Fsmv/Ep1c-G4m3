package com.voracious.ep1cG4m3.utils;

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
 * Utility Class for storing and retrieving (x, y) points
 * 
 * @author Voracious Softworks
 * @version 6/20/2011
 */

public class Point {
	private double myX;
	private double myY;
	
	/**
	 * Default Constructor, allows creating it then setting the values later.
	 */
	
	public Point(){
		myX = -1;
		myY = -1;
	}
	
	/**
	 * Constructor to allow setting the values on creation
	 * 
	 * @param x x-coordinate to set as this
	 * @param y y-coordinate to set as this
	 */
	
	public Point(double x, double y){
		myX = x;
		myY = y;
	}
	
	/**
	 * @return Stored x-coordinate
	 */
	
	public double getX(){
		return myX;
	}
	
	/**
	 * @return Stored y-coordinate
	 */
	
	public double getY(){
		return myY;
	}
	
	/**
	 * Changes the stored coordinate values of this to p
	 * 
	 * @param p Point object to set this to
	 */
	
	public void set(Point p){
		myX = p.getX();
		myY = p.getY();
	}
	
	/**
	 * Changes the stored coordinate values of this to (x, y)
	 * 
	 * @param x x-coordinate to switch this to
	 * @param y y-coordinate to switch this to
	 */
	
	public void set(double x, double y){
		myX = x;
		myY = y;
	}
	
	/**
	 * Changes the stored x-coordinate value without changing the y-coordinate value
	 * 
	 * @param x x-coordinate to switch this to
	 */
	
	public void setX(double x){
		myX = x;
	}
	
	/**
	 * Changes the stored y-coordinate value without changing the x-coordinate value
	 * 
	 * @param y y-coordinate to switch this to
	 */
	
	public void setY(double y){
		myY = y;
	}
}
