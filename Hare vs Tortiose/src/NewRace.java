/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Hojjat
 */
public class NewRace {

    private Tortoise tortoise;
    private Hare hare;

    public NewRace() {
        tortoise = new Tortoise();
        hare = new Hare();
    }

    public void start() {
        System.out.print("Race starts in ... ");
        timeTick(1000);
        System.out.print("3");
        timeTick(1000);
        System.out.print("   2");
        timeTick(1000);
        System.out.print("   1");
        timeTick(1000);
        System.out.println("    Go!!");
        timeTick(1000);

        while (checkForWinner() == false) {
            updateDisplay();
            timeTick(1000);
            tortoise.move();
            hare.move();
        }
        terminate();
    }

    private void terminate() {
        System.out.println("Race finished!!");
        if (hare.getPosition() > tortoise.getPosition()) {
            System.out.println("Winner: Hare!!");
        } else if (hare.getPosition() < tortoise.getPosition()) {
            System.out.println("Winner: Tortoise!!");
        } else {
            System.out.println("It's a tie!!");
        }

    }

    private void timeTick(int duration) {
        //I do not know what does this code actually do!!
        //I've just coppied it from Stackoverflow!! :-D
        try {
            Thread.sleep(duration);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    private boolean checkForWinner() {
        if (hare.getPosition() >= 50 || tortoise.getPosition() >= 50) {
            return true;
        } else {
            return false;
        }
    }

    private void updateDisplay() {
        clearDisplay();
        for (int i = 0; i < tortoise.getPosition(); System.out.print(" "), i++);
        System.out.println("T");
        for (int i = 0; i < hare.getPosition(); System.out.print(" "), i++);
        System.out.println("H");

        //for(int i = 0 ; i < 180 ; System.out.println("-"), i++);
    }

    private void clearDisplay() {
        try {
            String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                Runtime.getRuntime().exec("cls");
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (Exception exception) {
            //  simulate cleaning screen by 10 new lines!
            for (int i = 0; i < 10; System.out.println(""), i++);
        }
    }
}
