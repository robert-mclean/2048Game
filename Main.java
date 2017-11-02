package rhmclean;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		final int EDITOR_WIDTH = 600,
				EDITOR_HEIGHT = 500;
		
		
		TileBoard board = InstanceCreator.getTileBoard();		
		View view = InstanceCreator.getView();

		view.setBackground(new Color(75, 156, 211));
		
		JFrame frame = new JFrame("2048 Game");
		frame.setOpacity(1f);

		Container c = frame.getContentPane();
		c.setBackground(Color.GRAY);
		
		frame.add(view);
		frame.setVisible(true);
		frame.setSize(EDITOR_WIDTH, EDITOR_HEIGHT);
		BoardController controller = new BoardController();
		frame.addKeyListener((KeyListener)controller);
		
		board.newTile();
		board.newTile();
		board.newTile();
	}

}
