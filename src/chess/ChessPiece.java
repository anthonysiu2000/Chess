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
 * @author Anthony Siu
 * @author Benjamin Lee
 * 
 */
public abstract class ChessPiece {
	/**
	 * the row number of the piece, in chess terms, it is numbers 1 to 8
	 */
	public int row;
	/**
	 * the column number of the piece, in chess terms, it is letters a to h
	 */
	public int col;
	/**
	 * the owner of the piece, white or black
	 */
	public String player;
	/**
	 * the identity of the piece; pawn, knight, bishop, rook, queen, king
	 */
	public String identity;
	/**
	 * the value of whether a space is being attacked or not by a piece, set to false by default
	 */
	public boolean takenOrAttacked;
	/**
	 * the value of whether a piece has moved in the past or not, set to false by default
	 */
	public boolean hasMoved;
	/**
	 * the method used to determine which pieces are checking the king
	 */
	public boolean canEnpassant;
	/**
	 * the method used to determine which pieces are checking the king
	 */
	public boolean attackingKing;
	/**
	 * prints out the chess board
	 */
	public abstract void print();
	/**
	 * the method to check if move is legal
	 * 
	 * @param board			the 8x8 chess board created for pieces to be placed on and chess to be played
	 * @param x				the row number of the piece, in chess terms, it is numbers 1 to 8
	 * @param y				the column number of the piece, in chess terms, it is letters a to h
	 * @return				boolean true or false
	 */
	public abstract boolean isLegal(ChessPiece[][] board, int x, int y);
	/**
	 * the method used to flag tiles as being attacked
	 * 
	 * @param board			the 8x8 chess board created for pieces to be placed on and chess to be played
	 * @return				output
	 */
	public abstract ChessPiece[][] attacking(ChessPiece[][] board);
}