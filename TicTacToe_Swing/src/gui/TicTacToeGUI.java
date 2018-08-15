package gui;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class TicTacToeGUI extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	private Container pane;
	private String currPlayer;
	private JButton board[][];
	private boolean hasWinner;
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem quit,newGame;
	
	public TicTacToeGUI(){
		super();
		setUpUI();
	}
	
	private void setUpUI(){
		pane = getContentPane();
		pane.setLayout(new GridLayout(3, 3));
		setTitle("Tic Tac Toe");
		setSize(500,500);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);

		initializeMenuBar();
		initializeBoard();
		
	}
	
	private void initializeMenuBar(){
		menuBar = new JMenuBar();
		menu = new JMenu("File");
		newGame = new JMenuItem("New Game");
		newGame.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				resetBoard();
			}
		});
		menu.add(newGame);
		
		quit = new JMenuItem("Quit");
		quit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		menu.add(quit);
		
		menuBar.add(menu);
		setJMenuBar(menuBar);
	}

	private void initializeBoard(){
		currPlayer = "X";
		board = new JButton[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				JButton btn = new JButton();
				board[i][j] = btn;
				btn.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						JButton btn = (JButton)e.getSource();
						if(btn.getText()=="" && !hasWinner){
							btn.setText(currPlayer);
							hasWinner();
							togglePlayer();
						}
					}
				});
				pane.add(btn);
				board[i][j].setText("");
			}
		}
		hasWinner = false;
	}
	
	private void resetBoard(){
		hasWinner = false;
		currPlayer = "X";
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				board[i][j].setText("");
			}
		}
	}
	
	private void togglePlayer(){
		if(currPlayer.equals("X"))
			currPlayer = "O";
		else
			currPlayer = "X";
	}
	
	private void hasWinner(){
		for(int i = 0; i < 3; i++){
			if(board[i][0].getText() != "" && board[i][0].getText() == board[i][1].getText() && board[i][0].getText() == board[i][2].getText()){
				System.out.println("Player" + board[i][0].getText() + " Won!");
				hasWinner = true;
			}
		}

		// Columns
		for(int i = 0; i < 3; i++){
			if(board[0][i].getText() != "" && board[0][i].getText() == board[1][0].getText() && board[0][i].getText() == board[2][i].getText()){
				System.out.println("Player" + board[i][0].getText() + " Won!");
				hasWinner = true;
			}
		}

		//diagonal 1

		if(board[0][0].getText() != "" && board[0][0].getText() == board[1][1].getText() && board[0][0].getText() == board[2][2].getText()){
			System.out.println("Player" + board[0][0].getText() + " Won!");
			hasWinner = true;
		}
		//diagonal 2

		if(board[0][2].getText() != "" && board[0][2].getText() == board[1][1].getText() && board[0][2].getText() == board[2][0].getText()){
			System.out.println("Player" + board[0][0].getText() + " Won!");
			hasWinner = true;
		}

	}
}
