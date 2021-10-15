
/**
 * TrapCell.java
*/
package goosegame;

public class TrapCell extends BasicCell{

    public TrapCell (int index){
	super(index);
    }

    public boolean canBeLeft() {
	return false;
    }
	public String toString() {
		return super.toString()+" (trap)";
	}
}// TrapCell
