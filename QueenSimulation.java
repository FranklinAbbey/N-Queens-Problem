/***********************************************************************
*
*  Queen Simulation determines the number of solutions to a given 
*  "N-Queens" problem.
*  
* @author
*     Frank Abbey / Maia Gray  
* @version
*     April 12, 2019           
*
***********************************************************************/
import java.util.EmptyStackException;
import java.util.InputMismatchException;
import java.util.Scanner;

class QueenSimulation {

   private static Scanner in = new Scanner(System.in);
   
   public static void main(String[] args) {
      
      System.out.println("        ~ ~ ~ N-Queens Simulation ~ ~ ~ ");
      System.out.println("The N Queens puzzle is the problem of placing N chess queens");
      System.out.println("on an NxN chessboard so that no two queens attack each other.");
      System.out.println("\nN must be at least 4, as there are no solutions for gameboards");
      System.out.println("smaller than that."); 
      
      String findMore = "Y";
      while(findMore.equalsIgnoreCase("Y")) {  
      
         int gameSize = 3;
         //Exception is handled if given a non integer, but gameSize must also
         //be greater than three.    
         while (gameSize <= 3) { 
         
            System.out.print("\nPlease enter a valid game size (4 or greater) : ");
            try {
               gameSize = in.nextInt();
            }
            catch(InputMismatchException e) {
               System.out.println(e);
               in.next();
            }
            
         }
      
         findSolutions(gameSize);
         System.out.print("\nFind another solution (Y/N)? ");
         findMore = in.next();
     
      }
      
      System.out.println("\nexiting...\nThank you for using QueenSimulation.java!");
           
   }
   
   /*
   *  Discovers both the number of and coordinates of each solution
   *  based on the given gameboard size.
   *
   * @param int gameSize
   *     the size of the chess board
   * @retrun none
   * @precondition
   *     "gameSize" is valid (an integer > 3)
   */
   public static void findSolutions(int gameSize) {
      //the "gameBoard" is from here forward referred to as a
      //"LinkedStack" object of "Queen" object type
      
      
      LinkedStack<Queen> gameBoard = new LinkedStack<Queen>();
      
      int stackSize = 0;
      int solCount = 0;
      Queen topQueen = new Queen();
      Queen testQueen = new Queen();
      Queen tempQueen = new Queen();
      //lastWasPopped is used to reassign variables correctly
      boolean lastWasPopped = false;
      
      //the position of "topQueen" is paramount, and used as an 
      //indicator throughout the algorithm 
      gameBoard.push(topQueen);
      stackSize++;
      
      //while "topQueen" does not exceed the end of the board
      while(topQueen.getCol() <= gameSize) {
      
         //while the board isn't filled with one specific solution
         //(this would be a stackSize of 4 for a 4 x 4 gamebaord) 
         while(stackSize < gameSize) {
         
            //"tempQueen" is the last Queen placed
            try {
               tempQueen = gameBoard.itemAt(0);
            }
            catch (EmptyStackException e) {
               System.out.println(e);
            }
            
            if(lastWasPopped) {
               testQueen = new Queen(testQueen.getRow(), testQueen.getCol() + 1);
               lastWasPopped = false;
            }
            else    
               testQueen = new Queen(tempQueen.getRow() + 1, 1);
               
            int i;
            //loop through all existing queens, starting at the bottom of LinkedStack/top
            //of "gameBoard". For loop is re-initialized if conflicts are found, causing this
            //to process an indefinite amount of times.
            for(i = stackSize - 1; i >= 0; i--) {
               //temp initialized for use in while loop, 
               //but value should end up being "itemAt(i);
               Queen temp = new Queen();
               try {
                  temp = gameBoard.itemAt(i);      
               }
               catch (EmptyStackException e) {
                  System.out.println(e);
               }
               while(temp.conflict(testQueen) && testQueen.getCol() <= gameSize) {
                  //if "testQueen" needs to be incremented, all other existing Queens must
                  //be checked for conflict again
                  testQueen.setCol(testQueen.getCol() + 1);
                  i = stackSize; 
               }
               
               //if you've reached the limit of "testQueen" and there are still conflicts
               //AND "topQueen" is still the only Queen on the stack
               if(testQueen.getCol() > gameSize && stackSize == 1) {
                  topQueen = gameBoard.pop();
                  topQueen.setCol(topQueen.getCol() + 1);
                  testQueen = new Queen(topQueen.getRow() + 1, 1);
                  gameBoard.push(topQueen);  
               }
               
               else if(testQueen.getCol() > gameSize) {
                  testQueen = gameBoard.pop();
                  testQueen.setCol(testQueen.getCol() + 1);
                  
                  if(i == stackSize) {
                     i--;
                  }
                  stackSize--;
               }
               
            }   
            
            //if the for loop finished and there wasn't a conflict
            //and the top queen wasn't adjusted
            if(testQueen != topQueen) {
               gameBoard.push(testQueen);
               stackSize++;
            }             
         
         }//ends INDIVIDUAL solution
         
         if(topQueen.getCol() <= gameSize) {
         
            solCount++;
            System.out.print("\n\nSolution #" + solCount + ": ");
            
            //printing the coordinates of all Queens in the LinkedStack
            for(int i = stackSize - 1; i >= 0; i--) {
               Queen index = gameBoard.itemAt(i);
               System.out.print("(row : " + index.getRow() + " , column : " + index.getCol() + ")");   
            }
         }
         
         //popping the last Queen placed and starting over
         gameBoard.pop();
         stackSize--;
         lastWasPopped = true;
      
      }//ends TOTAL solutions
      
      System.out.println("\n\nTotal solutions for game size " + gameSize + " is " + solCount);
      
   }//ends findSolutions() 
   
}//ends main()

