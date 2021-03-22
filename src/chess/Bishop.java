package chess;

public class Bishop extends ChessPiece{
	//Constructor
	public Bishop(String owner, int x, int y) {
		player = owner;
		takenOrAttacked = true;
		identity = "rook";
		row = x;
		col = y;
	}
	//Prints piece to console
	public void print() {
		if (player.equals("white")) {
			System.out.print("wB ");
		} else {
			System.out.print("bB ");
		}
	}
	//checks legality of a move
	public boolean isLegal(ChessPiece[][] board, int x, int y) {
		if (board[x][y].player.equals(player)) {
			return false;
		}
		//check if the movement of the bishop is in a diagonal line
		else if (Math.abs(row-x) == Math.abs(col-y) && Math.abs(row-x) > -8 && Math.abs(row-x) < 8) {
			//if only moving one, already checked if player piece is at destination, so assume true
			if(Math.abs(row-x) == 1 && Math.abs(col-y) == 1) {
				return true;
			}
			//check if there are any pieces in between when moving diagonal (south east)
			else if (x > row && y > col) {
				for (int i = row+1; i < (x-1); i++) {
					for (int j = col+1; j < (y-1); j++) {
						if(!board[i][j].player.equals("neutral")) {
							return false;
						}
					}
				}
			}
			//check if there are any pieces in between when moving diagonal (south west)
			else if (x > row && y < col) {
				for (int i = row+1; i > (x-1); i++) {
					for (int j = col-1; j < (y+1); j--) {
						if(!board[i][j].player.equals("neutral")) {
							return false;
						}
					}
				}
			}
			//check if there are any pieces in between when moving diagonal (north east)
			else if (x < row && y > col) {
				for(int i = row-1; i > (x+1); i--) {
					for (int j = col+1; j > (x-1); j++) {
						if(!board[i][j].player.equals("neutral")) {
							return false;
						}
					}
				}
			}
			//check if there are any pieces in between when moving diagonal (north west)
			else if (x < row && y < col) {
				for(int i = row-1; i > (x+1); i--) {
					for (int j = col-1; j > (x+1); j--) {
						if(!board[i][j].player.equals("neutral")) {
							return false;
						}
					}
				}
			}
			//if no pieces are in between, move is successful
			return true;
		}
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
