package rhmclean;

public class AShift implements Runnable {
	final int TILE_WIDTH = 70;
	final int SPACE = 15;//Space between each tile
	final int NUMBER_OF_ITERATIONS = 85;
	Tile tile;
	int toX, toY;
	AMerge merge;
	
	public AShift(Tile newTile, int newY, int newX){
		tile = newTile;
		toY = newY;
		toX = newX;
	}
	
	public AShift(Tile newTile, int newY, int newX, AMerge newMerge){
		tile = newTile;
		toY = newY;
		toX = newX;
		merge = newMerge;
	}

	public void  run() {
		/*
		 * Determines the change in either X or Y value in number of tiles
		 */
		int dX = toX - tile.getX();
		int dY = toY - tile.getY();
		
		/*
		 * Calculates number of pixels moved by multiplying dX by the number of pixels for one shift
		 */
		dX *= (TILE_WIDTH + SPACE);
		dY *= (TILE_WIDTH + SPACE);
		
		/*
		 * The total number of pixels is divided by the number of iterations to give the amount shifted during each cycle
		 */
		dX /= NUMBER_OF_ITERATIONS;
		dY /= NUMBER_OF_ITERATIONS;
		
		for(int i = 0; i < NUMBER_OF_ITERATIONS; i++){
			tile.move(dY, dX);
			try {
				Thread.sleep(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}	
		
		/*
		 * Changes the position in the array saved to the tile
		 */
		tile.setX(toX);
		tile.setY(toY);
		
		if(merge != null){
			merge.run();
		}
	}

}
