package com.voracious.ep1cG4m3.tiles;

import java.awt.image.BufferedImage;

import com.voracious.ep1cG4m3.framework.Tile;

public class Brick extends Tile {
	public static final int ID = 0;
	public static final String NAME = "brick";
	
	public Brick(){
		super(NAME, ID);
	}
	
	public Brick(BufferedImage image) {
		super(NAME, ID, image);
		this.setTangible(true);
		this.setVisible(true);
	}
	
	@Override
	public Brick clone(){
		Brick result = new Brick(getImage());
		result.setTangible(true);
		result.setVisible(true);
		return result;
	}
}
