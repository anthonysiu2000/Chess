package chess;
/**
 * 
 * @author 		Anthony Siu
 * @author 		Benjamin Lee
 * @version		%I% %G%
 * @since		1.0
 *
 */
public class King extends ChessPiece{
	/**
	 * A king is a chess piece on the chess board.
	 * It moves a single space at a time and will be placed on the board at proper places.
	 * The object bishop is displayed as white or black as strings "wK" or "bK" and
	 * has various attributes to it from ChessPiece.java.
	 * 
	 * @param owner		sets the player to be the owner
	 * @param x			the row number of the piece, in chess terms, it is numbers 1 to 8
	 * @param y			the column number of the piece, in chess terms, it is letters a to h
	 */
	public King(String owner, int x, int y) {
		player = owner;
		takenOrAttacked = false;
		hasMoved = false;
		canEnpassant = false;
		attackingKing = false;
		identity = "king";
		row = x;
		col = y;
	}
	/**
	 * Prints piece to console. Consists of "wK" or "bK" strings
	 * to indicate white and black kings on the board, respectively.
	 * 
	 * @see ChessPiece#ChessPiece()
	 * @since 1.0
	 */
	public void print() {
		if (player.equals("white")) {
			System.out.print("wK ");
		} else {
			System.out.print("bK ");
		}
	}
	/**
	 * Method that implements isLegal and checks the legality of a move, specifically for
	 * a king. There are many conditions that need to be fulfilled in order for the move
	 * to be declared legal. The process of castling may put a king into check and needs
	 * to be checked before allowing a castle for either side.
	 * <p>
	 * These factors include:
	 * <ul>
	 * <li>Cannot take your own piece
	 * <li>Check if you want to castle to column 'a' rook
	 * <li>Checks if rook is at the corner, column 'a', and not moved
	 * <li>Checks if spaces between king and rook are empty
	 * <li>Check if you want to castle to column 'h' rook
	 * <li>Checks if rook is at the corner, column 'h', and not moved
	 * <li>Checks if spaces between king and rook are empty and not being attacked
	 * <li>Check to move the king in one space
	 * <ul>
	 * 
	 * @see ChessPiece#isLegal(ChessPiece[][], int, int)
	 */
	public boolean isLegal(ChessPiece[][] board, int x, int y) {
		//cannot take your own piece
		if (board[x][y].player.equals(player)) {
			return false;
		}
		//Check if you want to castle to column 'a' rook
		else if ((row == x) && (col - y) == 2 && hasMoved == false) {
			//Checks if rook is at the corner and not moved
			if (board[row][0].identity.equals("rook") && board[row][0].hasMoved == false) {
				//Checks if spaces between king and rook are empty
				if (!board[row][1].takenOrAttacked && !board[row][2].takenOrAttacked && !board[row][3].takenOrAttacked) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		}
		//Check if you want to castle to column 'h' rook
		else if ((row == x) && (col - y) == -2 && hasMoved == false) {
			//Checks if rook is at the corner and not moved
			if (board[row][7].identity.equals("rook") && board[row][7].hasMoved == false) {
				//Checks if spaces between king and rook are empty and not being attacked
				if (!board[row][5].takenOrAttacked && !board[row][6].takenOrAttacked) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		}
		//Check to move the king in one space
		if (Math.abs(row-x) > 1 || Math.abs(col-y) > 1) {
			return false;
		}
		return true;
	}
	//sets board tiles to either being attacked or not, to determine check/checkmate
	/**
	 * 
	 */
	public ChessPiece[][] attacking(ChessPiece[][] board) {
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				if ((row + i) < 0 || (row + i) > 7 || (col + j) < 0 || (col + j) > 7) {
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
