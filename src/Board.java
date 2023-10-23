import java.util.ArrayList;

public class Board {

    private int[][] gameboard;
    private ArrayList<Mine> mines;
    private int numberOfMines;

    // Added a member variable to hold the number of columns in gameboard
    private int cols;

    // Added a member variable to hold the number of rows in gameboard
    private int rows;


    public Board(int x, int y) {

        // Added 2 calls to setter methods for rows and cols to instantiate variables
        setRows(y);
        setCols(x);

        this.gameboard = new int[x][y];
        this.mines = new ArrayList<>();

    }

    private void setNumberOfMines(int mines) {

        this.numberOfMines = mines;

    }

    // Added a setter method for member variable cols
    private void setCols(int cols){
        this.cols = cols;
    }

    // Added a getter method for member variable cols
    private int getCols(){
        return this.cols;
    }

    // Added a setter method for member variable rows
    private void setRows( int rows ){
        this.rows = rows;
    }

    // Added a getter method for member variable rows
    private int getRows(){
        return this.rows;
    }


    private int getNumberOfMines() {
        return this.numberOfMines;
    }

    public void initBoard() {

        //Random number for x and y coordinates depending on board size

        //Create new mine
        for (int i = 0; i < numberOfMines; i++) {
            // int newMine = new Mine();
            // add to mineList?
        }
        //add mines to board
    }

    public boolean checkWin(){

        return false;
    }

    public boolean checkIfHit(){

        return false;
    }

    // Changed the method to print the whole board
    // instead of a method just returning a string
    //public String toString() {
    public void printBoard() {

        //char aChar = 'X';
        System.out.printf("         ");
        for( int col = 1; col <= this.cols; col++ )
        {
            System.out.printf("Col%2d   ", col);
        }
        System.out.printf("%n");

        for( int row = 0; row < this.rows; row++ )
        {
            System.out.printf("Row%2d  |",row+1);
            for( int col = 0; col < this.cols; col++ )
            {
                System.out.printf("   %C   |", this.gameboard[row][col] );
            }
            System.out.printf("%n");
        }
    }
}
