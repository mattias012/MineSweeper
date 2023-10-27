import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //Create scanner
        Scanner scanner = new Scanner(System.in);

        //Set levels and minimum sizes
        final double PERCENTAGE_HARD = 0.75;
        final double PERCENTAGE_MEDIUM = 0.5;
        final double PERCENTAGE_EASY = 0.25;
        final int MINIMUM_ROWS = 3;
        final int MINIMUM_COLS = 3;

        //Welcome message and setup board
        System.out.println("Welcome to Mine Sweeper!");
        System.out.println("How large battlefield do you want? (minimum "+ MINIMUM_ROWS + " x " + MINIMUM_COLS + ")\n");

        int numberOfRows;
        int numberOfColumns;

        //Setup board
        do {
            System.out.print("Number of columns: ");
            numberOfColumns = checkInputIsANumber(scanner);
            System.out.print("Number of rows: ");
            numberOfRows = checkInputIsANumber(scanner);
        } while (numberOfColumns < MINIMUM_COLS && numberOfRows < MINIMUM_ROWS);

        //Create board
        Board board = new Board(numberOfColumns, numberOfRows);

        //Initialize the board
        board.initBoard();

        //Create mines depending on level
        System.out.println("1. Easy, 2. Medium, 3. Hard");
        System.out.print("Select level: ");
        int selectedLevel = checkLevelChoice(scanner);

        int numberOfMines;

        if (selectedLevel == 3) {
            //Hard
            numberOfMines = (int) (PERCENTAGE_HARD * numberOfRows * numberOfColumns);
        } else if (selectedLevel == 2) {
            //Medium
            numberOfMines = (int) (PERCENTAGE_MEDIUM * numberOfRows * numberOfColumns);
        } else {
            //Easy
            numberOfMines = (int) (PERCENTAGE_EASY * numberOfRows * numberOfColumns);
        }

        //Set the mines to the board
        board.setMines(numberOfMines);

        //Print board
        board.printBoard();

        //Play the game
        boolean playing = true;
        while (playing) {

            System.out.println("Select column: ");
            int col = board.posExistAndIsNotTakenAlready(scanner, "col");

            System.out.println("Select row: ");

            int row = board.posExistAndIsNotTakenAlready(scanner, "row");


            if (board.checkIfHit(row, col)) {
                System.out.println("Game Over");
                playing = false;
                board.showAllMines();
            } else {
                board.markPlayerChoice(row, col);
            }
            board.printBoard();
        }
    }

    //Error handling methods
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

    //Error handling when selecting level of difficulty.
    public static int checkLevelChoice(Scanner scanner) {

        while (true) {
            try {
                String inputFromUser = scanner.nextLine().trim();
                int menuChoice = Integer.parseInt(inputFromUser);

                if (menuChoice == 1 || menuChoice == 2 || menuChoice == 3) {
                    return menuChoice;
                } else {
                    System.out.println("Wrong selection, please select 1, 2 or 3.");
                }
            } catch (Exception e) {
                System.out.println("Wrong selection, please select 1, 2 or 3.");
            }
        }
    }
}