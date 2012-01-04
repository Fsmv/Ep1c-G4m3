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

package com.voracious.ep1cG4m3.entities;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import com.voracious.ep1cG4m3.framework.Drawable;
import com.voracious.ep1cG4m3.framework.Entity;

/**
 * Represents the player. A user should be able to control the actions of this object.
 * 
 * @author Voracious Softworks
 */

public class Player extends Entity {
	private static final long serialVersionUID = 7613641952651564431L;

	public static final int BULLET_SPEED = 15;
	public static final int WALK_SPEED = 6;

	private Drawable gun;
	private ArrayList<Entity> bullets;
	private double gunRotation;
	private boolean facingRight = true;

	/**
	 * Initialize a new player object
	 */

	public Player() {
		super(new File("/entities/player/"));
		this.setAnimation("standing");
		//gun = new Drawable(this.getResource("gun"));
		//bullets = new ArrayList<Entity>();
		this.setImage(getCurrentAnimation().getImage());
		setGravity(true);
	}

	@Override
	public void update(long now) {
		super.update(now);

		if (this.getLocation().x > 640)
			this.setLocation(new Point(-1 * this.getBounds().width, this.getLocation().y));
		else if (this.getLocation().x < -1 * this.getBounds().width)
			this.setLocation(new Point(640, this.getLocation().y));

		if (this.getLocation().y < 300) {
			setFalling(true);
		} else {
			this.setLocation(new Point(this.getLocation().x, 300));
			setFalling(false);
		}

		if (this.getVelocity().x < 0 && facingRight)
			facingRight = false;
		else if (this.getVelocity().x > 0 && !facingRight)
			facingRight = true;

		BufferedImage image = new BufferedImage(this.getIconWidth(), this.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics g = image.getGraphics();

		if (facingRight) {
			g.drawImage(this.getImage(), 0, 0, null); //Using getImage works consistently because super.update sets it based on the animation
			// g.drawImage(gun.getImage(), this.getIconWidth() / 2, this.getIconHeight() / 3, null);
		} else {
			AffineTransform tx = AffineTransform.getScaleInstance(-1, 1); //flip horizontally
			tx.translate(-image.getWidth(null), 0);
			AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);

			// g.drawImage(gun.getImage(), this.getIconWidth() / 2, this.getIconHeight() / 3, null);
			g.drawImage(op.filter((BufferedImage) this.getImage(), null), 0, 0, null);
		}

		g.dispose();

		this.setImage(image);

		/*
		 * for (Entity b : bullets) { b.update(now); }
		 */
	}

	/**
	 * Rotate the player's gun
	 * 
	 * @param location point the rotate to face. (Usually the mouse pointer location)
	 */

	public void pointAt(Point.Double location) {
		Point.Double axis = new Point.Double(10.0, (double) gun.getIconHeight() / 2);
		double rads = Math.atan((location.y - axis.y) / (location.x - axis.x));
		setGunRotation(rads);
		gun.rotate(rads, axis);
	}

	/**
	 * Cause the player to fire a bullet
	 */

	public void shoot() {
		Entity temp = new Entity(this.getResource("bullet"));
		temp.setVelocity(new Point.Double(BULLET_SPEED * Math.cos(getGunRotation()), BULLET_SPEED * Math.sin(getGunRotation())));
		bullets.add(temp);
	}

	/**
	 * @return the gunRotation
	 */

	public double getGunRotation() {
		return gunRotation;
	}

	/**
	 * @param gunRotation the gunRotation to set
	 */

	private void setGunRotation(double gunRotation) {
		this.gunRotation = gunRotation;
	}

	public void keyPressed(KeyEvent e) { //TODO: Use a keydown handler with booleans instead of this
		Point.Double v = this.getVelocity();
		if (e.getKeyCode() == KeyEvent.VK_D) {
			this.setVelocity(new Point.Double(v.x + Player.WALK_SPEED, v.y));
		} else if (e.getKeyCode() == KeyEvent.VK_A) {
			this.setVelocity(new Point.Double(v.x - Player.WALK_SPEED, v.y));
		} else if (e.getKeyCode() == KeyEvent.VK_W) {
			if (!isFalling()) {
				this.setVelocity(new Point.Double(v.x, v.y - 25));
				this.setFalling(true);
			}
		}
	}

	public void keyReleased(KeyEvent e) {
		Point.Double v = this.getVelocity();
		if (e.getKeyCode() == KeyEvent.VK_D) {
			this.setVelocity(new Point.Double(v.x - Player.WALK_SPEED, v.y));
		} else if (e.getKeyCode() == KeyEvent.VK_A) {
			this.setVelocity(new Point.Double(v.x + Player.WALK_SPEED, v.y));
		}
	}
}
