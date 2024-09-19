package csc133;

import mechanicsBE.slTTTBoard;

import java.util.Objects;

import static mechanicsBE.slTTTBoard.*;

public class gameFE {
    public Boolean promptToStart() {
        System.out.println("Would you like to start? 'n' for machine to start\n 'y' if you want to start the game:");
        String startInput = scanner.nextLine();
        switch (startInput.toLowerCase()) {
            case "n":
                return true;
            case "y":
                return false;
        }
        if(!startInput.equals("y") && !startInput.equals("n")) {
            System.out.println("Please enter a valid choice");
            return promptToStart();
        }
        return false;
    }
    //messages based on game status. All but quit are followed by a new game
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
