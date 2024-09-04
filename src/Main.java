//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.Arrays;
import java.util.Scanner;
import java.io.*;
public class Main {
    static char[][] board = new char[3][3];
    public static void printBoard(char[][] board) {
        for(int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
    public static boolean checkContinue(char[][] board) {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(board[i][j] == '-')
                    return false;
            }
        }
    }
    public static void main(String[] args) {
        Arrays.fill(board, "-");
        boolean continue = true;
        Scanner scanner = new Scanner(System.in);
        while(continue){
            printBoard(board);
            System.out.print("Enter a row and a column, from 0 to 2, or press q to quit: ");
            if (scanner.hasNextInt() && scanner.nextInt() <= 2 && scanner.nextInt() >= 0) {
                int row = scanner.nextInt();
                if (scanner.hasNextInt() && scanner.nextInt() <= 2 && scanner.nextInt() >= 0) {
                    int col = scanner.nextInt();
                    board[row][col] = 'X';
                }
            }
            continue = checkContinue(board);
            else if{boolean b = scanner.next() == "q"} {
                continue = false;
                return;
            }
            else
                System.out.println("Invalid input");
        }
    }
    }
}