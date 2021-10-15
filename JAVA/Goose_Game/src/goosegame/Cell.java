package goosegame;

/**
 * Interface for the cells of the "jeu de l'oie" game. <br/> Note that there can be only 
 * one player by cell, the starting cell (index 0) excepted.
 */
public interface Cell {
	/**
	 * @return <code>true</code> if and only if the player in this cell can freely
	 *  leaves the cell, else he must wait for another player to reach this cell 
	 *  or wait a given number of turns
	 */
	public boolean canBeLeft();

	/** returns the index of this cell */
	public int getIndex();

	  /**
     * returns the size of the bounce for the player when the player
     *    reaches this cell.  For normal cells, the returned value is 0 and is thus independent from
     *   <code>diceThrow</code>.
     * @param diceThrow the result of the dice when the player reaches this cell
     * @return the index of the actual cell where the player eventually
     * arrives when the given throw of dice sends the player in this cell
     */
	public int bounce(int diceThrow);

	/** returns <code>true</code> iff a player is in this cell */
	public boolean isBusy();

	 /**  handles what happens when a player arrives in this cell 
     * @param player the new player in the cell
     */
	public void welcomePlayer(Player player);

	/** gets the player in this cell <code>null</code> if none */
	public Player getPlayer();
}// Cell
