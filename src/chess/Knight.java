package chess;

public class Knight extends ChessPiece{
	//Constructor
	public Knight(String owner) {
		player = owner;
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
