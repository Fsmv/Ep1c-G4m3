package com.voracious.ep1cG4m3.screens;

import java.awt.Graphics;
import java.awt.Graphics2D;

import com.voracious.ep1cG4m3.framework.Screen;
import com.voracious.ep1cG4m3.utils.ScreenResultEvent;

public class Instructions extends Screen {
	private static final long serialVersionUID = 6891345264117973033L;

	public Instructions(int id, ScreenResultEvent listener) {
		super(id, listener);
	}

	@Override
	public void start() {
	}

	@Override
	public void stop() {
	}
	
	@Override
	public void paint(Graphics g){
		super.paint(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.drawString("Instructions", 10, 25);
	}
}
