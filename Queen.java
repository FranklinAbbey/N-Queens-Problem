/***********************************************************************
*
*  Queen class used to represent a queen game piece placed on 
*  a chess board.
*  
* @author
*     Frank Abbey / Maia Gray  
* @version
*     April 12, 2019           
*
***********************************************************************/
class Queen {
   
   // Invariant of the Queen class:
   //   1. "row" represents the row, or "x" coordinate of the queen piece, and 
   //      "col" represents the column, or "y" corrdinate of the queen piece        
   //   2. Neither row or col should exceed the size of the chess board
   //       
   private int row;
   private int col;
   
   /**
   * No-argument constructor initializes a Queen's coordinates to (1, 1)
   * @param - none
   * @return - none
   *
   **/ 
   public Queen() {
      row = 1;
      col = 1;
   }
   
   /**
   * Constructor initializes queen to given coordinates
   * @param - int r, int c
   *     the row and column the queen is placed in
   * @return - none
   * 
   **/
   public Queen(int r, int c) {
      row = r;
      col = c;
   }
   
   /**
   * Accessor method retrieves the row of a queen
   * @param - none
   * @return - int row
   *     the row of the queen
   * 
   **/
   public int getRow() {
      return row;
   }
   
   /**
   * Accessor method retrieves the column of a queen
   * @param - none
   * @return - int col
   *     the column of the queen
   * 
   **/
   public int getCol() {
      return col;
   }
   
   /**
   * Modifier method alters the row of a queen
   * @param - int r
   * @return - none
   * @postcondition
   *     the queen's row position is now set to "r"
   * 
   **/
   public void setRow(int r) {
      row = r;
   }
   
   /**
   * Modifier method alters the column of a queen
   * @param - int c
   * @return - none
   * @postcondition
   *     the queen's column position is now set to "c"
   * 
   **/
   public void setCol(int c) {
      col = c;
   }
   
   /**
   * Determines if there is a conflict between two queens
   * @param - Queen bottom
   * @return - boolean
   *     true - there is a conflict
   *     false - there is NO conflict
   * @precondition
   *     it is assumed each new queen is placed in a different row
   *
   **/
   public boolean conflict(Queen bottom) {
      //if there is a conflict in rows (added safety if precondition is not met)
      if(row == bottom.row)
         return true;
      //if there is a conflict in columns   
      if(col == bottom.col)
         return true;
      //if there is a diagonal conflict, "abs()" method used in case Queens are called on in reverse order.  
      if(col + (Math.abs(bottom.row - row)) == bottom.col || col - (Math.abs(bottom.row - row)) == bottom.col)
            return true; 
      
      return false;      
   
   }
   
   /**
   * Prints the coordinates of a single Queen
   * @param - none
   * @return - String
   *     "row = row , column = col"
   *
   **/
   public String toString() {
   
      return "(row = " + row + " , column = " + col + ")";
   }

}//ends Queen class

