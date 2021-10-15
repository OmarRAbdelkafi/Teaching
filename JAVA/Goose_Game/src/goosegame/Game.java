/**
 * Game.java
*/
package goosegame;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Game {

	protected List<Player> thePlayers;
	protected Board board;
	private Iterator<Player> it_thePlayers;

	public Game(Board board) {
		thePlayers = new ArrayList<Player>();
		this.board = board;
	}

	/** must not be called when game has begun (not secured at now) */
	public void addPlayer(String name) {
	    this.addPlayer(new Player(name));
	}

	/** must not be called when game has begun (not secured at now) */
  public void addPlayer(Player player) {
       Cell zero = board.getCell(0);
       player.setCell(zero);
       zero.welcomePlayer(player);
       thePlayers.add(player);
  }

	/**
	 * to manage the go back when index is greater than the nb of cells of the
	 * board
	*/
	private int normalizeIndex(int index) {
		if (index > board.nbOfCells()) {
			return board.nbOfCells() - (index - board.nbOfCells());
		} else
			return index;
	}

	private Player nextPlayer() {
		if (it_thePlayers == null || (!it_thePlayers.hasNext())) {
			it_thePlayers = this.thePlayers.iterator();
		}
		return it_thePlayers.next();
	}

	public void play() {
		boolean won = false;
		Player player = null;

		while (!won) {
			player = this.nextPlayer();
			this.displayTurn(player);
			won = this.manageTurn(player);
		}
		this.displayWinner(player);
	}

	private boolean manageTurn(Player player) {
	   boolean won = false;
		if (this.canPlay(player)) {
			Cell reachedCell = this.play(player);
			this.movePlayer(player, reachedCell);
			won = (reachedCell.getIndex() == board.nbOfCells());
		} else {
			this.displayPlayerCannotPlay(player);
		}
		return won;
	}

	private Cell play(Player player) {
		int diceThrow = player.twoDiceThrow();
		this.displayPlayerDice(player, diceThrow);
		int reachedIndex = normalizeIndex(player.getCell().getIndex() + diceThrow);
		this.displayPlayerReaches(player, board.getCell(reachedIndex));
		// player can bounce when reaching a cell
		int realIndex = normalizeIndex(reachedIndex + board.getCell(reachedIndex).bounce(diceThrow));
		Cell reachedCell = board.getCell(realIndex);
		if (realIndex != reachedIndex) {
			this.displayPlayerJumps(player, reachedCell);
		}
		return reachedCell;
	}

	private boolean canPlay(Player player) {
		return player.getCell().canBeLeft();
	}

	private void movePlayer(Player player, Cell reachedCell) {
		Cell currentCell = player.getCell();
		if (reachedCell.isBusy()) {
			displaySwapPlayer(reachedCell.getPlayer(), currentCell);
			currentCell.welcomePlayer(reachedCell.getPlayer());
		} else {
			currentCell.welcomePlayer(null);
		}
		reachedCell.welcomePlayer(player);
	}

	private void displayTurn(Player player)  {
		System.out.print("\n"+player + " is in cell " + player.getCell().getIndex()+ ", ");
	}
	private void displayPlayerDice(Player player, int diceThrow)  {
		System.out.print(player + " throws "+diceThrow);
	}
	private void displayPlayerReaches(Player player, Cell reached)  {
		System.out.print(" and reaches "+reached);
	}
	private void displayPlayerJumps(Player player, Cell reached)  {
		System.out.print(" and jumps to "+reached);
	}
	private void displayPlayerCannotPlay(Player player)  {
		System.out.print(player + " cannot play.");
	}
	private void displayWinner(Player player) {
		System.out.print("\n"+player + " has won.");
	}
	private void displaySwapPlayer(Player player, Cell destinationCell) {
		System.out.print(" cell is busy, "+player + " is sent to " + destinationCell);
	}
}// Game
