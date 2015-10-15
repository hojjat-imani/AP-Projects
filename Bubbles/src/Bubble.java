
import java.awt.Color;
import java.awt.Toolkit;
import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Hojjat
 */
public class Bubble implements Serializable{
    
    private static final long serialVersionUID = -403250971215465050L;

    private int X_Speed;
    private int Y_Speed;
    private int X_position;
    private int Y_position;
    private Color color;
    private Bubble lastCollision;
    private final int ID;
    private String sentFor;

    Color[] colors = {Color.BLUE, Color.CYAN, Color.GRAY, Color.GREEN, Color.MAGENTA, Color.ORANGE, Color.PINK, Color.RED, Color.YELLOW};

    public Bubble(int X_position, int Y_position, int ID) {
        this.ID = ID;
        this.X_position = X_position;
        this.Y_position = Y_position;
        do {
            X_Speed = 5 - (int) (Math.random() * 10);
            Y_Speed = 5 - (int) (Math.random() * 10);
        } while (X_Speed == 0 || Y_Speed == 0);
//        X_Speed=Y_Speed=0;
        color = colors[(int) (Math.random() * 9)];
    }

    public void move() {
        X_position += getX_Speed();
        Y_position += getY_Speed();
        //for frame decoration enabled
//        if (getX_position() < 0 || getX_position() > Toolkit.getDefaultToolkit().getScreenSize().getWidth() - getSize() - 5) {
        if (getX_position() < 0 || getX_position() > Toolkit.getDefaultToolkit().getScreenSize().getWidth() - Main.bubbleSize) {
            lastCollision = null;
            X_Speed *= -1;
        }
        //for frame decoration enabled
//        if (getY_position() < 0 || getY_position() > Toolkit.getDefaultToolkit().getScreenSize().getHeight() - getSize() - 32) {
        if (getY_position() < 0 || getY_position() > Toolkit.getDefaultToolkit().getScreenSize().getHeight() - Main.bubbleSize) {
            lastCollision = null;
            Y_Speed *= -1;
        }
    }

    public void changeDirection(Bubble otherBubble) {
        setLastCollision(otherBubble);
        if (Math.abs(this.X_position - otherBubble.getX_position()) >= Math.abs(this.Y_position - otherBubble.getY_position())) {
            this.X_Speed *= -1;
//            X_Speed *= otherBubble.getX_Speed() / Math.abs(otherBubble.getX_Speed()) * -1;
        }
        if (Math.abs(this.X_position - otherBubble.getX_position()) <= Math.abs(this.Y_position - otherBubble.getY_position())) {
            this.Y_Speed *= -1;
//            Y_Speed *= otherBubble.getY_Speed() / Math.abs(otherBubble.getY_Speed()) * -1;
        }

    }

    public boolean isInPosition(int x_position, int y_position) {
        //for frame decoration enabled
//        if (Math.pow(this.getX_position() + Main.bubbleSize / 2 + 4 - x_position, 2) + Math.pow(this.getY_position() + Main.bubbleSize / 2 + 25 - y_position, 2) <= Math.pow(Main.bubbleSize / 2, 2)) {
        if (Math.pow(this.getX_position() + Main.bubbleSize / 2 - x_position, 2) + Math.pow(this.getY_position() + Main.bubbleSize / 2 - y_position, 2) <= Math.pow(Main.bubbleSize / 2, 2)) {
            return true;
        }
        return false;
    }

    /**
     * @return the X_position
     */
    public int getX_position() {
        return X_position;
    }

    /**
     * @return the Y_position
     */
    public int getY_position() {
        return Y_position;
    }

    /**
     * @return the color
     */
    public Color getColor() {
        return color;
    }

    /**
     * @return the Main.bubbleSize
     */
    public int getSize() {
        return Main.bubbleSize;
    }

    /**
     * @return the Y_Speed
     */
    public int getY_Speed() {
        return Y_Speed;
    }

    /**
     * @return the X_Speed
     */
    public int getX_Speed() {
        return X_Speed;
    }

    /**
     * @return the lastCollision
     */
    public Bubble getLastCollision() {
        return lastCollision;
    }

    /**
     * @param lastCollision the lastCollision to set
     */
    public void setLastCollision(Bubble lastCollision) {
        this.lastCollision = lastCollision;
    }

    /**
     * @return the sentFor
     */
    public String getSentFor() {
        return sentFor;
    }

    /**
     * @param sentFor the sentFor to set
     */
    public void setSentFor(String sentFor) {
        this.sentFor = sentFor;
    }

    /**
     * @return the ID
     */
    public int getID() {
        return ID;
    }

}
