package chess;

public class Queen extends ChessPiece{
	//Constructor
	public Queen(String owner, int x, int y) {
		player = owner;
		takenOrAttacked = true;
		hasMoved = false;
		identity = "queen";
		row = x;
		col = y;
	}
	//Prints piece to console
	public void print() {
		if (player.equals("white")) {
			System.out.print("wQ ");
		} else {
			System.out.print("bQ ");
		}
	}
	//checks legality of a move
	public boolean isLegal(ChessPiece[][] board, int x, int y) {
		//cannot take your own piece
		if (board[x][y].player.equals(player)) {
			return false;
		}
		
		
		
		//check if the movement of the queen is in a diagonal line
		if (Math.abs(row-x) == Math.abs(col-y)) {
			//if only moving one, already checked if player piece is at destination, so assume true
			if(Math.abs(row-x) == 1) {
				return true;
			}
			//check if there are any pieces in between when moving diagonal (south east)
			else if (x > row && y > col) {
				int j = col+1;
				for (int i = row+1; i < x; i++,j++) {
					if(!board[i][j].player.equals("neutral")) {
						return false;
					}
				}
			}
			//check if there are any pieces in between when moving diagonal (south west)
			else if (x > row && y < col) {
				int j = col-1;
				for (int i = row+1; i < x; i++,j--) {
					if(!board[i][j].player.equals("neutral")) {
						return false;
					}
				}
			}
			//check if there are any pieces in between when moving diagonal (north east)
			else if (x < row && y > col) {
				int j = col+1;
				for(int i = row-1; i > x; i--,j++) {
					if(!board[i][j].player.equals("neutral")) {
						return false;
					}
				
				}
			}
			//check if there are any pieces in between when moving diagonal (north west)
			else if (x < row && y < col) {
				int j = col-1;
				for(int i = row-1; i > x; i--,j--) {
					if(!board[i][j].player.equals("neutral")) {
						return false;
					}
				}
			}
			//if no pieces are in between, move is successful
			return true;
		}
		
		//check if the movement of the queen is in a straight line
		else if ((col-y) == 0 || (row-x) == 0) {
				//if only moving one, already checked if player piece is at destination, so assume true
				if(Math.abs(row-x) == 1 || Math.abs(col-y) == 1) {
					return true;
				}
				//check if there are any pieces in between when moving down
				else if (x > row) {
					for (int i = row+1; i < x; i++) {
						if(!board[i][col].player.equals("neutral")) {
							return false;
						}
					}
				}
				//check if there are any pieces in between when moving up
				else if (x < row) {
					for (int i = row-1; i > x; i--) {
						if(!board[i][col].player.equals("neutral")) {
							return false;
						}
					}
				}
				//check if there are any pieces in between when moving right
				else if (y > col) {
					for (int i = col+1; i < y; i++) {
						if(!board[row][i].player.equals("neutral")) {
							return false;
						}
					}
				}
				//check if there are any pieces in between when moving left
				else if (y < col) {
					for (int i = col-1; i > y; i--) {
						if(!board[row][i].player.equals("neutral")) {
							return false;
						}
					}
				}
				//if no pieces are in between, move is successful
				return true;
			}
		//neither straight line nor diagonal
		return false;
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
