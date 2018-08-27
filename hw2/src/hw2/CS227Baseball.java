package hw2;

/**
 * Simplified model of American baseball.
 * 
 * THIS CODE WILL NOT COMPILE UNTIL YOU HAVE ADDED STUBS FOR 
 * ALL METHODS SPECIFIED IN THE JAVADOC
 * 
 * @author YOUR NAME HERE
 */
public class CS227Baseball
{
  /**
   * Constant indicating that a pitch results in a ball.
   */
  public static final int BALL = 0;
  
  /**
   * Constant indicating that a pitch results in a strike.
   */
  public static final int STRIKE = 1;
  
  /**
   * Constant indicating that a pitch results in an out from a fly ball.
   */
  public static final int POP_FLY = 2;
  
  /**
   * Number of strikes causing a player to be out.
   */
  public static final int MAX_STRIKES = 3;
  
  /**
   * Number of balls causing a player to walk.
   */
  public static final int MAX_BALLS = 4;
  
  /**
   * Number of outs before the teams switch.
   */
  public static final int MAX_OUTS = 3;
  /**
   * Base Trackers
   */
  private int Base1 = 0;
  private int Base2 = 0;
  private int Base3 = 0;
  private int Home = 0;
  /**
   * Game Tracker
   */
  private int Innings = 1;
  private int maxInngings = 9;
  private int Strikes = 0;
  private int Balls = 0;
  private int Outs = 0;
  /**
   * Point Tracker
   */
  private int Top = 1;
  private int Team1 = 0;
  private int Team2 = 0;
  private int AtBat = 0;
  /**
   * All The returns for the game
   */
  // TODO: everything else
  /**
   * @return
   *   Returns whether there is a player on first base.
   */
  public boolean playerOnFirst() {
	  if (Base1 == 1) {
		  return true;
	  }else {
		  return false;
	  }
  }
  /**
   * @return
   *   Returns whether there is a player on second base.
   */
  public boolean playerOnSecond() {
	  if (Base2 == 1) {
		  return true;
	  }else {
		  return false;
	  }
  }
  /**
   * @return
   *   Returns whether there is a player on third base.
   */
  public boolean playerOnThird() {
	  if (Base3 == 1) {
		  return true;
	  }else {
		  return false;
	  }
  }
  /**
   * @return
   *   Returns true if it's the first half of the inning (team 0 is at bat).
   */
  public boolean isTop() {
	  if (AtBat == 0) {
		  return true;
	  }else {
		  return false;
	  }
  }
  /**
   * @return
   *   Returns the score of the 
   *   indicated team, where an argument of true indicates 
   *   team 0 (batting in the top of the inning) and an 
   *   argument of false indicates team 1 (batting in the 
   *   bottom of the inning).
   */
  public int getScore(boolean team0) {
	  if (team0) {
		  return Team1;
	  }else {
		  return Team2;
	  }
  }
  /**
   * @return
   *   Returns Number of Innings
   */
  public int getInning() {
	  return Innings;
  }
  /**
   * @return
   *   Returns Strikes
   */
  public int getStrikes() {
	  return Strikes;
  }
  /**
   * @return
   *   Returns balls
   */
  public int getBalls() {
	  return Balls;
  }
  /**
   * @return
   *   Returns Outs
   */
  public int getOuts() {
	  return Outs;
  }
  /**
   * Constructs a game that has the given number of innings and starts at the top of inning 1.
   * @author Gavin Monroe
   * @param
   * 	givenNumInnings - number of innings for the game
   * @return
   *   Nothing
   */
 public CS227Baseball(int givenNumInnings) {
	  maxInngings = givenNumInnings;
  }
  /**
   * @return
   *   Boolean: True = if Innings = 10 or over else False
   */
  public boolean isOver() {
	  if (Innings >= maxInngings) {
		  return true;
	  }else {
		  return false;
	  }
  }
  /**
   * Simple checks to make sure 
   * Outs don't go over and that the team is changed
   * with the changeTeam function.
   * @author Gavin Monroe
   * @param
   * 	outcome = If its a strike, popfly or a ball; 
   * @return
   *   Nothing
   */
  public void pitch(int outcome) {
	  if (outcome == STRIKE) {
		  Strikes++;
	  }else if (outcome == POP_FLY) {
		 Outs++;
		 Balls = 0;
		 Strikes = 0;
	  }else if (outcome == BALL) {
		Balls++;
	  }
	  if (Strikes == 3) {
		  Outs++;
		  Strikes = 0;
		  Balls = 0;
	  }
	  if (Balls == 4) {
		  advanceRunners(true);
		  Balls = 0;
		  Strikes = 0;
	  }
	  if (Outs >= 3) {
		  changeTeam();
	  }

  }
  /**
   * Advances Runners with true and then
   * updates the bases depending on the numBases
   *@author Gavin Monroe
   *@param
   * 	numBases = bases to pass on;
   * @return
   *   Nothing
   */
  public void pitchWithHit(int numBases) {
	 Strikes = 0;
	 Balls = 0;
	  //Advance Players
	 advanceRunners(true);
	 //Switch Bases
	  for(int counter = 0; counter<numBases-1; counter++) {
          advanceRunners(false);
      }
  }
  /**
   * Advances Runners with false if
   * whichBaseFielded = 1 else it puts true
   * Finally calculates the points depending on the team.
   * @author Gavin Monroe
   * @param
   * 	numBases = bases to pass on; whichBaseFielded = Which person is out.
   * @return
   *   Nothing
   */
  public void pitchWithHitAndOut(int numBases, int whichBaseFielded) {
		 Strikes = 0;
		 Balls = 0;
		  //Advance Players
		 advanceRunners(true);
		 //Switch Bases
		 for(int counter = 0; counter<numBases-1; counter++){
	          advanceRunners(false);
	      }
	  	 //Clear Bases and Advance Players
	  	 if (whichBaseFielded == 1) {
	  		 advanceRunners(false);
	  	 }else if (whichBaseFielded == 2) {
	  		Base2 = 0;
	  		Outs++;
	  	 }else if (whichBaseFielded  == 3) {
	  		Base3 = 0;
	  		Outs++;
	  	 }else if (whichBaseFielded == 4 && Base3 == 1) {
	  		Outs++;
	  		Base3=0;
	  	 }
	  	  if (Outs >= 3) {
			  changeTeam();
		  }
	  }
  /**
   * Sets Base3 to Base2
   * Sets Base2 to Base 1
   * And finally decides to add 0 or 1 to Base1
   * Depending if parameter is true or not.
   * @author Gavin Monroe
   * @param
   * 	newPlayerOnFirst = True put on base else Don't
   * @return
   *   Nothing
   */
  public void advanceRunners(boolean newPlayerOnFirst) {
	  //Switch Position on Base
	  if (Base3 == 1) {
          Base3 = 0;
          if (AtBat == 1) {
        	  Team2++;
          }else {
        	  Team1++;
          }
	  }
     Base3 = Base2;
     Base2 = Base1;
     
      if (newPlayerOnFirst) {
          Base1 = 1;
      }else {
    	  Base1 = 0;
      }
  }
  /**
   * Calculates the Points from the Bases.
   * @author Gavin Monroe
   * @param
   * 	numBases = is the number of bases that need to be advanced and added up.
   * @return
   *   The correct number of bases added up.
   */
  private int calcBases(int numBases) {
	  //Calculate all the bases regarding the NumBases
	  if (numBases == 4) {
		  return Base1 + Base2 + Base3 + 1;
	  }
	  //If its just 1 and Base 3 = 1 then Add point
	  if (numBases == 1 && Base3 == 1) {
		  return 1;
	  }
	  if (numBases == 2) {
		  return Base3 + Base2;
	  }
	  if (numBases == 3) {
		  return Base1 + Base2 + Base3;
	  }
	  //if nothing, then return 0
	  return 0;
  }
  /**
   * @author Gavin Monroe
   * Changes Team, setting Outs, Strikes, Balls, Bases to 0
   * Adds 1 to Innings
   * Changes the Team by which Int it is 0 or 1;
   * @return
   *   Nothing
   */
 
  private void changeTeam() {
	  //Reset Everything
	  Outs = 0;
	  Strikes = 0;
	  Balls = 0;
	  Base1 = 0;
	  Base2 = 0;
	  Base3 = 0;
	  //Advance Inngings
	  //Switch Teams
	  if (AtBat == 1) {
		  AtBat = 0;
		  Innings += 1;
	  }else {
		  AtBat = 1;
		 
	  }
  }
  /**
   * Returns a three-character string representing the players on base, 
   * in the order first, second, and third, where 'X' indicates a player 
   * is present and 'o' indicates no player.  For example, the string "XXo" 
   * means that there are players on first and second but not on third.
   * @author Gavin Monroe
   * @return
   *   three-character string showing players on base
   */
  public String getBases()
  {
    return (playerOnFirst() ? "X" : "o") +
        (playerOnSecond() ? "X" : "o") +
        (playerOnThird() ? "X" : "o");
  }
  
  /**
   * Returns a one-line string representation of the current game state.
   * The format is:
   * <pre>
   *    ooo Inning:1 (T) Score:0-0 Balls:0 Strikes:0 Outs:0
   * </pre>
   * The first three characters represent the players on base as returned by 
   * the <code>getBases()</code> method. The 'T' after the inning number indicates 
   * it's the top of the inning, and a 'B' would indicate the bottom.
   * 
   * @return
   *   one-line string representation of the game state
   */
  public String toString()
  {
    String bases = getBases();
    String topOrBottom = (isTop() ? "T" : "B");
    String fmt = "%s Inning:%d (%s) Score:%d-%d Balls:%d Strikes:%d Outs:%d";
    return String.format(fmt, bases, getInning(), topOrBottom, getScore(true), getScore(false), getBalls(), getStrikes(), getOuts());
  }

  /**
   * Returns a multi-line string representation of the current game state.
   * The format is:  
   * <pre>
   *     o - o    Inning:1 (T)
   *     |   |    Score:0-0
   *     o - H    Balls:0 Strikes:0 Outs:0
   * </pre>
   * The 'T' after the inning number indicates it's the top of the inning, 
   * and a 'B' would indicate the bottom.
   * @return
   *   multi-line string representation of current game state
   */
  public String toDisplayString()
  {
    String firstChar = (playerOnFirst() ? "X" : "o");
    String secondChar = (playerOnSecond() ? "X" : "o");
    String thirdChar = (playerOnThird() ? "X" : "o");
    String topOrBottom = (isTop() ? "T" : "B");
    String firstLine = String.format("%s - %s    Inning:%d (%s)\n", secondChar, firstChar, getInning(), topOrBottom);
    String secondLine = String.format("|   |    Score:%d-%d\n", getScore(true), getScore(false));
    String thirdLine = String.format("%s - H    Balls:%d Strikes:%d Outs:%d\n", thirdChar, getBalls(), getStrikes(), getOuts());
    return firstLine + secondLine + thirdLine;   
  }
  
}