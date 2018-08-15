package lect_game.tic_tac_toe;

import java.util.Scanner;

public class TicTacToe {
	Scanner s = new Scanner(System.in);
	Board b;
	Player p1;
	Player p2;
	boolean done;
	boolean player1Turn;

	TicTacToe() {
		done = false;
		player1Turn = true;
		p1 = getPlayer(1);
		p2 = getPlayer(2);
		while (p1.symbol == p2.symbol) {
			System.out.println("Please take another symbol !!");
			p2 = getPlayer(2);
		}
		b = new Board(p1, p2);
	}

	public static void main(String args[]) {
		TicTacToe game = new TicTacToe();
		game.startGame();
	}

	public void startGame() {
		while (b.getGameStatus() == Result.INCOMPLETE) {
			takeMove();
			b.print();
		}
		displayResult();
	}

	private void displayResult() {
		switch (b.getGameStatus()) {
				case PLAYER1_WON:
					System.out.println("Player 1 Won !!");
					break;
				case PLAYER2_WON:
					System.out.println("Player 2 Won !!");
					break;
				case DRAW:
					System.out.println("Draw !!");
					break;
				default:
					break;
		}
	}
	
	private void takeMove(){
		done = false;
		if (player1Turn) {
			while (!done) {
				move(p1);
			}
		} else {
			while (!done) {
				move(p2);
			}
		}
	}

	private void move(Player p) {
		if(player1Turn){
			System.out.println("Player 1 Turn ");
		}else{
			System.out.println("Player 2 Turn ");
		}
		try {
			System.out.println("Enter x ");
			int x = s.nextInt();
			System.out.println("Enter y");
			int y = s.nextInt();
			b.move(p.symbol, x, y);
			done = true;
			player1Turn = !player1Turn;
		} catch (InvalidMoveException e) {
			System.out.println("Invalid Move !!! Try Again");
		}
	}

	private Player getPlayer(int num) {
		@SuppressWarnings("resource")
		Scanner s = new Scanner(System.in);
		System.out.println("Enter player " + num + " name ");
		String name = s.nextLine();
		System.out.println("Enter player " + num + " symbol ");
		char symbol = s.next().charAt(0);
		Player p = new Player(name, symbol);
		return p;
	}

}
