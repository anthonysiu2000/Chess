package chess;
/**
 * The Knight class is a subclass extended from the superclass ChessPiece.java
 * 
 * @author 		Benjamin Lee
 * @author		Anthony Siu
 * @version		%I% %G%
 * @since		1.0
 *
 */
public class Knight extends ChessPiece{
	/**
	 * A knight is a chess piece on the chess board. It moves in an "L" shape and will be placed on the board at proper places. The object knight is displayed as white or black as strings "wN" or "bN" and has various attributes to it from ChessPiece.java.
	 * 
	 * @param owner		sets the player to be the owner
	 * @param x			the row number of the piece, in chess terms, it is numbers 1 to 8
	 * @param y			the column number of the piece, in chess terms, it is letters a to h
	 */
	public Knight(String owner, int x, int y) {
		player = owner;
		takenOrAttacked = true;
		hasMoved = false;
		canEnpassant = false;
		attackingKing = false;
		identity = "knight";
		row = x;
		col = y;
	}
	/**
	 * Prints piece to console. Consists of "wN" or "bN" strings
	 * to indicate white and black knights on the board, respectively.
	 * 
	 * @see ChessPiece#ChessPiece()
	 * @since 1.0
	 */
	public void print() {
		if (player.equals("white")) {
			System.out.print("wN ");
		} else {
			System.out.print("bN ");
		}
	}
	/**
	 * Method that implements isLegal and checks the legality of a move, specifically for
	 * a bishop. There are many conditions that need to be fulfilled in order for the move
	 * to be declared legal. The spaces in between the destination and starting space DO NOT
	 * need to be checked for pieces in between.
	 * <p>
	 * These factors include:
	 * <ul>
	 * <li>Cannot take your own piece
	 * <li>The appropiate number of spaces are moved for a knight
	 * </ul>
	 * 
	 * @see ChessPiece#isLegal(ChessPiece[][], int, int)
	 */
	public boolean isLegal(ChessPiece[][] board, int x, int y) {
		//cannot take your own piece
		if (board[x][y].player.equals(player)) {
			return false;
		} else if (Math.abs(row-x) + Math.abs(col-y) == 3 && (row-x) != 0 && (col-y) != 0) {
			return true;
		}
		return false;
	}
	//sets board tiles to either being attacked or not, to determine check/checkmate
	/**
	 * Checks if the knight is attacking the enemy king, thus creating check for the other player.
	 * 
	 * @return board
	 */
	public ChessPiece[][] attacking(ChessPiece[][] board) {
		for (int i = -2; i < 3; i++) {
			for (int j = -2; j < 3; j++) {
				if (i == 0 || j == 0) {
					continue;
				}
				if ((row + i) < 0 || (row + i) > 7 || (col + j) < 0 || (col + j) > 7) {
					continue;
				}
				if (Math.abs(i) + Math.abs(j) != 3) {
					continue;
				}
				if (board[row + i][col + j].identity.equals("king") && !board[row + i][col + j].player.equals(player)) {
					board[row][col].attackingKing = true;
				}
				board[row + i][col + j].takenOrAttacked = true;
			}
		}
		return board;
	}
}
