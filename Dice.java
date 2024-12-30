/*Assignment 4 (Dice): Lilli Lewis 
 * 10/25/23
 * I confirm that the above list of sources is complete AND that I have 
 *  not talked to anyone else about the solution to this problem.*/
public class Dice {
	//fields
	private int side;
	
	//constructor
	public Dice() {
		this.side = 0;
	}//constructor
	
	/**
	 * Sets side to i
	 * @param i, an int between 1 and 6 (inclusive)
	 */
	public void setSide(int i) {
		this.side = i;
	}//setSide
	
	/**
	 * Returns the value of the side that's been rolled
	 * @return i, an int
	 */ 
	public int getSide() {
		return this.side;
	}//getSide
	
	/**
	 * Checks if side is equal to n
	 * @param n, an int
	 * @return true if n is equal to the side
	 */
	public boolean checkSide(int n) {
		return (this.side == n);
	}//checkSide

}//Dice
