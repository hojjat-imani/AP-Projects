
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Hojjat
 */
public class Enemy extends JPanel{
    private int number;
    private int x_position;
    private int y_position;
    

    public Enemy(int number, int x_position, int y_position, MouseListener listener) {
        this.number = number;
        this.x_position = x_position;
        this.y_position = y_position;
        this.addMouseListener(listener);
        this.setOpaque(false);
        this.setSize(100, 70);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image enemyImg = new ImageIcon("files\\Icons\\enemy.png").getImage();
        g.drawImage(enemyImg, 0, 0, this);
    }
    
    

    /**
     * @return the number
     */
    public int getNumber() {
        return number;
    }
    
    @Override
    public String toString() {
        return "Enemy  ";
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
     * @param number the number to set
     */
    public void setNumber(int number) {
        this.number = number;
    }
    
    
}
