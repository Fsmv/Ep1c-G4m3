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

/**
 * Super class all Tile types must extend.
 * 
 * @author Voracious Softworks
 * @version 6/20/2011
 * @see Drawable
 */

public class Tile extends Drawable {
	
	public static final String IMAGE_RESOURCE = "tiles.png";
	public static final int TILE_SIZE = 25;
	
	public static Map<String, Integer> types;
	private boolean isTangible;
	private String myType;
	private int myId;
	
	/**
	 * Adds the type to the global list of tile types. Initializes tile properties.
	 * 
	 * @param type Name for the tile type
	 * @param tileId Incremental number for the tile
	 */
	
	public Tile(String type, int tileId){
		super(calculateImage(tileId), true);
		if(!types.containsKey(type))
			types.put(type, new Integer(tileId));
		isTangible = false;
		myType = type;
		myId = tileId;
		types = new HashMap<String, Integer>();
	}
	
	/**
	 * Adds the type to the global list of tile types and sets location. Initializes tile properties.
	 * 
	 * @param type Name for the tile type
	 * @param tileId Incremental number for the tile
	 * @param point Location for the tile
	 * @see Point
	 */
	
	public Tile(String type, int tileId, Point point){
		super(calculateImage(tileId), true, point);
		types.put(type, new Integer(tileId));
		isTangible = false;
		myType = type;
		myId = tileId;
		types = new HashMap<String, Integer>();
	}
	
	/**
	 * Adds the type to the global list of tile types, sets location and sets tangibility. 
	 * 
	 * @param type Name for the tile type
	 * @param tileId Incremental number for the tile
	 * @param point Location for the tile 
	 * @param tangible Whether entities can interact with this tile or not
	 * @see Point
	 */
	
	public Tile(String type, int tileId, Point point, boolean tangible){
		super(calculateImage(tileId), true, point);
		types.put(type, new Integer(tileId));
		isTangible = tangible;
		myType = type;
		myId = tileId;
		types = new HashMap<String, Integer>();
	}
	
	/**
	 * Used to find the tile's image in the main source image.
	 * 
	 * @param id Tile id to find the image for
	 * @return The image for the tile with the id specified
	 */
	
	private static BufferedImage calculateImage(int id){
		try {
			BufferedImage img = ImageIO.read(new File("/res/", IMAGE_RESOURCE));
			img = img.getSubimage(img.getWidth()%(id*TILE_SIZE), img.getHeight()%(id*TILE_SIZE), TILE_SIZE, TILE_SIZE);
			return img;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Tests if an entity is in contact with the tile 
	 * 
	 * @param entity Entity to test
	 * @return Whether or not it is in contact with the tile.
	 * @see Entity
	 */
	
	//TODO: Make hitTest actually work. Not just be a copy of isTangible()
	public boolean hitTest(Entity entity){
		return isTangible();
	}
	
	/**
	 * Change whether the tile is tangible or not.
	 * 
	 * @param tangible Whether entities can interact with this tile or not
	 */
	
	public void setTangible(boolean tangible){
		isTangible = tangible;
	}
	
	/**
	 * Supply whether the tile is tangible or not.
	 * 
	 * @return wether the tile is tangible or not.
	 */
	
	public boolean isTangible(){
		return isTangible;
	}
	
	/**
	 * Supply the type of the tile
	 * 
	 * @return tile type
	 */
	
	public String getType(){
		return myType;	
	}
	
	/**
	 * Supply the type id of the tile
	 * 
	 * @return tile type id.
	 */
	
	public int getId(){
		return myId;	
	}
}
