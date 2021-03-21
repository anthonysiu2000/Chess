package chess;

public class Rook extends ChessPiece{
	//Constructor
	public Rook(String owner, int x, int y) {
		player = owner;
		takenOrAttacked = true;
		identity = "rook";
		row = x;
		col = y;
	}
	//Prints piece to console
	public void print() {
		if (player.equals("white")) {
			System.out.print("wR ");
		} else {
			System.out.print("bR ");
		}
	}
}
