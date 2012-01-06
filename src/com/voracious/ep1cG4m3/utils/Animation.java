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
import java.util.ArrayList;

/**
 * Utility class for storing and playing an animation.
 * 
 * @author Voracious Softworks
 * @version 6/20/2011
 */

public class Animation {
	public static final int FPS = 60;

	private int currentFrame;
	private ArrayList<BufferedImage> frames;
	private long frameTime;
	private long duration;

	/**
	 * Default Constructor, allows creating it then setting the values later.
	 */

	public Animation() {
		frames = new ArrayList<BufferedImage>();
		currentFrame = 0;
		start();
	}

	/**
	 * Initialize the class with the frames already in an ArrayList.
	 * 
	 * @param frames List of images to be used as frames
	 */

	public Animation(ArrayList<BufferedImage> frames) {
		this.frames = frames;
		currentFrame = 0;
		duration = (long) ((1.0 / (FPS * 1000)) * frames.size());
		start();
	}

	/**
	 * Starts animation over from the beginning
	 */

	public synchronized void start() {
		frameTime = 0;
		currentFrame = 0;
	}

	/**
	 * Changes the animation's frame if enough time has passed
	 * 
	 * @param dt delta time, time since last update in ms
	 */

	public synchronized void update(long dt) {
		if (frames.size() > 1) {
			frameTime += dt;

			if (frameTime >= duration) {
				frameTime = frameTime % duration;
				currentFrame = 0;
			}

			while (frameTime > currentFrame * ((double) duration / frames.size()))
				currentFrame++;
		}
	}

	public synchronized BufferedImage getImage() {
		return frames.get(currentFrame);
	}

	/**
	 * Supplies the next frame number.
	 * 
	 * @return The frame number that will be returned next
	 */

	public synchronized int getCurrentFrame() {
		return currentFrame;
	}

	/**
	 * Supplies the number of frames in the animation.
	 * 
	 * @return Total number of frames in the animation
	 */

	public int getNumFrames() {
		return frames.size();
	}

	/**
	 * Supplies a specific frame.
	 * 
	 * @param frame Frame number that should be returned
	 * @return The requested frame image
	 */

	public BufferedImage getFrame(int frame) {
		return frames.get(frame);
	}

	/**
	 * Appends a frame to the list
	 * 
	 * @param frame frame to append
	 */

	public void addFrame(BufferedImage frame) {
		frames.add(frame);
	}

	/**
	 * Changes the current frame to the supplied value.
	 * 
	 * @param frame frame to switch to
	 */

	public synchronized void gotoFrame(int frame) {
		frameTime = frameTime % duration;
		currentFrame = frame;
	}
}