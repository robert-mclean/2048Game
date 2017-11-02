package rhmclean;

import java.util.ArrayList;
import java.util.List;

public class TileBoard {
	final int SIDE_LENGTH = 4;

	Tile[][] board = new Tile[SIDE_LENGTH][SIDE_LENGTH];

	
	public void upShift(){
		/*
		 * Iterates through each column beginning with the left. Within each column, starts at the second row and iterates down while 
		 * shifting each tile to the highest open spot.  If the tile above the one being shifted contains the same value, they are merged 
		 * to form a single tile with a value double of the original of each.
		 * 
		 * Each time a tile is shifted, an AShift Thread is created but not started and added to to shiftThreads. The AShift thread completes 
		 * the animation of tiles shifting.  If two tiles are merged, an AMerge object is passed as an argument to the AShift constructor
		 * which is run following the completion of the animation.  The AMerge deletes two two tiles that are merging and creates a new tile in their place.
		 */
		List<Thread> shiftThreads = new ArrayList<Thread>();
		int i,j,k;
		boolean shifted = false;//Changed to true if a shift occurs
		
		for(j = 0; j < SIDE_LENGTH; j++){
			for(i = 1; i < SIDE_LENGTH; i++){
				if(board[i][j] == null)continue;
				k = i - 1;
				while(!(k<1)){
					/*
					 * This loop finds the next tile up from board[i][j]
					 */
					if(board[k][j] != null)break;
					k--;
				}
				if(board[k][j] == null){
					shiftThreads.add(new Thread(new AShift(board[i][j], k, j)));
					board[k][j] = board[i][j]; 
					board[i][j] = null;
					shifted = true;
				}
				else if(board[k][j].getVal() == (board[i][j].getVal())){
					AMerge mergeArg = new AMerge(board[i][j], board[k][j], board, k, j);
					shiftThreads.add(new Thread(new AShift(board[i][j], k, j, mergeArg)));
					board[k][j] = new Tile(k,j,1);
					board[i][j] = null;
					shifted = true;
					
				}
				else if(board[k][j].getVal() != (board[i][j].getVal())){
					if(k != i - 1){
						shiftThreads.add(new Thread(new AShift(board[i][j], k+1, j)));
						board[k+1][j] = board[i][j];
						board[i][j] = null;
						shifted = true;
					}
				}
			}
		}
		
		/*
		 * Here each of the found AShifts are started
		 */
		for(Thread t: shiftThreads){
			t.start();
		}
		
		/*
		 * If a shift has occurred, a new tile is added to the board 
		 */
		if(shifted){
			newTile();
		}
	}
	
	public void downShift(){
		/*
		 * Iterates through each column beginning with the left. Within each column, starts at the second to last row and iterates up while 
		 * shifting each tile to the highest lowest spot.  If the tile below the one being shifted contains the same value, they are merged 
		 * to form a single tile with a value double of the original of each.
		 * 
		 * Each time a tile is shifted, an AShift Thread is created but not started and added to to shiftThreads. The AShift thread completes 
		 * the animation of tiles shifting.  If two tiles are merged, an AMerge object is passed as an argument to the AShift constructor
		 * which is run following the completion of the animation.  The AMerge deletes two two tiles that are merging and creates a new tile in their place.
		 */
		List<Thread> shiftThreads = new ArrayList<Thread>();
		int i,j,k;
		boolean shifted = false;//Changed to true if a shift occurs
		
		for(j = 0; j < SIDE_LENGTH; j++){
			for(i = SIDE_LENGTH-2; i > -1; i--){
				if(board[i][j] == null)continue;
				k = i + 1;
				while(k < SIDE_LENGTH-1){
					/*
					 * This loop finds the next tile up from board[i][j]
					 */
					if(board[k][j] != null)break;
					k++;
				}
				if(board[k][j] == null){
					shiftThreads.add(new Thread(new AShift(board[i][j], k, j)));
					board[k][j] = board[i][j]; 
					board[i][j] = null;
					shifted = true;
				}
				else if(board[k][j].getVal() == (board[i][j].getVal())){
					AMerge mergeArg = new AMerge(board[i][j], board[k][j], board, k, j);
					shiftThreads.add(new Thread(new AShift(board[i][j], k, j, mergeArg)));
					board[k][j] = new Tile(k,j,1);
					board[i][j] = null;
					shifted = true;
					
				}
				else if(board[k][j].getVal() != (board[i][j].getVal())){
					if(k != i + 1){
						shiftThreads.add(new Thread(new AShift(board[i][j], k-1, j)));
						board[k-1][j] = board[i][j];
						board[i][j] = null;
						shifted = true;
					}			
				}
			}
		}
		
		/*
		 * Here each of the found AShift Threads are started
		 */
		for(Thread t: shiftThreads){
			t.start();
		}
		
		/*
		 * If a shift has occurred, a new tile is added to the board 
		 */
		if(shifted){
			newTile();
		}
	}
	
	public void leftShift(){
		/*
		 * Iterates through each column beginning with the left. Within each column, starts at the second to last row and iterates up while 
		 * shifting each tile to the highest lowest spot.  If the tile below the one being shifted contains the same value, they are merged 
		 * to form a single tile with a value double of the original of each.
		 * 
		 * Each time a tile is shifted, an AShift Thread is created but not started and added to to shiftThreads. The AShift thread completes 
		 * the animation of tiles shifting.  If two tiles are merged, an AMerge object is passed as an argument to the AShift constructor
		 * which is run following the completion of the animation.  The AMerge deletes two two tiles that are merging and creates a new tile in their place.
		 */
		List<Thread> shiftThreads = new ArrayList<Thread>();
		int i,j,k;
		boolean shifted = false;//Changed to true if a shift occurs
		
		for(j = 0; j < SIDE_LENGTH; j++){
			for(i = 1; i < SIDE_LENGTH; i++){
				if(board[j][i] == null)continue;
				k = i - 1;
				while(!(k<1)){
					/*
					 * This loop finds the next tile up from board[i][j]
					 */
					if(board[j][k] != null)break;
					k--;
				}
				if(board[j][k] == null){
					shiftThreads.add(new Thread(new AShift(board[j][i], j, k)));
					board[j][k] = board[j][i]; 
					board[j][i] = null;
					shifted = true;
				}
				else if(board[j][k].getVal() == (board[j][i].getVal())){
					AMerge mergeArg = new AMerge(board[j][i], board[j][k], board, j, k);
					shiftThreads.add(new Thread(new AShift(board[j][i], j, k, mergeArg)));
					board[j][k] = new Tile(j,k,1);
					board[j][i] = null;
					shifted = true;
					
				}
				else if(board[j][k].getVal() != (board[j][i].getVal())){
					if(k != i - 1){
						shiftThreads.add(new Thread(new AShift(board[j][i], j, k+1)));
						board[j][k+1] = board[j][i];
						board[j][i] = null;
						shifted = true;
					}
				}
			}
		}
		
		/*
		 * Here each of the found AShift Threads are started
		 */
		for(Thread t: shiftThreads){
			t.start();
		}
		
		/*
		 * If a shift has occurred, a new tile is added to the board 
		 */
		if(shifted){
			newTile();
		}
	}
	
	public void rightShift(){
		/*
		 * Iterates through each column beginning with the left. Within each column, starts at the second to last row and iterates up while 
		 * shifting each tile to the highest lowest spot.  If the tile below the one being shifted contains the same value, they are merged 
		 * to form a single tile with a value double of the original of each.
		 * 
		 * Each time a tile is shifted, an AShift Thread is created but not started and added to to shiftThreads. The AShift thread completes 
		 * the animation of tiles shifting.  If two tiles are merged, an AMerge object is passed as an argument to the AShift constructor
		 * which is run following the completion of the animation.  The AMerge deletes two two tiles that are merging and creates a new tile in their place.
		 */
		List<Thread> shiftThreads = new ArrayList<Thread>();
		int i,j,k;
		boolean shifted = false;//Changed to true if a shift occurs
		
		for(j = 0; j < SIDE_LENGTH; j++){
			for(i = SIDE_LENGTH-2; i > -1; i--){
				if(board[j][i] == null)continue;
				k = i + 1;
				while(k < SIDE_LENGTH-1){
					/*
					 * This loop finds the next tile up from board[i][j]
					 */
					if(board[j][k] != null)break;
					k++;
				}
				if(board[j][k] == null){
					shiftThreads.add(new Thread(new AShift(board[j][i], j, k)));
					board[j][k] = board[j][i]; 
					board[j][i] = null;
					shifted = true;
				}
				else if(board[j][k].getVal() == (board[j][i].getVal())){
					AMerge mergeArg = new AMerge(board[j][i], board[j][k], board, j, k);
					shiftThreads.add(new Thread(new AShift(board[j][i], j, k, mergeArg)));
					board[j][k] = new Tile(j,k,1);
					board[j][i] = null;
					shifted = true;
					
				}
				else if(board[j][k].getVal() != (board[j][i].getVal())){
					if(k != i + 1){
						shiftThreads.add(new Thread(new AShift(board[j][i], j, k-1)));
						board[j][k-1] = board[j][i];
						board[j][i] = null;
						shifted = true;
					}			
				}
			}
		}
		
		/*
		 * Here each of the found AShift Threads are started
		 */
		for(Thread t: shiftThreads){
			t.start();
		}
		
		/*
		 * If a shift has occurred, a new tile is added to the board 
		 */
		if(shifted){
			newTile();
		}
	}
	
	public void newTile(){
		/*
		 * Creates a new tile with ~20% chance that the tile will have a value of 4, the rest of the time it will be 2
		 */
		int newValue;
		double r = Math.random();
		
		if(r < 0.20){
			newValue = 4;
		}
		else{
			newValue = 2;
		}
		
		int rX, rY;
		while(true){
			/*
			 * Chooses a random point on the board and creates the new tile if there is none already there
			 */
			rX = (int)Math.floor((Math.random() * SIDE_LENGTH));
			rY = (int)Math.floor((Math.random() * SIDE_LENGTH));
			if(board[rY][rX] == null){
				board[rY][rX] = new Tile(rY, rX, newValue);
				break;
			}
		}
	}
	
	public void printBoard(){
		/*
		 *   **USED FOR TESTING**
		 *   
		 * Prints the 2D-Array the tiles are stored, used to locate discrepancies between data and the view window
		 */
		System.out.println("START PRINT_______________");
		for(int i = 0; i < SIDE_LENGTH; i++){
			for(int j = 0; j < SIDE_LENGTH; j++){
				if(board[i][j] != null){
					System.out.print(board[i][j].getVal() + "  ");
				}
				else{
					System.out.print("__ ");
				}
			}
			System.out.print("\n");
		}
		System.out.print("\n");
	}
	
}
