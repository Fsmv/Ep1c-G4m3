package com.voracious.ep1cG4m3.utils;

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
