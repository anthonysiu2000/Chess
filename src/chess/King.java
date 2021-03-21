package chess;

public class King extends ChessPiece{
	//Constructor
	public King(String owner) {
		player = owner;
	}
	//Prints piece to console
	public void print() {
		if (player == "white") {
			System.out.print("wK ");
		} else {
			System.out.print("bK ");
		}
	}
}
