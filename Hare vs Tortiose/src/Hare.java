
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
public class Hare extends Contender {

    public Hare() {
        position = 1;
    }

    public void move() {
        Random randomGenerator = new Random();
        int randomNumber = randomGenerator.nextInt(10);
        if (randomNumber < 2) {
            //Sleep
        } else if (randomNumber < 4) {
            bigHop();
        } else if (randomNumber < 7) {
            smallHop();
        } else if (randomNumber < 9) {
            smallSlip();
        } else {
            bigSlip();
        }
    }

    private void smallHop() {
        goForward(2);
    }

    private void bigHop() {
        goForward(8);
    }

    private void smallSlip() {
        if (goBackward(2) == false) {
            Random randomGenerator = new Random();
            int randomNumber = randomGenerator.nextInt(7);
            if (randomNumber < 2) {
                //Sleep
            } else if (randomNumber < 4) {
                bigHop();
            } else {
                smallHop();
            }
        }
    }

    private void bigSlip() {
        if (goBackward(10) == false) {
            Random randomGenerator = new Random();
            int randomNumber = randomGenerator.nextInt(9);
            if (randomNumber < 2) {
                //Sleep
            } else if (randomNumber < 4) {
                bigHop();
            } else if (randomNumber < 7) {
                smallHop();
            } else {
                smallSlip();
            }
        }
    }

}