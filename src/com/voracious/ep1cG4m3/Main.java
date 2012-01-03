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

package com.voracious.ep1cG4m3;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.voracious.ep1cG4m3.framework.Screen;
import com.voracious.ep1cG4m3.screens.Instructions;
import com.voracious.ep1cG4m3.screens.LevelEditor;
import com.voracious.ep1cG4m3.screens.Menu;
import com.voracious.ep1cG4m3.screens.Play;
import com.voracious.ep1cG4m3.screens.Preloader;
import com.voracious.ep1cG4m3.utils.Logger;
import com.voracious.ep1cG4m3.utils.ScreenResultListener;

/**
 * This is the main class that coordinates all of the screens and owns the
 * window.
 * 
 * @author Voracious Softworks
 */

public class Main extends JPanel implements ScreenResultListener, ActionListener {
    private static final long serialVersionUID = 9198379891709086094L;

    private JPanel panel;
    private Timer timer;

    private int currentScreen = -1;
    private Screen screens[] = {new Preloader(this, 0), new Menu(this, 1), new Play(this, 2), new Instructions(this, 3), new LevelEditor(this, 4) };

    public static void main(String[] args) {
	new Main();
    }

    public Main() {
	JFrame frame = new JFrame("Ep1c G4m3");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	panel = (JPanel) frame.getContentPane();
	panel.setPreferredSize(new Dimension(640, 512));
	CardLayout layout = new CardLayout();
	panel.setLayout(layout);
	panel.setOpaque(false);
	Logger.setLogFile(new File("log.txt"));

	for (int i = 0; i < screens.length; i++)
	    panel.add(screens[i], Integer.toString(i));

	frame.validate();
	frame.pack();
	frame.setResizable(false);
	frame.setVisible(true);

	switchScreen(0);
	setTimer(new Timer(0, this));
	timer.start();
    }

    /**
     * Display a different screen
     * 
     * @param id
     *            the id of the screen to switch to
     */

    public void switchScreen(int id) {
	CardLayout cl = (CardLayout) panel.getLayout();
	if (currentScreen >= 0 && currentScreen < screens.length) {
	    screens[currentScreen].stop();
	}

	if (id >= 0 && id < screens.length) {
	    currentScreen = id;
	    cl.show(panel, Integer.toString(currentScreen));
	    screens[currentScreen].start();
	} else {
	    throw new IllegalArgumentException("ERROR: Screen (id = " + id + ") not found. At main.switchScreen(id).");
	}
    }

    @Override
    public void onScreenResult(int id, int resultCode) {
	switch (id) {
	case 0:
	    if (resultCode == Preloader.RESULT_OK)
		switchScreen(1);
	    break;
	case 1:
	    if (resultCode == Menu.RESULT_PLAY)
		switchScreen(2);
	    else if (resultCode == Menu.RESULT_INSTRUCTIONS)
		switchScreen(3);
	    else if (resultCode == Menu.RESULT_LEVEL_EDITOR)
		switchScreen(4);
	}
    }

    long lastTime;

    @Override
    public void actionPerformed(ActionEvent e) {
	lastTime = System.currentTimeMillis();
	
	if (currentScreen >= 0 && currentScreen < screens.length) {
	    screens[currentScreen].render();
	    screens[currentScreen].update();
	}
	
	try {
	    Thread.sleep(10);
	} catch (InterruptedException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}

	long mspf = System.currentTimeMillis() - lastTime; //milliseconds per frame
	int fps = (int) (1.0 / (mspf / 1000.0));
	Play.sendFps(fps);
    }

    /**
     * @param timer
     *            the timer to set
     */

    public void setTimer(Timer timer) {
	this.timer = timer;
    }

    /**
     * @return the timer
     */

    public Timer getTimer() {
	return timer;
    }
}
