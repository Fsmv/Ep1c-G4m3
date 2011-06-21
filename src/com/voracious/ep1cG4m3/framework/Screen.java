package com.voracious.ep1cG4m3.framework;

import java.awt.Graphics2D;

/**
 * Absract super class all screens must extend.
 * 
 * @author Voracious Softworks
 * @see Drawable
 */

public abstract class Screen extends Drawable {
	int myId;
	
	/**
	 * Sets id to be returned for identifying in the calling class
	 * 
	 * @param id unique screen id
	 */
	
	public Screen(int id){
		myId = id;
	}
	
	/**
	 * Called when the screen is being switched to. Each screen can initialize GUI elements and listeners here.
	 */
	
	public abstract void start();
	
	/**
	 * Called when switching to another screen. Each screen MUST remove it's specific listeners here.
	 */
	
	public abstract void stop();
	
	/**
	 * Called when the screen is active. Should be used to draw all GUI components. Overrides Drawable's implementation because screens are not one image.
	 * 
	 * @see Drawable
	 */
	
	@Override
	public abstract void draw(Graphics2D p);
	
	/**
	 * A method to call when GUI elements are interacted with.
	 * 
	 * @param id id for the GUI element
	 * @param type Action type
	 */
	
	protected abstract void onGUIAction(int id, int type);
}
