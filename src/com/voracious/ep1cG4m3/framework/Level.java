package com.voracious.ep1cG4m3.framework;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.voracious.ep1cG4m3.utils.Art;

public class Level {
	private Tile[][] tiles;
	private String mapName;
	private ArrayList<Entity> entities;
	private int currentWorld = 1;
	private int currentLevel = 1;

	public Level() {
		loadNextLevel();
	}

	
	//These methods are in two parts so it can be called without arguments even though I designed the new world detection system throught recursion
	public void loadNextLevel() {
		loadNextLevel(false);
		//currentLevel++; Disabled because of testing (There's only one level right now)
	}

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

	public int getWidth() {
		return tiles.length;
	}

	public int getHeight() {
		return tiles[0].length;
	}

	public Tile getTile(Point location) {
		if (location.x >= 0 && location.x < getWidth() && location.y >= 0 && location.y < getHeight()) {
			return tiles[location.x][location.y];
		} else {
			return null;
		}
	}

	public void setTile(Point location, Tile tile){
		tiles[location.x][location.y] = tile;
	}
	
	public Tile[][] getTiles() {
		return tiles;
	}
	
	public void setTiles(Tile[][] tiles){
		this.tiles = tiles;
	}

	public void loadMap(BufferedImage mapFile) {
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
					tiles[x][y] = getTile(color);
				else if ((color | 0xff00ff) == color) {
					//tileEntities
				}
			}
		}
	}

	public Tile getTile(int color) {
		color = color & 0x00ffffff;
		if ((color | 0xffff) == color) {
			if (color == 0xffffff)
				return new Tile(); //Air 
			else
				return Tile.getTile(Math.abs(color / 0x10000));
		}
		throw new IllegalArgumentException("Invalid tile format should be 0x[id]FFFF");
	}

	public Entity getEntity(int color) {
		if ((color | 0xffff00) == color) {
			return Entity.getEntity(color & 0xff);
		}
		throw new IllegalArgumentException("Invalid tile format should be 0x[id]FFFF");
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
