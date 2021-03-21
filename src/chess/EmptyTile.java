package chess;

public class EmptyTile extends ChessPiece{
	//You show "##" if the empty tile is black, and "  " if it's white
	private boolean showTile;
	//Constructor
	public EmptyTile(int x, int y) {
		if ((x+y) % 2 == 0) {
			showTile = false;
		} else {
			showTile = true;
		}
		player = "neutral";
		identity = "empty";
		takenOrAttacked = false;
	}
	//Prints piece to console
	public void print() {
		if (showTile) {
			System.out.print("## ");
		} else {
			System.out.print("   ");
		}
	}
}
