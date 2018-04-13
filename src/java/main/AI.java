import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Taylor Nguyen
 */
public class AI {

	/**
	 * Picks a random spot in the game
	 *
	 * @param game
	 * @return
	 */
	public static int pickSpot(final TicTacToe game) {
		// find all available spots
		final List<Integer> availableSpots = new ArrayList<>();
		for (int i = 1; i < TicTacToe.BOARD_SIZE + 1; i++) {
			if (!game.isSpotTaken(i)) {
				availableSpots.add(i);
			}
		}

		// randomly select one
		final Random rd = new Random();
		return availableSpots.get(Math.abs(rd.nextInt() % availableSpots.size()));
	}

}
