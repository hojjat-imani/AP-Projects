/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Hojjat
 */
public class TankPeice {

    private int x_position;
    private int y_position;
    private boolean isFound;

    public TankPeice() {
        x_position = -1;
        y_position = -1;
        isFound = false;
    }

    /**
     * @return the x_position
     */
    public int getX_position() {
        return x_position;
    }

    /**
     * @param x_position the x_position to set
     */
    public void setX_position(int x_position) {
        this.x_position = x_position;
    }

    /**
     * @return the y_position
     */
    public int getY_position() {
        return y_position;
    }

    /**
     * @param y_position the y_position to set
     */
    public void setY_position(int y_position) {
        this.y_position = y_position;
    }

    /**
     * @return the isFound
     */
    public boolean IsFound() {
        return isFound;
    }

    /**
     * @param isFound the isFound to set
     */
    public void setIsFound(boolean isFound) {
        this.isFound = isFound;
    }

}
