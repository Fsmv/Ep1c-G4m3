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

import com.voracious.ep1cG4m3.utils.Art;

/**
 * Represents a displayed tile.
 * 
 * @author Voracious Softworks
 */

public class Tile extends Drawable {
	private static final long serialVersionUID = 7672342088231505969L;
	private boolean isTangible;
	private boolean isAir;
	private static Image image;
	private int id;
	private static String name = "";

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
		setImage(image);
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
}
