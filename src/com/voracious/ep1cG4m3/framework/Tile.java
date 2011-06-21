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

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.voracious.ep1cG4m3.utils.Point;

public class Tile extends Drawable {
	
	public static final String IMAGE_RESOURCE = "tiles.png";
	
	public static final int TILE_SIZE = 25;
	
	public static Map<String, Integer> types = new HashMap<String, Integer>();
	private boolean isTangible;
	private String myType;
	private int myId;
	
	public Tile(String type, int tileId){
		super(calculateImage(tileId), true);
		types.put(type, new Integer(tileId));
		isTangible = false;
		myType = type;
		myId = tileId;
	}
	
	public Tile(String type, int tileId, Point point){
		super(calculateImage(tileId), true, point);
		types.put(type, new Integer(tileId));
		isTangible = false;
		myType = type;
		myId = tileId;
	}
	
	public Tile(String type, int tileId, Point point, boolean tangible){
		super(calculateImage(tileId), true, point);
		types.put(type, new Integer(tileId));
		isTangible = tangible;
		myType = type;
		myId = tileId;
	}
	
	private static BufferedImage calculateImage(int id){
		try {
			BufferedImage img = ImageIO.read(new File("/res/", IMAGE_RESOURCE));
			img = img.getSubimage(img.getWidth()*(id*TILE_SIZE), img.getHeight()*(id*TILE_SIZE), TILE_SIZE, TILE_SIZE);
			return img;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//TODO: Make hitTest actually test if the entity is hitting the block.
	public boolean hitTest(Entity entity){
		return isTangible();
	}
	
	public void setTangible(boolean tangible){
		isTangible = tangible;
	}
	
	
	public boolean isTangible(){
		return isTangible;
	}
	
	public String getType(){
		return myType;	
	}
	
	public int getId(){
		return myId;	
	}
}
