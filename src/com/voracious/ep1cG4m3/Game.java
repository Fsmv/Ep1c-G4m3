package com.voracious.ep1cG4m3;

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

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.voracious.ep1cG4m3.entities.Player;
import com.voracious.ep1cG4m3.framework.Screen;
import com.voracious.ep1cG4m3.framework.TileFactory;
import com.voracious.ep1cG4m3.utils.ScreenResultEvent;
import com.voracious.ep1cG4m3.utils.Text;
import com.voracious.ep1cG4m3.screens.*;

/**
 * Main class. It makes the window and handles screen switching.
 * 
 * @author Voracious Softworks
 * @version 6/20/2011
 * @see JApplet
 */

public class Game extends JFrame implements ScreenResultEvent {
	
	private static final long serialVersionUID = 4442265073346973079L;
	public static final int ID_LOADER = -1;
	public static final int ID_MENU = 0;
	public static final int ID_PLAY = 1;
	public static final int ID_INSTRUCTIONS = 2;
	public static final int ID_LEVEL_EDITOR = 3;
	
	private String filePaths[] = {"/font.png", "/entities/player.png", "/tiles.png"};
	private BufferedImage images[];
	private JPanel cards;
	private int currentScreen;
	private Preloader loader;
	private Menu menu;
	private Play play;
	private Instructions instructions;
	private LevelEditor levelEditor;
	
	public Game(){
		setTitle("Ep1c G4m3");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(true);
		
		loader = new Preloader(ID_LOADER, this);
		loader.setVisible(true);
		loader.setOpaque(false);
		loader.setPreferredSize(new Dimension(640, 512));
		menu = new Menu(ID_MENU, this);
		menu.setVisible(true);
		menu.setOpaque(false);
		menu.setPreferredSize(new Dimension(640, 512));
		play = new Play(ID_PLAY, this);
		play.setVisible(true);
		play.setOpaque(false);
		play.setPreferredSize(new Dimension(640, 512));
		instructions = new Instructions(ID_INSTRUCTIONS, this);
		instructions.setVisible(true);
		instructions.setOpaque(false);
		instructions.setPreferredSize(new Dimension(640, 512));
		levelEditor = new LevelEditor(ID_LEVEL_EDITOR, this);
		levelEditor.setVisible(true);
		levelEditor.setOpaque(false);
		levelEditor.setPreferredSize(new Dimension(640, 512));
		
		currentScreen = ID_LOADER;
		
		cards = new JPanel(new CardLayout());
		
		cards.add(getScreenFromId(ID_LOADER), "loader");
		cards.add(getScreenFromId(ID_MENU), "menu");
		cards.add(getScreenFromId(ID_PLAY), "play");
		cards.add(getScreenFromId(ID_INSTRUCTIONS), "instructions");
		cards.add(getScreenFromId(ID_LEVEL_EDITOR), "level editor");
		getScreenFromId(getCurrentScreenId()).start();
		
		add(cards);
		validate();
		pack();
		images = loader.loadImages(filePaths);
		sendImages();
		try {
			Thread.sleep(2500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		switchCurrentScreen(ID_MENU);
	}
	
	public static void main(String[] args){
		new Game();
	}
	
	public void sendImages(){
		Text.setFont(getImageFromPath("/font.png"));
		Player.setAnimationSource(getImageFromPath("/entities/player.png"));
		TileFactory.setResourceImage(getImageFromPath("/tiles.png"));
		TileFactory.findAllTextures();
	}
	
	public BufferedImage getImageFromPath(String path){
		for(int i=0; i<filePaths.length; i++){
			if(filePaths[i].equals(path))
				return images[i];
		}
		return null;
	}
	
	@Override
	public void onScreenResult(int id, int result){
		switch(id){
			case ID_MENU:
				if(result == Menu.RESULT_PLAY)
					switchCurrentScreen(ID_PLAY);
				else if(result == Menu.RESULT_INSTRUCTIONS)
					switchCurrentScreen(ID_INSTRUCTIONS);
				else if(result == Menu.RESULT_LEVEL_EDITOR)
					switchCurrentScreen(ID_LEVEL_EDITOR);
				else
					System.out.println("ERROR: Unknown result type " + result);
				break;
			default:
				System.out.println("WARNING: Unknown id ("+id+") returned a result ("+result+")!");
				break;
		}
	}
	
	public int getCurrentScreenId(){
		return currentScreen;
	}
	
	public void switchCurrentScreen(int id){
		CardLayout cl = (CardLayout)cards.getLayout();
		getScreenFromId(currentScreen).stop();
		currentScreen = id;
		getScreenFromId(currentScreen).start();
		cl.show(cards, getNameFromId(currentScreen));
	}
	
	public Screen getScreenFromId(int id){
		switch(id){
			case ID_LOADER:
				return loader;
			case ID_MENU:
				return menu;
			case ID_PLAY:
				return play;
			case ID_INSTRUCTIONS:
				return instructions;
			case ID_LEVEL_EDITOR:
				return levelEditor;
			default:
				System.out.println("ERROR: screen not found id:" + id);
				return null;
		}
	}
	
	public String getNameFromId(int id){
		switch(id){
			case ID_LOADER:
				return "loader";
			case ID_MENU:
				return "menu";
			case ID_PLAY:
				return "play";
			case ID_INSTRUCTIONS:
				return "instructions";
			case ID_LEVEL_EDITOR:
				return "level editor";
			default:
				System.out.println("ERROR: screen not found id:" + id);
				return null;
		}
	}
}
