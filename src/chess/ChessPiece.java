package chess;

public abstract class ChessPiece {
	//Stores location of piece
	public int row;
	public int col;
	//Stores owner and identity of piece
	public String player;
	public String identity;
	//Used to determine if tile is being attacked, usually set to false
	public boolean takenOrAttacked;
	//Used to determine if piece already moved (specifically king or rook)
	public boolean hasMoved;
	//Used to determine if a pawn can be taken by enpassant
	public boolean canEnpassant;
	//method used to determine which pieces are checking the king
	public boolean attackingKing;
	//method to print piece to board
	public abstract void print();
	//method to check if move is legal
	public abstract boolean isLegal(ChessPiece[][] board, int x, int y);
	//method used to flag tiles as being attacked
	public abstract ChessPiece[][] attacking(ChessPiece[][] board);
}