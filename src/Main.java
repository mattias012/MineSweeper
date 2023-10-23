public class Main {
    public static void main(String[] args) {

        Board board = new Board(5, 5);

        board.initBoard();
        board.setMines(5);
        board.printBoard();

    }
}