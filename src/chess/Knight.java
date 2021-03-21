package chess;

public class Knight extends ChessPiece{
	//Constructor
	public Knight(String owner, int x, int y) {
		player = owner;
		takenOrAttacked = true;
		row = x;
		col = y;
	}
	//Prints piece to console
	public void print() {
		if (player == "white") {
			System.out.print("wN ");
		} else {
			System.out.print("bN ");
		}
	}
}
