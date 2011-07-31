package com.voracious.ep1cG4m3.screens;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.swing.Timer;

import com.voracious.ep1cG4m3.entities.Player;
import com.voracious.ep1cG4m3.framework.Entity;
import com.voracious.ep1cG4m3.framework.Screen;
import com.voracious.ep1cG4m3.framework.Tile;
import com.voracious.ep1cG4m3.framework.TileFactory;
import com.voracious.ep1cG4m3.utils.Point;
import com.voracious.ep1cG4m3.utils.ScreenResultEvent;

public class Play extends Screen implements ActionListener{

	/**
	 * Generated by eclipse 
	 */
	private static final long serialVersionUID = -1522258565924156537L;
	
	public static int currentLevel = 1;
	public static int currentWorld = 1;
	
	
	private int levelTiles[][] = new int[16][20];
	private int entitiesStart[][] = new int[16][20];
	
	private ArrayList<Tile> gameTiles = new ArrayList<Tile>();
	private ArrayList<Entity> gameEnemies = new ArrayList<Entity>();
	
	private Player player;
	private Timer timer;

	public Play(int id, ScreenResultEvent game) {
		super(id, game);
		timer = new Timer(1000/36, this);
	}

	@Override
	public void start() {
		player = new Player();
		loadLevel(currentLevel, currentWorld);
		for(int r=0; r<entitiesStart.length; r++){
			for(int c=0; c<entitiesStart[0].length; c++){
				if(entitiesStart[r][c] == 1){
					player.setLocation(new Point(c*TileFactory.TILE_SIZE,(r*TileFactory.TILE_SIZE)-Player.HEIGHT));
				}
			}
		}
		timer.start();
		addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_W){
                	player.jump();
                }else if(e.getKeyCode() == KeyEvent.VK_A){
                	
                }else if(e.getKeyCode() == KeyEvent.VK_S){
                	
                }else if(e.getKeyCode() == KeyEvent.VK_D){
                	
                }
            }
        });
	}

	@Override
	public void stop() {
		timer.stop();
	}
	
	public Player getPlayer(){
		return player;
	}
	
	public void loadNextLevel(){
		currentLevel++;
		if(currentLevel > 10){
			currentLevel = 1;
			currentWorld++;
		}
		loadLevel(currentLevel, currentWorld);
	}
	
	public void loadLevel(int level, int world){
		InputStreamReader reader = new InputStreamReader(Play.class.getResourceAsStream("/"+world+"/"+level));
		char[] arr = new char[1961]; //level files are always exactly 1961 bytes (For now there is no scrolling)
		StringBuffer buf = new StringBuffer();
		int numChars;
		try {
			numChars = reader.read(arr, 0, arr.length);
			buf.append(arr, 0, numChars);
			String levelData = buf.toString();
			int phase = 0;
			int col = 0;
			int row = 0;
			for(int i=0; i<levelData.length(); i++){
				if(levelData.charAt(i) == '<'){
					if(levelData.substring(i+1, i+7).equals("tiles>")){
						i += 8;
						phase = 1;
						col = 0;
						row = 0;
					}
					if(levelData.substring(i+1, i+10).equals("entities>")){
						i += 11;
						phase = 2;
						col = 0;
						row = 0;
					}
				}
				if(phase == 1){
					if(levelData.charAt(i) == ' '){
						i++;
						col++;
					}else if(levelData.charAt(i) == '\n'){
						i++;
						row++;
						col = 0;
					}
					if(levelData.substring(i-1, i+2).equals("\n</"))
						phase = 0;
					else{
						levelTiles[row][col] = Integer.parseInt(levelData.substring(i, i+2))-1;
						i++; //I only add one here because the loop will also add one right after this. Next iteration it will be at the space after each set of two numbers in the file. Allowing the program to increment row and col.
					}
				}else if(phase == 2){
					if(levelData.charAt(i) == ' '){
						i++;
						col++;
						if(col > 9){
							col = 0;
							row++;
						}
					}
					entitiesStart[row][col] = Integer.parseInt(levelData.substring(i, i+2))-1;
					i += 2;
					if(levelData.substring(i, i+3).equals("\n</"))
						phase = 0;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update(){
		player.update();
		repaint();
	}
	
	@Override
	public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		for(int r=0; r<levelTiles.length; r++){
			for(int c=0; c<levelTiles[0].length; c++){
				gameTiles.add(TileFactory.getNewTile(levelTiles[r][c]));
				if(gameTiles.get(gameTiles.size()-1) == null)
					gameTiles.remove(gameTiles.size()-1);
				else{
					gameTiles.get(gameTiles.size()-1).setLocation(new Point(c*TileFactory.TILE_SIZE,r*TileFactory.TILE_SIZE));
					gameTiles.get(gameTiles.size()-1).draw(g2);
				}
			}
		}
		player.draw(g2);
		super.paintComponent(g);
		Toolkit.getDefaultToolkit().sync();
	    g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		update();
	}
}
