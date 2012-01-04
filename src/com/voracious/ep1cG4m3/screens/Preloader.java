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
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.voracious.ep1cG4m3.framework.Screen;
import com.voracious.ep1cG4m3.utils.Art;
import com.voracious.ep1cG4m3.utils.ScreenResultListener;

public class Preloader extends Screen {

	public static final int RESULT_OK = 0;

	private static final long serialVersionUID = -2293651813069405885L;
	private BufferedImage bg;

	public Preloader(ScreenResultListener listener, int id) {
		super(listener, id);
	}

	@Override
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		bg = Art.loadImage("/loadImage.png");

		g2.drawImage(bg, null, 0, 0);
	}

	//private int ticks = 0;

	@Override
	public void update() {
		/*
		 * ticks++; if(ticks == 72)
		 */
		dispatchResult(Preloader.RESULT_OK);
	}
}
