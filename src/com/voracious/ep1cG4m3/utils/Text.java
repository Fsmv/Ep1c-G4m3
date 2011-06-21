package com.voracious.ep1cG4m3.utils;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.voracious.ep1cG4m3.framework.Drawable;

/**
 * Utility class for drawing text in the game's font.
 * 
 * @author Voracious Softworks
 */

public class Text extends Drawable {
	
	public static final String IMAGE_RESOURCE = "font.png";
	public static final int FONT_WIDTH = 5;
	public static final int FONT_HEIGHT = 7;
	
	private String myText;
	private int mySpacing;
	private int mySize;
	private Color myColor;
	
	/**
	 * Make an empty object
	 */
	
	public Text(){
		super();
	}
	
	/**
	 * Create a Drawable string out of a regular String.
	 * 
	 * @param text string to be made into an image
	 */
	
	public Text(String text){
		super(parseString(text), true);
	}
	
	/**
	 * Create a Drawable string out of a regular String and Initialize it's location.
	 * 
	 * @param text
	 * @param point
	 */
	
	public Text(String text, Point point){
		super(parseString(text), true, point);
	}
	
	/**
	 * Use the font file to create an image of text.
	 * 
	 * @param text string to parse
	 * @return string converted into an image
	 */
	
	public static BufferedImage parseString(String text){
		try {
			BufferedImage fontImg = ImageIO.read(new File("/res/", IMAGE_RESOURCE));
			/* This line could be usefull: img = img.getSubimage(x, y, width, height);
			 * Do everything within the try{} block
			 * I'm sorry I'm not sure how to create an image and append to it. But here is the BufferedImage
			 * Documentation: http://download.oracle.com/javase/1.4.2/docs/api/java/awt/image/BufferedImage.html
			 * You could at least write the rest of it and leave a todo comment and I'll figure it out.
			 */
			return fontImg;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} //TODO: Implement method completely
		/*
		 * The letters are in ascii order starting at number 32 and going to 176.
		 * So, they can be found by looping through the string and
		 * converting each character into and int. Then the letter graphic if the int is > 176 set it equal to 177,
		 * the location of the fallback character. the position you want will be at: (math unverified)
		 * (fontImg.getWidth()%(FONT_WIDTH*((int)char-32)), fontImg.getHeight()%(FONT_HEIGHT*((int)char-32)))
		 * Get all the letter images and concatinate into one larger image with the space inbetween them as
		 * mySpacing. Then scale the image to: newHeight=LETTER_HEIGHT+mySize; newWidth=(LETTER_WIDTH+mySize)*text.length
		 * Finally add a filter on the image of myColor at opacity 99% (Hopefully the transparent parts won't change. 
		 * if they do we'll just remove the color option.). 
		 */
	}
	
	/**
	 * Change the string that is displayed.
	 * 
	 * @param text string to set to
	 */
	
	public void setText(String text){
		myText = text;
		setImage(parseString(text));
	}
	
	/**
	 * Change the letter spacing.
	 * 
	 * @param spacing number of pixels between each letter. Must be >= 0.
	 */
	
	public void setSpacing(int spacing){
		if(spacing >= 0){
			mySpacing = spacing;
			setImage(parseString(myText));
		}
	}
	
	/**
	 * Change the font size.
	 * 
	 * @param size pixels to be added to each side.
	 */
	
	public void setSize(int size){
		mySize = size;
	}
	
	/**
	 * Set the font color.
	 * 
	 * @param color color the text will be.
	 */
	
	public void setColor(Color color){
		myColor = color;
	}
	
	/**
	 * Supplies the image string.
	 * 
	 * @return string Image text
	 */
	
	@Override
	public String toString(){
		return myText;
	}
	
	/**
	 * Supplies the font size.
	 * 
	 * @return size modifier
	 */
	
	public int getSize(){
		return mySize;
	}
	
	/**
	 * Supplies the font spacing.
	 * 
	 * @return pixels between each letter
	 */
	
	public int getSpacing(){
		return mySpacing;
	}
	
	/**
	 * Supplies font color.
	 * 
	 * @return font color
	 */
	
	public Color getColor(){
		return myColor;
	}
}
