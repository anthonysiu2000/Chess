package chess;
/**
 * 
 * @author 		Anthony Siu
 * @author 		Benjamin Lee
 * @version		%I% %G%
 * @since		1.2
 *
 */
public class EmptyTile extends ChessPiece{
	//You show "##" if the empty tile is black, and "  " if it's white
	private boolean showTile;
	//Constructor
	/**
	 * 
	 * @param x
	 * @param y
	 */
	public EmptyTile(int x, int y) {
		if ((x+y) % 2 == 0) {
			showTile = false;
		} else {
			showTile = true;
		}
		player = "neutral";
		identity = "empty";
		takenOrAttacked = false;
		hasMoved = false;
		canEnpassant = false;
		attackingKing = false;
		row = x;
		col = y;
	}
	//Prints piece to console
	/**
	 * 
	 */
	public void print() {
		if (showTile) {
			System.out.print("## ");
		} else {
			System.out.print("   ");
		}
	}
	//method that implements isLegal (will not be used)
	public boolean isLegal(ChessPiece[][] board, int x, int y) {
		return true;
	}
	//method that implements ChessPiece (will not be used)
	public ChessPiece[][] attacking(ChessPiece[][] board) {
		return null;
	}
}
