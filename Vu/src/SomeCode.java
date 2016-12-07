import java.util.Scanner;
public class SomeCode {

	//These variables are flexible and can be used by all methods
	static int row = 0;
	static int column = 0;
	static int playerTurn = 1;
	static int gameMode = 0;
	static int botwins = 0;
	static int playerwins = 0;
	static int Xwins = 0;
	static int Owins = 0;

	public static void main(String[] args) {
		//Import scanner
		Scanner input = new Scanner(System.in);
		
		//These two booleans are important to warp the player around the game interface
		//I really wish I can somehow make it more simple, though
		boolean menu = true;
		boolean gameInterface = true;
		
		//The main game interface loop
		do {
		//The menu loop
		while (menu) {
			//Disabling main game interface loop just makes it work well
			//I don't want to know what happens if I do otherwise
			gameInterface = false;
			//Sweet Tic Tac Toe graphics
			System.out.println("==============================================");
			System.out.println("___ _ ____    ___ ____ ____    ___ ____ ____ ");
			System.out.println(" |  | |    __  |  |__| |    __  |  |  | |___");
			System.out.println(" |  | |___     |  |  | |___     |  |__| |___ ");
			System.out.println("==============================================");
			System.out.println();
			
			//Menu options and such
			System.out.println("Welcome to Tic Tac Toe! What would be your selection?");
			System.out.println("(1) Play against a computer (no A.I.)");
			System.out.println("(2) Play against a human (on same computer)");
			System.out.println("(3) Check out the stats");
			System.out.println("(4) Exit the game");
			System.out.print("Your selection: ");

			//Receive the selection from the user
			int menuSelection = input.nextInt();
			
			//Some fun switch statements, depending on what the user picked as options
			switch (menuSelection) {
			case 1: System.out.println("Let's start a bot game!");
			System.out.println("Please note that the bot has no intelligence whatsoever.");
			gameMode = 1;
			menu = false;
			break;
			case 2: System.out.println("Let's play a multiplayer game!");
			System.out.println("Please note that you need a friend to play this with");
			gameMode = 2;
			menu = false;
			break;	
			case 3: System.out.println();
			System.out.println("Let's check the stats");
			System.out.println("HUMAN VS. COMPUTER GAMES");
			System.out.println("Number of computer wins: " + botwins);
			System.out.println("Number of player wins: " + playerwins);
			System.out.println();
			System.out.println("HUMAN VS. HUMAN GAMES");
			System.out.println("Number of player X wins: " + Xwins);
			System.out.println("Number of player O wins: " + Owins);
			System.out.println("Press enter to return to the main menu");
			pressEnterToContinue();
			break;
			case 4: System.out.println("Okay. The game will exit...");
					System.exit(0);	
					break;
			default: System.out.println("The program will now nuke itself in rage");
					 System.exit(0);
					 break;
					
			}
		}

		//Creating 2D array
		String[][] board = createBoard(3,3);

		boolean gameOn = true;
		while (gameOn) {
			//display the board
			displayBoard(board);

			boolean emptyspace;
			String option;

			do {
				if (gameMode == 1 && playerTurn == 2) {
					System.out.println("The computer will try and make its choice...");
				}
				//call 2 custom methods
				row = rowSelection();
				column = columnSelection();

				//assign position
				String position = board[row][column];

				//if position empty = valid
				if (position.equals("|   |")) {
					emptyspace = true;
				}
				//if not tell user to insert it again
				else {
					emptyspace = false;
					if (gameMode == 2 || (gameMode == 1 && playerTurn==1)) {
					System.out.println("That position has been chosen. Try again."); }
				}
			} while (emptyspace==false);


			//if good insert into board
			boardInsertion(board);

			//checking winner
			if (checkWinner(board)) {
				displayBoard(board);
				String player;
				//what player?
				switch (gameMode) {
				case 2: if (playerTurn == 1) {
							player = "X";
							Xwins++;
						}
						else {
							player = "O";
							Owins++;
						}
						System.out.println("Player " + player + " has won.");
						System.out.print("Do you want to play again? y/n: ");
						option = input.next();
						gameOn = (option.equalsIgnoreCase("y"));
						if (gameOn == false) {
							System.out.println("Do you want to return to the main menu? y/n");
							option = input.next();
							gameInterface = (option.equalsIgnoreCase("y"));
							}
						break;
				case 1: if (playerTurn==1) {
							System.out.println("Congratulations, you have won!");
							playerwins++;
						}
						else {
							System.out.println("The computer won!");
							botwins++;
						}
						System.out.print("Do you want to play again? y/n: ");
						option = input.next();
						gameOn = (option.equalsIgnoreCase("y"));
						if (gameOn == false) {
							System.out.println("Do you want to return to the main menu? y/n");
							option = input.next();
							gameInterface = (option.equalsIgnoreCase("y"));
							}
				}
				//initiate board again
				board = createBoard(3, 3);
			}

			//checking draw (comments are same as checking winners)
			if (checkDraw(board)) {
				displayBoard(board);
				System.out.println("Tie game!");
				System.out.print("Do you want to play again? y/n: ");
				option = input.next();
				gameOn = (option.equalsIgnoreCase("y"));
				if (gameOn == false) {
					System.out.println("Do you want to return to the main menu? y/n");
					option = input.next();
					gameInterface = (option.equalsIgnoreCase("y"));
					}
				board = createBoard(3, 3);
			}

			//alternate turns
			if (playerTurn == 1) playerTurn = 2;
			else playerTurn = 1;
		}
		//boohoo
		System.out.println("Game over!");
		if (gameInterface==true) {
			System.out.println("Returning back to the menu...");
			gameOn = false;
			menu = true;
		}

		} while (gameInterface = true);
	}


	private static void boardInsertion(String[][] board) {
		//insert depending on player
		String selection;
		if (playerTurn == 1) {
			selection = "| X |";
		}
		else selection = "| O |";

		board[row][column] = selection;

	}

	private static int columnSelection() {
		//custom method to select column
		Scanner input = new Scanner(System.in);
		int column = 0;
		boolean validSelection = false;

		while (validSelection == false) {
			switch (gameMode) {
			case 2: if (playerTurn==1) {
						System.out.print("Enter the column for player X: ");
					}
					else {
						System.out.print("Enter the column for player O: ");
					}
					column = input.nextInt();
					if (column >=0 && column <=2) validSelection = true;
					else System.out.println("Invalid input!");
		
					break;
			case 1: if (playerTurn==1) {
						System.out.print("Please enter the column you wish to choose: ");
						column = input.nextInt();
						if (column >=0 && column <=2) validSelection = true;
						else System.out.println("Invalid input!");
					}
					else {
						column = (int) (Math.random() * (3 - 0) + 0);
						validSelection = true;
					}
					break;
			}
		}
		return column;
	}

	private static int rowSelection() {
		//custom method to select row
		Scanner input = new Scanner(System.in);
		int row = 0;
		boolean validSelection = false;

		while (validSelection == false) {
			switch (gameMode) {
			case 2: if (playerTurn==1) {
						System.out.print("Enter the row for player X: ");
					}
					else {
						System.out.print("Enter the row for player O: ");
					}
					row = input.nextInt();
					if (row >=0 && row <=2) validSelection = true;
					else System.out.println("Invalid input!");
					break;
			case 1: if (playerTurn==1) {
						System.out.print("Please enter the row you wish to choose: ");
						row = input.nextInt();
						if (row >=0 && row <=2) validSelection = true;
						else System.out.println("Invalid input!");
					}
					else {
						row = (int) (Math.random() * (3 - 0) + 0);
						validSelection = true;
					}
					break;
			}
		}
		return row;
	}

	private static void displayBoard(String[][] board) {
		//print the board for users to see
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				System.out.print(board[i][j]);
			}
			System.out.println();
		}

	}

	private static String[][] createBoard(int x, int y) {
		//initialize board
		String[][] board = new String[x][y];

		for (int i=0; i<board.length; i++) {
			for (int j=0; j<board[i].length; j++) {

				board[i][j] = "|   |";
			}
		}
		return board;
	}

	private static boolean checkWinner(String[][] board) {
		//check winner
		String token;
		if (playerTurn == 1) {
			token = "| X |";
		}
		else token = "| O |";
		return (checkColumn(board, token) || checkRow(board,token) || checkDiagonal(board, token));

	}

	private static boolean checkRow(String[][] board, String token) {
		//sweep by row
		int count = 0;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] == token) count++;
			}
			if (count == 3) return true;
			else count = 0;
		}

		return false;

	}

	private static boolean checkColumn(String[][] board, String token) {
		//sweep by column
		int count = 0;
		for (int j = 0; j < board[0].length; j++) {
			for (int i = 0; i < board.length; i++) {
				if (board[i][j] == token) count++;
			}
			if (count == 3) return true;
			else count = 0;
		}

		return false;
	}

	private static boolean checkDiagonal(String[][] board, String token) {
		//sweep by diagonal, from top left
		int count = 0;
		int x = 0;
		int y = 0;
		while (x < board.length && y < board.length) {

			if (board[y][x] == token) count++;
			if (count == 3) return true;
			x++;
			y++;
		}
		//rest
		count = 0;
		//sweep by diagonal, from top right
		x = 0;
		y = board.length - 1;
		while (x < board.length && y >= 0) {

			if (board[y][x] == token) count++;
			if (count == 3) return true;
			y--;
			x++;
		}


		return false;

	}
	
	private static boolean checkDraw(String[][] board) {
		int count = 0;
		//count + 1 if board is occupied by either x or o
		for (int i=0; i<board.length; i++) {
			for (int j=0; j<board[i].length; j++) {
				if (board[i][j] == "| X |" || board[i][j] == "| O |") {
					count++;
				}
			}
		}
		if (count==9) return true;
		else return false;
	}
	
	private static void pressEnterToContinue() { 
		try
		{
			System.in.read();
		}  
		catch(Exception e)
		{}  
	}
}