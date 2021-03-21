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
	
	//Method called to check if a player is in check
	public boolean inCheck(String player) {
		return true;
	}
	//Method called to check if a player is in checkmate
	public boolean inCheckmate(String player) {
		return true;
	}
	
}
