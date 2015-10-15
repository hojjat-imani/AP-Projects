
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
public class HumanPlayer extends Player {

    private Scanner inputReader;

    public HumanPlayer(String playerName) {
        super(playerName);
        inputReader = new Scanner(System.in);
    }

    @Override
    public void placeTanks() {
        System.out.println("");
        System.out.println("\033[32mPlacing 1th Tank:\033[0m");
        assignTanksPosition();
        System.out.println("");
        System.out.println("\033[32mPlacing 2th Tank:\033[0m");
        assignTanksPosition();
        System.out.println("");
        System.out.println("\033[32mPlacing 3th Tank:\033[0m");
        assignTanksPosition();
        System.out.println("");
        System.out.println("\033[32mPlacing 4th Tank:\033[0m");
        assignTanksPosition();
        System.out.println("");
        System.out.println("\033[32mPlacing 5th Tank:\033[0m");
        assignTanksPosition();
    }

    private void assignTanksPosition() {
        boolean inputsAreInvalid = false;
        int x1, y1, x2, y2;
        // be khatere oon try catch age ina ro nazari ide gir mide! :(
        x1 = x2 = y1 = y2 = 0;
        System.out.println("Enter a valid area: \033[47m(Ex. (1,1)to(1,4) -> '1 1 1 4'\033[0m");
        System.out.print("               -> ");
        do {
            if (inputsAreInvalid) {
                System.out.print(">>Try again    -> ");
                inputsAreInvalid = false;
            }
            try {
                x1 = inputReader.nextInt() - 1;
                y1 = inputReader.nextInt() - 1;
                x2 = inputReader.nextInt() - 1;
                y2 = inputReader.nextInt() - 1;
                //clearing buffer
                inputReader.nextLine();
            } catch (Exception exception) {
                System.out.println("\033[31mUse the example pattern to enter input!!\033[0m");
                inputsAreInvalid = true;
                inputReader.nextLine();
                continue;
            }
            if (InputAreaIsInvalid_PrintMainError(x1, y1, x2, y2)) {
                inputsAreInvalid = true;
            }
        } while (inputsAreInvalid);
        for (int i = Math.min(x1, x2); i <= Math.max(x1, x2); i++) {
            for (int j = Math.min(y1, y2); j <= Math.max(y1, y2); j++) {
                field.getCell(i, j).putShipInCell();
                numberOfTankPeices++;
            }
        }
    }

    private boolean InputAreaIsInvalid_PrintMainError(int x1, int y1, int x2, int y2) {
        if (areaIsOutOfField(x1, y1, x2, y2)) {
            System.out.println("\033[31mInput area is out of field!\033[0m");
            return true;
        }
        if (areaIsDiagonal(x1, y1, x2, y2)) {
            System.out.println("\033[31mInput area is diagonal!\033[0m");
            return true;
        }
        if (sizeOfAreaIsNotValid(x1, y1, x2, y2)) {
            System.out.println("\033[31mSize of tank must be between 2 to 5!\033[0m");
            return true;
        }
        if (areaHasOverlabWithPreviosTanks(x1, y1, x2, y2)) {
            System.out.println("\033[31mInput Area hs Overlab with another Tank!\033[0m");
            return true;
        }
        return false;
    }

    @Override
    public void turnToShoot(Player playerToBeShooted) {
        int X_position, Y_position;
        X_position = Y_position = 0;
        boolean inputsAreInvalid = false;
        System.out.println("Which cell do you want to shoot?? \033[47m(Ex. (1,4) -> '1 4'\033[0m");
        System.out.print("               -> ");
        do {
            if (inputsAreInvalid) {
                System.out.print(">>Try again    -> ");
                inputsAreInvalid = false;
            }
            try {
                X_position = inputReader.nextInt() - 1;
                Y_position = inputReader.nextInt() - 1;
                //clearing buffer
                inputReader.nextLine();
            } catch (Exception exception) {
                System.out.println("\033[31mUse the example pattern to enter input!!\033[0m");
                inputsAreInvalid = true;
                inputReader.nextLine();
                continue;
            }
            if (X_position < 0 || Y_position < 0 || X_position > 9 || Y_position > 9) {
                System.out.println("\033[31mOut of feild!!\033[0m");
                inputsAreInvalid = true;
            }
        } while (inputsAreInvalid);
        boolean shotWasHit = playerToBeShooted.beShooted(X_position, Y_position);
//        System.out.println(shotWasHit ? "\033[32mGreat!! that was a hit!!\033[0m" : "\033[31mOh! That was missed!\033[0m");
    }
}
