import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static java.lang.Math.random;

public class Board {

    private char[][] gameboard;
    private List<Mine> mines;
    private int numberOfMines;

    // Added a member variable to hold the number of columns in gameboard
    private int cols;

    // Added a member variable to hold the number of rows in gameboard
    private int rows;

    private String symbol;

    public Board(int cols, int rows) {

        // Added 2 calls to setter methods for rows and cols to instantiate variables
        setRows(rows);
        setCols(cols);

        //Create gameboard
        this.gameboard = new char[cols][rows];
        this.mines = new ArrayList<>();
        this.symbol = " ";

    }

    //Set number of mines
    private void setNumberOfMines(int mines) {

        this.numberOfMines = mines;
    }

    // Added a setter method for member variable cols
    private void setCols(int cols) {
        this.cols = cols;
    }

    // Added a getter method for member variable cols
    private int getCols() {
        return this.cols;
    }

    // Added a setter method for member variable rows
    private void setRows(int rows) {
        this.rows = rows;
    }

    // Added a getter method for member variable rows
    private int getRows() {
        return this.rows;
    }


    private int getNumberOfMines() {
        return this.numberOfMines;
    }

    public void initBoard() {

        //Set blank space for board
        for (int i = 0; i < this.cols; i++) {
            for (int j = 0; j < this.rows; j++) {
                this.gameboard[i][j] = ' ';
            }
        }
    }

    public void setMines(int setThisNumberOfMines) {

        setNumberOfMines(setThisNumberOfMines);

        //Create new mines
        for (int i = 0; i < setThisNumberOfMines; i++) {

            boolean continueCreate = true;
            while (continueCreate) {
                int randomRow = getRandomNumber(1, this.rows);
                int randomCol = getRandomNumber(1, this.cols);

                if (!isThisPositionAMine(randomRow, randomCol)) {

                    Mine newMine = new Mine(randomRow, randomCol);
                    // add to mineList
                    this.mines.add(newMine);
                    continueCreate = false;
                }
            }
        }
    }

    public List<Mine> getMines() {
        return this.mines;
    }

    protected int posExist(Scanner scanner, String rowOrCol) {

        int pos = Main.checkInputIsANumber(scanner);
        if (rowOrCol.equals("col")) {

            while (pos > this.cols || pos < 1) {
                System.out.println("Make sure the column exist. Try again");
                pos = Main.checkInputIsANumber(scanner);
            }
        } else {
            while (pos > this.rows || pos < 1) {
                System.out.println("Make sure the row exist. Try again");
                pos = Main.checkInputIsANumber(scanner);
            }
        }

        return pos;
    }
    //Check if box is taken
    protected boolean isNotTaken(int row, int col){

        if (this.gameboard[col - 1][row - 1] == ' '){
            return true;
        }
        return false;
    }

    //Check if position is a mine
    private boolean isThisPositionAMine(int row, int col) {

        //List<Mine> mines = getMines();
        boolean foundMine = false;

        if (row < 1 || row > this.rows || col < 1 || col > this.cols ) return false;

        for (Mine mine : this.mines) {

            if (row == mine.getRow() && col == mine.getCol()) {
                foundMine = true;
            }
        }
        return foundMine;
    }

    //Create a random number to place the mine(s)
    private int getRandomNumber(int min, int max) {
        return (int) ((random() * (max - min + 1)) + min);
    }

    //Check if selected box is a hit
    public boolean checkIfHit(int row, int col) {

        return isThisPositionAMine(row, col);
    }

    //Mark the selected box
    //Display adjacent mines when open
    //Open empty boxes close by
    public void markPlayerChoice(int row, int col) {

        char adjacentMines = '0';

        if( row == 0 || row > this.rows ) return;
        if( col == 0 || col > this.cols ) return;
        if ( this.gameboard[ col - 1 ][ row - 1 ] != ' ' ) return;

        if (isThisPositionAMine(row - 1, col - 1 )) adjacentMines += 1;
        if (isThisPositionAMine(row - 1, col + 0 )) adjacentMines += 1;
        if (isThisPositionAMine(row - 1, col + 1 )) adjacentMines += 1;
        if (isThisPositionAMine(row + 0, col - 1 )) adjacentMines += 1;
        if (isThisPositionAMine(row + 0, col + 1 )) adjacentMines += 1;
        if (isThisPositionAMine(row + 1, col - 1 )) adjacentMines += 1;
        if (isThisPositionAMine(row + 1, col + 0 )) adjacentMines += 1;
        if (isThisPositionAMine(row + 1, col + 1 )) adjacentMines += 1;

        this.gameboard[col - 1][row - 1] = adjacentMines;

        //Open up empty boxes
        //Only called when adjacentMines = 0
        //method inside method
        if (adjacentMines == '0') {
            markPlayerChoice( row - 1, col - 1);
            markPlayerChoice( row - 1, col + 0);
            markPlayerChoice( row - 1, col + 1);
            markPlayerChoice( row + 0, col - 1);
            markPlayerChoice( row + 0, col + 1);
            markPlayerChoice( row + 1, col - 1);
            markPlayerChoice( row + 1, col + 0);
            markPlayerChoice( row + 1, col + 1);
        }
    }

    //Display all mines when game is over
    public void showAllMines() {
        for (Mine mine : this.mines) {
            this.gameboard[mine.getCol() - 1][mine.getRow() - 1] = mine.getSymbol();
        }
    }

    //Check if all boxes are selected and no hits, that would be a win!
    protected boolean checkIfWin() {

        boolean isWin = false;

        int numberOfMines = mines.size();
        int sumOfBoxesLeftToSelect = 0;
        int numberOfBoxes = this.rows * this.cols;

        for (int i = 0; i < this.cols; i++) {
            for (int j = 0; j < this.rows; j++) {
                if (this.gameboard[i][j] != ' ') {
                    sumOfBoxesLeftToSelect++;
                }
            }
        }

        if (sumOfBoxesLeftToSelect == (numberOfBoxes - numberOfMines)) {
            isWin = true;
        }

        return isWin;
    }

    // Changed the method to print the whole board
    // instead of a method just returning a string
    //public String toString() {
    public void printBoard() {

        for (int col = 0; col < this.cols; col++) {
            System.out.print("----");
        }
        System.out.print(" Mine Sweeper ");
        for (int col = 0; col < this.cols; col++) {
            System.out.print("----");
        }
        System.out.println();
        System.out.println();

        //char aChar = 'X';
        System.out.printf("         ");
        for (int col = 1; col <= this.cols; col++) {
            System.out.printf("Col%2d   ", col);
        }
        System.out.printf("%n");

        for (int row = 0; row < this.rows; row++) {

            System.out.print("        ");
            for (int col = 0; col < this.cols; col++) {
                System.out.print("------- ");
                if (col == this.cols - 1) {
                    System.out.println();
                }
            }

            System.out.printf("Row%2d  |", row + 1);
            for (int col = 0; col < this.cols; col++) {
                if (this.gameboard[col][row] <= '1'){
                   System.out.printf(Main.BLUE + "   %C   " + Main.RESET + "|", this.gameboard[col][row]);
                }
                else if (this.gameboard[col][row] == '2'){
                    System.out.printf(Main.YELLOW + "   %C   " + Main.RESET + "|", this.gameboard[col][row]);
                }
                else if (this.gameboard[col][row] >= '3'){
                     System.out.printf(Main.RED + "   %C   " + Main.RESET + "|", this.gameboard[col][row]);
                }
                else {
                    System.out.printf("   %C   |", this.gameboard[col][row]);
                }

            }
            System.out.printf("%n");
        }
        System.out.print("        ");
        for (int col = 0; col < this.cols; col++) {
            System.out.print("------- ");
        }
        System.out.println();
        System.out.println();
    }
}
