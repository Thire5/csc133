//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.Arrays;
import java.util.Scanner;
public class Main {
    static char[][] board = new char[3][3];
    private static char defaultSpace = '-';
    private static char played = 'P';

    public static void printBoard(char[][] board) {
        for(int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }
    }
    public static boolean checkContinue(char[][] board) {
        for(int row = 0; row < 3; row++) {
            for(int col = 0; col < 3; col++) {
                if(board[row][col] == defaultSpace)
                    return true;
            }
        }
        return false;
    }
    public static void main(String[] args) {
        for(int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = defaultSpace;
            }
        }
        Scanner scanner = new Scanner(System.in);
        boolean cont = checkContinue(board);
        while(cont) {
            printBoard(board);
            System.out.print("Enter a row and a column, from 0 to 2, or press q to quit: ");
            if (scanner.hasNextInt() && scanner.nextInt() <= 2 && scanner.nextInt() >= 0) {
                int row = scanner.nextInt();
                if (scanner.hasNextInt() && scanner.nextInt() <= 2 && scanner.nextInt() >= 0) {
                    int col = scanner.nextInt();
                    board[row][col] = played;
                    return;
                }
            }
            cont = checkContinue(board);
            if(scanner.next() == "q") {
                cont = false;
                System.out.println("thanks for playing!");
                return;
            }
            else
                System.out.println("Invalid input");
        }
    }
    }