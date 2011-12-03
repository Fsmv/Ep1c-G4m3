package com.voracious.ep1cG4m3.framework;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Level {
	private Tile[][] tiles;
	private String mapName;
	private ArrayList<Entity> entities;
	
	public int getWidth(){
		return tiles.length;
	}
	
	public int getHeight(){
		return tiles[0].length;
	}
	
	public Tile getTile(Point location){
		if(location.x >= 0 && location.x < getWidth() && location.y >= 0 && location.y < getHeight()){
			return tiles[location.x][location.y];
		}else{
			return null;
		}
	}
	
	public void loadMap(BufferedImage mapFile){
		tiles = new Tile[mapFile.getWidth()][mapFile.getHeight()];
		entities = new ArrayList<Entity>();
		
		for(int x=0; x<mapFile.getWidth(); x++){
			for(int y=0; y<mapFile.getHeight(); y++){
				int color = mapFile.getRGB(x, y);
				if((0x0000ff & color) > 0)
					entities.add(getEntity(color));
				else
					tiles[x][y] = getTile(color);
			}
		}
	}
	
	public Tile getTile(int color){
		if(color == 0xffffff) return new Tile(); //Air
		//else if(color == 0xffff00) return new Brick(); 
		else return null;
	}
	
	public Entity getEntity(int color){
		return null;
	}
}
