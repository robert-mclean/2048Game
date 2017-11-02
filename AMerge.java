package rhmclean;

public class AMerge implements Runnable {
	Tile tile;
	Tile tile2;
	Tile[][] board;
	int x, y;
	View view = InstanceCreator.getView();
	
	
	public AMerge(Tile newTile, Tile newTile2, Tile[][] newBoard, int newY, int newX) {
		/*
		 * Tile and Tile2 are the two tiles that are merging together
		 * y and x are the coordinates of the new Tiles location
		 */
		tile = newTile;
		tile2 = newTile2;
		board = newBoard;
		y = newY;
		x = newX;
	}

	public void run() {
		int newVal = tile.getVal() * 2;
		
		/*
		 * Deletes the TilePainters associated with the merging tiles as well as the placeholder tile
		 */
		tile.delete();
		tile2.delete();
		board[y][x].delete();
		board[y][x] = null;

		board[y][x] = new Tile(y, x, newVal);//Creates the new tile

		view.repaint();//Repaints to represent the changes

	}

}
