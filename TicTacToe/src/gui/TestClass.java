package gui;

import javax.swing.SwingUtilities;

public class TestClass {

	public static void main(){
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new TicTacToeGUI();
			}
		});
	}

}
