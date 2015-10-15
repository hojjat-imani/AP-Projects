
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
public class Game_1Player {

    HumanPlayer human;
    ComputerPlayer computer;
    Scanner inputReader;

    public Game_1Player(String playerName) {
        human = new HumanPlayer(playerName);
        computer = new ComputerPlayer("computer");
        inputReader = new Scanner(System.in);
    }

    public void startGame() {
        fakeCleaningTheConsole();
        showWhoseTurnItIs(human);
        human.placeTanks();
        System.out.println("");
        System.out.println("This is your field:");
        human.printFieldForYourSelf();
        getPermisionToContinue(human);
        showWhoseTurnItIs(computer);
        System.out.print("\033[32mPlacing Tanks ");
        computer.placeTanks();
        System.out.print(". ");
        delay(500);
        System.out.print("\033[32m. ");
        delay(500);
        System.out.println(".");
        delay(500);
        System.out.println("\033[31mdone!\033[0m");
        delay(500);
        runGameLoop();
    }

    private void runGameLoop() {
        Player winner;
        while (true) {
            fakeCleaningTheConsole();
            showWhoseTurnItIs(human);
            showMapsToghether(human, computer);
            human.turnToShoot(computer);
            if ((winner = checkForWinner()) != null) {
                break;
            }
            fakeCleaningTheConsole();
            computer.turnToShoot(human);
            if ((winner = checkForWinner()) != null) {
                break;
            }
        }
        fakeCleaningTheConsole();
        System.out.println("                                                      \033[31mGame Over!!");
        System.out.println("\033[32m                                      " + winner.getPlayerName() + " wins!!");
        showMapsToghether(human, computer);

    }

    private Player checkForWinner() {
        if (human.isLost()) {
            return computer;
        }
        if (computer.isLost()) {
            return human;
        }
        return null;
    }

    private void showWhoseTurnItIs(Player playerThatIsHisTurn) {
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

    private void getPermisionToContinue(Player playerThatShouldPermit) {
        System.out.println("");
        System.out.println("\033[31m" + playerThatShouldPermit.getPlayerName() + "\033[0m! Press \033[34mEnter\033[0m to continue");
        inputReader.nextLine();
        fakeCleaningTheConsole();
    }

    private void showMapsToghether(Player playerWhichIsHisturn, Player otherPlayer) {
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

    private void delay(int duration) {
        //I do not know what does this code actually do!!
        //I've just coppied it from Stackoverflow!! :-D
        try {
            Thread.sleep(duration);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

}
