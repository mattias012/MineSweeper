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


    public Board(int x, int y) {

        // Added 2 calls to setter methods for rows and cols to instantiate variables
        setRows(y);
        setCols(x);

        //Create gameboard
        this.gameboard = new char[x][y];
        this.mines = new ArrayList<>();
        this.symbol = " ";

    }

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
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
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
    protected int posExistAndIsNotTakenAlready(Scanner scanner, String rowOrCol){

        int pos = Main.checkInputIsANumber(scanner);
        if (rowOrCol.equals("col")){

            while (pos > this.cols){
                System.out.println("Make sure the column exist. Try again");
                pos = Main.checkInputIsANumber(scanner);
            }
        }
        else {
            while (pos > this.rows){
                System.out.println("Make sure the row exist. Try again");
                pos = Main.checkInputIsANumber(scanner);
            }
        }

        return pos;
    }

    private boolean isThisPositionAMine(int row, int col) {

        //List<Mine> mines = getMines();
        boolean foundMine = false;

        for (Mine mine : this.mines) {

            if (row == mine.getRow() && col == mine.getCol()) {
                foundMine = true;
            }
        }
        return foundMine;
    }
    private int getRandomNumber(int min, int max) {
        return (int) ((random() * (max - min)) + min);
    }
    public boolean checkWin() {

        return false;
    }

    public boolean checkIfHit(int row, int col) {

        return isThisPositionAMine(row, col);
    }

    public void markPlayerChoice(int row, int col) {

        this.gameboard[row - 1][col - 1] = 'X';
    }

    public void showAllMines() {

        for (Mine mine : this.mines) {

            this.gameboard[mine.getRow()-1][mine.getCol()-1] = mine.getSymbol();

        }
    }

    // Changed the method to print the whole board
    // instead of a method just returning a string
    //public String toString() {
    public void printBoard() {

        //char aChar = 'X';
        System.out.printf("         ");
        for (int col = 1; col <= this.cols; col++) {
            System.out.printf("Col%2d   ", col);
        }
        System.out.printf("%n");

        for (int row = 0; row < this.rows; row++) {
            System.out.printf("Row%2d  |", row + 1);
            for (int col = 0; col < this.cols; col++) {
                System.out.printf("   %C   |", this.gameboard[row][col]);
            }
            System.out.printf("%n");
        }
    }
}
