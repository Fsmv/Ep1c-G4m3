package com.voracious.ep1cG4m3.framework;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.security.CodeSource;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

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

    public void loadNextLevel(){
	loadNextLevel(false);
    }
    
    public void loadNextLevel(boolean addedToWorld) {
	BufferedImage image = Art.loadImage("/worlds/" + currentWorld + "/" + currentLevel + ".png");
	if(image != null)
	    loadMap(image);
	else{
	    currentLevel = 1;
	    currentWorld++;
	    if(!addedToWorld)
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
    
    public Tile[][] getTiles(){
	return tiles;
    }

    public void loadMap(BufferedImage mapFile) {
	tiles = new Tile[mapFile.getWidth()][mapFile.getHeight()];
	entities = new ArrayList<Entity>();

	for (int x = 0; x < mapFile.getWidth(); x++) {
	    for (int y = 0; y < mapFile.getHeight(); y++) {
		int color = mapFile.getRGB(x, y);
		if(x > 3)
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
     * @param mapName
     *            the mapName to set
     */
    public void setMapName(String mapName) {
	this.mapName = mapName;
    }
}
