import java.util.ArrayList;

public class Board {

    private int[][] gameboard;
    private ArrayList<Mine> mines;
    private int numberOfMines;

    public Board(int x, int y) {

        this.gameboard = new int[x][y];

    }

    private void setNumberOfMines(int mines) {

        this.numberOfMines = mines;

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

    public String toString() {

        return "X";
    }
}
