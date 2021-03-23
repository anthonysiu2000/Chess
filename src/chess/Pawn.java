package chess;

public class Pawn extends ChessPiece {
	//Constructor
	public Pawn(String owner,int x, int y) {
		player = owner;
		takenOrAttacked = true;
		identity = "pawn";
		row = x;
		col = y;
	}
	//Prints piece to console
	public void print() {
		if (player.equals("white")) {
			System.out.print("wp ");
		} else {
			System.out.print("bp ");
		}
	}
	//checks legality of a move
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
			//if black pawn is at starting point, you can move forwad two spaces
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
	public ChessPiece[][] attacking(ChessPiece[][] board) {
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				if ((row + i) < 0 || (row + i) > 7 || (col + j) < 0 || (col + j) > 7) {
					continue;
				}
				board[row + i][col + j].takenOrAttacked = true;
			}
		}
		return board;
	}
}