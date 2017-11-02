package rhmclean;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class BoardController implements KeyListener, ActionListener {
	TileBoard board = InstanceCreator.getTileBoard();
	View view = InstanceCreator.getView();

	public BoardController(){
		((Component)view).addKeyListener(this);
	}
	
	public void keyTyped(KeyEvent e) {
		/*
		 * When testing the 'p' key is used to print the board array to the console
		 * This method must be uncommented to use
		 */
//		char c = e.getKeyChar();
//		if(c=='p'){
//			board.printBoard();
//		}
	}

	@Override
	public  void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		
		if(keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_KP_UP){
			board.upShift();
		}
		else if(keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_KP_DOWN){
			board.downShift();
		}
		else if(keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_KP_LEFT){
			board.leftShift();
		}
		else if(keyCode == KeyEvent.VK_RIGHT || keyCode == KeyEvent.VK_KP_RIGHT){
			board.rightShift();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

}
