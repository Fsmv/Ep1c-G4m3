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

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 * Super class all Tile types must extend.
 * 
 * @author Voracious Softworks
 * @version 6/20/2011
 * @see Drawable
 */

public class Tile extends Drawable implements Cloneable {
	
	private boolean isTangible;
	private String myType;
	private int myId;
	
	public Tile(String type, int tileId){
		super();
		isTangible = false;
		myType = type;
		myId = tileId;
	}
	
	/**
	 * Adds the type to the global list of tile types. Initializes tile properties.
	 * 
	 * @param type Name for the tile type
	 * @param tileId Incremental number for the tile
	 */
	
	public Tile(String type, int tileId, BufferedImage image){
		super(image, false);
		isTangible = false;
		myType = type;
		myId = tileId;
	}

	/**
	 * Tests if an entity is in contact with the tile 
	 * 
	 * @param entity Entity to test
	 * @return Whether or not it is in contact with the tile.
	 * @see Entity
	 */
	
	public boolean hitTest(Entity entity){
		Rectangle mBounds = getBounds();
		Rectangle eBounds = entity.getBounds();
		return mBounds.intersects(eBounds) || ((mBounds.getX()+mBounds.getWidth()) >= eBounds.getX() && 
				mBounds.getX() <= (eBounds.getX()+eBounds.getWidth()) &&
				(mBounds.getY()+mBounds.getHeight()) >= eBounds.getY() &&
				mBounds.getY() <= (eBounds.getY()+eBounds.getWidth()));
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
	 * @return whether the tile is tangible or not.
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
	
	/**
	 * Doesn't clone position or any other <i>Drawable</i> properties (except image).
	 * 
	 * @see Drawable
	 * @returns A new object of the same type with the default properties.
	 */
	@Override
	public Tile clone(){
		return new Tile(getType(), getId(), getImage());
	}
}
