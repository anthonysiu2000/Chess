package chess;

public class ChessBoard {
	
	//Fields stored by a ChessBoard object
	ChessPiece[][] board = new ChessPiece[8][8];
	boolean whiteTurn;
	
	//Constructor used to initialize a ChessBoard Object
	public ChessBoard() {
		//Code goes through each tile and sets them to the proper subclass of ChessPiece
		for (int i = 2; i < 6; i++) {
			for (int j = 0; j < 8; j++) {
				board[i][j] = new EmptyTile(i, j);
			}
		}
		for (int k = 0; k < 8; k++) {
			board[1][k] = new Pawn("black", 1, k);
			board[6][k] = new Pawn("white", 6, k);
		}
		board[0][0] = new Rook("black", 0, 0);
		board[0][7] = new Rook("black", 0, 7);
		board[7][0] = new Rook("white", 7, 0);
		board[7][7] = new Rook("white", 7, 7);
		board[0][1] = new Knight("black", 0, 1);
		board[0][6] = new Knight("black", 0, 6);
		board[7][1] = new Knight("white", 7, 1);
		board[7][6] = new Knight("white", 7, 6);
		board[0][2] = new Bishop("black", 0, 2);
		board[0][5] = new Bishop("black", 0, 5);
		board[7][2] = new Bishop("white", 7, 2);
		board[7][5] = new Bishop("white", 7, 5);
		board[0][3] = new Queen("black", 0, 3);
		board[7][3] = new Queen("white", 7, 3);
		board[0][4] = new King("black", 0, 4);
		board[7][4] = new King("white", 7, 4);
		
		//White goes first
		whiteTurn = true;
	}
	
	//Method called when we want to display the board to console
	public void display() {
		for(int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				board[i][j].print();
			}
			System.out.print((8-i) + "\n");
		}
		System.out.print(" a  b  c  d  e  f  g  h\n");
	}
	
	//Method called to execute an input instruction after finding instruction is legal
	public void execute(String[] input) {
		int col = input[0].charAt(0) - 97;
		int row = 7 - (input[0].charAt(1) - 49);
		int Dcol = input[1].charAt(0) - 97;
		int Drow = 7 - (input[1].charAt(1) - 49);
		
		
		//Implements pawn promotion if a pawn gets to the end of a column
		if (board[row][col].identity.equals("pawn")){
			if (board[row][col].player.equals("white") && Drow == 0) {
				//Checks input if its Q, R, N, B, or anything else, promotes pawn to requested piece, if no specified piece, becomes white queen
				if(input[2].equals("Q")) {
					board[Drow][Dcol] = new Queen("white",Drow, Dcol);;
					board[row][col] = new EmptyTile(row, col);
				}
				else if(input[2].equals("R")) {
					board[Drow][Dcol] = new Rook("white",Drow, Dcol);;
					board[row][col] = new EmptyTile(row, col);
				}
				else if(input[2].equals("N")) {
					board[Drow][Dcol] = new Knight("white",Drow, Dcol);;
					board[row][col] = new EmptyTile(row, col);
				}
				else if(input[2].equals("B")) {
					board[Drow][Dcol] = new Bishop("white",Drow, Dcol);;
					board[row][col] = new EmptyTile(row, col);
				}
				else {
					board[Drow][Dcol] = new Queen("white",Drow, Dcol);;
					board[row][col] = new EmptyTile(row, col);
				}
			}
			else if (board[row][col].player.equals("black") && Drow == 7) {
				//Checks input if its Q, R, N, B, or anything else, promotes pawn to requested piece, if no specified piece, becomes black queen
				if(input[2].equals("Q")) {
					board[Drow][Dcol] = new Queen("black",Drow, Dcol);;
					board[row][col] = new EmptyTile(row, col);
				}
				else if(input[2].equals("R")) {
					board[Drow][Dcol] = new Rook("black",Drow, Dcol);;
					board[row][col] = new EmptyTile(row, col);
				}
				else if(input[2].equals("N")) {
					board[Drow][Dcol] = new Knight("black",Drow, Dcol);;
					board[row][col] = new EmptyTile(row, col);
				}
				else if(input[2].equals("B")) {
					board[Drow][Dcol] = new Bishop("black",Drow, Dcol);;
					board[row][col] = new EmptyTile(row, col);
				}
				else {
					board[Drow][Dcol] = new Queen("black",Drow, Dcol);;
					board[row][col] = new EmptyTile(row, col);
				}
			}
			
		}
		else {
			//replaces the destination tile with the piece, and the origin tile with an empty tile
			board[Drow][Dcol] = board[row][col];
			board[Drow][Dcol].hasMoved = true;
			board[Drow][Dcol].col = Dcol;
			board[Drow][Dcol].row = Drow;
			board[row][col] = new EmptyTile(row, col);
		}
		
		
	}
	
	//Method called to reset taken/attacked values of the board
	public void resetAttacked(int x, int y) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (board[i][j].getPlayer().equals("white") || board[i][j].getPlayer().equals("black")) {
					if (i != x && j != y) {
						board[i][j].takenOrAttacked = true;
					} else {
						board[i][j].takenOrAttacked = false;
					}
				} else {
					board[i][j].takenOrAttacked = false;
				}
			}
		}
	}
	
	//Method called to check if a player is in check
	public boolean inCheck(String player) {
		int kingRow = -1;
		int kingCol = -1;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (board[i][j].getPlayer().equals(player) && board[i][j].identity.equals("king")) {
					kingRow = i;
					kingCol = j;
				}
			}
		}
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (!board[i][j].getPlayer().equals(player) && !board[i][j].getPlayer().equals("neutral")) {
					board = board[i][j].attacking(board);
				}
			}
		}
		if (board[kingRow][kingCol].takenOrAttacked) {
			resetAttacked(kingRow, kingCol);
			return true;
		} else {
			resetAttacked(kingRow, kingCol);
			return false;
		}
	}
	
	//Method called to check if a player is in checkmate
	public boolean inCheckmate(String player) {
		return true;
	}
	
}
