package chess;
/**
 * The EmptyTile class is a subclass extended from the superclass ChessPiece.java
 * 
 * @author 		Anthony Siu
 * @author 		Benjamin Lee
 * @version		%I% %G%
 * @since		1.0
 *
 */
public class EmptyTile extends ChessPiece{
	private boolean showTile;
	/**
	 * The EmptyTile is a chess piece on the chess board that represents a blank space.
	 * The object EmptyTile is displayed as white or black as strings "##" or "  " and
	 * has various attributes to it from ChessPiece.java. 
	 * 
	 * @param x			the row number of the piece, in chess terms, it is numbers 1 to 8
	 * @param y			the column number of the piece, in chess terms, it is letters a to h
	 * @see ChessPiece#ChessPiece()
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
	/**
	 * Prints piece to console. Consists of either "##" or "  " strings
	 * to indicate black spaces and white spaces on the board, respectively.
	 * Does not hold any pieces, rather neutral spaces that have nothing on it.
	 * 
	 * @see ChessPiece#ChessPiece()
	 * @since 1.0
	 */
	public void print() {
		if (showTile) {
			System.out.print("## ");
		} else {
			System.out.print("   ");
		}
	}
	/**
	 * Method that implements isLegal (will not be used)
	 * 
	 * @see ChessPiece#isLegal(ChessPiece[][], int, int)
	 */
	public boolean isLegal(ChessPiece[][] board, int x, int y) {
		return true;
	}
	/**
	 * Method that implements ChessPiece (will not be used)
	 * 
	 * @see ChessPiece
	 */
	public ChessPiece[][] attacking(ChessPiece[][] board) {
		return null;
	}
}
