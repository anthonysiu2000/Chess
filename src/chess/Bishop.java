package chess;

public class Bishop extends ChessPiece{
	//Constructor
	public Bishop(String owner, int x, int y) {
		player = owner;
		takenOrAttacked = true;
		row = x;
		col = y;
	}
	//Prints piece to console
	public void print() {
		if (player == "white") {
			System.out.print("wB ");
		} else {
			System.out.print("bB ");
		}
	}
}
