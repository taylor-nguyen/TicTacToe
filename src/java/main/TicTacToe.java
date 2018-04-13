
/**
 * @author Taylor Nguyen
 */
public class TicTacToe {
	private static final char EMPTY_CELL = '-';
	static final int BOARD_SIZE = 9;

	private final char[] board;
	private final char userMarker;
	private final char aiMarker;
	char currentMarker;
	private char winner;

	public TicTacToe(final char playerToken, final char aiToken) {
		this.userMarker = playerToken;
		this.aiMarker = aiToken;
		this.currentMarker = this.userMarker;
		this.board = setUpBoard();
		this.winner = EMPTY_CELL;
	}

	private static char[] setUpBoard() {
		final char[] newBoard = new char[BOARD_SIZE];
		for (int i = 0; i < newBoard.length; i++) {
			newBoard[i] = EMPTY_CELL;
		}
		return newBoard;
	}

	public boolean playTurn(final int spot) {
		// validate the intended spot
		final boolean isValid = withinRange(spot) && !isSpotTaken(spot);
		if (isValid) {
			this.board[spot - 1] = this.currentMarker;
			this.currentMarker = this.currentMarker == this.userMarker ? this.aiMarker : this.userMarker;
		}
		return isValid;
	}

	boolean isSpotTaken(final int spot) {
		return this.board[spot - 1] != EMPTY_CELL;
	}

	private boolean withinRange(final int spot) {
		return spot < this.board.length + 1 && spot > 0;
	}

	//@formatter:off
	/**
	 * Prints out the current board
	 * 	// | - | - | -
        // ------------
        // | - | - | -
        // ------------
		// | - | - | -
	 */
	//@formatter:on
	public void printBoard() {
		System.out.println();
		for (int i = 0; i < this.board.length; i++) {
			if (i % 3 == 0 && i != 0) {
				System.out.println();
				System.out.println("-------------");
			}
			System.out.print(" | " + this.board[i]);
		}
		System.out.println();
	}

	//@formatter:off
	/**
	 * 	// | 1 | 2 | 3
        // ------------
        // | 4 | 5 | 6
        // ------------
		// | 7 | 8 | 9
	 */
	//@formatter:on
	public static void printIndexBoard() {
		System.out.println();
		for (int i = 0; i < 9; i++) {
			if (i % 3 == 0 && i != 0) {
				System.out.println();
				System.out.println("------------");
			}
			System.out.print(" | " + (i + 1));
		}
		System.out.println();
	}

	public String gameOver() {
		if (isThereAWinner()) {
			// someone wins
			return "We have a winner! The winner is " + (this.winner == this.userMarker ? "you" : "me") + "("
					+ this.winner + "'s)";
		} else if (isBoardFilled()) {
			return "Draw: Game over!";
		}
		return "notOver";
	}

	private boolean isBoardFilled() {
		for (int i = 0; i < this.board.length; i++) {
			if (this.board[i] == EMPTY_CELL) {
				return false;
			}
		}
		return true;
	}

	private boolean isThereAWinner() {
		final boolean diagonalsAndMiddles = (leftDiag() || rightDiag() || middleRow() || middleCol())
				&& this.board[4] != EMPTY_CELL;
		final boolean topRowAndCol = (firstRow() || firstCol()) && this.board[0] != EMPTY_CELL;
		final boolean bottomRowAndCol = (lastRow() || lastCol()) && this.board[8] != EMPTY_CELL;

		// decide the winner
		if (diagonalsAndMiddles) {
			this.winner = this.board[4];
		} else if (topRowAndCol) {
			this.winner = this.board[0];
		} else if (bottomRowAndCol) {
			this.winner = this.board[8];
		}
		return diagonalsAndMiddles || topRowAndCol || bottomRowAndCol;
	}

	private boolean rightDiag() {
		return this.board[2] == this.board[4] && this.board[4] == this.board[6];
	}

	private boolean leftDiag() {
		return this.board[0] == this.board[4] && this.board[4] == this.board[8];
	}

	private boolean lastCol() {
		return this.board[2] == this.board[6] && this.board[6] == this.board[8];
	}

	private boolean middleCol() {
		return this.board[1] == this.board[4] && this.board[4] == this.board[7];
	}

	private boolean firstCol() {
		return this.board[0] == this.board[3] && this.board[3] == this.board[6];
	}

	private boolean lastRow() {
		return this.board[6] == this.board[7] && this.board[7] == this.board[8];
	}

	private boolean middleRow() {
		return this.board[3] == this.board[4] && this.board[4] == this.board[5];
	}

	private boolean firstRow() {
		return this.board[0] == this.board[1] && this.board[1] == this.board[2];
	}

}
