package rhmclean;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

public class View extends Component {
	List<TilePainter> listeners = new ArrayList<TilePainter>();
	int size;
	final int xBorder = 100;
	final int yBorder = 100;
	final int tileWidth = 70;
	final int space = 15;
	
	public View(){
		setFocusable(true);
	}
	
	public void paint(Graphics g){
		/*
		 * Iterates through the TilePainters in listeners and paints each one
		 */
		super.paint(g);
		Graphics2D g2D = (Graphics2D)g;
		for (int index = 0; index < size; index++) {
			listeners.get(index).paint(g2D);
        }
		
		/*
		 * Prints the outline of the board and instructions
		 */
		g.setColor(Color.BLACK);
		g2D.drawLine(xBorder, yBorder, (xBorder+(tileWidth*4)+(space*3)), yBorder);//Top Line
		g2D.drawLine(xBorder, (yBorder+(tileWidth*4)+(space*3)), (xBorder+(tileWidth*4)+(space*3)), (yBorder+(tileWidth*4)+(space*3)));//Bottom
		g2D.drawLine(xBorder, yBorder, xBorder, (yBorder+(tileWidth*4)+(space*3)));//Left
		g2D.drawLine((xBorder+(tileWidth*4)+(space*3)), yBorder, (xBorder+(tileWidth*4)+(space*3)), (yBorder+(tileWidth*4)+(space*3)));//Right
		g2D.drawString("Move and combine the tiles using the arrow key", xBorder, yBorder/2);//Instruction

	}
	
	public void addPaintListener(TilePainter newListener){
		if (!listeners.contains(newListener)){
			listeners.add(newListener);
			size++;   
		}
    }
}
