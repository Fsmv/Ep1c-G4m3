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

/**
 * Class to contain the listener method. So screens can pass data to their caller.
 * 
 * @author Voracious Softworks
 */

public interface ScreenResultListener {
	/**
	 * Method to allow a screen to pass data to a class that implements this
	 * 
	 * @param id screen id
	 * @param resultCode screen result code
	 */
	
	public abstract void onScreenResult(int id, int resultCode);
}
