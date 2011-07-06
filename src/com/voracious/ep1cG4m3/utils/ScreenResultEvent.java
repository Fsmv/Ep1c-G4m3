package com.voracious.ep1cG4m3.utils;

/**
 * Class to contain the listener method. So screens can pass data to their caller.
 * 
 * @author Voracious Softworks
 */

public interface ScreenResultEvent {
	/**
	 * Method to allow a screen to pass data to a class that implements this
	 * 
	 * @param id screen id
	 * @param resultCode screen result code
	 */
	public abstract void onScreenResult(int id, int resultCode);
}
