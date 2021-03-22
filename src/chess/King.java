package chess;

public class King extends ChessPiece{
	//Constructor
	public King(String owner, int x, int y) {
		player = owner;
		takenOrAttacked = false;
		identity = "king";
		row = x;
		col = y;
	}
	//Prints piece to console
	public void print() {
		if (player.equals("white")) {
			System.out.print("wK ");
		} else {
			System.out.print("bK ");
		}
	}
	//checks legality of a move
	public boolean isLegal(ChessPiece[][] board, int x, int y) {
		if (board[x][y].player.equals(player)) {
			return false;
		}
		//Check to move the king in one space
		else if (Math.abs(row-x) > 2 || Math.abs(col-y) > 2) {
			return false;
		}
		//Check if you want to castle to column 'a' rook
		else if ((row == x) && (col - y) == 2 && board[row][col].hasMoved == false) {
			//need to figure out how to move the rook also
			board[row][col].hasMoved = true;
			return true;
		}
		//Check if you want to castle to column 'h' rook
		else if ((row == x) && (col - y) == -2 && board[row][col].hasMoved == false) {
			//need to figure out how to move the rook also
			board[row][col].hasMoved = true;
			return true;
		}
		board[row][col].hasMoved = true;
		return true;
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
