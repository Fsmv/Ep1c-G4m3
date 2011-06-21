package com.voracious.ep1cG4m3.utils;

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

import java.awt.Image;
import java.util.ArrayList;

public class Animation {
	private int currentFrame;
	private ArrayList<Image> myFrames;
	
	public Animation(){
		myFrames = new ArrayList<Image>();
		currentFrame = 0;
	}
	
	public Animation(ArrayList<Image> frames){
		myFrames = frames;
		currentFrame = 0;
	}
	
	public void add(Image frame){
		myFrames.add(frame);
	}
	
	public int getCurrentFrame(){
		return currentFrame;
	}
	
	public int getNumFrames(){
		return myFrames.size();
	}
	
	public Image getNextFrame(){
		currentFrame++;
		if(currentFrame > getNumFrames())
			currentFrame = 0;
		return myFrames.get(currentFrame-1);
	}
	
	public void gotoFrame(int frame){
		currentFrame = frame;
	}
}
