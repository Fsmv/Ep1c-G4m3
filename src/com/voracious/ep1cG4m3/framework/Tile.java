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

package com.voracious.ep1cG4m3.framework;

import java.awt.Image;
import java.util.HashMap;

import com.voracious.ep1cG4m3.tiles.Brick;
import com.voracious.ep1cG4m3.utils.Art;
import com.voracious.ep1cG4m3.utils.Logger;

/**
 * Represents a displayed tile.
 * 
 * @author Voracious Softworks
 */

public class Tile extends Drawable {
	private static final long serialVersionUID = 7672342088231505969L;
	private boolean isTangible;
	private boolean isAir;
	private int id;
	private static Image image;
	private static String name = "";
	public static int tileSize = 16;
	public static HashMap<String, Class<? extends Tile>> tiles = new HashMap<String, Class<? extends Tile>>();
	static{
		tiles.put("air", Tile.class);
		tiles.put(Brick.getName(), Brick.class);
	}
	

	public Tile(int id){
		super();
		isTangible = false;
		this.id = id;
		
		if(this.id < 0)
			this.isAir = true;
		else
			this.isAir = false;

		if(image == null){
			image = Art.getTileImage(getName());
		}
		//This line sets the displayed image (Drawable.setImage()) to the stored static image for this tile type
		setImage(image); //The names are confusing, I know. I'll think about changing this up. 
	}
	
	/**
	 * Called when an entity comes in contact with this tile
	 * 
	 * @param entity the entity that came in contact with this tile
	 */
	public static void onEntityContact(Entity entity){
	}
	
	/**
	 * Called when an entity breaks contact with this tile
	 * 
	 * @param entity the entity that broke contact with this tile
	 */
	public static void onEntityBreakContact(Entity entity){
	}

	/**
	 * @return whether the tile is tangible or not
	 */
	public boolean isTangible() {
		return isTangible;
	}

	/**
	 * @param isTangible the tangibility of the tile
	 */
	public void setTangible(boolean isTangible) {
		this.isTangible = isTangible;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * If the block is air it will not be rendered
	 * 
	 * @return Whether the block is air or not
	 */

	public boolean isAir() {
		return isAir;
	}

	/**
	 * If the block is air it will not be rendered
	 * 
	 * @param isAir Set the block to be air or not
	 */
	public void setAir(boolean isAir) {
		this.isAir = isAir;
	}

	/**
	 * @return the name
	 */
	public static String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public static void setName(String name) {
		Tile.name = name;
	}
	

	/**
	 * @return the tileSize
	 */
	public static int getTileSize() {
		return tileSize;
	}

	/**
	 * @param tileSize the tileSize to set
	 */
	public static void setTileSize(int tileSize) {
		Tile.tileSize = tileSize;
	}
	
	/**
	 * Allows other classes to get new instances of tiles by name
	 * 
	 * @param name the name of the tile to return
	 * @return the requested tile
	 */
	public static Tile getTile(String name){
		//TODO: Make the game exit instead of just ignoring this error
		//TODO: Also find the other Exceptions like this and do the same It's 01:30 I can't be bothered right now
		//TODO: Thow an illegal argument exception if name doesn't exist
		try {
			return tiles.get(name).newInstance();
		} catch (InstantiationException e) {
			Logger.log(Logger.TYPE_ERROR, e.getStackTrace().toString());
		} catch (IllegalAccessException e) {
			Logger.log(Logger.TYPE_ERROR, e.getStackTrace().toString());
		}
		
		return null;
	}
	
	/**
	 * Allows other classes to get new instances of tiles by id
	 * 
	 * @param id the id of the tile to return
	 */
	public static Tile getTile(int id){
		//TODO: Make the game exit instead of just ignoring this error
		//TODO: Also find the other Exceptions like this and do the same It's 01:30 I can't be bothered right now
		//TODO: Thow an illegal argument exception if name doesn't exist
		Tile[] tileArray = new Tile[tiles.size()];
		tileArray = tiles.values().toArray(tileArray);
		for(int i=0; i<tiles.size(); i++){
			if(tileArray[i].getId() == id)
				try {
					return tileArray[i].getClass().newInstance();
				} catch (InstantiationException e) {
					Logger.log(Logger.TYPE_ERROR, e.getStackTrace().toString());
				} catch (IllegalAccessException e) {
					Logger.log(Logger.TYPE_ERROR, e.getStackTrace().toString());
				}
		}
		return null;
	}
}