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

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Utility class for storing and playing an animation.
 * 
 * @author Voracious Softworks
 */

public class Animation {
	private int currentFrame;
	private ArrayList<BufferedImage> myFrames;
	
	/**
	 * Default Constructor, allows creating it then setting the values later.
	 */
	
	public Animation(){
		myFrames = new ArrayList<BufferedImage>();
		currentFrame = 0;
	}
	
	/**
	 * Initialize the class with the frames already in an ArrayList.
	 * 
	 * @param frames List of images to be used as frames
	 */
	
	public Animation(ArrayList<BufferedImage> frames){
		myFrames = frames;
		currentFrame = 0;
	}
	
	/**
	 * Appends a frame to the end of the frame list.
	 * 
	 * @param frame Image to be added
	 */
	
	public void add(BufferedImage frame){
		myFrames.add(frame);
	}
	
	/**
	 * Supplies the next frame number.
	 * 
	 * @return The frame number that will be returned next
	 */
	
	public int getCurrentFrame(){
		return currentFrame;
	}
	
	/**
	 * Supplies the number of frames in the animation.
	 * 
	 * @return Total number of frames in the animation
	 */
	
	public int getNumFrames(){
		return myFrames.size();
	}
	
	/**
	 * Supplies a specific frame.
	 * 
	 * @param frame Frame number that should be returned
	 * @return The requested frame image
	 */
	
	public BufferedImage getFrame(int frame){
		return myFrames.get(frame);
	}
	
	/**
	 * Increments the frame number and returns the next frame. Can be used in a loop to play the animation.
	 * 
	 * @return Image of the next frame
	 */
	
	public BufferedImage getNextFrame(){
		currentFrame++;
		if(currentFrame > getNumFrames())
			currentFrame = 0;
		return myFrames.get(currentFrame-1);
	}
	
	/**
	 * Changes the current frame to the supplied value.
	 * 
	 * @param frame frame to switch to
	 */
	
	public void gotoFrame(int frame){
		currentFrame = frame;
	}
}
