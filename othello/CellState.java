package othello;

public enum CellState{
	EMPTY(' '), 
	WHITE('W'), 
	BLACK('B'),
	POSSIBLE('P');
	
	char value;
	
	private CellState(char val){
		this.value = val;
	}
	
	public CellState getState(char val){
		switch(val){
		 	case 'W': return WHITE;
		 	case 'B': return BLACK;
		 	case 'P': return POSSIBLE;
		 	default : return EMPTY;
		}
	}
}
