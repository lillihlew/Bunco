import java.util.InputMismatchException;
import java.util.Scanner;

/*Assignment 4 (BeautifulCode): Lilli Lewis 
 * 10/25/23
 * I used Stack Overflow to discover how to scan in an enter from the console
 * https://stackoverflow.com/questions/18281543/java-using-scanner-enter-key-pressed
 * I confirm that the above list of sources is complete AND that I have 
 *  not talked to anyone else about the solution to this problem.*/
public class BuncoGame {
	public final static int ROUNDS = 6;//number of rounds in a game
	/**
	 * Checks if all of the dice have been rolled to the same side 
	 * @param db, a DiceBox
	 * @return true if all sides are the same and false if not
	 */
	public static boolean isABunco(DiceBox db, int round) {
		return ((db.d1.getSide())==(db.d2.getSide()) && 
				(db.d1.getSide())==(db.d3.getSide()) && 
				(db.d1.getSide() == round));
	}//isABunco
	
	/**
	 * Checks if all dice have been rolled to a 1
	 * @param db, a DiceBox
	 * @param round, an int
	 * @return true if DiceBox has a Bunco (using method above) and if the sides are all 1s
	 */
	public static boolean isAMiniBunco(DiceBox db, int round) {
		return ((db.d1.getSide())==(db.d2.getSide()) && 
				(db.d1.getSide())==(db.d3.getSide()) && 
				(db.d1.getSide() == 1)&&
				(round !=1));
	}//isAMiniBunco
	
	
	/**
	 * Simulates a round of Bunco played by one player, printing rolls and scores and interacting with the user via print statements
	 *   to the terminal.
	 * @param p, the Player who's turn it is
	 * @param db, a DiceBox 
	 * @param input, a Scanner to the user's input
	 * @param round, the round of Bunco it is
	 * @param last, if the player is the last player in the rotation of players
	 */
	public static void playRound(Player p, DiceBox db, Scanner input, int round, boolean last) {
		System.out.println(p.getName()+"'s turn!");//Tell user who's turn it is
		
		//initial shake of DiceBox if it's a user's turn
		if(!(p.getName() == "Comp")){//if it's not the computer playing
			System.out.println("Hit enter to shake the dice box!");
			if(input.nextLine().equals("")) {//if user hits enter
				db.shake();//shake the dice 
				System.out.println(p.getName()+"'s roll: ");
				db.seeResults();
			}//if user hits enter
		}//if not computer's turn
		
		//initial shake of DiceBox if it's computer's turn
		else {//computer's turn
			db.shake();//shake the dice again
			System.out.println("Comp's roll: ");
			db.seeResults();
		}//computer's turn
	
		boolean correctRoll = true;//correctRoll is if at least one of the die matches the round
		
		//start while loop
		while(correctRoll) {//while at least one die matches round			
			//check if any of the sides rolled match the round being played
			if(!((db.d1.checkSide(round)) || (db.d2.checkSide(round)) || (db.d3.checkSide(round)))) {//if none match
				System.out.println("None of the die matched.");
				//if it's the last player in the last round, exit the while loop
				if((last == true) && (round == 6))
					break;
				//Print player's points at end of the round
				System.out.println("After round "+round+ ": "+p.getName()+"'s points: "+p.getPoints());
				correctRoll=false;//set correcttRoll to false to be able to exit the loop
			}//if player didn't roll any dice to the proper side
			//check for a Bunco, a miniBunco, or just a sum of points
			else {//if player did roll some dice to proper side
				if(isABunco(db, round)) {//if it's a Bunco
					p.addPoints(21);
					System.out.println(p.getName()+" rolled a Bunco! They get 21 points");
				}//if it's a Bunco
				else {//it's not a Bunco
					if(isAMiniBunco(db, round)) {//if it's a mini Bunco
						p.addPoints(5);//add 5 points
						System.out.println(p.getName()+" rolled a mini Bunco! They get 5 points");
					}//if it's a mini Bunco
					else {//it's neither a Bunco or a mini Bunco
						int count = 0;//count of how many dice matched
						if (db.d1.checkSide(round)){//if die 1 is correct
							p.addPoints(1);//add a point
							count++;//increment count
						}//if die 1 matched
						if (db.d2.checkSide(round)){//if die 2 is correct
							p.addPoints(1);//add a point
							count++;//increment count
						}//if die2 matched
						if (db.d3.checkSide(round)){//if die 3 is correct
							p.addPoints(1);//add a point
							count++;//increment count
						}//if die 3 matched
						System.out.println(count +" of the die matched, so "+p.getName()+" gets "+count+" points.");
					}//it's neither a Bunco or a mini Bunco
				}//it's not a Bunco
				
				//instruct user to shake dice box again
				if(!(p.getName() == "Comp")){//if it's not the computer playing
					System.out.println("Hit enter to shake the dice box again");
					if(input.nextLine().equals("")) {//if user hits enter
						db.shake();//shake the dice box again
						//tell user what they rolled
						System.out.println(p.getName()+"'s roll: ");
						db.seeResults();
					}//if user shook
				}//if not computer's turn
				else {//computer's turn
					db.shake();//shake the dice again
					//tell user what computer rolled
					System.out.println("Comp's roll: ");
					db.seeResults();
				}//computer's turn
			}//else player rolled some dice to proper side
		}//while
	}//playRound
	
	
	
	
	public static void main(String[] args){
		try {//handle exceptions
			boolean newPlayers = true;//if user wants to play again with the same players
			Scanner input = new Scanner(System.in);//make a new Scanner for user input
			//greet user
			System.out.println("Hello! Welcome to the Bunco game.");
			while(newPlayers) {
				newPlayers=false;//assume user wants to play with same players because that's how I wrote the program
				boolean again = true;//boolean of if user wants to play again
				Player computer = new Player();//make computer a player
				//ask how many players
				System.out.println("How many players will there be, excluding me?");
				int numPlayers = input.nextInt();//store number of players into numPlayers
				//loop until user provides valid number of players
				while((numPlayers<1)||(numPlayers>3)) {
					System.out.println("There can only be between 1 and 3 other players. Please try again:");
					numPlayers = input.nextInt();
				}//at this point we will have valid number of players
				System.out.println("Sounds good! What is your name?");
				Player p1 = new Player(input.next());//make player 1
				System.out.println("Nice to meet you, "+p1.getName()+".");
				int p1Win = 0;//number of games won by user	
				int games = 1; //number of games played
				int compWin = 0;//number of games won by computer
				int ties = 0;//number of times computer and user have tied
				switch(numPlayers) {//switch on number of players
				/*A lot of the code in these three cases is similar, but it was necessary to write it all out like
				 * this since the situations are unique.*/
					case 1:{
						while(again) {//while user wants to keep playing
							//for one user, shake a dice box
							System.out.println("To decide who goes first, let's both shake the die cup! I'll go first!");
							//make dice box for computer, shake it, and print results to terminal
							DiceBox dbC = new DiceBox();
							dbC.shake();
							System.out.println("I rolled:");
							dbC.seeResults();
							//make dice box for user, instruct user to hit enter, and if they do, shake dice box and print results to terminal
							DiceBox dbU = new DiceBox();
							System.out.println("Your turn! Hit enter to shake the dice box");
							if(input.nextLine().equals("")&&(input.nextLine().equals(""))) {//bite off dangling enter. if user hits enter to start the game
								dbU.shake();
								System.out.println("You rolled:");
								dbU.seeResults();
								//loop if sums of two boxes are equal, shaking and presenting results until sums are not equal
								while(dbC.boxSum()==dbU.boxSum()) {//doing a loop in case we roll the same thing multiple times
									System.out.println("Since we rolled the same sum, we'll roll again. I'll go first.");
									dbC.shake();
									System.out.println("I rolled: ");
									dbC.seeResults();
									System.out.println("Your turn!");
									dbU.shake();
									System.out.println("You rolled:");
									dbU.seeResults();
								}//at this point, we will have different rolls
								if(dbU.boxSum()>dbC.boxSum()) {//if user rolls higher
									System.out.println("Since you rolled a higher sum, you'll go first!");
									//start the game
									for(int round = 1; round<(ROUNDS + 1); round++) {//loop through 6 rounds of the game
										System.out.println("----------ROUND "+round+"---------- ");//tell user which round it is
										System.out.print(p1.getName()+"'s points: "+p1.getPoints());//tell user how many points they have
										System.out.println(" Comp's points: "+computer.getPoints());//tell user how many points computer has		
										DiceBox db = new DiceBox();//make a new DiceBox 
										playRound(p1, db, input, round, false);//play round with user going first
										playRound(computer, db, input, round, true);//play round with computer going second
									}//end of the game
								}//if user rolled higher
								else{//if computer rolls higher
									System.out.println("Since I rolled a higher sum, I'll go first!");
									//start the game
									for(int round = 1; round<(ROUNDS+1); round++) {//loop through 6 rounds of the game
										System.out.println("----------ROUND "+round+"---------- ");//tell user which round it is
										System.out.print(p1.getName()+"'s points: "+p1.getPoints());//tell user how many points they have
										System.out.println(" Comp's points: "+computer.getPoints());//tell user how many points computer has	
										DiceBox db = new DiceBox();//make a new DiceBox
										playRound(computer, db, input, round, false);//play round with computer going first
										playRound(p1, db, input, round, true);//play round with user going second
									}//end of the game
								}//if computer rolled higher
						
								//results of game
								if(p1.getPoints()==computer.getPoints()) {//if a tie
									//inform user it was a tie and tell them how many points they scored
									System.out.println("We tied, each with "+p1.getPoints()+" points. Nobody wins.");
									ties++;//increment number of ties
								}else {//not a tie
									if(p1.getPoints()>computer.getPoints()) {//if user scored higher
										//inform user they won and tell them how many points they scored
										System.out.println("Congratulations, "+ p1.getName()+", you won with "+p1.getPoints()+" points!");
										p1Win++;//increment number of player wins
									}//if user scored higher
									else {//computer scored higher
										//inform user computer won and tell them how many points the computer scored
										System.out.println("Comp won with "+computer.getPoints()+" points.");
										compWin++;//increment number of computer wins
									}//computer scored higher
								}//not a tie
							}//if user doesn't hit enter to start the game
							
							//Tell user about stats of number of games, ties, wins, and losses
							System.out.println("Total number of games played: "+games);
							games++;
							System.out.println("Total number of ties: "+ties);
							System.out.println("Total number of games won by Comp: "+compWin);
							System.out.println("Total number of games won by "+p1.getName()+": "+p1Win);
							
							//ask user if they want to play again
							System.out.println("Would you like to play again?");
							String ans = input.next();//store answer into ans
							again = (!(ans.equals(("no").toLowerCase())));//if answer isn't 'no', play again
							
							//ask user if they would like to play with same players
							if(again) {
								System.out.println("Would you like to play with the same players?");
								ans = input.next();//store answer into ans
								newPlayers = (ans.equals(("no").toLowerCase()));//set newPlayers to true if user enters 'no'
							}//if user wants to play again
							
							if(newPlayers)//if user wants new players
								break;//exit this loop
							
							//reset points for both players
							p1.resetPoints();
							computer.resetPoints();
						}//while
					break;//end case 1
					}//case 1
					case 2:{
						//I use arrays a lot in cases 2 and 3 because it's easier to loop through based on order of input names
						int[] winsArr = {0, 0, 0};//number of wins from computer, player 1, and player 2
						System.out.println("What is the second person's name?");//prompt for player 2
						Player p2 = new Player(input.next());//create player 2
						if((p1.getName().equals(p2.getName()))) {//check for duplicate names, deliberately not case sensitive
							System.out.println("No duplicate names allowed. Please try again.");
							System.exit(0);//quit
						}//if duplicate names
						//welcome players to game
						System.out.println("Welcome to the game, "+p1.getName()+" and "+p2.getName());
						
						//start game
						while(again) {
							//for more than one user, roll a die
							System.out.println("To decide who goes first, let's all roll a die. I'll go first:");
							int[] arr = {0, 0, 0};//array of rolls
							//roll for computer, save into array, and inform users of computer's roll
							int compRoll = DiceBox.roll();
							arr[0]=compRoll;
							System.out.println("I rolled a "+compRoll);
							//tell user it's their turn and prompt for enter
							System.out.println("Your turn, "+p1.getName()+ ". Hit enter to roll a die.");
							//roll for player 1, save into array, and inform player 1 of their roll
							if(input.nextLine().equals("")&&input.nextLine().equals("")) {//bite off dangling enter. if user hits enter
								int p1Roll = DiceBox.roll();
								arr[1]=p1Roll;
								System.out.println(p1.getName()+" rolled a "+p1Roll);
							}//if p1 hits enter
							//move to player 2's turn to roll, and prompt them to hit enter to roll
							System.out.println("Your turn, "+p2.getName()+ ". Hit enter to roll a die.");
							if(input.nextLine().equals("")) {//if user hits enter
								//roll for player 2, inform player 2 of their roll, and store roll into roll array
								int p2Roll = DiceBox.roll();
								System.out.println(p2.getName()+" rolled a "+p2Roll);
								arr[2]=p2Roll;
							}//if p2 hits enter
							
							//find the max roll
							int max = 0;//set temporary max to 0
							String highRoller = "";//use to keep name of highest roller, just so code doesn't have to access it again and again
							int index = 0;//index in array of highest roll
							Player[] players = {computer, p1, p2};//array of players
							String[] names = {"Comp", p1.getName(), p2.getName()};
							for(int i = 0; i<(numPlayers+1);i++) {//iterate through array of rolls 
								if(arr[i]>max) {//if roll is higher than max. If there's a tie, the first person to roll the max will go first
									max = arr[i];//store roll as max
									highRoller = names[i];//store name of high roller
									index = i;//store index of highest roll (index in all of the arrays I've created are the same)
								}//if
							}//for, at end of this max has highest roll and highRoller has player's name
							//tell users who rolled the highest and decide they will go first
							System.out.println("Since "+highRoller+" had the highest roll, "+highRoller+" will go first.");
							
							//start the game
							for(int round = 1; round<(ROUNDS + 1); round++) {//play through 6 rounds
								System.out.println("----------ROUND: "+round+"----------");	//print which round it is
								DiceBox db = new DiceBox();//make a new DiceBox
								for(int i = 0; i<(numPlayers+1); i++) {//to go through players in order of input, starting with highest roller
									index = index % (numPlayers+1);	//set index to remainder of index divided by number of players (including computer)	
									playRound(players[index], db, input, round, (i==numPlayers));//play round for player
									index++;//increment index to move to next player
								}//for, end of turns
								System.out.println("Points at end of round "+round);//print all of players points at end of round
								for(int i = 0; i<(numPlayers+1);i++) {//iterate through players and print their names and scores
									System.out.print(players[i].getName()+": "+players[i].getPoints()+" ");
								}//for, printing scores
								System.out.println();//new line
							}//end of the game]					
							
							//determine winner
							int maxPoints = 0;//new temp int to store highest number of points
							String winner = "";//new temp to store winner's name
							for(int i = 0; i<(numPlayers+1);i++) {//iterate through players
								if(players[i].getPoints()>maxPoints) {//if player has more points than maxPoints
									maxPoints = players[i].getPoints();//max points is player's points
									winner = players[i].getName();//store name of player into winner
									index = i;//save index
								}//if
							}//for
							if((players[index].getPoints()) == (players[((index+1)%(numPlayers+1))].getPoints())||
									(players[index].getPoints()) == (players[((index+2)%(numPlayers+1))].getPoints())) {//check for a tie
								System.out.println("There was a tie, so there are no winners.");//inform user
								ties++;//increment ties
							}//if tie
							else {//no tie
								//tell who won
								System.out.println(winner+" won with "+maxPoints+" points!");
								winsArr[index]++;//increment winner's value in win array
							}//no tie
							
							//tell how many games have been played and increment games
							System.out.println(games+" games have been played");
							games++;
							
							//print out how many games each player has won
							for(int i = 0; i<(numPlayers+1); i++) {
								System.out.println(names[i]+" has won "+winsArr[i]+" games.");
							}//for
							
							//ask if user wants to play again
							System.out.println("Would you like to play again?");
							String ans = input.next();
							again = (!(ans.equals(("no").toLowerCase())));//set again to false if user enters 'no'
							
							//ask user if they would like to play with same players
							if(again) {
								System.out.println("Would you like to play with the same players?");
								ans = input.next();//store answer into ans
								newPlayers = (ans.equals(("no").toLowerCase()));//set newPlayers to true if user enters 'no'
							}//if user wants to play again
							
							if(newPlayers)//if user wants new players
								break;//exit this loop
							
							//reset points
							p1.resetPoints();
							p2.resetPoints();
							computer.resetPoints();
						}//while
						break;
					}//case 2
					case 3: {//three players 
						//This is almost exactly the same as case 2, just modified for three players
						int[] winsArr = {0, 0, 0, 0};//wins array for players
						//prompt for player 2
						System.out.println("What is the second person's name?");
						Player p2 = new Player(input.next());//create player 2
						//prompt for player 3
						System.out.println("What is the third person's name?");
						Player p3 = new Player(input.next());//create player 3
						if((p1.getName().equals(p2.getName()))||
								(p1.getName().equals(p3.getName()))||
								(p2.getName().equals(p3.getName()))) {//check if any names are equal, deliberately case insensitive
							System.out.println("No duplicate names allowed. Please try again.");
							System.exit(0);//exit program
						}//if duplicate names
						//greet users
						System.out.println("Welcome to the game, "+p1.getName()+", "+p2.getName()+", and "+p3.getName());
						
						//start game
						while(again) {
							//single die for more than one user
							System.out.println("To decide who goes first, let's all roll a die. I'll go first:");
							int[] arr = {0, 0, 0, 0};//make array of rolls
							//roll for computer, store roll into roll array and present roll to user
							int compRoll = DiceBox.roll();
							arr[0]=compRoll;
							System.out.println("I rolled a "+compRoll);
							//prompt player 1 to roll
							System.out.println("Your turn, "+p1.getName()+ ". Hit enter to roll a die.");
							if(input.nextLine().equals("")&&input.nextLine().equals("")) {//if player 1 hits enter
								//roll for player 1, store value into array, and present roll to player 1
								int p1Roll = DiceBox.roll();
								arr[1]=p1Roll;
								System.out.println(p1.getName()+" rolled a "+p1Roll);
							}//if player 1 hit enter
							//prompt player 2
							System.out.println("Your turn, "+p2.getName()+ ". Hit enter to roll a die.");
							if(input.nextLine().equals("")) {//if player 2 hits enter
								//roll for player 2, print roll, and store roll into rolls array
								int p2Roll = DiceBox.roll();
								System.out.println(p2.getName()+" rolled a "+p2Roll);
								arr[2]=p2Roll;
							}//if player 2 hit enter
							//prompt player 3
							System.out.println("Your turn, "+p3.getName()+ ". Hit enter to roll a die.");
							if(input.nextLine().equals("")) {//if player 3 hits enter
								//roll for player 3, present roll to player 3, and save roll into rolls array
								int p3Roll = DiceBox.roll();
								System.out.println(p3.getName()+" rolled a "+p3Roll);
								arr[3]=p3Roll;
							}//if player 3 hit enter
							//determine who rolled the highest
							int max = 0;//temp value for highest roll
							String highRoller = "";//temp string for highest roller name
							int index = 0;//temp index of highest roll since all arrays are in the same order
							Player[] players = {computer, p1, p2, p3};//players array
							String[] names = {"Comp", p1.getName(), p2.getName(), p3.getName()};//names array
							for(int i = 0; i<(numPlayers+1);i++) {//iterate through rolls array
								if(arr[i]>max) {//if roll is higher than max
									max = arr[i];//set roll to max
									highRoller = names[i];//set name of roller to highRoller
									index = i;//save index
								}//if
							}//for, at end of this max has highest roll and highRoller has player's name
							//present highest roller to user and tell them highRoller will go first
							System.out.println("Since "+highRoller+" had the highest roll, "+highRoller+" will go first.");
							
							//start the game
							for(int round = 1; round<(ROUNDS + 1); round++) {//play through 6 rounds
								System.out.println("----------ROUND: "+round+"---------- ");//print out which round it is	
								DiceBox db = new DiceBox();//make new dice box
								for(int i = 0; i<(numPlayers+1); i++) {//iterate through players
									index = index % (numPlayers+1);//set index to remainder of index divided by number of players
									playRound(players[index], db, input, round, (i==numPlayers));//play round for player
									index++;//increment index to switch players
								}//for player iteration
								System.out.println("Points at end of round "+round);//print out end of round
								//print out points for each player 
								for(int i = 0; i<(numPlayers+1);i++) {//iterate through players array
									System.out.print(players[i].getName()+": "+players[i].getPoints()+" ");
								}//for player iteration
								System.out.println();//new line
							}//end of the game
							int maxPoints = 0;//temp for highest number of points a player has
							String winner = "";//temp for winner's name
							for(int i = 0; i<(numPlayers+1);i++) {//iterate through players
								if(players[i].getPoints()>maxPoints) {//if player has more points than maxPoints
									maxPoints = players[i].getPoints();//set maxPoints to player's points
									winner = players[i].getName();//set player's name to winner
									index = i;//save index
								}//if
							}//for
							if((players[index].getPoints()) == (players[((index+1)%(numPlayers+1))].getPoints())||
									(players[index].getPoints()) == (players[((index+2)%(numPlayers+1))].getPoints()) ||
											(players[index].getPoints()) == (players[((index+3)%(numPlayers+1))].getPoints())) {//if there was a tie
								System.out.println("There was a tie, so there are no winners.");//inform users
								ties++;//increment ties
							}//if tie
							else{//not a tie
								//print out who won and how many points they had
								System.out.println(winner+" won with "+maxPoints+" points!");
								winsArr[index]++;//increment their value in winner array
							}//not a tie
							
							//print out how many games have been played
							System.out.println(games+" games have been played");
							games++;//increment number of games played
							//print out how many games each player has won
							for(int i = 0; i<(numPlayers+1); i++) {//iterate through players
								System.out.println(names[i]+" has won "+winsArr[i]+" games.");
							}//for player iteration
							
							//ask if users would like to play again
							System.out.println("Would you like to play again?");
							String ans = input.next();//save input into ans
							again = (!(ans.equals(("no").toLowerCase())));//play again unless ans is 'no'
							
							//ask user if they would like to play with same players
							if(again) {
								System.out.println("Would you like to play with the same players?");
								ans = input.next();//store answer into ans
								newPlayers = (ans.equals(("no").toLowerCase()));//set newPlayers to true if user enters 'no'
							}//if user wants to play again
							
							if(newPlayers)//if user wants new players
								break;//exit this loop
							
							//reset players' points
							p1.resetPoints();
							p2.resetPoints();
							p3.resetPoints();
							computer.resetPoints();
						}//while
						break;
					}//case 3
				}//switch
			}//while for same players
			input.close();//close scanner
			System.out.println("Goodbye!");
		}catch(InputMismatchException e){//handle improper input
			System.out.println("Error: improper input");
		}//catch
	}//main
}//BuncoGame