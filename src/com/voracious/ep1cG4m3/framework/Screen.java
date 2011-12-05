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

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;

import com.voracious.ep1cG4m3.utils.ScreenResultListener;

/**
 * Framework class; should be extended to create a new screen.
 * 
 * @author Voracious Softworks
 */

public abstract class Screen extends Canvas {
	private static final long serialVersionUID = 2222464333625474339L;
	
	private BufferStrategy buffer;
	private ScreenResultListener listener;
	private int id;
	
	/**
	 * Creates a new Screen object
	 * 
	 * @param listener listener this screen will post messages to
	 * @param id the screens id (for switching purposes)
	 */
	
	public Screen(ScreenResultListener listener, int id){
		setListener(listener);
		setId(id);
		setBounds(0, 0, 640, 512);
	}
	
	@Override
	public void addNotify(){
		super.addNotify();
		
		this.createBufferStrategy(2);
		buffer = getBufferStrategy();
	}
	
	/**
	 * Called when the screen is being switched to. Each screen can initialize GUI elements and listeners here.
	 * <br />Call super() when overriding.
	 */
	
	public void start(){
		setVisible(true);
		setBackground(Color.WHITE);
	}
	
	/**
	 * Called when switching to another screen. Each screen MUST remove its listeners here.
	 * <br />Call super() when overriding.
	 */
	
	public void stop(){
		setVisible(false);
		buffer.dispose();
	}
	
	/**
	 * Implement this method to draw graphics.
	 * 
	 * @param g Graphics object that will be displayed
	 */
	
	public abstract void draw(Graphics g);
	
	/**
	 * Called immediately after rendering completes. Should be used to change the graphics that will be drawn next frame.
	 */
	
	public void update(){
		//Nothing
	}
	
	/**
	 * This message should not be overridden.
	 * 
	 * Renders all of the graphics.
	 */
	
	public void render(){
		Graphics g = buffer.getDrawGraphics();
		g.clearRect(0, 0, 640, 512);
		
		draw(g);
		
		g.dispose();
		buffer.show();
		Toolkit.getDefaultToolkit().sync();
	}
	
	/**
	 * The game object that called the screen. Needed to call onActivityResult.
	 * 
	 * @return the class that the screen will return a result to
	 */
	
	public ScreenResultListener getListener(){
		return listener;
	}
	
	/**
	 * A method to call when GUI elements are interacted with.
	 * 
	 * @param id id for the GUI element
	 * @param type Action type
	 */
	
	protected void dispatchResult(int type){
		getListener().onScreenResult(getId(), type);
	}

	/**
	 * Set the listener object
	 * 
	 * @param listener the listener change to
	 */
	
	public void setListener(ScreenResultListener listener) {
		this.listener = listener;
	}

	/**
	 * Set the screen id
	 * 
	 * @param id the id to set
	 */
	
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Get the screen id
	 * 
	 * @return the id
	 */
	
	public int getId() {
		return id;
	}
}
