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
	private int id = -1;
	private Image image;
	private String name = "air";
	public static int tileSize = 16;
	public static HashMap<String, Class<? extends Tile>> tiles = new HashMap<String, Class<? extends Tile>>();
	public static HashMap<Integer, String> tileIds = new HashMap<Integer, String>();

	static {
		tiles.put("air", Tile.class);
		tileIds.put(-1, "air");
		tiles.put(Brick.name, Brick.class);
		tileIds.put(Brick.id, Brick.name);
	}

	public Tile() {
		super();
		isTangible = false;
	}

	public Tile(String name, int id) {
		super();
		isTangible = false;
		setName(name);
		setId(id);
		
		if (id < 0)
			this.isAir = true;
		else
			this.isAir = false;

		if (image == null && !isAir()) {
			image = Art.getTileImage(name);
		}
		if (image != null)
			setImage(image);
	}

	/**
	 * Called when an entity comes in contact with this tile
	 * 
	 * @param entity the entity that came in contact with this tile
	 */
	public static void onEntityContact(Entity entity) {
	}

	/**
	 * Called when an entity breaks contact with this tile
	 * 
	 * @param entity the entity that broke contact with this tile
	 */
	public static void onEntityBreakContact(Entity entity) {
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
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	public static Tile getTile(String name) {
		// TODO: Make the game exit instead of just ignoring this error
		// TODO: Also find the other Exceptions like this and do the same It's 01:30 I can't be bothered right now
		// TODO: Throw an illegal argument exception if name doesn't exist
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
	public static Tile getTile(int id) {
		// TODO: Make the game exit instead of just ignoring this error
		// TODO: Also find the other Exceptions like this and do the same It's 01:30 I can't be bothered right now
		if (id > 0) {
			try {
				return tiles.get(tileIds.get(Integer.valueOf(id))).newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}

		return null;
	}
}