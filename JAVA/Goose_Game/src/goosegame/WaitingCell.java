
/**
 * WaitCell.java
*/
package goosegame;

public class WaitingCell extends BasicCell {

    protected int waitTime;
    protected int waitDuration;

    public WaitingCell (int index, int waitDuration){
	super(index);
	this.waitDuration = waitDuration;
    }

    public boolean canBeLeft() {
	return (waitTime--) == 0;
    }

    public void welcomePlayer(Player player) {
	super.welcomePlayer(player);
	waitTime = waitDuration;
    }

	public String toString() {
		return super.toString()+" (wait for "+this.waitDuration+" turns )";
	}
}// WaitCell
