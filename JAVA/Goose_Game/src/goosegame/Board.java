/**
 * Board.java
*/
package goosegame;

abstract public class Board {

	/** must be greater than the max result of the thow of dies */
	protected final int NB_OF_CELLS;

	protected Cell[] theCells;

	public Board(int nbOfCells) {
		NB_OF_CELLS = nbOfCells;
		theCells = new Cell[NB_OF_CELLS + 1];
		this.theCells[0] = new StartCell();
		initBoard();
	}

	/** initializes the board by creating the cells */
	abstract protected void initBoard();

	public Cell getCell(int numero) {
		return theCells[numero];
	}

	public int nbOfCells() {
		return NB_OF_CELLS;
	}
}// Board
