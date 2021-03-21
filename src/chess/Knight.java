package chess;

public class Knight extends ChessPiece{
	//Constructor
	public Knight(String owner, int x, int y) {
		player = owner;
		takenOrAttacked = true;
		identity = "knight";
		row = x;
		col = y;
	}
	//Prints piece to console
	public void print() {
		if (player.equals("white")) {
			System.out.print("wN ");
		} else {
			System.out.print("bN ");
		}
	}
	//checks legality of a move
	public boolean isLegal(ChessPiece[][] board, int x, int y) {
		if (board[x][y].player.equals(player)) {
			return false;
		} else if (Math.abs(row-x) + Math.abs(col-y) == 3 && (row-x) != 0 && (col-y) != 0) {
			return true;
		}
		return false;
	}
	//sets board tiles to either being attacked or not, to determine check/checkmate
	public ChessPiece[][] attacking(ChessPiece[][] board) {
		for (int i = -2; i < 3; i++) {
			for (int j = -2; j < 3; j++) {
				if ((row + i) < 0 || (row + i) > 8 || (col + j) < 0 || (col + j) > 8) {
					continue;
				}
				if (i == 0 || j == 0) {
					continue;
				}
				if (Math.abs(i) + Math.abs(j) != 3) {
					continue;
				}
				board[row + i][col + j].takenOrAttacked = true;
			}
		}
		return board;
	}
}
