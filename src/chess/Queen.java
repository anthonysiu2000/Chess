package chess;
/**
 * The Queen class is a subclass extended from the superclass ChessPiece.java
 * 
 * @author 		Benjamin Lee
 * @author		Anthony Siu
 * @version		%I% %G%
 * @since		1.0
 *
 */
public class Queen extends ChessPiece{
	//Constructor
	/**
	 * A queen is a chess piece on the chess board. It moves in either a straight line or a diagonal line and will be placed on the board at proper places.The object queen is displayed as white or black as strings "wQ" or "bQ" and has various attributes to it from ChessPiece.java.
	 * 
	 * @param owner		sets the player to be the owner
	 * @param x			the row number of the piece, in chess terms, it is numbers 1 to 8
	 * @param y			the column number of the piece, in chess terms, it is letters a to h
	 */
	public Queen(String owner, int x, int y) {
		player = owner;
		takenOrAttacked = true;
		hasMoved = false;
		canEnpassant = false;
		attackingKing = false;
		identity = "queen";
		row = x;
		col = y;
	}
	/**
	 * Prints piece to console. Consists of "wQ" or "bQ" strings
	 * to indicate white and black queens on the board, respectively.
	 * 
	 * @see ChessPiece#ChessPiece()
	 * @since 1.0
	 */
	public void print() {
		if (player.equals("white")) {
			System.out.print("wQ ");
		} else {
			System.out.print("bQ ");
		}
	}
	/**
	 * Method that implements isLegal and checks the legality of a move, specifically for
	 * a queen. There are many conditions that need to be fulfilled in order for the move
	 * to be declared legal. The spaces in between the destination and starting space need
	 * to be checked for pieces in between, otherwise be declared illegal.
	 * <p>
	 * These factors include:
	 * <ul>
	 * <li>Cannot take your own piece
	 * <li>Check if the movement of the queen is in a straight line
	 * <li>If only moving one, already checked if player piece is at destination, so assume true
	 * <li>Check if there are any pieces in between when moving down
	 * <li>Check if there are any pieces in between when moving up
	 * <li>Check if there are any pieces in between when moving right
	 * <li>Check if there are any pieces in between when moving left
	 * <li>Check if the movement of the queen is in a diagonal line
	 * <li>Check if there are any pieces in between when moving diagonal (south east)
	 * <li>Check if there are any pieces in between when moving diagonal (south west)
	 * <li>Check if there are any pieces in between when moving diagonal (north east)
	 * <li>Check if there are any pieces in between when moving diagonal (north west)
	 * <li>If no pieces are in between, move is successful
	 * </ul>
	 * 
	 * @see ChessPiece#isLegal(ChessPiece[][], int, int)
	 */
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
	/**
	 * Checks if the rook is attacking the enemy king, thus creating check for the other player.
	 * <p>
	 * This includes:
	 * <ul>
	 * <li>Parses through all spaces south east and sets them to attacked until meeting a player or enemy piece
	 * <li>Parses through all spaces south west and sets them to attacked until meeting a player or enemy piece
	 * <li>Parses through all spaces north east and sets them to attacked until meeting a player or enemy piece
	 * <li>Parses through all spaces north west and sets them to attacked until meeting a player or enemy piece
	 * <li>Parses through all spaces right and sets them to attacked until meeting a player or enemy piece
	 * <li>Parses through all spaces left and sets them to attacked until meeting a player or enemy piece
	 * <li>Parses through all spaces down and sets them to attacked until meeting a player or enemy piece
	 * <li>Parses through all spaces up and sets them to attacked until meeting a player or enemy piece
	 * </ul>
	 * 
	 * @return board
	 */
	public ChessPiece[][] attacking(ChessPiece[][] board) {
		//parses through all spaces south east and sets them to attacked until meeting a player or enemy piece
		int j = col+1;
		for (int i = row+1; i < 8 && j < 8; i++,j++) {
			if(!board[i][j].player.equals("neutral")) {
				if (board[i][j].identity.equals("king") && !board[i][j].player.equals(player)) {
					board[row][col].attackingKing = true;
				}
				board[i][j].takenOrAttacked = true;
				break;
			}
			board[i][j].takenOrAttacked = true;
		}
		//parses through all spaces south west and sets them to attacked until meeting a player or enemy piece
		j = col-1;
		for (int i = row+1; i < 8 && j > -1; i++,j--) {
			if(!board[i][j].player.equals("neutral")) {
				if (board[i][j].identity.equals("king") && !board[i][j].player.equals(player)) {
					board[row][col].attackingKing = true;
				}
				board[i][j].takenOrAttacked = true;
				break;
			}
			board[i][j].takenOrAttacked = true;
		}
		//parses through all spaces north east and sets them to attacked until meeting a player or enemy piece
		j = col+1;
		for(int i = row-1; i > -1 && j < 8; i--,j++) {
			if(!board[i][j].player.equals("neutral")) {
				if (board[i][j].identity.equals("king") && !board[i][j].player.equals(player)) {
					board[row][col].attackingKing = true;
				}
				board[i][j].takenOrAttacked = true;
				break;
			}
			board[i][j].takenOrAttacked = true;
		}
		//parses through all spaces north west and sets them to attacked until meeting a player or enemy piece
		j = col-1;
		for(int i = row-1; i > -1 && j > -1; i--,j--) {
			if(!board[i][j].player.equals("neutral")) {
				if (board[i][j].identity.equals("king") && !board[i][j].player.equals(player)) {
					board[row][col].attackingKing = true;
				}
				board[i][j].takenOrAttacked = true;
				break;
			}
			board[i][j].takenOrAttacked = true;
		}
		
		
		//parses through all spaces right and sets them to attacked until meeting a player or enemy piece
		for (int i = row+1; i < 8; i++) {
			if(!board[i][col].player.equals("neutral")) {
				if (board[i][col].identity.equals("king") && !board[i][col].player.equals(player)) {
					board[row][col].attackingKing = true;
				}
				board[i][col].takenOrAttacked = true;
				break;
			}
			board[i][col].takenOrAttacked = true;
		}
		//parses through all spaces left and sets them to attacked until meeting a player or enemy piece
		for (int i = row-1; i >-1; i--) {
			if(!board[i][col].player.equals("neutral")) {
				if (board[i][col].identity.equals("king") && !board[i][col].player.equals(player)) {
					board[row][col].attackingKing = true;
				}
				board[i][col].takenOrAttacked = true;
				break;
			}
			board[i][col].takenOrAttacked = true;
		}
		//parses through all spaces down and sets them to attacked until meeting a player or enemy piece
		for (int i = col+1; i < 8; i++) {
			if(!board[row][i].player.equals("neutral")) {
				if (board[row][i].identity.equals("king") && !board[row][i].player.equals(player)) {
					board[row][col].attackingKing = true;
				}
				board[row][i].takenOrAttacked = true;
				break;
			}
			board[row][i].takenOrAttacked = true;
		}
		//parses through all spaces up and sets them to attacked until meeting a player or enemy piece
		for (int i = col-1; i > -1; i--) {
			if(!board[row][i].player.equals("neutral")) {
				if (board[row][i].identity.equals("king") && !board[row][i].player.equals(player)) {
					board[row][col].attackingKing = true;
				}
				board[row][i].takenOrAttacked = true;
				break;
			}
			board[row][i].takenOrAttacked = true;
		}
		return board;
	}
}
