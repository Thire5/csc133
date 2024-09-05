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
    //simple loop to print all board spaces in a grid
    public static void printBoard() {
        for(int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }
    }
    //checks if all spaces are filled
    public static boolean checkContinue(char[][] board) {
        for(int row = 0; row < 3; row++) {
            for(int col = 0; col < 3; col++) {
                if(board[row][col] == defaultSpace)
                    return true;
            }
        }
        return false;
    }
    public static void play() {
        //fills the board with the default character
        for(int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = defaultSpace;
            }
        }
        Scanner scanner = new Scanner(System.in);
        boolean cont = checkContinue(board);
        //loop repeatedly takes input, fills appropriate board space, and prints board
        while(cont) {
            printBoard();
            if(!checkContinue(board)) {
                cont = checkContinue(board);
                System.out.println("Congratulations! You have won the game!");
                continue;
            }
            System.out.print("Enter a row and a column, from 0 to 2, or press q to quit: ");
            //note that input must be separated by a space
            if (scanner.hasNextInt()) {
                int row = scanner.nextInt();
                if (scanner.hasNextInt()) {
                    int col = scanner.nextInt();
                    if (row < 0 || row >= 3 || col < 0 || col >= 3) {
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
            cont = checkContinue(board);
            //option to quit at any time
            if(Objects.equals(scanner.next(), "q")) {
                cont = false;
                System.out.println("thanks for playing!");
            }
        }
    }
    }