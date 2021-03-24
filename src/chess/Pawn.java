package chess;
/**
 * 
 * @author 		Anthony Siu
 * @author 		Benjamin Lee
 * @version		%I% %G%
 * @since		1.2
 *
 */
public class Pawn extends ChessPiece {
	//Constructor
	/**
	 * 
	 * @param owner
	 * @param x
	 * @param y
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
	//Prints piece to console
	/**
	 * 
	 */
	public void print() {
		if (player.equals("white")) {
			System.out.print("wp ");
		} else {
			System.out.print("bp ");
		}
	}
	//checks legality of a move
	/**
	 * 
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
				if((!board[x][y].player.equals("neutral")) && (!board[x][y].player.equals(player))) {
					return true;
				} else {
					return false;
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
				if((!board[x][y].player.equals("neutral")) && (!board[x][y].player.equals(player))) {
					return true;
				} else {
					return false;
				}
			} else {
				//invalid destination
				return false;
			}
		}
	}
	//sets board tiles to either being attacked or not, to determine check/checkmate
	/**
	 * 
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