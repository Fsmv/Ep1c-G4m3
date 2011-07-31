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

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import com.voracious.ep1cG4m3.tiles.Brick;


public class TileFactory {
	public static final int TILE_SIZE = 32;
	public static final String tileNames[] = {Brick.NAME};
	public static Tile tileClasses[] = {new Brick()};
	public static BufferedImage textures[];
	public static BufferedImage resourceMap;
	
	private TileFactory(BufferedImage tileMap){
		resourceMap = tileMap;
		textures = new BufferedImage[tileNames.length];
		for(int i=0; i<tileNames.length; i++){
			textures[i] = calculateImage(i, tileMap);
		}
	}
	
	public static void setResourceImage(BufferedImage tileMap){
		resourceMap = tileMap;
	}
	
	public static void findAllTextures(){
		if(resourceMap != null){
			textures = new BufferedImage[tileNames.length];
			for(int i=0; i<tileNames.length; i++){
				textures[i] = calculateImage(i, resourceMap);
				tileClasses[i].setImage(textures[i]);
			}
		}
	}
	
	public static Tile getNewTile(String type){
		if(type.equals("air"))
			return null;
		for(int i=0; i<tileNames.length; i++){
			if(tileNames[i].equals(type)){
				return tileClasses[i].clone();
			}
		}
		return null;
	}
	
	public static Tile getNewTile(int id){
		if(id == -1)
			return null;
		if(tileClasses.length >= id)
			return tileClasses[id].clone();
		return null;
	}
	
	private static BufferedImage calculateImage(int id, BufferedImage image){ 
		return scale(image.getSubimage((id%(image.getWidth()/(TILE_SIZE/2)))*(TILE_SIZE/2), (id/(image.getWidth()/(TILE_SIZE/2)))*(TILE_SIZE/2), (TILE_SIZE/2), (TILE_SIZE/2)), TILE_SIZE, TILE_SIZE);
	}
	
	public static BufferedImage scale(BufferedImage pImage, int pWidth, int pHeight){
        int type = pImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : pImage.getType();
        BufferedImage resizedImage = new BufferedImage(pWidth, pHeight, type);
        Graphics2D graphics = resizedImage.createGraphics();
        graphics.setComposite(AlphaComposite.Src);

        graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
        graphics.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);

        graphics.drawImage(pImage, 0, 0, pWidth, pHeight, null);
        graphics.dispose();

        return resizedImage;
    }
}
