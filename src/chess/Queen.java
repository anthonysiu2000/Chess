package chess;

public class Queen extends ChessPiece{
	//Constructor
	public Queen(String owner, int x, int y) {
		player = owner;
		takenOrAttacked = true;
		row = x;
		col = y;
	}
	//Prints piece to console
	public void print() {
		if (player == "white") {
			System.out.print("wQ ");
		} else {
			System.out.print("bQ ");
		}
	}
}
