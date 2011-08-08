package com.voracious.ep1cG4m3.utils;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;

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
		super(parseString(text, 7, 1, Color.BLACK), true);
		myText = text;
		mySpacing = 1;
		mySize = 7;
		myColor = Color.BLACK;
	}
	
	/**
	 * Create a Drawable string out of a regular String and Initialize it's location.
	 * 
	 * @param text
	 * @param point
	 */
	
	public Text(String text, Point point){
		super(parseString(text, 7, 1, Color.BLACK), true, point);
		myText = text;
		mySpacing = 1;
		mySize = 7;
		myColor = Color.BLACK;
	}
	
	public Text(String text, Point point, int size){
		super(parseString(text, size, 1, Color.BLACK), true, point);
		myText = text;
		mySpacing = 1;
		mySize = size;
		myColor = Color.BLACK;
	}
	
	public Text(String text, Point point, int size, int spacing){
		super(parseString(text, size, spacing, Color.BLACK), true, point);
		myText = text;
		mySpacing = spacing;
		mySize = size;
		myColor = Color.BLACK;
	}
	
	public Text(String text, Point point, int size, int spacing, Color color){
		super(parseString(text, size, spacing, color), true, point);
		myText = text;
		mySpacing = spacing;
		mySize = size;
		myColor = color;
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
	
	public static BufferedImage parseString(String text, int size, int spacing, Color color){
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
			
		int letterWidth = FONT_WIDTH+(size-FONT_HEIGHT); 
		int letterHeight = size;
		BufferedImage result = new BufferedImage(letterWidth*maxCharsPerLine + (maxCharsPerLine-1)*spacing, (letterHeight+1)*numLines, BufferedImage.TYPE_INT_ARGB);
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
				
				BufferedImage letter = fontImg.getSubimage(((charCode-32)%(fontImg.getWidth()/FONT_WIDTH))*FONT_WIDTH,((charCode-32)/(fontImg.getWidth()/FONT_WIDTH))*FONT_HEIGHT, FONT_WIDTH, FONT_HEIGHT);
				if(size != 7)
					letter = scale(letter, size);
				if(color != Color.WHITE)
					letter = changeColor(letter, color);
				g2.drawImage(letter, null, letterWidth*currentCharInLine + (currentCharInLine*spacing), letterHeight*currentLine + currentLine);
				currentCharInLine++;
			}
		}
		g2.dispose();
		return result;
	}
	
	public static BufferedImage scale(BufferedImage image, int size){
		BufferedImage result = new BufferedImage(FONT_WIDTH+(size-FONT_HEIGHT), size, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = result.createGraphics();
		
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
		g2.drawImage(image, 0, 0, FONT_WIDTH+(size-FONT_HEIGHT), size, null);
		g2.dispose();
		return result;
	}
	
	public static BufferedImage changeColor(BufferedImage image, final Color color){
		ImageFilter filter = new RGBImageFilter() {
            public final int filterRGB(int x, int y, int rgb) {
                    if (rgb == Color.WHITE.getRGB()) {
                        return color.getRGB();
                    } else {
                        return rgb;
                    }
            }
		};
		ImageProducer ip = new FilteredImageSource(image.getSource(), filter);
		Image out = Toolkit.getDefaultToolkit().createImage(ip);
		BufferedImage result = new BufferedImage(out.getWidth(null), out.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		result.createGraphics().drawImage(out, 0, 0, null);
		
		return result;
	}
	
	/**
	 * Change the string that is displayed.
	 * 
	 * @param text string to set to
	 */
	
	public void setText(String text){
		myText = text;
		setImage(parseString(text, getSize(), getSpacing(), getColor()));
	}
	
	/**
	 * Change the letter spacing.
	 * 
	 * @param spacing number of pixels between each letter. Must be >= 0.
	 */
	
	public void setSpacing(int spacing){
		if(spacing >= 0){
			mySpacing = spacing;
			setImage(parseString(myText, getSize(), getSpacing(), getColor()));
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
			setImage(parseString(myText, getSize(), getSpacing(), getColor()));
		}
	}
	
	/**
	 * Set the font color.
	 * 
	 * @param color color the text will be.
	 */
	
	public void setColor(Color color){
		myColor = color;
		setImage(parseString(myText, getSize(), getSpacing(), getColor()));
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
