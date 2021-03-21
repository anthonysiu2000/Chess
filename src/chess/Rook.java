package chess;

public class Rook extends ChessPiece{
	//Constructor
	public Rook(String owner) {
		player = owner;
	}
	//Prints piece to console
	public void print() {
		if (player == "white") {
			System.out.print("wR ");
		} else {
			System.out.print("bR ");
		}
	}
}
