
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Hojjat
 */
public class Game_2Player {

    HumanPlayer player1;
    HumanPlayer player2;
    Scanner inputReader;

    Game_2Player(String player1_Name, String player2_Name) {
        player1 = new HumanPlayer(player1_Name);
        player2 = new HumanPlayer(player2_Name);
        inputReader = new Scanner(System.in);
    }

    public void startGame() {
        fakeCleaningTheConsole();
        showWhoseTurnItIs(player1);
        player1.placeTanks();
        System.out.println("");
        System.out.println("This is your field:");
        player1.printFieldForYourSelf();
        getPermisionToContinue(player1);
        showWhoseTurnItIs(player2);
        player2.placeTanks();
        System.out.println("");
        System.out.println("This is your field:");
        player2.printFieldForYourSelf();
        getPermisionToContinue(player2);
        runGameLoop();
    }

    private void runGameLoop() {
        HumanPlayer winner;
        while (true) {
            player_Turn(player1, player2);
            if ((winner = checkForWinner()) != null) {
                break;
            }
            player_Turn(player2, player1);
            if ((winner = checkForWinner()) != null) {
                break;
            }
        }
        fakeCleaningTheConsole();
        if (winner == player1) {
            showMapsToghether(player1, player2);
        } else {
            showMapsToghether(player2, player1);
        }
        System.out.println("Game Over!!");
        System.out.println(winner.getPlayerName() + " wins!!");
    }

    private HumanPlayer checkForWinner() {
        if (player1.isLost()) {
            return player2;
        }
        if (player2.isLost()) {
            return player1;
        }
        return null;
    }

    private void player_Turn(HumanPlayer playerWhichIsHisTurn, HumanPlayer otherPlayer) {
        getPermisionToContinue(playerWhichIsHisTurn);
        showWhoseTurnItIs(playerWhichIsHisTurn);
        showMapsToghether(playerWhichIsHisTurn, otherPlayer);
        playerWhichIsHisTurn.turnToShoot(otherPlayer);
        getPermisionToContinue(playerWhichIsHisTurn);
    }

    private void showWhoseTurnItIs(HumanPlayer playerThatIsHisTurn) {
        System.out.println("\033[34m");
        for (int i = 0; i < 50; System.out.printf("%c", 95), i++);
        System.out.println("");
        System.out.println(">>>>>>>>> \033[31m" + playerThatIsHisTurn.getPlayerName() + "\033[34m");
        for (int i = 0; i < 50; System.out.printf("%c", 175), i++);
        System.out.println("\033[0m");
    }

    private void fakeCleaningTheConsole() {
        for (int i = 0; i < 50; i++) {
            System.out.println("");
        }
    }

    private void getPermisionToContinue(HumanPlayer playerThatShouldPermit) {
        System.out.println("");
        System.out.println("\033[31m" + playerThatShouldPermit.getPlayerName() + "\033[0m! Press \033[34mEnter\033[0m to continue");
        inputReader.nextLine();
        fakeCleaningTheConsole();
    }

    private void showMapsToghether(HumanPlayer playerWhichIsHisturn, HumanPlayer otherPlayer) {
        System.out.printf("\033[35m                  your field                        \033[0m |\033[35m                      contender's field\033[0m\n    ");
        for (int i = 0; i < 10; System.out.printf("%2d  ", i + 1), i++);
        System.out.print("         |           ");
        for (int i = 0; i < 10; System.out.printf("%2d  ", i + 1), i++);
        System.out.printf("\n   ");
        for (int i = 0; i < 10; System.out.print("|---"), i++) ;
        System.out.print("|         |          ");
        for (int i = 0; i < 10; System.out.print("|---"), i++) ;
        System.out.println("|");
        for (int i = 0; i < 10; i++) {
            System.out.printf("%2d ", i + 1);
            for (int j = 0; j < 10; j++) {
                System.out.print("|" + (playerWhichIsHisturn.getField().getCells()[i][j].isFree() ? (playerWhichIsHisturn.getField().getCells()[i][j].isShooted() ? "\033[47m * \033[0m" : "   ") : (playerWhichIsHisturn.getField().getCells()[i][j].isShooted() ? "\033[41m T \033[0m" : "\033[42m T \033[0m")));
            }
            System.out.printf("|         |       %2d ", i + 1);
            for (int j = 0; j < 10; j++) {
                System.out.print("|" + (otherPlayer.getField().getCells()[i][j].isShooted() ? (otherPlayer.getField().getCells()[i][j].isFree() ? "\033[47m m \033[0m" : "\033[41m H \033[0m") : "   "));
            }
            System.out.printf("|\n   ");
            for (int k = 0; k < 10; System.out.print("|---"), k++) ;
            System.out.print("|         |          ");
            for (int k = 0; k < 10; System.out.print("|---"), k++) ;
            System.out.println("|");
        }
    }
}
