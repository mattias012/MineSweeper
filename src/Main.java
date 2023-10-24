import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Board board = new Board(5, 5);

        board.initBoard();
        board.setMines(10);
        board.printBoard();

        Scanner scanner = new Scanner(System.in);

        boolean playing = true;
        while (playing) {
            System.out.println("Select column: ");
            int col = checkInputIsANumber(scanner);
            System.out.println("Select row: ");
            int row = checkInputIsANumber(scanner);

            if (board.checkIfHit(row, col)) {
                System.out.println("Game Over");
                playing = false;
                board.showAllMines();
            }
            else {
                board.markPlayerChoice(row, col);
            }
            board.printBoard();
        }
    }
    public static int checkInputIsANumber(Scanner scanner) {

        while (true) {
            try {
                String inputFromUser = scanner.nextLine().trim();
                int input = Integer.parseInt(inputFromUser);
                return input;
            } catch (Exception e) {
                System.out.println("Only numbers please, try again.");
            }
        }
    }
}