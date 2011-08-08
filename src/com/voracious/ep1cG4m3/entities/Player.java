package com.voracious.ep1cG4m3.entities;

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

import java.awt.Point;
import java.util.ArrayList;

import com.voracious.ep1cG4m3.framework.Entity;
import com.voracious.ep1cG4m3.framework.Tile;

public class Player extends Entity {
	public static final int WIDTH = 25;
	public static final int HEIGHT = 50;
	
	
	public ArrayList<Tile> tiles;
	public boolean falling = false;
	
	public Player(){
		super();
	}
	
	public void jump(){
		falling = true;
		Point vel = getVelocity();
		Point acc = getAccelleration();
		setVelocity(vel.getX(), vel.getY()-20);
		setAccelleration(acc.getX(), acc.getY()+2);
	}
	
	@Override
	public void update(){
		Point vel = getVelocity();
		Point acc = getAccelleration();
		if(falling){
			if(vel.getY() > 10)
				setAccelleration(acc.getX(), 0);
			for(int i=0; i<tiles.size(); i++){
				Tile temp = tiles.get(i);
				if(temp.hitTest(this)){
					falling = false;
					this.setVelocity(vel.getX(), 0);
					this.setAccelleration(acc.getX(), 0);
					this.setLocation(new Point((int)(this.getLocation().getX()), (int)(temp.getLocation().getY()-Player.HEIGHT)));
				}
			}
		}
		super.update();
	}

	public void setLevel(ArrayList<Tile> levelTiles){
		tiles = levelTiles;
	}
}
