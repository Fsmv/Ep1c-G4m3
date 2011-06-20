package com.voracious.ep1cG4m3.utils;

public class Location {
	private double myX;
	private double myY;
	
	public Location(){
		myX = -1;
		myY = -1;
	}
	
	public Location(double x, double y){
		myX = x;
		myY = y;
	}
	
	public double getX(){
		return myX;
	}
	
	public double getY(){
		return myY;
	}
	
	public void set(Location l){
		myX = l.getX();
		myY = l.getY();
	}
	
	public void set(double x, double y){
		myX = x;
		myY = y;
	}
	
	public void setX(double x){
		myX = x;
	}
	
	public void setY(double y){
		myY = y;
	}
}
