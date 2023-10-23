import java.util.ArrayList;

public class Board {

    private int[][] gameboard;
    private ArrayList<Mine> mines;
    private int numberOfMines;

    public Board(int x, int y) {

        this.gameboard = new int[x][y];
        this.mines = new ArrayList<>();

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

    public boolean checkWin(){

        return false;
    }

    public boolean checkIfHit(){

        return false;
    }

    public String toString() {

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
                System.out.printf("   %C   |", this.board[row][col] );
            }
            System.out.printf("%n");
        }
    }
}
