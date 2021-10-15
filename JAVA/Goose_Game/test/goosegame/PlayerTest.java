package goosegame;

import static org.junit.Assert.*;
import goosegame.BasicCell;
import goosegame.Cell;
import goosegame.Player;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PlayerTest {

	private Cell cell;
	private Player player;

	@Before
	public void setUpBeforeClass() throws Exception {
		this.cell = new StartCell();
		this.player = new Player("playerTest");
	}

	@Test
	public void testPlayer() {
		assertNotNull("player created ?", player);
	}

	@Test
	public void testGetSetCell() {
		// same tests for setter and getter
		assertNull("no cell set", player.getCell());
		player.setCell(cell);
		assertNotNull("cell set", player.getCell());
	}

	// ---Pour permettre l'exécution des test----------------------
 public static junit.framework.Test suite() {
		 return new junit.framework.JUnit4TestAdapter(goosegame.PlayerTest.class);
 }

}
