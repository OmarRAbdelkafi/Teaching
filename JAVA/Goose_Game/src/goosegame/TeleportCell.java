/**
 * JumpCell.java
*/
package goosegame;

public class TeleportCell extends BasicCell {

	protected int jumpIndex;

	public TeleportCell(int index, int jumpIndex) {
		super(index);
		this.jumpIndex = jumpIndex;
	}

	public int bounce(int dieThrow) {
		return this.jumpIndex - this.index;
	}
	public String toString() {
		return super.toString()+" (teleport to "+this.jumpIndex+")";
	}
}// JumpCell
