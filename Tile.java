package rhmclean;

public class Tile {
	/*
	 * X_BORDER: Size of margin on the left side of the screen
	 * Y_BORDER: Size of margin on the top of the screen
	 * TILE_WIDTH: Length of a tile side
	 * SPACE: Space between the tiles
	 * x: horizontal position in the array
	 * y: vertical position in the array
	 * cornerX: X position in pixels of the top left corner
	 * cornerY: Y position in pixels of the top left corner
	 * val: Value stored in the tile
	 */
	final int X_BORDER = 100;
	final int Y_BORDER = 100;
	final int TILE_WIDTH = 70;
	final int SPACE = 15;
	private int x, y, cornerX, cornerY, val;
	View view = InstanceCreator.getView();
	TilePainter painter = new TilePainter(this);
	
	
	public Tile(int inY, int inX, int inVal){
		y = inY;
		x = inX;
		cornerY = Y_BORDER + (TILE_WIDTH * y) + (SPACE * y);
		cornerX = X_BORDER + (TILE_WIDTH * x) + (SPACE * x);
		val = inVal;
		view.addPaintListener(painter);
	}
	
	public int getX(){return x;}
	
	public void setX(int inX){
		x = inX;
		view.repaint();
	}
	
	public int getY(){return y;}

	public void setY(int inY){
		y = inY;
		view.repaint();
	}
	
	public int getVal(){return val;}
	
	public void setVal(int inVal){
		x = inVal;
		view.repaint();
	}
	
	public int getCornerY(){return cornerY;}
	
	public int getCornerX(){return cornerX;}

	public void move(int dY, int dX) {
		cornerY += dY;
		cornerX += dX;
		view.repaint();
	}

	public void delete(){
		/*
		 * Deletes the TilePainter associated with the tile
		 */
		view.listeners.remove(painter);
		view.size--;
		painter = null;
	}
}
