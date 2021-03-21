package chess;

public abstract class ChessPiece {
	//Stores location of piece
	public int row;
	public int col;
	//Stores owner of piece
	public String player;
	// Used to determine if tile is being attacked, usually set to false
	public boolean takenOrAttacked;
	//method to obtain player variable 
	public String getPlayer() {
		return player;
	}
	//method to print piece to board
	public void print() {
	}
	//method used to flag tiles as being attacked
	public ChessPiece[][] attacking(ChessPiece[][] board) {
		return board;
	}
}