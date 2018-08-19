package othello;

import java.util.ArrayList;
import java.util.Scanner;

public class Othello {
	static Scanner s;
	static Board othello;
	static Player p1,p2;
	static boolean turnFplayer;
	static boolean isCompleted;
	
	class Position{
		int row;
		int col;
		
		Position(int row,int col){
			this.row = row;
			this.col = col;
		}
		
		public String toString(){
			return "Row : "+ (this.row+1) + " Col : "+ (this.col+1);
		}
	}
	
	public static void main(String[] args) {
		s = new Scanner(System.in);
		p1 = new Player(s.next(),'W');
		p2 = new Player(s.next(),'B');
		othello = new Board(p1,p2);
		turnFplayer = true;
		isCompleted = false;
		new Othello().play();
		s.close();
	}
	
	public void play(){
		while(!isCompleted){
			ArrayList<Position> possible = possibleMoves();
			if(turnFplayer)
				System.out.print("Player 1 ");
			else
				System.out.print("Player 2 ");
			System.out.println("Select a position from the given options");
			for (Position pos : possible){
				System.out.println(pos.toString());
			}
			if(possible.size()==0){
				turnFplayer = !turnFplayer;
				if(possibleMoves().size()!=0){
					int row = s.nextInt()-1;
					int col = s.nextInt()-1;
					makeAmove(new Position(row, col), false);
					othello.print();
				}else
					isCompleted = true;
				turnFplayer = !turnFplayer;
			}
			int row = s.nextInt()-1;
			int col = s.nextInt()-1;
			makeAmove(new Position(row, col), false);
			othello.print();
			turnFplayer = !turnFplayer;
		}
		System.out.println(othello.evaluateBoard(isCompleted));
	}
	
	public ArrayList<Position> possibleMoves(){
		ArrayList<Position> output = new ArrayList<>();
		for(int row = 0; row < othello.size;row++){
			for(int col=0; col<othello.size; col++){
				Position at = new Position(row, col);
				if(makeAmove(at,true)){
					output.add(at);
				}
			}
		}
		return output;
	}
	
	
	public boolean makeAmove(Position at, boolean isCheck){
		int xDir[] = {-1,0,1,1,1,0,-1,-1};
		int yDir[] = {1,1,1,0,-1,-1,-1,0};

		boolean movePossible = false;
		for(int i=0;i<8;i++){
			int currX = at.row+xDir[i];
			int currY = at.col+yDir[i];
			int count = 0;
			while(othello.validateMove(currX,currY)){
				if(othello.board[currX][currY].value==p1.color){
					if(turnFplayer && count>0){
						movePossible = true;
						if(!isCheck)
							makeChange(p1,currX,currY,xDir[i],yDir[i],at.row,at.col);
						else
							return true;
						break;
					}else if(turnFplayer && count==0){
						break;
					}else{
						count++;
					}
				}else if(othello.board[currX][currY].value==p2.color){
					if(!turnFplayer && count>0){
						movePossible = true;
						if(!isCheck)
							makeChange(p2,currX,currY,xDir[i],yDir[i],at.row,at.col);
						else
							return true;
						break;
					}else if(!turnFplayer && count==0){
						break;
					}else{
						count++;
					}
				}else{
					break;
				}
				currX+= xDir[i];
				currY+= yDir[i];
			}
		}
		return movePossible;
	}

	private void makeChange(Player currPlayer, int currRow, int currCol, int changeR, int changeC, int endR, int endC){
		try{ 
			currRow -= changeR;
			currCol -= changeC;
			System.out.println("change at" + currRow + " "+ currCol);
			System.out.println("end at" + endR + " "+ endC);
		while(currRow!=endR || currCol!=endC){
			othello.move(currRow, currCol, currPlayer); 
			currRow -= changeR;
			currCol -= changeC;
		}
		
		othello.move(endR, endC, currPlayer); 
		}catch(InvalidMoveException e){
			e.printStackTrace();
		}
	}
}
