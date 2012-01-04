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
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>. 
 */

package com.voracious.ep1cG4m3.framework;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

/**
 * Should be extended by any class that will be drawn or has an image
 * 
 * @author Voracious Softworks
 */

public class Drawable extends ImageIcon {
	private static final long serialVersionUID = 5748722120305601075L;

	private Point location = new Point(0, 0);

	public Drawable() {
		super();
	}

	public Drawable(Image i) {
		super(i);
	}

	public Drawable(Image i, Point location) {
		super(i);
		setLocation(location);
	}

	public void setLocation(Point location) {
		this.location = location;
	}

	public Point getLocation() {
		return location;
	}

	/**
	 * Paints the icon using the stored location
	 * 
	 * @param c component to draw on
	 * @param g graphics to draw on
	 */

	public void paintIcon(Component c, Graphics g) {
		Point loc = getLocation();
		super.paintIcon(c, g, (int) loc.getX(), (int) loc.getY());
	}

	/**
	 * @return rectangle representing the object
	 */

	public Rectangle getBounds() {
		Point loc = getLocation();
		BufferedImage img = (BufferedImage) getImage();
		return new Rectangle((int) loc.getX(), (int) loc.getY(), img.getWidth(), img.getHeight());
	}

	/**
	 * Rotates the image by [rads] radians
	 * 
	 * @param theta radians to rotate to
	 * @param axis point to rotate around
	 */

	public void rotate(double theta, Point.Double axis) {
		BufferedImage currImg = (BufferedImage) getImage();
		BufferedImage newImg = new BufferedImage(currImg.getWidth(), currImg.getHeight(), currImg.getType());
		Graphics2D g2 = (Graphics2D) newImg.getGraphics();
		g2.rotate(theta, axis.x, axis.y);
		g2.drawImage(currImg, null, 0, 0);
		g2.rotate(0, axis.x, axis.y);
		g2.dispose();
		setImage(newImg);
	}
}