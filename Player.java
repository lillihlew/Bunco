/*Assignment 4 (Player): Lilli Lewis 
 * 10/25/23
 * I confirm that the above list of sources is complete AND that I have 
 *  not talked to anyone else about the solution to this problem.*/
public class Player {
	//fields
	private String name;
	private int points;
	
	//empty constructor
	public Player() {
		this.name = "Comp";
		this.points = 0;
	}//empty constructor

	//one argument constructor, overloading the original one
	public Player(String str) {
		this.name = str;
		this.points = 0;
	}//one argument constructor
	
	/**
	 * Adds points from a round to a player's total number of points
	 * @param i, the points from the round
	 */
	public void addPoints(int i) {
		this.points+= i;
	}//setPoints

	/**
	 * Name accessor
	 * @return name, a String
	 */
	public String getName() {
		return this.name;
	}//getName
	
	/**
	 * points accessor
	 * @return points, an int
	 */
	public int getPoints() {
		return this.points;
	}//getPoints
	
	/**
	 * sets points to 0
	 */
	public void resetPoints() {
		this.points = 0;
	}
}//Player
