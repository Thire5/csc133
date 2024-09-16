package mechanicsBE;//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.Objects;
import java.util.Scanner;
public class slTTTBoard {
    static char[][] board = new char[3][3];
    //character in board before being played
    public final static char defaultSpace = '-';
    //character to mark played spaces
    public final static char played = 'P';
    public final static char machine = 'M';
    public final static int rowColMin = 0;
    public final static int rowColMax = 3;
    public final static int GAME_INCOMPLETE = 0;
    public final static int GAME_QUIT = 1;
    public final static int GAME_PLAYER = 2;
    public final static int GAME_MACHINE = 3;
    public final static int GAME_DRAW = 4;
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
                    return true;
            }
        }
        return false;
    }
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
        if (board[2][0] == played && board[1][1] == played && board[0][2] == played)
            return true;
        return false;
    }
    public static boolean checkTrailingWinMachine(char[][] board) {
        if (board[2][0] == machine && board[1][1] == machine && board[0][2] == machine)
            return true;
        return false;
    }
    public static int checkStatus(char[][] board) {
        if(!checkDraw(board))
            return GAME_DRAW;
        if(checkLeadingWinPlayer(board) || checkTrailingWinPlayer(board) || checkRowWinPlayer(board) || checkColWinPlayer(board))
            return GAME_PLAYER;
        if(checkLeadingWinMachine(board) || checkTrailingWinMachine(board) || checkRowWinMachine(board) || checkColWinMachine(board))
            return GAME_MACHINE;
        else
            return GAME_INCOMPLETE;
    }
    public static void resetBoard() {
        for(int row = rowColMin; row < rowColMax; row++) {
            for (int col = rowColMin; col < rowColMax; col++) {
                board[row][col] = defaultSpace;
            }
        }
    }
    public static int play() {
        Scanner scanner = new Scanner(System.in);
        int gameStatus = checkStatus(board);
        //loop repeatedly takes input, fills appropriate board space, and prints board
        while(checkStatus(board) == GAME_INCOMPLETE) {
            System.out.print("Enter a row and a column, from 0 to 2, or press q to quit: ");
            //note that input must be separated by a space
            if (scanner.hasNextInt()) {
                int row = scanner.nextInt();
                if (scanner.hasNextInt()) {
                    int col = scanner.nextInt();
                    if (row < rowColMin || row >= rowColMax || col < rowColMin || col >= rowColMax) {
                        System.out.println("Invalid row or column");
                        continue;
                    }
                    if (board[row][col] != defaultSpace) {
                        System.out.println("That space is already taken");
                        continue;
                    }
                    board[row][col] = played;
                    continue;
                }
            }
            //option to quit at any time
            if(Objects.equals(scanner.next(), "q")) {
                gameStatus = 1;
                return gameStatus;
            }
            gameStatus = checkStatus(board);
            //machinePlay(board)
        }
        return gameStatus;
    }
    }