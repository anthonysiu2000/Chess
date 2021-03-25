package chess;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader; 

/**
 * Chess is a class that manages the inputs of the player and outputs through the console.
 * 
 * @author 		Anthony Siu
 * @author 		Benjamin Lee
 * @version		%I% %G%
 * @since		1.0
 *
 */
public class Chess {
	/**
	 * The method for getting commands through the console. The method getMove takes inputs and processes them into
	 * commands for the program to do moves in chess on the board.
	 * <p>
	 * This method listens for an input in the console, upon receiving said input, it completes commands based on
	 * the parameters it is given. This is done through the BufferedReader and InputStreamReader commands.
	 * 
	 * @param board			the chess board created for moves to be called for
	 * @return				the input, if legal
	 * @throws IOException	if an input or output exception occurred
	 *						input errors to remember, but not implemented yet
	 *						3rd input can only be N,R,Q,B if pawn is entering last rank
	 *						single input "draw" can only be allowed after a "draw?" by other player, 
	 *						all of these errors are errors in input, not illegal moves, which are implemented elsewhere
	 */
	//method called to ask for and store user input
	public static String[] getMove(ChessBoard board) throws IOException {
		//variables to store user input
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String[] input;
		
		//obtains input
		if (board.whiteTurn) {
			System.out.print("\nWhite's move: ");
		} else {
			System.out.print("\nBlack's move: ");
		}
		input = reader.readLine().trim().split("\\s+");
		System.out.print("\n");
		
		//incorrect inputs
		if (input.length > 3) {
			return null;
		}
		if (input.length == 1 && (!input[0].equals("draw") && !input[0].equals("resign"))) {
			return null;
		}
		if (input.length == 3 && (!input[2].equals("draw?") && !input[2].equals("N") && !input[2].equals("R") && !input[2].equals("Q") && !input[2].equals("B"))) {
			return null;
		}
		if (input.length > 1) {
			if (input[0].length() != 2) {
				return null;
			}
			if (input[1].length() != 2) {
				return null;
			}
			if (input[0].charAt(0) < 97 || input[0].charAt(0) > 104) {
				return null;
			}
			if (input[0].charAt(1) < 49 || input[0].charAt(1) > 56) {
				return null;
			}
			if (input[1].charAt(0) < 97 || input[1].charAt(0) > 104) {
				return null;
			}
			if (input[1].charAt(1) < 49 || input[1].charAt(1) > 56) {
				return null;
			}
		}
		//input errors to remember, but not implemented yet
		//3rd input can only be N,R,Q,B if pawn is entering last rank
		//single input "draw" can only be allowed after a "draw?" by other player
		//all of these errors are errors in input, not illegal moves, which are implemented elsewhere
		
		return input;
	}
	
	/**
	 * This method does the outputs of the console to tell the player of the status of the game or the status of their input.
	 * It constitutes a display and outputs of the chess game.
	 * <p>
	 * This method displays the board and manages the turns of white and black pieces, illegal moves, and inputs to draw and resign.
	 * There is also outputs from the console on whether white or black wins the game or if there is a draw based on inputs to draw,
	 * resign, or checkmates. Stalemates are not accounted for.
	 * <p>
	 * These include:
	 * <ul>
	 * <li>Displays board, sets takenOrAttacked values as according to the player, to help castling legality
	 * <li>Resign
	 * <li>Draw confirm
	 * <li>Draw request
	 * <li>Checks if selected unit is the player's and if the move is legal
	 * <li>Executes the move and updates board
	 * <li>Checks if the move puts the same player in check
	 * <li>Break statement used when either player resigns/draws
	 * <li>Changes turn and checks for check/checkmate
	 * <ul>
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		ChessBoard BOARD = new ChessBoard();
		boolean resigning = false;
		boolean drawing = false;
		
		while (true) {
			//displays board
			BOARD.display();
			//sets takenOrAttacked values as according to the player, to help castling legality
			if (BOARD.whiteTurn) {
				BOARD.inCheck("white");
			} else {
				BOARD.inCheck("black");
			}
			
			while (true) {
				//gets input
				String[] input = getMove(BOARD);
				if (input == null) {
					System.out.print("Invalid input. Try again.");
					continue;
				}
				//resign 
				if (input[0].equals("resign")) {
					if (BOARD.whiteTurn) {
						System.out.print("Black wins");
						resigning = true;
						break;
					} else {
						System.out.print("White wins");
						resigning = true;
						break;
					}
				}
				//draw confirm
				if (input[0].equals("draw")) {
					if (!drawing) {
						System.out.print("Invalid input. Try again.");
						continue;
					} else {
						resigning = true;
						break;
					}
				}
				//draw request
				drawing = false;
				if (input.length == 3 && input[2].equals("draw?")) {
					drawing = true;
				}
				//checks if selected unit is the player's and if the move is legal
				int col = input[0].charAt(0) - 97;
				int row = 7 - (input[0].charAt(1) - 49);
				int Dcol = input[1].charAt(0) - 97;
				int Drow = 7 - (input[1].charAt(1) - 49);
				if (!BOARD.board[row][col].player.equals("white") && BOARD.whiteTurn == true) {
					System.out.print("Illegal move, try again");
					continue;
				}
				if (!BOARD.board[row][col].player.equals("black") && BOARD.whiteTurn == false) {
					System.out.print("Illegal move, try again");
					continue;
				}
				if (!BOARD.board[row][col].isLegal(BOARD.board, Drow, Dcol)) {
					System.out.print("Illegal move, try again");
					continue;
				}
				//executes the move and updates board
				ChessBoard tempBOARD = new ChessBoard();
				tempBOARD.clone(BOARD);
				tempBOARD.execute(input);
				//checks if the move puts the same player in check
				if (tempBOARD.inCheck("white") && tempBOARD.whiteTurn == true) {
					System.out.print("Illegal move, try again");
					continue;
				}
				if (tempBOARD.inCheck("black") && tempBOARD.whiteTurn == false) {
					System.out.print("Illegal move, try again");
					continue;
				}
				BOARD.clone(tempBOARD);
				break;
			}
			//break statement used when either player resigns/draws
			if (resigning) {
				break;
			}
			//changes turn and checks for check/checkmate
			if (BOARD.whiteTurn) {
				BOARD.whiteTurn = false;
				if (BOARD.inCheckmate("black")) {
					BOARD.display();
					System.out.print("Checkmate\n");
					System.out.print("White wins\n");
					break;
				}
				if (BOARD.inCheck("black")) {
					System.out.print("Check\n\n");
				}
			} else {
				BOARD.whiteTurn = true;
				if (BOARD.inCheckmate("white")) {
					BOARD.display();
					System.out.print("Checkmate\n");
					System.out.print("Black wins");
					break;
				}
				if (BOARD.inCheck("white")) {
					System.out.print("Check\n\n");
				}
			}
		}
		
	}
}
