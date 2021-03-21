package chess;

public class King extends ChessPiece{
	//Constructor
	public King(String owner, int x, int y) {
		player = owner;
		takenOrAttacked = false;
		row = x;
		col = y;
	}
	//Prints piece to console
	public void print() {
		if (player == "white") {
			System.out.print("wK ");
		} else {
			System.out.print("bK ");
		}
	}
	public ChessPiece[][] attacking(ChessPiece[][] board) {
		return board;
	}
}
