import java.util.Scanner;

/**
 * @author Taylor Nguyen
 */
public class App {

	public static void main(final String[] args) {
		final Scanner sc = new Scanner(System.in);
		boolean playMore = true;

		// game loop
		while (playMore) {
			// print out some statements here
			System.out.println("Welcome to Tic Tac Toe! You are about to go against "
					+ "the best player of Tic Tac Toe. Are you ready?\n"
					+ "First, choose a character to represent you and another character to represent me.");
			System.out.println();
			System.out.print("Enter a single character that will represent you on the board: ");
			final char playerToken = sc.next().charAt(0);
			System.out.print("Enter a single character that will represent your opponent on the board: ");
			final char aiToken = sc.next().charAt(0);

			final TicTacToe game = new TicTacToe(playerToken, aiToken);

			// game instruction
			System.out.println();
			System.out.println("Now we can start the game. To play, enter a number to put your token "
					+ "in its place.\nThe numbers go from 1-9, left to right. We shall see who will win this round.");
			TicTacToe.printIndexBoard();
			System.out.println();

			// game play
			while (game.gameOver().equals("notOver")) {
				if (game.currentMarker == playerToken) {
					System.out.println("It's your turn! Enter a spot for your token");
					int spot = sc.nextInt();
					while (!game.playTurn(spot)) {
						System.out.println("Try again. " + spot + " is invalid. This spot is already taken"
								+ " or it is out of range");
						spot = sc.nextInt();
					}
					System.out.println("You picked " + spot + "!");
				} else {
					System.out.println("It's my turn!");
					final int aiSpot = AI.pickSpot(game);
					game.playTurn(aiSpot);
					System.out.println("I picked " + aiSpot + "!");
				}
				System.out.println();
				game.printBoard();
			}
			System.out.println(game.gameOver());
			System.out.println();

			System.out.println("Do you want to play again? Enter Y if you do. " + "Enter anything else to exit.");
			final char response = Character.toUpperCase(sc.next().charAt(0));
			playMore = response == 'Y';
			System.out.println();
			System.out.println();
		}
		sc.close();
	}

}
