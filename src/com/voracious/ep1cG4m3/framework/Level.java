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

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.voracious.ep1cG4m3.utils.Art;

/**
 * Used to store and load levels
 * 
 * @author Voracious Softworks
 */

public class Level {
	private Tile[][] tiles;
	private String mapName;
	private ArrayList<Entity> entities;
	private int currentWorld = 1;
	private int currentLevel = 1;

	public Level() {
		loadNextLevel();
	}

	/**
	 * Goes to next level, to be called when each level is completed
	 */

	//These methods are in two parts so it can be called without arguments even though I designed the new world detection system throught recursion
	public void loadNextLevel() {
		loadNextLevel(false);
		//currentLevel++; Disabled because of testing (There's only one level right now)
	}

	/**
	 * Actually loads the level and increments the world when there are no more levels to load
	 * 
	 * @param addedToWorld if the world value was incremented or not
	 */

	private void loadNextLevel(boolean addedToWorld) {
		//TODO: Fix the infinite loop that occurs when we run out of levels
		BufferedImage image = Art.loadImage("/worlds/" + currentWorld + "/" + currentLevel + ".png");
		if (image != null)
			loadMap(image);
		else {
			currentLevel = 1;
			currentWorld++;
			if (!addedToWorld)
				loadNextLevel(true);
		}
	}

	/**
	 * @return the width of the level in tiles
	 */

	public int getWidth() {
		return tiles.length;
	}

	/**
	 * @return the height of the level in tiles
	 */
	public int getHeight() {
		return tiles[0].length;
	}

	/**
	 * location should be defined in tiles not in pixel coordinates
	 * 
	 * @param location location of the tile to return
	 * @return the tile at the specified location. Null if out of bounds.
	 */

	public Tile getTile(Point location) {
		if (location.x >= 0 && location.x < getWidth() && location.y >= 0 && location.y < getHeight()) {
			return tiles[location.x][location.y];
		} else {
			return null; //TODO: Throw exception instead of returning null
		}
	}

	/**
	 * Set the tile at the specified location
	 * 
	 * @param location the location to set at
	 * @param tile the tile to set to
	 */

	public void setTile(Point location, Tile tile) {
		tiles[location.x][location.y] = tile;
	}

	/**
	 * @return the level tiles
	 */

	public Tile[][] getTiles() {
		return tiles; //TODO: This should probably return a copy and not the object itself (security risk) 
	}

	/**
	 * @param tiles the set of tiles to set as the level
	 */
	public void setTiles(Tile[][] tiles) {
		this.tiles = tiles;
	}

	/**
	 * Loads a map image into the level data
	 * 
	 * @param mapFile
	 */

	public void loadMap(BufferedImage mapFile) { //TODO: Add some sort of error catching eg: validate the image as a mapfile
		tiles = new Tile[mapFile.getWidth()][mapFile.getHeight()];
		entities = new ArrayList<Entity>();

		for (int x = 0; x < mapFile.getWidth(); x++) {
			for (int y = 0; y < mapFile.getHeight(); y++) {
				int color = mapFile.getRGB(x, y);
				if (x > 3)
					System.currentTimeMillis();
				if ((0xffff00 | color) == color) {
					//entities.add(getEntity(color));
				} else if ((color | 0x00ffff) == color)
					tiles[x][y] = Level.getTile(color);
				else if ((color | 0xff00ff) == color) {
					//tileEntities
				}
			}
		}
	}

	/**
	 * Get a tile object based on pixel color (from the map)
	 * 
	 * @param color color to decode
	 * @return Tile object based on color input
	 */

	private static Tile getTile(int color) {
		color = color & 0x00ffffff;
		if ((color | 0xffff) == color) {
			if (color == 0xffffff)
				return new Tile(); //Air 
			else
				return Tile.getTile(Math.abs(color / 0x10000));
		}
		throw new IllegalArgumentException("Invalid tile format should be 0x[id]FFFF");
	}

	/**
	 * Get an entity object based on pixel color (from the map)
	 * 
	 * @param color color to decode
	 * @return Entity object based on color input
	 */

	public static Entity getEntity(int color) {
		if ((color | 0xffff00) == color) {
			return Entity.getEntity(color & 0xff);
		}
		throw new IllegalArgumentException("Invalid tile format should be 0xFFFF[id]");
	}

	/**
	 * @return the mapName
	 */
	public String getMapName() {
		return mapName;
	}

	/**
	 * @param mapName the mapName to set
	 */
	public void setMapName(String mapName) {
		this.mapName = mapName;
	}
}
