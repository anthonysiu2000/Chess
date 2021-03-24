package chess;
/**
 * 
 * @author 		Anthony Siu
 * @author 		Benjamin Lee
 * @version		%I% %G%
 * @since		1.2
 *
 */
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
		for (int k = 0; k < 8; k ++) {
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
	
	//Method to duplicate a chessboard object 
	/**
	 * 
	 * @param BOARD
	 */
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
	/**
	 * 
	 */
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
	/**
	 * 
	 * @param input
	 */
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
		}
		//Finds any pieces at the beginning of black's turn in row 3 and sets their canEnpassant to false
		for(int i = 0; i < 8; i++){
			if(board[row][col].player.equals("black")) {
				board[3][i].canEnpassant = false;
			}
		}
		
		//Implements king castling from either side of the board
		if(board[row][col].identity.equals("king")) {
			//Implements white king castling to g1
			if (board[row][col].player.equals("white") && Drow == 7 && Dcol == 6) {
				board[7][5] = board[7][7];
				board[7][5].hasMoved = true;
				board[7][5].col = 5;
				board[7][5].row = 7;
				board[7][7] = new EmptyTile(7, 7);
			}
			//Implements white king castling to c1
			else if (board[row][col].player.equals("white") && Drow == 7 && Dcol == 2) {
				board[7][3] = board[7][0];
				board[7][3].hasMoved = true;
				board[7][3].col = 3;
				board[7][3].row = 7;
				board[7][0] = new EmptyTile(7, 0);
			}
			//Implements black king castling to g8
			else if (board[row][col].player.equals("black") && Drow == 0 && Dcol == 6) {
				board[0][5] = board[0][7];
				board[0][5].hasMoved = true;
				board[0][5].col = 5;
				board[0][5].row = 0;
				board[0][7] = new EmptyTile(0, 7);
			}
			//Implements black king castling to c8
			else if (board[row][col].player.equals("black") && Drow == 0 && Dcol == 2) {
				board[0][3] = board[0][0];
				board[0][3].hasMoved = true;
				board[0][3].col = 3;
				board[0][3].row = 0;
				board[0][0] = new EmptyTile(0, 0);
			}
		}
		
		//Implements pawn promotion if a pawn gets to the end of a column, assigns canEnpassant, and implements enpassant if conditions are met
		if (board[row][col].identity.equals("pawn") && board[row][col].player.equals("white") && Drow == 0){
			//Checks input if its Q, R, N, B, or anything else, promotes pawn to requested piece, if no specified piece, becomes white queen
			if (input.length == 2) {
				board[Drow][Dcol] = new Queen("white",Drow, Dcol);
				board[row][col] = new EmptyTile(row, col);
			}
			else if(input[2].equals("Q")) {
				board[Drow][Dcol] = new Queen("white",Drow, Dcol);
				board[row][col] = new EmptyTile(row, col);
			}
			else if(input[2].equals("R")) {
				board[Drow][Dcol] = new Rook("white",Drow, Dcol);
				board[Drow][Dcol].hasMoved = true;
				board[row][col] = new EmptyTile(row, col);
			}
			else if(input[2].equals("N")) {
				board[Drow][Dcol] = new Knight("white",Drow, Dcol);
				board[row][col] = new EmptyTile(row, col);
			}
			else if(input[2].equals("B")) {
				board[Drow][Dcol] = new Bishop("white",Drow, Dcol);
				board[row][col] = new EmptyTile(row, col);
			}				
			else {
				board[Drow][Dcol] = new Queen("white",Drow, Dcol);
				board[row][col] = new EmptyTile(row, col);
			}
			return;
		}
			///Implements promotion for black pawns
		if (board[row][col].identity.equals("pawn") && board[row][col].player.equals("black") && Drow == 7) {
			//Checks input if its Q, R, N, B, or anything else, promotes pawn to requested piece, if no specified piece, becomes black queen
			if (input.length == 2) {
				board[Drow][Dcol] = new Queen("black",Drow, Dcol);
				board[row][col] = new EmptyTile(row, col);
			}
			else if(input[2].equals("Q")) {
				board[Drow][Dcol] = new Queen("black",Drow, Dcol);
				board[row][col] = new EmptyTile(row, col);
			}
			else if(input[2].equals("R")) {
				board[Drow][Dcol] = new Rook("black",Drow, Dcol);
				board[Drow][Dcol].hasMoved = true;
				board[row][col] = new EmptyTile(row, col);
			}
			else if(input[2].equals("N")) {
				board[Drow][Dcol] = new Knight("black",Drow, Dcol);
				board[row][col] = new EmptyTile(row, col);
			}
			else if(input[2].equals("B")) {
				board[Drow][Dcol] = new Bishop("black",Drow, Dcol);
				board[row][col] = new EmptyTile(row, col);
			}
			else {
				board[Drow][Dcol] = new Queen("black",Drow, Dcol);
				board[row][col] = new EmptyTile(row, col);
			}
			return;
		}
			//Implements canEmpassant for white pawns that enter row 4
		if (board[row][col].identity.equals("pawn") && board[row][col].player.equals("white") && Drow == 4 && row == 6) {
			board[Drow][Dcol] = board[row][col];
			board[Drow][Dcol].canEnpassant = true;
			board[row][col] = new EmptyTile(row, col);
			return;
		}
			//Implements canEmpassant for black pawns that enter row 3
		if (board[row][col].identity.equals("pawn") && board[row][col].player.equals("black") && Drow == 3 && row == 1) {
			board[Drow][Dcol] = board[row][col];
			board[Drow][Dcol].canEnpassant = true;
			board[row][col] = new EmptyTile(row, col);
			return;
		}
			//Implements enpassant for white pawns on black pawns who moved in the most recent turn
		if (board[row][col].identity.equals("pawn") && Drow == 2 && row == 3 && (Dcol == col+1 || Dcol == col-1) && (board[row][col-1].canEnpassant == true || board[row][col+1].canEnpassant == true)){
			//Moves white pawn to destination
			board[Drow][Dcol] = board[row][col];
			board[row][col] = new EmptyTile(row, col);
			//Takes away black pawn due to enpassant
			board[row][Dcol] = new EmptyTile(row, Dcol);
			return;
		}
			//Implements enpassant for black pawns on white pawns who moved in the most recent turn
		if (board[row][col].identity.equals("pawn") && Drow == 5 && row == 4 && (Dcol == col+1 || Dcol == col-1) && (board[row][col-1].canEnpassant == true || board[row][col+1].canEnpassant == true)){
			//Moves black pawn to destination
			board[Drow][Dcol] = board[row][col];
			board[row][col] = new EmptyTile(row, col);
			//Takes away white pawn due to enpassant
			board[row][Dcol] = new EmptyTile(row, Dcol);
			return;
		}	
		//replaces the destination tile with the piece, and the origin tile with an empty tile
			board[Drow][Dcol] = board[row][col];
			board[Drow][Dcol].hasMoved = true;
			board[Drow][Dcol].col = Dcol;
			board[Drow][Dcol].row = Drow;
			board[row][col] = new EmptyTile(row, col);
			return;
	}
	
	//Method called to reset taken/attacked values of the board
	/**
	 * 
	 * @param x
	 * @param y
	 * @param setUnitsAttacked
	 */
	public void resetAttacked(int x, int y, boolean setUnitsAttacked) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				board[i][j].attackingKing = false;
				if (board[i][j].player.equals("white") || board[i][j].player.equals("black")) {
					if (i == x && j == y) {
						board[i][j].takenOrAttacked = false;
					} else {
						if (setUnitsAttacked) {
							board[i][j].takenOrAttacked = true;
						} else {
							board[i][j].takenOrAttacked = false;
						}
					}
				} else {
					board[i][j].takenOrAttacked = false;
				}
			}
		}
	}
	
	//Method called to check if a player is in check
	/**
	 * 
	 * @param player
	 * @return
	 */
	public boolean inCheck(String player) {
		//gets king indexes
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
		//assigns takenOrAttacked values
		resetAttacked(kingRow, kingCol, true);
		setAttack(player, true);
		
		if (board[kingRow][kingCol].takenOrAttacked) {
			return true;
		} else {
			return false;
		}
	}
	//method called to calculate takenOrAttacked by a certain side
	/**
	 * 
	 * @param player
	 * @param kingAttack
	 */
	public void setAttack(String player, boolean kingAttack) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (!board[i][j].player.equals(player) && !board[i][j].player.equals("neutral")) {
					if (!kingAttack && board[i][j].identity.equals("king")) {
						continue;
					}
					board = board[i][j].attacking(board);
				}
			}
		}
	}
	
	
	//Method called to check if a player is in checkmate
	/**
	 * 
	 * @param player
	 * @return
	 */
	public boolean inCheckmate(String player) {
		//gets king indexes
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
		
		//king must be in check
		if (!inCheck(player)) {
			return false;
		}
		//king's surroundings must be taken or attacked
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				if (kingRow+i < 0 || kingRow+i > 7 || kingCol+j < 0 || kingCol+j > 7) {
					continue;
				}
				if (!board[kingRow+i][kingCol+j].takenOrAttacked) {
					return false;
				}
			}
		}
		//Attacking piece(s) cannot be taken
		resetAttacked(kingRow, kingCol, false);
		if (player.equals("white")) {
			setAttack("black", true);
		} else {
			setAttack("white", true);
		}
		boolean attackingPieceCantTake = false;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				//finds attacking piece(s)
				if (board[i][j].attackingKing && !board[i][j].player.equals(player) && !board[i][j].takenOrAttacked) {
					
					//Path between attacking piece(s) and king cannot be blocked, unless attacking piece is knight or pawn
					if (board[i][j].identity.equals("pawn") || board[i][j].identity.equals("knight")) {
						return true;
					}
					
					//checks for horizontal movement
					if (board[i][j].identity.equals("rook") || board[i][j].identity.equals("queen")) {
						if ((kingRow-i) == 0 || (kingCol-j) == 0) {
							//there must be at least one space between attacking piece and king
							if((Math.abs(kingRow-i) == 1) || (Math.abs(kingCol-j) == 1)) {
								return true;
							} else {
								

								//resets takenOrAttacked for king not allowed to move between attacking piece and itself
								resetAttacked(kingRow, kingCol, false);
								if (player.equals("white")) {
									setAttack("black", false);
								} else {
									setAttack("white", false);
								}
								
								
								
								if (i > kingRow) {
									boolean continueThing = false;
									for (int k = kingRow+1; k < i; k++) {
										//a space between can be attacked
										if(board[k][kingCol].takenOrAttacked) {
											continueThing = true;
										}
									}
									if (continueThing) {
										continue;
									}
									return true;
								} else if (i < kingRow) {
									boolean continueThing = false;
									for (int k = kingRow-1; k > i; k--) {
										//a space between can be attacked
										if(board[k][kingCol].takenOrAttacked) {
											continueThing = true;
										}
									}
									if (continueThing) {
										continue;
									}
									return true;
								} else if (j > kingCol) {
									boolean continueThing = false;
									for (int k = kingCol+1; k < j; k++) {
										//a space between can be attacked
										if(board[kingRow][k].takenOrAttacked) {
											continueThing = true;
										}
									}
									if (continueThing) {
										continue;
									}
									return true;
								} else if (j < kingCol) {
									boolean continueThing = false;
									for (int k = kingCol-1; k > j; k--) {
										//a space between can be attacked
										if(board[kingRow][k].takenOrAttacked) {
											continueThing = true;
										}
									}
									if (continueThing) {
										continue;
									}
									return true;
									
								}
							}
						}
					}
					
					//checks for diagonal movement
					if (board[i][j].identity.equals("bishop") || board[i][j].identity.equals("queen")) {
						if (Math.abs(kingRow-i) == Math.abs(kingCol-j)) {
							//there must be at least one space between attacking piece and king
							if(Math.abs(kingRow-i) == 1) {
								return true;
							} else {
								//resets takenOrAttacked for king not allowed to move between attacking piece and itself
								resetAttacked(kingRow, kingCol, false);
								if (player.equals("white")) {
									setAttack("black", false);
								} else {
									setAttack("white", false);
								}
								
								if (i > kingRow && j > kingCol) {
									boolean continueThing = false;
									int k = kingCol+1;
									for (int l = kingRow+1; l < i; l++,k++) {
										if(board[l][k].takenOrAttacked) {
											continueThing = true;
										}
									}
									if (continueThing) {
										continue;
									}
									return true;
								} else if (i > kingRow && j < kingCol) {
									boolean continueThing = false;
									int k = kingCol-1;
									for (int l = kingRow+1; l < i; l++,k--) {
										if(board[l][k].takenOrAttacked) {
											continueThing = true;
										}
									}
									if (continueThing) {
										continue;
									}
									return true;
								} else if (i < kingRow && j > kingCol) {
									boolean continueThing = false;
									int k = kingCol+1;
									for (int l = kingRow-1; l > i; l--,k++) {
										if(board[l][k].takenOrAttacked) {
											continueThing = true;
										}
									}
									if (continueThing) {
										continue;
									}
									return true;
								} else if (i < kingRow && j < kingCol) {
									boolean continueThing = false;
									int k = kingCol-1;
									for (int l = kingRow-1; l > i; l--,k--) {
										if(board[l][k].takenOrAttacked) {
											continueThing = true;
										}
									}
									if (continueThing) {
										continue;
									}
									return true;
								}
							}
						}
					}
				}
			}
			if (attackingPieceCantTake) {
				continue;
			}
		}
		//if attacking piece can be taken, then we make sure if we do attack the attacking piece, the king is not in check
		if (attackingPieceCantTake) {
			return true;
		} else {
			return false;
		}
	}
	
}
