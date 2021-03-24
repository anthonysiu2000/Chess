package chess;
/**
 * ChessPiece is an abstract class used to represent pieces on the chess board.
 * Chess pieces are one of the major parts of chess and so, they have various values attached to themselves.
 * A ChessPiece object includes state information needed for different operations of chess.
 * This state information includes:
 * <ul>
 * <li>Coordinates of the piece
 * <li>Player ownership of piece
 * <li>Identity of the piece
 * <li>Status on being moved
 * <li>Status to be able to be taken by enpassant
 * <li>Status on it being attacked or not
 * </ul>
 * 
 * <p>
 * 
 * @author Anthony Siu
 * @author Benjamin Lee
 * @param row				the row number of the piece, in chess terms, it is numbers 1 to 8
 * @param col				the column number of the piece, in chess terms, it is letters a to h
 * @param player			the owner of the piece, white or black
 * @param identity			the identity of the piece; pawn, knight, bishop, rook, queen, king
 * @param takenOrAttacked	the value of whether a space is being attacked or not by a piece, set to false by default
 * @param hasMoved			the value of whether a piece has moved in the past or not, set to false by default
 * @param canEnpassant		the value of whether a pawn can be taken by an enemy pawn if the enemy chooses to do enpassant
 * @param attackingKing		the method used to determine which pieces are checking the king
 * @param print				prints out the chess board
 * @param isLegal			the method to check if move is legal
 * @param attacking			the method used to flag tiles as being attacked
 * 
 */
public abstract class ChessPiece {
	public int row;
	public int col;
	public String player;
	public String identity;
	public boolean takenOrAttacked;
	public boolean hasMoved;
	public boolean canEnpassant;
	public boolean attackingKing;
	/**
	 * 
	 */
	public abstract void print();
	/**
	 * 
	 * 
	 * @param board			the 8x8 chess board created for pieces to be placed on and chess to be played
	 * @param x				the row number of the piece, in chess terms, it is numbers 1 to 8
	 * @param y				the column number of the piece, in chess terms, it is letters a to h
	 * @return
	 */
	public abstract boolean isLegal(ChessPiece[][] board, int x, int y);
	/**
	 * 
	 * @param board
	 * @return
	 */
	public abstract ChessPiece[][] attacking(ChessPiece[][] board);
}