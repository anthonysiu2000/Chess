package chess;

public class Pawn extends ChessPiece {
	//Constructor
	public Pawn(String owner,int x, int y) {
		player = owner;
		takenOrAttacked = true;
		identity = "pawn";
		row = x;
		col = y;
	}
	//Prints piece to console
	public void print() {
		if (player.equals("white")) {
			System.out.print("wp ");
		} else {
			System.out.print("bp ");
		}
	}
}