package chess;

public class Queen extends ChessPiece{
	//Constructor
	public Queen(String owner) {
		player = owner;
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
