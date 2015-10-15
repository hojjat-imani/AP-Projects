
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Hojjat
 */
public class Tortoise extends Contender {

    public Tortoise() {
        position = 1;
    }

    public void move() {
        Random randomGenerator = new Random();
        int randomNumber = randomGenerator.nextInt(10);
        if (randomNumber < 5) {
            fastPlod();
        } else if (randomNumber < 7) {
            slowPlod();
        } else {
            slip();
        }
    }

    private void fastPlod() {
        goForward(3);
    }

    private void slowPlod() {
        goForward(1);
    }

    private void slip() {
        if (goBackward(5) == false) {
            Random randomGenerator = new Random();
            if (randomGenerator.nextInt(7) < 5) {
                fastPlod();
            } else {
                slowPlod();
            }
        }
    }
}
