package chess;

public class Pawn extends ChessPiece {
	//Constructor
	public Pawn(String owner) {
		player = owner;
	}
	//Prints piece to console
	public void print() {
		if (player == "white") {
			System.out.print("wp ");
		} else {
			System.out.print("bp ");
		}
	}
}