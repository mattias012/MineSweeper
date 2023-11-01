import java.util.Scanner;

public class Main {
    //Variables for the colored text
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String RED = "\u001B[31m";
    public static final String BLUE = "\u001B[34m";

    //resets colors to normal
    public static final String RESET = "\u001B[0m";
    
    public static void main(String[] args) {
        
        //Create scanner
        Scanner scanner = new Scanner(System.in);

        //Set levels and minimum sizes
        final double PERCENTAGE_HARD = 0.4;
        final double PERCENTAGE_MEDIUM = 0.25;
        final double PERCENTAGE_EASY = 0.1;
        final int MINIMUM_ROWS = 3;
        final int MINIMUM_COLS = 3;

        //Welcome message and setup board
        System.out.println("Welcome to Mine Sweeper!");

        //Create player name
        System.out.println();
        System.out.print("Please enter your name: ");
        String playerName = scanner.nextLine();
        Player player = new Player(playerName);
        System.out.println();

        System.out.println("How large battlefield do you want "+ player.getName() + "? (minimum "+ MINIMUM_ROWS + " x " + MINIMUM_COLS + ")\n");

        int numberOfRows;
        int numberOfColumns;

        //Setup board
        do {
            System.out.print("Number of columns: ");
            numberOfColumns = checkInputIsANumber(scanner);
            System.out.print("Number of rows: ");
            numberOfRows = checkInputIsANumber(scanner);
        } while (numberOfColumns < MINIMUM_COLS || numberOfRows < MINIMUM_ROWS);


        //Create board
        Board board = new Board(numberOfColumns, numberOfRows);

        //Initialize the board
        board.initBoard();

        //Create mines depending on level
        System.out.println();
        System.out.println(GREEN + "1. Easy, " + YELLOW +  "2. Medium, " + RED + "3. Hard" + RESET);
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
            //numberOfMines = (int) (PERCENTAGE_EASY * numberOfRows * numberOfColumns);
            numberOfMines = 1;
        }

        //Set the mines to the board
        board.setMines(numberOfMines);

        //Print board
        board.printBoard();

        //Play the game
        boolean playing = true;
        boolean aWin = false;
        while (playing) {

            System.out.print("Select column: ");
            int col = board.posExistAndIsNotTakenAlready(scanner, "col");

            System.out.print("Select row: ");

            int row = board.posExistAndIsNotTakenAlready(scanner, "row");

            if (board.checkIfHit(row, col)) {
                System.out.println(player.getName() + ", you hit a mine!" + RED + "\nGame Over!!!" + RESET);
                playing = false;
                board.showAllMines();
            }
            else {
                board.markPlayerChoice(row, col);
                if (board.checkIfWin()){
                    playing = false;
                    aWin = true;
                    board.showAllMines();
                }
            }
            //Print board
            board.printBoard();
            if ( aWin ){
                System.out.println("\n *** Congratulations "+player.getName() + "! You have found all mines, that is not easy - well done! ***\n");
            }
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