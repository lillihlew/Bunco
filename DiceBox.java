/*Assignment 4 (DiceBox): Lilli Lewis 
 * 10/25/23
 * I confirm that the above list of sources is complete AND that I have 
 *  not talked to anyone else about the solution to this problem.*/
import java.util.Random;

public class DiceBox {
	//fields
	protected Dice d1 = new Dice();
	protected Dice d2 = new Dice();
	protected Dice d3 = new Dice();
	
	//constructor
	public DiceBox() {
		d1.setSide(0);
		d2.setSide(0);
		d3.setSide(0);
	}//constructor
	
	/**
	 * randomly generate three rolls and set them to the sides of the dice
	 */
	public void shake() {
		int r1 = roll();
		this.d1.setSide(r1);
		int r2 = roll();
		this.d2.setSide(r2);
		int r3 = roll();
		this.d3.setSide(r3);
	}//shake
	
	/**
	 * randomly generates a number between 1 and 6 (inclusive) and returns that value.
	 * @return roll, the side of the dice that was randomly generated
	 */
	public static int roll() {
		//generate a random digit between 1 and 6 (inclusive)
		Random r = new Random();
		int roll = r.nextInt(6)+1;
		return roll;
	}//roll
	
	/**
	 * prints rolls from the box
	 */
	public void seeResults() {
		System.out.println(this.d1.getSide() +", "+ this.d2.getSide() + ", "+this.d3.getSide());
	}//seeResults
	
	/**
	 * add up rolls 
	 * @return an int, the sum of rolls from box
	 */
	public int boxSum() {
		return this.d1.getSide() + this.d2.getSide() + this.d3.getSide();
	}
}//DiceBox
