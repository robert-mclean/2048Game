package rhmclean;

/*
 * Singleton's Creator
 * 
 * Allows easy access to objects that are referenced by multiple classes
 */

public class InstanceCreator {
	static BoardController controller;
	static TileBoard board;
	static View view;
	
	public static BoardController getBoardController(){
		if(controller == null){
			controller = new BoardController();
		}
		return controller;
	}
	
	public static TileBoard getTileBoard(){
		if(board == null){
			board = new TileBoard();
		}
		return board;
	}

	public static View getView(){
		if(view == null){
			view = new View();
		}
		return view;
	}
}
