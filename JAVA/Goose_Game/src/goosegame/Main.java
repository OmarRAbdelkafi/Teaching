/**
 * Main.java
 *
*/
package goosegame;

public class Main {

	public static void main(String[] args) {
		Board board = ClassicBoard.THE_BOARD;
		Game game = new Game(board);
		game.addPlayer("Omar");
		game.addPlayer("Sandra");
		game.addPlayer("sam");
		game.play();
	}

}// Main
