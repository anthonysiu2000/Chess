package chess;

public class Queen extends ChessPiece{
	public Queen(String owner) {
		player = owner;
	}
	public void print() {
		if (player == "white") {
			System.out.print(false);
		}
	}
}
