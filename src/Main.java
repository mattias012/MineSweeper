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

        //Print setup message
        System.out.println("How large battlefield do you want " + player.name() + "? (minimum " + MINIMUM_ROWS + " x " + MINIMUM_COLS + ")\n");

        int numberOfRows;
        int numberOfColumns;

        //Setup board
        do {
            System.out.print("Number of columns: ");
            numberOfColumns = checkInputIsANumber(scanner);
            System.out.print("Number of rows: ");
            numberOfRows = checkInputIsANumber(scanner);

            //Print message if board size is too small
            if (numberOfColumns < MINIMUM_COLS || numberOfRows < MINIMUM_ROWS) {
                System.out.println("Size of board must be at least + " + RED + "3 rows and 3 columns" + RESET + ", try again.");
            }
        } while (numberOfColumns < MINIMUM_COLS || numberOfRows < MINIMUM_ROWS);


        //Create board object
        Board board = new Board(numberOfColumns, numberOfRows);

        //Initialize the board
        board.initBoard();

        //Create mines depending on level
        System.out.println();
        System.out.println(GREEN + "1. Easy, " + YELLOW + "2. Medium, " + RED + "3. Hard" + RESET);
        System.out.print("Select level: ");
        int selectedLevel = checkLevelChoice(scanner);

        System.out.println();

        //Set number of mines depending on level
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
            //numberOfMines = 2; //For demo and testing
        }

        //Set the mines to the board
        board.setMines(numberOfMines);

        //Print board
        board.printBoard();

        //Play the game
        boolean playing = true;
        boolean aWin = false;
        int counter = 0;
        while (playing) {

            //Select column and row
            //Make sure that the position is not already taken before
            if (counter == 0){
                System.out.println("Let's play! What is your first move?\n");
            }
            else {
                System.out.println("Well done, let's continue playing.. \n");
            }

            //Initialize col and row
            int col;
            int row;

            do {
                 System.out.print("Select column: ");
                 col = board.posExist(scanner, "col");
                 System.out.print("Select row: ");
                 row = board.posExist(scanner, "row");

                if (!board.isNotTaken(row, col)){
                    System.out.println("You've already opened that box. select another box\n");
                }
            } while (!board.isNotTaken(row, col));

            //Line break
            System.out.println();
            //If hit, game over, show all mines and a better luck next time message
            if (board.checkIfHit(row, col)) {

                board.showAllMines();
                board.printBoard();

                System.out.println(player.name() + ", you hit a mine!" + RED + "\nGame Over!!!" + RESET);
                playing = false; //Do not continue playing

            } else {

                //Mark the box
                board.markPlayerChoice(row, col);

                //Check if this mark will create a win
                if (board.checkIfWin()) {
                    playing = false;
                    aWin = true;
                    board.showAllMines();
                }

                //Print board
                board.printBoard();
                if (aWin) {
                    System.out.println("\n *** Congratulations " + player.name() + "! You have found all mines, that is not easy - well done! ***\n");
                }
            }
            counter++; //increment counter
        }
    }

    //Error handling methods
    //Create checkInputIsANumber method to check if the user input is a number
    public static int checkInputIsANumber(Scanner scanner) {

        while (true) {
            try {
                String inputFromUser = scanner.nextLine().trim();
                return Integer.parseInt(inputFromUser);
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