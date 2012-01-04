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

package com.voracious.ep1cG4m3.screens;

import java.awt.Graphics;
import java.awt.Point;

import com.voracious.ep1cG4m3.framework.Screen;
import com.voracious.ep1cG4m3.utils.ScreenResultListener;
import com.voracious.ep1cG4m3.utils.Text;

/**
 * Screen used to display game instructions
 * 
 * @author Voracious Softworks
 */

public class Instructions extends Screen {
	private static final long serialVersionUID = 6891345264117973033L;
	private Text title;
	private Text instructions;

	public Instructions(ScreenResultListener listener, int id) {
		super(listener, id);
	}

	@Override
	public void start() {
		title = new Text("Instructions:", new Point(5, 5), 20);
		instructions = new Text(
				"* Use WASD to move the player (a two colored rectangle\n  for now)\n" +
						"* Press spacebar to make the view window move around\n" +
						"* Press Z to randomly add a tile\n" +
						"* Press X to randomly remove a tile\n" +
						"* Press C to toggle between the level map and a\n  full screen of tiles",
				new Point(5, 30));
	}

	@Override
	public void draw(Graphics g) {
		title.paintIcon(this, g);
		instructions.paintIcon(this, g);
	}
}
