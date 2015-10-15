/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Hojjat
 */
public class Contender {

    /**
     *
     */
    protected int position;

    /**
     * will decrease the position by StepsToSlip if new psisition doesn't be
     * negative
     *
     * @param steps
     * @return true if position changes successfully and false if new position
     * be negative and position does not change
     */
    protected boolean goBackward(int steps) {
        int newPosition = position - steps;
        if (newPosition > 1) {
            position = newPosition;
            return true;
        }
        return false;
    }

    protected void goForward(int steps) {
        position += steps;
    }

    /**
     * @return the position
     */
    public int getPosition() {
        return position;
    }
}
