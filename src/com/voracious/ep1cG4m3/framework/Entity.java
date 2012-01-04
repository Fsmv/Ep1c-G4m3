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

import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;

import com.voracious.ep1cG4m3.screens.Play;
import com.voracious.ep1cG4m3.utils.Animation;
import com.voracious.ep1cG4m3.utils.Art;

/**
 * Like Drawable except this class has methods to facilitate animation and motion.
 * 
 * @author Voracious Softworks
 * @version 6/20/2011
 * @see Drawable
 */

public class Entity extends Drawable {
	private static final long serialVersionUID = -1211928635812350349L;
	private HashMap<String, Animation> animations = new HashMap<String, Animation>();
	private HashMap<String, BufferedImage> resources = new HashMap<String, BufferedImage>();
	private String currentAnimation = "";
	private boolean falling = false;
	private boolean gravity = false;
	private Point.Double velocity = new Point.Double(0, 0);
	private Point.Double acceleration = new Point.Double(0, 0);

	/**
	 * Initializes animations list, acceleration and speed. Runs the default constructor of Drawable.
	 * 
	 * @see Drawable
	 */

	public Entity() {
		super();
	}

	/**
	 * Initializes Entity and loads the resources from resource folder
	 * 
	 * @param width image width
	 * @param height image height
	 */

	public Entity(File resourceFolder) {
		super();
		loadAnimations(resourceFolder);
		loadResources(resourceFolder);
	}

	/**
	 * Initializes entity without Animation functionality
	 * 
	 * @param image the image to display
	 */

	public Entity(Image image) {
		super(image);
	}

	/**
	 * Adds an animation to the list of animations for this entity.
	 * 
	 * @param animationName name to identify the animation with
	 * @param animation animation object to be added
	 * @see Animation
	 */

	public void addAnimation(String animationName, Animation animation) {
		animations.put(animationName, animation);
	}

	/**
	 * Adds a resource to the list of resources for this entity.
	 * 
	 * @param resourceName name to identify the resource with
	 * @param resource resource object to be added
	 */

	public void addResource(String resourceName, BufferedImage resource) {
		resources.put(resourceName, resource);
	}

	/**
	 * Get a resource by its name
	 * 
	 * @param name the name of the resource to get
	 * @return the resource
	 * @throws IllegalArgumentException When animation doesn't exist
	 */

	public BufferedImage getResource(String name) {
		if (resources.containsKey(name))
			return resources.get(name);
		else
			throw new IllegalArgumentException("Resource (\"" + name + "\") not found");
	}

	/**
	 * Change the currently playing animation.
	 * 
	 * @param name animation name to change to
	 * @see Animation
	 */

	public void setAnimation(String name) {
		currentAnimation = name;
	}

	/**
	 * Supplies the list of animations.
	 * 
	 * @return list of animations
	 */

	public HashMap<String, Animation> getAnimations() {
		return animations;
	}

	/**
	 * Get an animation by its name
	 * 
	 * @param name the name of the animation to get
	 * @return the animation
	 * @throws IllegalArgumentException When animation doesn't exist
	 * @see Animation
	 */

	public Animation getAnimation(String name) {
		if (animations.containsKey(name))
			return animations.get(name);
		else
			throw new IllegalArgumentException("Animation (\"" + name + "\") not found");
	}

	/**
	 * Supplies the current animation.
	 * 
	 * @return current animation name
	 * @see Animation
	 */

	public Animation getCurrentAnimation() {
		try {
			return getAnimation(currentAnimation);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	/**
	 * Causes the Entity to accelerate, move, and to display the next animation frame.
	 * 
	 * @see Animation
	 * @param dt delta time, Amount of time elapsed since last update in milliseconds
	 */

	long last = System.currentTimeMillis();

	public void update(long now) {
		long dt = (now - last) / 10;

		Point loc = getLocation();
		Point.Double vel = getVelocity();
		Point.Double acc = getAcceleration();

		if (gravity && falling) {
			this.setAcceleration(new Point.Double(acc.x, Play.GRAVITY));
		} else if (!falling) {
			this.setAcceleration(new Point.Double(acc.x, 0));
			this.setVelocity(new Point.Double(vel.x, 0));
		}
		acc = getAcceleration();
		vel = getVelocity();

		vel.setLocation(vel.getX() + (acc.getX() * dt), vel.getY() + (acc.getY() * dt));
		this.setVelocity(vel);
		loc.setLocation(loc.getX() + (vel.getX() * dt), loc.getY() + (vel.getY() * dt));
		this.setLocation(loc);

		if (getCurrentAnimation() != null) {
			this.setImage(getCurrentAnimation().getImage());
		}
		last = now;
	}

	/**
	 * Cuts the animation frames out of the source image.
	 * 
	 * @param resourceFolder the folder that contains the source image
	 */

	/*
	 * This method has some code that is nearly the same as some code in the Art class. The similar code is in the method loadTiles This method is also similar to the loadResources method.
	 */

	private void loadAnimations(File resourceFolder) {
		BufferedImage frames = Art.loadImage(resourceFolder.getPath() + "/" + resourceFolder.getName() + ".png");

		int tempColor = frames.getRGB(0, 0);
		int width = (tempColor & 0xff0000) / 0x10000;
		int height = (tempColor & 0x00ff00) / 0x100;

		for (int xx = 1; xx < frames.getWidth(); xx++) {
			int color = frames.getRGB(xx, 0);
			if (frames.getRGB(xx, 0) == 0xffffffff)
				break;

			if (xx % 5 == 1) {
				String hex = "";
				for (int i = 0; i < 4; i++) {
					color = frames.getRGB(xx + i, 0) - 0xff000000;
					int a = (color & 0xff0000) / 0x10000;
					int b = (color & 0x00ff00) / 0x100;
					int c = (color & 0x0000ff);

					if (a == 0) {
						break;
					} else if (b == 0) {
						hex += Integer.toHexString(a);
						break;
					} else if (c == 0) {
						hex += Integer.toHexString((a * 0x100) + b);
						break;
					} else
						hex += Integer.toHexString(color);
				}
				xx += 4;

				int numFrames = frames.getRGB(xx, 0) - 0xff000000;
				Animation temp = new Animation();
				for (int i = 0; i < numFrames; i++) {
					temp.addFrame(frames.getSubimage((i * width) % frames.getWidth(), (height * ((i * width) / frames.getWidth())) + 1, width, height));
				}

				addAnimation(Art.hexToString(hex), temp);
			}
		}
	}

	/**
	 * Cuts the resource images out of the source image.
	 * 
	 * @param resourceFolder the folder that contains the source image
	 */

	private void loadResources(File resourceFolder) {
		if (new File(resourceFolder.getPath(), "rsc.png").exists()) {
			BufferedImage rsc = Art.loadImage(resourceFolder.getPath() + "/rsc.png");

			for (int xx = 0; xx < rsc.getWidth(); xx++) {
				if (rsc.getRGB(xx, 0) == 0xffffffff && rsc.getRGB(xx, 1) == 0xffffffff)
					break;
				if (xx % 3 == 0) {
					String hex = "";
					outter: for (int col = 0; col < 2; col++) {
						for (int row = 0; row < 2; row++) {
							int color = rsc.getRGB(xx + row, col) - 0xff000000;
							int a = (color & 0xff0000) / 0x10000;
							int b = (color & 0x00ff00) / 0x100;
							int c = (color & 0x0000ff);
							if (a == 0) {
								break outter;
							} else if (b == 0) {
								hex += Integer.toHexString(a);
								break outter;
							} else if (c == 0) {
								hex += Integer.toHexString((a * 0x100) + b);
								break outter;
							} else
								hex += Integer.toHexString(color);
						}
					}
					xx++;
					String name = Art.hexToString(hex);

					int colorTop = rsc.getRGB(xx + 1, 0) - 0xff000000;
					int colorBottom = rsc.getRGB(xx + 1, 1) - 0xff000000;

					int x = (colorTop & 0xff0000) / 0x10000;
					int y = (colorTop & 0x00ff00) / 0x100;
					int width = (colorBottom & 0xff0000) / 0x10000;
					int height = (colorBottom & 0x00ff00) / 0x100;

					addResource(name, rsc.getSubimage(x, y, width, height));
				}
			}
		}
	}

	/**
	 * @param velocity velocity to set
	 */

	public void setVelocity(Point.Double velocity) {
		this.velocity = velocity;
	}

	/**
	 * @return velocity
	 */

	public Point.Double getVelocity() {
		return velocity;
	}

	/**
	 * @param acceleration acceleration to set
	 */

	public void setAcceleration(Point.Double acceleration) {
		this.acceleration = acceleration;
	}

	/**
	 * @return acceleration
	 */

	public Point.Double getAcceleration() {
		return acceleration;
	}

	/**
	 * @return the gravity
	 */
	public boolean hasGravity() {
		return gravity;
	}

	/**
	 * @param gravity the gravity to set
	 */
	public void setGravity(boolean gravity) {
		this.gravity = gravity;
	}

	/**
	 * @return the falling
	 */
	public boolean isFalling() {
		return falling;
	}

	/**
	 * @param falling the falling to set
	 */
	public void setFalling(boolean falling) {
		this.falling = falling;
	}

	public static Entity getEntity(int i) {
		//TODO:
		return null;
	}
}