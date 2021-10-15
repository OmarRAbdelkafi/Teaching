/**
 * ClassicBoard.java
*/
package goosegame;

public class ClassicBoard extends Board {

	private static final int CLASSICAL_DEFAYULT_CELLS = 63;
   public static final ClassicBoard THE_BOARD = new ClassicBoard();

	private ClassicBoard() {
		super(CLASSICAL_DEFAYULT_CELLS);
	}

	/** initializes the board by creating the cells */
	protected void initBoard() {

		for (int i = 1; i < NB_OF_CELLS + 1; i++) {
			theCells[i] = new BasicCell(i);
		}
		// special cells
		// goose cells
		for (int i = 9; i < NB_OF_CELLS; i = i + 9) {
			theCells[i] = new GooseCell(i);
		}
		// jum cells
		theCells[58] = new TeleportCell(58, 1);
		theCells[42] = new TeleportCell(42, 30);
		theCells[6] = new TeleportCell(6, 12);
		// wait cells
		theCells[19] = new WaitingCell(19, 2);
		// trap cells
		theCells[52] = new TrapCell(52);
		theCells[31] = new TrapCell(31);
	}

}// ClassicBoard
