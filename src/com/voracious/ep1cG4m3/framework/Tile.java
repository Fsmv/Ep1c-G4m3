package com.voracious.ep1cG4m3.framework;

public class Tile extends Drawable {
	
	public static final String IMAGE_RESOURCE = "tiles.png";
	
	public static final int TILE_SIZE = 25;
	
	public static Map types;
	private boolean isTangible;
	private String myType;
	private int myId;
	public Tile(String type, int tileID){
		
	}
	
	public Tile(String type, int tileID, Location location){
		
	}
	
	public Tile(String type, Location location, boolean tangible){
		
	}
	
	public void setTangible(boolean tangible){
		
	}
	
	public String getType(){
	return myType;	
	}
	
	public int getId(){
	return myId;	
	}
	}

}
