package othello;

class Position{
	int row;
	int col;
	
	public Position(int row,int col){
		this.row = row;
		this.col = col;
	}
}

public class Board {
	public CellState[][] board;
	private Player p1;
	private Player p2;
	int size = 8;

	public Board(Player p1,Player p2){
		this.p1 = p1;
		this.p2 = p2;
		start();
	}
	
	public void start(){
		board = new CellState[size][size];

		for(int row=0;row<size;row++){
			for(int col=0;col<size;col++){
				board[row][col] = CellState.EMPTY;
			}
		}
		int midR = size/2;
		int midC = size/2;
		board[midR-1][midC-1] = CellState.BLACK;
		board[midR-1][midC] = CellState.WHITE;
		board[midR][midC-1] = CellState.WHITE;
		board[midR][midC] = CellState.BLACK;
		print();
	}
	
	public void print(){	
		for(int col=0;col<size;col++){
			System.out.print("  |"+ (col+1));
		}

		System.out.print("\n  ");
		for(int col=0;col<size;col++){
			System.out.print(" ___");
		}
		System.out.println();
		
		for(int row=0;row<size;row++){
			System.out.print(row+1 + " |");
			for(int col=0;col<size;col++){
				System.out.print(board[row][col].value + "  |");
			}
			System.out.print("\n  ");
			for(int col=0;col<size;col++){
				System.out.print(" ___");
			}
			System.out.println();
		}
	}
	
	public Result evaluateBoard(boolean isCompleted){
		if(!isCompleted)
			return Result.INCOMPLETE;
		
		int player1Grits = 0;
		int player2Grits = 0;
		for(int row=0;row<size;row++){
			for(int col=0;col<size;col++){
				if(board[row][col].value==p1.color)
					player1Grits++;
				else if(board[row][col].value==p2.color)
					player2Grits++;
			}
		}
		
		if(player1Grits-player2Grits >0)
			return Result.PLAYER_1_WON;
		else if(player1Grits-player2Grits< 0)
			return Result.PLAYER_2_WON;
		else
			return Result.DRAW;
	}
	
	public void move(int row, int col, Player p) throws InvalidMoveException{
		validateMove(row,col);
		this.board[row][col] = this.board[row][col].getState(p.color);
	}
	
	public boolean validateMove(int x, int y){
		if((x<0 || x>=size )||(y<0 || y>=size)){
			return false;
		}
		
		return true;
	}
}
