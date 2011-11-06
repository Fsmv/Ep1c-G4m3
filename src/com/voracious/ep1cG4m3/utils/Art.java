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

import javax.imageio.ImageIO;

public class Art {
	public static BufferedImage font = loadImage("/font.png");
	public static BufferedImage tiles = loadImage("/tiles.png");
	
	public static BufferedImage loadImage(String fileName){
		try{
			return ImageIO.read(Art.class.getResource(fileName));
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
}