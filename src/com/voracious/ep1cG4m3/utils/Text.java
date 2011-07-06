package com.voracious.ep1cG4m3.utils;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.voracious.ep1cG4m3.framework.Drawable;

/**
 * Utility class for drawing text in the game's font.
 * 
 * @author Voracious Softworks
 */

public class Text extends Drawable {
	
	public static final int FONT_WIDTH = 5;
	public static final int FONT_HEIGHT = 7;
	
	public static BufferedImage fontImg;
	
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
		super(parseString(text, 7, 1), true);
	}
	
	/**
	 * Create a Drawable string out of a regular String and Initialize it's location.
	 * 
	 * @param text
	 * @param point
	 */
	
	public Text(String text, Point point){
		super(parseString(text, 7, 1), true, point);
	}
	
	public Text(String text, Point point, int size, int spacing){
		super(parseString(text, size, spacing), true, point);
	}
	
	public static void setFont(BufferedImage image){
		fontImg = image;
	}
	
	/**
	 * Use the font file to create an image of text.
	 * 
	 * @param text string to parse
	 * @return string converted into an image
	 */
	
	public static BufferedImage parseString(String text, int size, int spacing){
			int numLines = 1;
			int maxCharsPerLine = 0;
			
			int tempCount = 0;
			for(int i=0; i<text.length(); i++){
				char tempChar = text.charAt(i);
				
				if(tempChar == '\n'){
					numLines++;
					tempCount = 0;
				}else{
					tempCount++;
				}

				if(tempCount > maxCharsPerLine)
					maxCharsPerLine = tempCount;
			}
			
			BufferedImage result = new BufferedImage(FONT_WIDTH*maxCharsPerLine + (maxCharsPerLine-1)*spacing, (FONT_HEIGHT+1)*numLines, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2 = result.createGraphics();
			
			int currentLine = 0;
			int currentCharInLine = 0;
			for(int i=0; i<text.length(); i++){
				if(text.charAt(i) == '\n'){
					currentLine++;
					currentCharInLine = 0;
				}else{
					int charCode = (int)text.charAt(i);
					if(charCode < 32 && charCode > 176){
						charCode = 177; //This is the fallback character.
					}
					g2.drawImage(fontImg.getSubimage(((charCode-32)%(fontImg.getWidth()/FONT_WIDTH))*FONT_WIDTH,((charCode-32)/(fontImg.getWidth()/FONT_WIDTH))*FONT_HEIGHT,FONT_WIDTH,FONT_HEIGHT),null,FONT_WIDTH*currentCharInLine + currentCharInLine,FONT_HEIGHT*currentLine + currentLine);
					currentCharInLine++;
				}
			}
			g2.dispose();
			return result;
	}
	
	/**
	 * Change the string that is displayed.
	 * 
	 * @param text string to set to
	 */
	
	public void setText(String text){
		myText = text;
		setImage(parseString(text, getSize(), getSpacing()));
	}
	
	/**
	 * Change the letter spacing.
	 * 
	 * @param spacing number of pixels between each letter. Must be >= 0.
	 */
	
	public void setSpacing(int spacing){
		if(spacing >= 0){
			mySpacing = spacing;
			setImage(parseString(myText, getSize(), getSpacing()));
		}
	}
	
	/**
	 * Change the font size.
	 * 
	 * @param size pixels to be added to each side.
	 */
	
	public void setSize(int size){
		if(size > 0){
			mySize = size;
			setImage(parseString(myText, getSize(), getSpacing()));
		}
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
