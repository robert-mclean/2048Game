package rhmclean;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class TilePainter {
	final int xBorder = 100;
	final int yBorder = 100;
	final int tileWidth = 70;
	final int space = 15;
	Tile toPaint;
	int cornerX, cornerY; //Holds the coordinates of the top right corner

	public TilePainter(Tile tile) {
		toPaint = tile;
	}

	public void paint(Graphics2D g) {
		
		if(toPaint.getVal() != 1){
			//Top Line
			cornerX = toPaint.getCornerX();
			cornerY = toPaint.getCornerY();
			
			/*
			 * Assigns the color the tile is painted based on the value stored inside it
			 */
			if(toPaint.getVal() == 2){
				g.setColor(Color.WHITE);
			}else if(toPaint.getVal() == 4){
				g.setColor(Color.lightGray);
			}else if(toPaint.getVal() == 8){
				g.setColor(new Color(75, 156, 211));//UNC Blue
			}else if(toPaint.getVal() == 16){
				g.setColor(new Color(19, 41, 75));//UNC Navy
			}else if(toPaint.getVal() == 32){
				g.setColor(new Color(255, 153, 204));//Pink
			}else if(toPaint.getVal() == 64){
				g.setColor(Color.ORANGE);//TODO
			}else if(toPaint.getVal() == 128){
				g.setColor(Color.RED);//TODO
			}else if(toPaint.getVal() == 256){
				g.setColor(new Color(102, 255, 51));//Light Green
			}else if(toPaint.getVal() == 512){
				g.setColor(new Color(0, 153, 51));//Dark Green
			}else if(toPaint.getVal() == 1024){
				g.setColor(Color.CYAN);
			}else if(toPaint.getVal() == 2048){
				g.setColor(Color.YELLOW);
			}
			
			//Paints the tile the assigned color
			g.fill(new Rectangle(cornerX, cornerY, tileWidth, tileWidth));
			
			int centerX = cornerX + (tileWidth / 2);
			int centerY = cornerY + (tileWidth / 2);
			
			
			/*
			 * If the tile color is white, the number color must be set to black.  In all other instances, the number the white
			 */
			if(toPaint.getVal() == 2){
				g.setColor(Color.BLACK);
			}else{
				g.setColor(Color.WHITE);
			}
			
			g.setFont(Font.decode(Font.SANS_SERIF));//Sans-serif seemed a slightly more aesthetic font choice
			
			
			/*
			 * This block determines the number of characters in the number and offsets it to the left
			 */
			int charPixels = 7;
			int numOfChar = 0;
			int charVal = toPaint.getVal();
			while(charVal != 0){
				charVal /= 10;
				numOfChar++;
			}
			
			/*
			 * fontX and fontY are the offset coordinates
			 */
			int fontX = centerX - ((numOfChar) * charPixels)/2;
			int fontY = centerY + charPixels;
				
			g.drawString(Integer.toString(toPaint.getVal()), fontX, fontY);//the number is printed
		}
	}

}
