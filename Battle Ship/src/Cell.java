/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Hojjat
 */
public class Cell {
    private boolean isFree;
    private boolean isShooted;

    public Cell() {
        isFree = true;
        isShooted = false;
    }

    /**
     *
     * @return false if shoot is missed and true if shoot made hit
     */
    public boolean shoot() {
        isShooted = true;
        if (isFree) {
            return false;
        }
        return true;
    }

    public void putShipInCell() {
        isFree = false;
    }

    /**
     * @return the isFree
     */
    public boolean isFree() {
        return isFree;
    }

    /**
     * @return the isShooted
     */
    public boolean isShooted() {
        return isShooted;
    }

}
