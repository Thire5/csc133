package csc133;

import mechanicsBE.slTTTBoard;

import java.util.Objects;
import java.util.Scanner;

public class gameFE {
    Scanner scanner = new Scanner(System.in);
    public Boolean promptToStart() {
        System.out.println("Would you like to start? 'n' for machine to start\n 'y' if you want to start the game:");
        if(Objects.equals(scanner.next(), "y")
            return false;
        if(Objects.equals(scanner.next(), "n")
            return true;
        System.out.println("Please enter a valid choice");
        Boolean tryAgain = promptToStart();
        return tryAgain;
    }

    public void print_exit_message(int gameStatus) {
        switch (gameStatus) {
            case slTTTBoard.GAME_QUIT:
                System.out.println("Sorry to see you go; come again!");
                break;
            case slTTTBoard.GAME_PLAYER:
                System.out.println("Congratulations! you have won!");
                break;
            case slTTTBoard.GAME_MACHINE:
                System.out.println("Sorry, you did not win; try again!");
                break;
            case slTTTBoard.GAME_DRAW:
                System.out.println("Hey, you almost beat me, let's try again!");
                break;
            default:
                break;

        }
    }
}
