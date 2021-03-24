package chess;
/**
 * 
 * @author 		Anthony Siu
 * @author 		Benjamin Lee
 * @version		%I% %G%
 * @since		1.0
 *
 */
public class Pawn extends ChessPiece {
	/**
	 * A pawn is a chess piece on the chess board.
	 * It moves in a straight line, unless capturing pieces and will be placed on
	 * the board at proper places.
	 * The object pawn is displayed as white or black as strings "wp" or "bp" and
	 * has various attributes to it from ChessPiece.java.
	 * 
	 * @param owner		sets the player to be the owner
	 * @param x			the row number of the piece, in chess terms, it is numbers 1 to 8
	 * @param y			the column number of the piece, in chess terms, it is letters a to h
	 */
	public Pawn(String owner,int x, int y) {
		player = owner;
		takenOrAttacked = true;
		hasMoved = false;
		canEnpassant = false;
		attackingKing = false;
		identity = "pawn";
		row = x;
		col = y;
	}
	/**
	 * Prints piece to console. Consists of "wp" or "bp" strings
	 * to indicate white and black pawns on the board, respectively.
	 * 
	 * @see ChessPiece#ChessPiece()
	 * @since 1.0
	 */
	public void print() {
		if (player.equals("white")) {
			System.out.print("wp ");
		} else {
			System.out.print("bp ");
		}
	}
	/**
	 * Method that implements isLegal and checks the legality of a move, specifically for
	 * a queen. There are many conditions that need to be fulfilled in order for the move
	 * to be declared legal.
	 * <p>
	 * These factors include:
	 * <ul>
	 * <li>Cannot take your own piece
	 * <li>If white pawn is at starting point, you can move forward two spaces
	 * <li>If white pawn can only move to a space that is "neutral"
	 * <li>Other possible actions: white pawn moves forward one... (as long as its a neutral space)
	 * <li>... or white pawn attacks at a diagonal
	 * <li>Checks if space adjacent to white pawn is a space not neutral and not player, AKA an enemy piece
	 * <li>Allows enpassant if in right row and previous move was black pawn up 2
	 * <li>Invalid destination for white pawn
	 * <li>If black pawn is at starting point, you can move forward two spaces
	 * <li>If white pawn can only move to a space that is "neutral"
	 * <li>Other possible actions: black pawn moves forward one... (as long as its a neutral space)
	 * <li>... or black pawn attacks at a diagonal
	 * <li>Checks if space adjacent to black pawn is a space not neutral and not player, AKA an enemy piece
	 * <li>Allows enpassant if in right row and previous move was white pawn up 2
	 * <li>Invalid destination for black pawn
	 * <ul>
	 * 
	 * @see ChessPiece#isLegal(ChessPiece[][], int, int)
	 */
	public boolean isLegal(ChessPiece[][] board, int x, int y) {
		//cannot take your own piece
		if (board[x][y].player.equals(player)) {
			return false;
		}
		//checks for white pawns
		else if(player.equals("white")) {	
			//if white pawn is at starting point, you can move forward two spaces
			if(row == 6) {
				//can only move to a space that is "neutral"
				if ((col == y) && (row-x) == 2 && board[x][y].player.equals("neutral") && board[x+1][y].player.equals("neutral")){
					return true;
				}
			}
			
			//Other possible actions: white pawn moves forward one... (as long as its a neutral space)
			if ((col == y) && (row-x) == 1 && board[x][y].player.equals("neutral")){
				return true;
			}
			//... or attacks at a diagonal
			else if((row-x) == 1 && Math.abs(col-y) == 1) {
				// checks if it is a space not neutral and not player, AKA an enemy piece
				if(board[x][y].player.equals(player)) {
					return false;
				} else if (board[x][y].player.equals("neutral")) {
					//allows enpassant if in right row and previous move was pawn up 2
					if (row == 3 && board[3][y].canEnpassant) {
						return true;
					}
					return false;
				} else {
					return true;
				}
			} else {
				//invalid destination
				return false;
			}
		}
		else{
			//if black pawn is at starting point, you can move forward two spaces
			if(row == 1) {
				//can only move to a space that is "neutral"
				if ((col == y) && (row-x) == -2 && board[x][y].player.equals("neutral") && board[x-1][y].player.equals("neutral")){
					return true;
				}
			}
			
			//Other possible actions: black pawn moves forward one... (as long as its a neutral space)
			if ((col == y) && (row-x) == -1 && board[x][y].player.equals("neutral")){
				return true;
			}
			//... or attacks at a diagonal
			else if((row-x) == -1 && Math.abs(col-y) == 1) {
				// checks if it is a space not neutral and not player, AKA an enemy piece
				if(board[x][y].player.equals(player)) {
					return false;
				} else if (board[x][y].player.equals("neutral")) {
					//allows enpassant if in right row and previous move was pawn up 2
					if (row == 4 && board[4][y].canEnpassant) {
						return true;
					}
					return false;
				} else {
					return true;
				}
			} else {
				//invalid destination
				return false;
			}
		}
	}
	//sets board tiles to either being attacked or not, to determine check/checkmate
	/**
	 * Checks if the pawn is attacking the enemy king, thus creating check for the other player.
	 * 
	 * @return board
	 */
	public ChessPiece[][] attacking(ChessPiece[][] board) {
		if (player.equals("white")) {
			if (col < 7) {
				if (board[row-1][col+1].identity.equals("king") && !board[row-1][col+1].player.equals(player)) {
					board[row][col].attackingKing = true;
				}
				board[row-1][col+1].takenOrAttacked = true;
			} 
			if (col > 0) {
				if (board[row-1][col-1].identity.equals("king") && !board[row-1][col-1].player.equals(player)) {
					board[row][col].attackingKing = true;
				}
				board[row-1][col-1].takenOrAttacked = true;
			}
			
		} else {
			if (col < 7) {
				if (board[row+1][col+1].identity.equals("king") && !board[row+1][col+1].player.equals(player)) {
					board[row][col].attackingKing = true;
				}
				board[row+1][col+1].takenOrAttacked = true;
			} 
			if (col > 0) {
				if (board[row+1][col-1].identity.equals("king") && !board[row+1][col-1].player.equals(player)) {
					board[row][col].attackingKing = true;
				}
				board[row+1][col-1].takenOrAttacked = true;
			}
			
		}
		return board;
	}
}