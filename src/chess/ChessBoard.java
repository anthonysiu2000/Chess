package chess;

public class ChessBoard {
	
	//Fields stored by a ChessBoard object
	ChessPiece[][] board = new ChessPiece[8][8];
	boolean whiteTurn;
	
	//Constructor used to initialize a ChessBoard Object
	public ChessBoard() {
		//Code goes through each tile and sets them to the proper subclass of ChessPiece
		for (int i = 1; i < 7; i++) {
			for (int j = 0; j < 8; j++) {
				board[i][j] = new EmptyTile(i, j);
			}
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
	
	//Method to duplicate a chessboard object 
	public void clone(ChessBoard BOARD) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (BOARD.board[i][j].player.equals("white")) {
					if (BOARD.board[i][j].identity.equals("pawn")) {
						board[i][j] = new Pawn("white", i, j);
					}
					if (BOARD.board[i][j].identity.equals("queen")) {
						board[i][j] = new Queen("white", i, j);
					}
					if (BOARD.board[i][j].identity.equals("knight")) {
						board[i][j] = new Knight("white", i, j);
					}
					if (BOARD.board[i][j].identity.equals("bishop")) {
						board[i][j] = new Bishop("white", i, j);
					}
					if (BOARD.board[i][j].identity.equals("king")) {
						board[i][j] = new King("white", i, j);
						board[i][j].hasMoved = BOARD.board[i][j].hasMoved;
					}
					if (BOARD.board[i][j].identity.equals("rook")) {
						board[i][j] = new Rook("white", i, j);
						board[i][j].hasMoved = BOARD.board[i][j].hasMoved;
					}
				} else if (BOARD.board[i][j].player.equals("black")){
					if (BOARD.board[i][j].identity.equals("pawn")) {
						board[i][j] = new Pawn("black", i, j);
					}
					if (BOARD.board[i][j].identity.equals("queen")) {
						board[i][j] = new Queen("black", i, j);
					}
					if (BOARD.board[i][j].identity.equals("knight")) {
						board[i][j] = new Knight("black", i, j);
					}
					if (BOARD.board[i][j].identity.equals("bishop")) {
						board[i][j] = new Bishop("black", i, j);
					}
					if (BOARD.board[i][j].identity.equals("king")) {
						board[i][j] = new King("black", i, j);
						board[i][j].hasMoved = BOARD.board[i][j].hasMoved;
					}
					if (BOARD.board[i][j].identity.equals("rook")) {
						board[i][j] = new Rook("black", i, j);
						board[i][j].hasMoved = BOARD.board[i][j].hasMoved;
					}
				} else {
					board[i][j] = new EmptyTile(i, j);
				}
			}
		}
		whiteTurn = BOARD.whiteTurn;
		return;
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
		
		//Finds any pieces at the beginning of white's turn in row 4 and sets their canEnpassant to false
		for(int i = 0; i < 8; i++){
			if(board[row][col].player.equals("white")) {
				board[4][i].canEnpassant = false;
			}
			else {
				continue;
			}
		}
		//Finds any pieces at the beginning of black's turn in row 3 and sets their canEnpassant to false
		for(int i = 0; i < 8; i++){
			if(board[row][col].player.equals("black")) {
				board[3][i].canEnpassant = false;
			}
			else {
				continue;
			}
		}
		
		//Implements king castling from either side of the board
		if(board[row][col].identity.equals("king")) {
			//Implements white king castling to g1
			if (board[row][col].player.equals("white") && Drow == 7 && Dcol == 6 && board[row][col].hasMoved == false && board[7][7].hasMoved == false) {
				board[Drow][Dcol] = board[row][col];
				board[row][col] = new EmptyTile(row, col);
				board[7][5] = board[7][7];
				board[7][7] = new EmptyTile(7, 7);
			}
			//Implements white king castling to c1
			else if (board[row][col].player.equals("white") && Drow == 7 && Dcol == 2 && board[row][col].hasMoved == false && board[7][0].hasMoved == false) {
				board[Drow][Dcol] = board[row][col];
				board[row][col] = new EmptyTile(row, col);
				board[7][3] = board[7][0];
				board[7][0] = new EmptyTile(7, 0);
			}
			//Implements black king castling to g8
			else if (board[row][col].player.equals("black") && Drow == 0 && Dcol == 6 && board[row][col].hasMoved == false && board[0][7].hasMoved == false) {
				board[Drow][Dcol] = board[row][col];
				board[row][col] = new EmptyTile(row, col);
				board[0][5] = board[0][7];
				board[0][7] = new EmptyTile(0, 7);
			}
			//Implements black king castling to c8
			else if (board[row][col].player.equals("black") && Drow == 0 && Dcol == 2 && board[row][col].hasMoved == false && board[0][0].hasMoved == false) {
				board[Drow][Dcol] = board[row][col];
				board[row][col] = new EmptyTile(row, col);
				board[0][3] = board[0][0];
				board[0][0] = new EmptyTile(0, 0);
			}
		}
		
		//Implements pawn promotion if a pawn gets to the end of a column, assigns canEnpassant, and implements enpassant if conditions are met
		if (board[row][col].identity.equals("pawn")){
			//Implements promotion for white pawns
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
			///Implements promotion for black pawns
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
			//Implements canEmpassant for white pawns that enter row 4
			else if (board[row][col].player.equals("white") && Drow == 4 && row == 6) {
				board[Drow][Dcol] = board[row][col];
				board[Drow][Dcol].canEnpassant = true;
				board[row][col] = new EmptyTile(row, col);
			}
			//Implements canEmpassant for black pawns that enter row 3
			else if (board[row][col].player.equals("black") && Drow == 3 && row == 1) {
				board[Drow][Dcol] = board[row][col];
				board[Drow][Dcol].canEnpassant = true;
				board[row][col] = new EmptyTile(row, col);
			}
			//Implements enpassant for white pawns on black pawns who moved in the most recent turn
			else if (Drow == 2 && row == 3 && (Dcol == col+1 || Dcol == col-1) && (board[row][col-1].canEnpassant == true || board[row][col+1].canEnpassant == true)){
				//Moves white pawn to destination
				board[Drow][Dcol] = board[row][col];
				board[row][col] = new EmptyTile(row, col);
				//Takes away black pawn due to enpassant
				board[row][Dcol] = new EmptyTile(row, Dcol);
			}
			//Implements enpassant for black pawns on white pawns who moved in the most recent turn
			else if (Drow == 5 && row == 4 && (Dcol == col+1 || Dcol == col-1) && (board[row][col-1].canEnpassant == true || board[row][col+1].canEnpassant == true)){
				//Moves black pawn to destination
				board[Drow][Dcol] = board[row][col];
				board[row][col] = new EmptyTile(row, col);
				//Takes away white pawn due to enpassant
				board[row][Dcol] = new EmptyTile(row, Dcol);
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
				if (board[i][j].player.equals(player) && board[i][j].identity.equals("king")) {
					kingRow = i;
					kingCol = j;
				}
			}
		}
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (!board[i][j].player.equals(player) && !board[i][j].player.equals("neutral")) {
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
		return false;
	}
	
}
