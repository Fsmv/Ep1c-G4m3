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

package com.voracious.ep1cG4m3.utils;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class Art {
	public static BufferedImage font = loadImage("/font.png");
	public static BufferedImage tileTextures = loadImage("/tiles.png");

	private static HashMap<String, BufferedImage> tiles = loadTiles(tileTextures);

	/**
	 * Loads a specified image
	 * 
	 * @param fileName the image file to load
	 * @return the loaded image. Null if an error was encountered
	 */
	public static BufferedImage loadImage(String fileName){
		try{
			return ImageIO.read(Art.class.getResource(fileName));
		}catch(IOException e){
			Logger.log(Logger.TYPE_ERROR, e.getStackTrace().toString());
			return null;
		}
	}

	/**
	 * Cuts the tile images out of the texture file and stores them in a HashMap with their names as keys.
	 * 
	 * @param tileTextures The texture file to cut up
	 * @return the HashMap with all of the images stored by name
	 */
	
	/*
	 * This method has some code that is nearly the same as some code in the Entity class.
	 * The similar code is in the methods loadResources and loadAnimations
	 */
	private static HashMap<String, BufferedImage> loadTiles(BufferedImage tileTextures){
		HashMap<String, BufferedImage> result = new HashMap<String, BufferedImage>();

		int color = tileTextures.getRGB(0, 0);
		int sideLen = (color & 0xff0000)/0x10000;

		for(int xx=1; xx<tileTextures.getWidth(); xx++){
			color = tileTextures.getRGB(xx, 0);
			if(color == 0xffffffff)
				break;

			if(xx%4 == 1){
				String hex = "";
				for(int i=0; i<4; i++){
					int a = (color & 0xff0000)/0x10000;
					int b = (color & 0x00ff00)/0x100;
					int c = (color & 0x0000ff);

					if(a == 0){
						break;
					}else if(b == 0){
						hex += Integer.toHexString(a);
						break;
					}else if(c == 0){
						hex += Integer.toHexString((a*0x100)+b);
						break;
					}else
						hex += Integer.toHexString(color);
				}
				result.put(hexToString(hex), tileTextures.getSubimage(((xx/4)*sideLen)%tileTextures.getWidth(), sideLen*(((xx/4)*sideLen)/tileTextures.getWidth()), sideLen, sideLen));

				xx += 4;
			}
		}

		return result;
	}
	
	/**
	 * Turns a hex number represented into as a string into a string of ascii chars
	 * 
	 * @param hex value to translate
	 * @return translated hex
	 */
	
	public static String hexToString(String hex){
		String result = "";
		for(int i=0; i<hex.length()-1; i+=2 ){
			String substr = hex.substring(i, (i + 2));
			int decimal = Integer.parseInt(substr, 16);
			result += (char)decimal;
		}
		
		return result;
	}
	
	/**
	 * @param name The name of the tile resource to return
	 * @return The tile image with the defined name
	 * @throws IllegalArgumentException When the name requested is not defined in the texture file
	 */
	public static BufferedImage getTileImage(String name) throws IllegalArgumentException{
		if(tiles.containsKey(name)){
			return tiles.get(name);
		}else{
			throw new IllegalArgumentException("No tile image with the name: \"" + name + "\" exists.");
		}
	}
}
