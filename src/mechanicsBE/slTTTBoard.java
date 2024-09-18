package mechanicsBE;//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.Objects;
import java.util.Scanner;
import java.util.Random;
public class slTTTBoard {
    static char[][] board = new char[3][3];
    //character in board before being played
    public final static char defaultSpace = '-';
    //character to mark played spaces
    public final static char played = 'P';
    //character to mark machine played spaces
    public final static char machine = 'M';
    public final static int rowColMin = 0;
    public final static int rowColMax = 3;
    //ints for all possible game states
    public final static int GAME_INCOMPLETE = 0;
    public final static int GAME_QUIT = 1;
    public final static int GAME_PLAYER = 2;
    public final static int GAME_MACHINE = 3;
    public final static int GAME_DRAW = 4;
    public static int gameStatus = GAME_INCOMPLETE;
    public static Scanner scanner = new Scanner(System.in);
    //prints board
    public static void printBoard() {
        for(int row = rowColMin; row < rowColMax; row++) {
            for (int col = rowColMin; col < rowColMax; col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }
    }
    //checks if all spaces are filled
    public static boolean checkDraw(char[][] board) {
        for(int row = rowColMin; row < rowColMax; row++) {
            for(int col = rowColMin; col < rowColMax; col++) {
                if(board[row][col] == defaultSpace)
                    return false;
            }
        }
        return true;
    }
    //checks for all possible player and machine three in a row
    public static boolean checkRowWinPlayer(char[][] board) {
        for(int row = rowColMin; row < rowColMax; row++) {
            if(board[row][0] == played && board[row][1] == played && board[row][2] == played)
                return true;
        }
        return false;
    }
    public static boolean checkRowWinMachine(char[][] board) {
        for(int row = rowColMin; row < rowColMax; row++) {
            if(board[row][0] == machine && board[row][1] == machine && board[row][2] == machine)
                return true;
        }
        return false;
    }
    public static boolean checkColWinPlayer(char[][] board) {
        for(int col = rowColMin; col < rowColMax; col++) {
            if(board[0][col] == played && board[1][col] == played && board[2][col] == played)
                return true;
        }
        return false;
    }
    public static boolean checkColWinMachine(char[][] board) {
        for(int col = rowColMin; col < rowColMax; col++) {
            if(board[0][col] == machine && board[1][col] == machine && board[2][col] == machine)
                return true;
        }
        return false;
    }
    public static boolean checkLeadingWinPlayer(char[][] board) {
        if(board[0][0] == played && board[1][1] == played && board[2][2] == played)
            return true;
        return false;
    }
    public static boolean checkLeadingWinMachine(char[][] board) {
        if(board[0][0] == machine && board[1][1] == machine && board[2][2] == machine)
            return true;
        return false;
    }
    public static boolean checkTrailingWinPlayer(char[][] board) {
        if(board[2][0] == played && board[1][1] == played && board[0][2] == played)
            return true;
        return false;
    }
    public static boolean checkTrailingWinMachine(char[][] board) {
        if(board[2][0] == machine && board[1][1] == machine && board[0][2] == machine)
            return true;
        return false;
    }
    //runs all checks for wins or draws
    public static int checkStatus(char[][] board) {
        if(checkDraw(board))
            return GAME_DRAW;
        if(checkLeadingWinPlayer(board) || checkTrailingWinPlayer(board) || checkRowWinPlayer(board) || checkColWinPlayer(board))
            return GAME_PLAYER;
        if(checkLeadingWinMachine(board) || checkTrailingWinMachine(board) || checkRowWinMachine(board) || checkColWinMachine(board))
            return GAME_MACHINE;
        else
            return GAME_INCOMPLETE;
    }
    //fills the board with default space characters
    public static void resetBoard() {
        for(int row = rowColMin; row < rowColMax; row++) {
            for (int col = rowColMin; col < rowColMax; col++) {
                board[row][col] = defaultSpace;
            }
        }
    }
    //not very scalable, but in current build will only play first
    public static void playRandom() {
        Random rand = new Random();
        int randomRow = rand.nextInt(3);
        int randomCol = rand.nextInt(3);
        board[randomRow][randomCol] = machine;
    }
    public static int play() {
        gameStatus = checkStatus(board);
        printBoard();
        //loop repeatedly takes input, fills appropriate board space, and prints board
        while(gameStatus == GAME_INCOMPLETE) {
            System.out.print("Enter a row and a column, from 0 to 2, separated by a space, or press q to quit: ");
            String input = scanner.nextLine().trim();
            //option to quit at any time
            if(input.equalsIgnoreCase("q")) {
                gameStatus = GAME_QUIT;
                return gameStatus;
            }
            String[] parts = input.split("\\s+"); // Split by one or more spaces
            if (parts.length != 2) {
                System.out.println("Invalid input. Please enter two numbers separated by a space.");
                continue;
            }

            try {
                int row = Integer.parseInt(parts[0]);
                int col = Integer.parseInt(parts[1]);

                if (row < rowColMin || row >= rowColMax || col < rowColMin || col >= rowColMax) {
                    System.out.println("Invalid row or column. Please enter numbers between 0 and 2.");
                    continue;
                }
                if (board[row][col] != defaultSpace) {
                    System.out.println("That space is already taken. Choose another space.");
                    continue;
                }

                board[row][col] = played;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter valid integers.");
                continue;
            }
                /*int row = scanner.nextInt();
                int col = scanner.nextInt();
                if (row < rowColMin || row >= rowColMax || col < rowColMin || col >= rowColMax) {
                    System.out.println("Invalid row or column");
                    continue;
                }
                if (board[row][col] != defaultSpace) {
                    System.out.println("That space is already taken");
                    continue;
                }
                board[row][col] = played;*/
            printBoard();
            gameStatus = checkStatus(board);
            if(gameStatus == GAME_INCOMPLETE)
                gameStatus = machinePlay(board);
        }
        return gameStatus;
    }
    //simple strategy, coded very inconveniently
    public static int machinePlay(char[][] board) {
        //check all imminent win conditions for machine
        if(board[0][0] == machine && board[0][1] == machine && board[0][2] == defaultSpace) {
            board[0][2] = machine;
            gameStatus = checkStatus(board);
            printBoard();
            return gameStatus;
        }
        if(board[0][0] == machine && board[0][2] == machine && board[0][1] == defaultSpace) {
            board[0][1] = machine;
            gameStatus = checkStatus(board);
            printBoard();
            return gameStatus;
        }
        if(board[0][2] == machine && board[0][1] == machine && board[0][0] == defaultSpace) {
            board[0][0] = machine;
            gameStatus = checkStatus(board);
            printBoard();
            return gameStatus;
        }
        if(board[1][0] == machine && board[1][1] == machine && board[1][2] == defaultSpace) {
            board[1][2] = machine;
            gameStatus = checkStatus(board);
            printBoard();
            return gameStatus;
        }
        if(board[1][1] == machine && board[1][2] == machine && board[1][0] == defaultSpace) {
            board[0][2] = machine;
            gameStatus = checkStatus(board);
            printBoard();
            return gameStatus;
        }
        if(board[1][0] == machine && board[1][2] == machine && board[1][1] == defaultSpace) {
            board[1][1] = machine;
            gameStatus = checkStatus(board);
            printBoard();
            return gameStatus;
        }
        if(board[2][0] == machine && board[2][1] == machine && board[2][2] == defaultSpace) {
            board[2][2] = machine;
            gameStatus = checkStatus(board);
            printBoard();
            return gameStatus;
        }
        if(board[2][1] == machine && board[2][2] == machine && board[2][0] == defaultSpace) {
            board[2][0] = machine;
            gameStatus = checkStatus(board);
            printBoard();
            return gameStatus;
        }
        if(board[2][0] == machine && board[2][2] == machine && board[2][1] == defaultSpace) {
            board[2][1] = machine;
            gameStatus = checkStatus(board);
            printBoard();
            return gameStatus;
        }
        if(board[0][0] == machine && board[1][0] == machine && board[2][0] == defaultSpace) {
            board[2][0] = machine;
            gameStatus = checkStatus(board);
            printBoard();
            return gameStatus;
        }
        if(board[2][0] == machine && board[1][0] == machine && board[0][0] == defaultSpace) {
            board[0][0] = machine;
            gameStatus = checkStatus(board);
            printBoard();
            return gameStatus;
        }
        if(board[2][0] == machine && board[0][0] == machine && board[1][0] == defaultSpace) {
            board[1][0] = machine;
            gameStatus = checkStatus(board);
            printBoard();
            return gameStatus;
        }
        if(board[0][1] == machine && board[1][1] == machine && board[2][1] == defaultSpace) {
            board[2][1] = machine;
            gameStatus = checkStatus(board);
            printBoard();
            return gameStatus;
        }
        if(board[1][1] == machine && board[2][1] == machine && board[0][1] == defaultSpace) {
            board[0][1] = machine;
            gameStatus = checkStatus(board);
            printBoard();
            return gameStatus;
        }
        if(board[0][1] == machine && board[2][1] == machine && board[1][1] == defaultSpace) {
            board[1][1] = machine;
            gameStatus = checkStatus(board);
            printBoard();
            return gameStatus;
        }
        if(board[0][2] == machine && board[1][2] == machine && board[2][2] == defaultSpace) {
            board[2][2] = machine;
            gameStatus = checkStatus(board);
            printBoard();
            return gameStatus;
        }
        if(board[1][2] == machine && board[2][2] == machine && board[0][2] == defaultSpace) {
            board[0][2] = machine;
            gameStatus = checkStatus(board);
            printBoard();
            return gameStatus;
        }
        if(board[0][2] == machine && board[2][2] == machine && board[1][2] == defaultSpace) {
            board[1][2] = machine;
            gameStatus = checkStatus(board);
            printBoard();
            return gameStatus;
        }
        if(board[0][0] == machine && board[1][1] == machine && board[2][2] == defaultSpace) {
            board[2][2] = machine;
            gameStatus = checkStatus(board);
            printBoard();
            return gameStatus;
        }
        if(board[1][1] == machine && board[2][2] == machine && board[0][0] == defaultSpace) {
            board[0][0] = machine;
            gameStatus = checkStatus(board);
            printBoard();
            return gameStatus;
        }
        if(board[0][0] == machine && board[2][2] == machine && board[1][1] == defaultSpace) {
            board[1][1] = machine;
            gameStatus = checkStatus(board);
            printBoard();
            return gameStatus;
        }
        if(board[2][0] == machine && board[1][1] == machine && board[0][2] == defaultSpace) {
            board[0][2] = machine;
            gameStatus = checkStatus(board);
            printBoard();
            return gameStatus;
        }
        if(board[1][1] == machine && board[0][2] == machine && board[2][0] == defaultSpace) {
            board[2][0] = machine;
            gameStatus = checkStatus(board);
            printBoard();
            return gameStatus;
        }
        if(board[2][0] == machine && board[0][2] == machine && board[1][1] == defaultSpace) {
            board[1][1] = machine;
            gameStatus = checkStatus(board);
            printBoard();
            return gameStatus;
        }
        //check all imminent win conditions for player
        if(board[0][0] == played && board[0][1] == played && board[0][2] == defaultSpace) {
            board[0][2] = machine;
            gameStatus = checkStatus(board);
            printBoard();
            return gameStatus;
        }
        if(board[0][0] == played && board[0][2] == played && board[0][1] == defaultSpace) {
            board[0][1] = machine;
            gameStatus = checkStatus(board);
            printBoard();
            return gameStatus;
        }
        if(board[0][2] == played && board[0][1] == played && board[0][0] == defaultSpace) {
            board[0][0] = machine;
            gameStatus = checkStatus(board);
            printBoard();
            return gameStatus;
        }
        if(board[1][0] == played && board[1][1] == played && board[1][2] == defaultSpace) {
            board[1][2] = machine;
            gameStatus = checkStatus(board);
            printBoard();
            return gameStatus;
        }
        if(board[1][1] == played && board[1][2] == played && board[1][0] == defaultSpace) {
            board[0][2] = machine;
            gameStatus = checkStatus(board);
            printBoard();
            return gameStatus;
        }
        if(board[1][0] == played && board[1][2] == played && board[1][1] == defaultSpace) {
            board[1][1] = machine;
            gameStatus = checkStatus(board);
            printBoard();
            return gameStatus;
        }
        if(board[2][0] == played && board[2][1] == played && board[2][2] == defaultSpace) {
            board[2][2] = machine;
            gameStatus = checkStatus(board);
            printBoard();
            return gameStatus;
        }
        if(board[2][1] == played && board[2][2] == played && board[2][0] == defaultSpace) {
            board[2][0] = machine;
            gameStatus = checkStatus(board);
            printBoard();
            return gameStatus;
        }
        if(board[2][0] == played && board[2][2] == played && board[2][1] == defaultSpace) {
            board[2][1] = machine;
            gameStatus = checkStatus(board);
            printBoard();
            return gameStatus;
        }
        if(board[0][0] == played && board[1][0] == played && board[2][0] == defaultSpace) {
            board[2][0] = machine;
            gameStatus = checkStatus(board);
            printBoard();
            return gameStatus;
        }
        if(board[2][0] == played && board[1][0] == played && board[0][0] == defaultSpace) {
            board[0][0] = machine;
            gameStatus = checkStatus(board);
            printBoard();
            return gameStatus;
        }
        if(board[2][0] == played && board[0][0] == played && board[1][0] == defaultSpace) {
            board[1][0] = machine;
            gameStatus = checkStatus(board);
            printBoard();
            return gameStatus;
        }
        if(board[0][1] == played && board[1][1] == played && board[2][1] == defaultSpace) {
            board[2][1] = machine;
            gameStatus = checkStatus(board);
            printBoard();
            return gameStatus;
        }
        if(board[1][1] == played && board[2][1] == played && board[0][1] == defaultSpace) {
            board[0][1] = machine;
            gameStatus = checkStatus(board);
            printBoard();
            return gameStatus;
        }
        if(board[0][1] == played && board[2][1] == played && board[1][1] == defaultSpace) {
            board[1][1] = machine;
            gameStatus = checkStatus(board);
            printBoard();
            return gameStatus;
        }
        if(board[0][2] == played && board[1][2] == played && board[2][2] == defaultSpace) {
            board[2][2] = machine;
            gameStatus = checkStatus(board);
            printBoard();
            return gameStatus;
        }
        if(board[1][2] == played && board[2][2] == played && board[0][2] == defaultSpace) {
            board[0][2] = machine;
            gameStatus = checkStatus(board);
            printBoard();
            return gameStatus;
        }
        if(board[0][2] == played && board[2][2] == played && board[1][2] == defaultSpace) {
            board[1][2] = machine;
            gameStatus = checkStatus(board);
            printBoard();
            return gameStatus;
        }
        if(board[0][0] == played && board[1][1] == played && board[2][2] == defaultSpace) {
            board[2][2] = machine;
            gameStatus = checkStatus(board);
            printBoard();
            return gameStatus;
        }
        if(board[1][1] == played && board[2][2] == played && board[0][0] == defaultSpace) {
            board[0][0] = machine;
            gameStatus = checkStatus(board);
            printBoard();
            return gameStatus;
        }
        if(board[0][0] == played && board[2][2] == played && board[1][1] == defaultSpace) {
            board[1][1] = machine;
            gameStatus = checkStatus(board);
            printBoard();
            return gameStatus;
        }
        if(board[2][0] == played && board[1][1] == played && board[0][2] == defaultSpace) {
            board[0][2] = machine;
            gameStatus = checkStatus(board);
            printBoard();
            return gameStatus;
        }
        if(board[1][1] == played && board[0][2] == played && board[2][0] == defaultSpace) {
            board[2][0] = machine;
            gameStatus = checkStatus(board);
            printBoard();
            return gameStatus;
        }
        if(board[2][0] == played && board[0][2] == played && board[1][1] == defaultSpace) {
            board[1][1] = machine;
            gameStatus = checkStatus(board);
            printBoard();
            return gameStatus;
        }
        //now the actual strategy part
        //first, claim middle spot if open
        if(board[1][1] == defaultSpace) {
            board[1][1] = machine;
            gameStatus = checkStatus(board);
            printBoard();
            return gameStatus;
        }
        //if middle is filled, claim most optimal corner
        if(board[0][0] == defaultSpace && board[2][2] != machine && board[2][1] != machine && board[1][2] != machine) {
            board[0][0] = machine;
            gameStatus = checkStatus(board);
            printBoard();
            return gameStatus;
        }
        if(board[2][2] == defaultSpace && board[0][0] != machine && board[0][1] != machine && board[1][0] != machine) {
            board[2][2] = machine;
            gameStatus = checkStatus(board);
            printBoard();
            return gameStatus;
        }
        if(board[2][0] == defaultSpace && board[0][2] != machine && board[0][1] != machine && board[1][2] != machine) {
            board[2][0] = machine;
            gameStatus = checkStatus(board);
            printBoard();
            return gameStatus;
        }
        if(board[0][2] == defaultSpace && board[2][0] != machine && board[2][1] != machine && board[1][0] != machine) {
            board[0][2] = machine;
            gameStatus = checkStatus(board);
            printBoard();
            return gameStatus;
        }
        /* if none of those trigger, any corner space should do, initial checks will prevent loss after middle is filled */
        if(board[0][0] == defaultSpace) {
            board[0][0] = machine;
            gameStatus = checkStatus(board);
            printBoard();
            return gameStatus;
        }
        if(board[0][2] == defaultSpace) {
            board[0][2] = machine;
            gameStatus = checkStatus(board);
            printBoard();
            return gameStatus;
        }
        if(board[2][0] == defaultSpace) {
            board[2][0] = machine;
            gameStatus = checkStatus(board);
            printBoard();
            return gameStatus;
        }
        if(board[2][2] == defaultSpace) {
            board[2][2] = machine;
            gameStatus = checkStatus(board);
            printBoard();
            return gameStatus;
        }
        if(board[1][0] == defaultSpace) {
            board[1][0] = machine;
            gameStatus = checkStatus(board);
            printBoard();
            return gameStatus;
        }
        if(board[0][1] == defaultSpace) {
            board[0][1] = machine;
            gameStatus = checkStatus(board);
            printBoard();
            return gameStatus;
        }
        if(board[2][1] == defaultSpace) {
            board[2][1] = machine;
            gameStatus = checkStatus(board);
            printBoard();
            return gameStatus;
        }
        if(board[1][2] == defaultSpace) {
            board[1][2] = machine;
            gameStatus = checkStatus(board);
            printBoard();
            return gameStatus;
        }
        /* serves as an out if somehow none of this triggers. shouldn't be possible */
        gameStatus = checkStatus(board);
        return gameStatus;
    }
}