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
				board[i][j] = new EmptyTile();
			}
		}
		for (int k = 0; k < 8; k++) {
			board[1][k] = new Pawn("black");
			board[6][k] = new Pawn("white");
		}
		board[0][0] = new Rook("black");
		board[0][7] = new Rook("black");
		board[7][0] = new Rook("white");
		board[7][7] = new Rook("white");
		board[0][1] = new Knight("black");
		board[0][6] = new Knight("black");
		board[7][1] = new Knight("white");
		board[7][6] = new Knight("white");
		board[0][2] = new Bishop("black");
		board[0][5] = new Bishop("black");
		board[7][2] = new Bishop("white");
		board[7][5] = new Bishop("white");
		board[0][3] = new Queen("black");
		board[7][3] = new Queen("white");
		board[0][4] = new King("black");
		board[7][4] = new King("white");
		
		//White goes first
		whiteTurn = true;
	}
	
	//Method called when we want to display the board to console
	public void display() {
		
	}
	
	
}
