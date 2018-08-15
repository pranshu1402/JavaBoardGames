package lect_game.tic_tac_toe;

public class Board {

	private char[][] board;
	Player p1;
	Player p2;
	

	Board(Player p1, Player p2){
		board = new char[3][3];
		this.p1 = p1;
		this.p2 = p2;
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				board[i][j] = ' ';
			}
		}
	}

	public void move(char symbol, int x, int y) throws InvalidMoveException {
		if(x < 0 || x > 2 || y < 0 || y > 2 || board[x][y] != ' '){
			throw new InvalidMoveException();
		}
		board[x][y] = symbol;
	}

	// 1 - Player1 2 - p2, 3 - draw, 4 - incomplete
	public Result getGameStatus() {
		// Rows
		for(int i = 0; i < 3; i++){
			if(board[i][0] != ' '&& board[i][0] == board[i][1] && board[i][0] == board[i][2]){
				if(board[i][0] == p1.symbol){
					return Result.PLAYER1_WON;
				}else{
					return Result.PLAYER2_WON;
				}
			}
		}

		// Columns
		for(int i = 0; i < 3; i++){
			if(board[0][i] != ' '&& board[0][i] == board[1][i] && board[0][i] == board[2][i]){
				if(board[0][i] == p1.symbol){
					return Result.PLAYER1_WON;
				}else{
					return Result.PLAYER2_WON;
				}
			}
		}

		//diaganol 1

		if(board[0][0] != ' ' && board[0][0] == board[1][1] && board[0][0] == board[2][2]){
			if(board[0][0] == p1.symbol){
				return Result.PLAYER1_WON;
			}else{
				return Result.PLAYER2_WON;
			}
		}
		//diagonal 2

		if(board[0][2] != ' ' && board[0][2] == board[1][1] && board[0][2] == board[2][0]){
			if(board[0][2] == p1.symbol){
				return Result.PLAYER1_WON;
			}else{
				return Result.PLAYER2_WON;
			}
		}

		
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				if(board[i][j] == ' '){
					return Result.INCOMPLETE;
				}
			}
		}

		return Result.DRAW;

	}

	public void print() {
		// TODO Auto-generated method stub
		System.out.println("     =============");
		
		for(int i = 0; i < 3; i++){
			System.out.print("     | ");
			for(int j = 0; j < 3; j++){
					System.out.print(board[i][j]+" | ");
			}
			System.out.println("      ");
		}
		System.out.println("     =============");
	}

}
