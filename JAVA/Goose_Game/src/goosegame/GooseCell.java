/**
 * GooseCell.java
*/
package goosegame;

public class GooseCell extends BasicCell {

	public GooseCell(int index) {
		super(index);
	}

	public int bounce(int dieThrow) {
		return dieThrow;
	}
	public String toString() {
		return super.toString()+" (goose)";
	}
}// GooseCell
