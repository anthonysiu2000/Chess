package chess;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader; 

public class Chess {
	
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
		if (input.length == 1 && !input[0].equals("draw")) {
			return null;
		}
		if (input.length == 3 && (!input[2].equals("draw?") && !input[2].equals("N") && !input[2].equals("R") && !input[2].equals("Q") && !input[2].equals("B") && !input[2].equals("P"))) {
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
		//3rd input can only be N,R,p,Q,B if pawn is entering last rank
		//single input "draw" can only be allowed after a "draw?" by other player
		//all of these errors are errors in input, not illegal moves, which are implemented elsewhere
		
		return input;
	}
	
	//main method
	public static void main(String[] args) throws IOException {

		ChessBoard BOARD = new ChessBoard();
		while (true) {
			//displays board
			BOARD.display();
			
			while (true) {
				//gets input
				String[] input = getMove(BOARD);
				if (input == null) {
					System.out.print("Invalid input. Try again.");
					continue;
				}
				//checks if selected unit is the player's and if the move is legal
				int col = input[0].charAt(0) - 97;
				int row = input[0].charAt(1) - 49;
				int Dcol = input[1].charAt(0) - 97;
				int Drow = input[1].charAt(1) - 49;
				if (!BOARD.board[row][col].player.equals("white") && BOARD.whiteTurn == true) {
					System.out.print("Illegal move, try again");
					continue;
				}
				if (!BOARD.board[row][col].player.equals("black") && BOARD.whiteTurn == false) {
					System.out.print("Illegal move, try again");
					continue;
				}
				if (!BOARD.board[row][col].isLegal(Drow, Dcol)) {
					System.out.print("Illegal move, try again");
					continue;
				}
				//executes the move and updates board
				ChessBoard tempBOARD = BOARD;
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
				BOARD = tempBOARD;
				break;
			}
			
			//changes turn and checks for check/checkmate/draw
			if (BOARD.whiteTurn) {
				BOARD.whiteTurn = false;
				if (BOARD.inCheck("white")) {
					System.out.print("Check");
				}
			} else {
				BOARD.whiteTurn = true;
				if (BOARD.inCheck("black")) {
					System.out.print("Check");
				}
			}
		}
		
	}
}
