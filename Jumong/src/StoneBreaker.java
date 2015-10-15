
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
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
public class StoneBreaker extends JPanel {

    public StoneBreaker(MouseListener listener) {
        this.addMouseListener(listener);
        this.setOpaque(false);
        this.setSize(new Dimension(70, 70));
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image backgroundImage = new ImageIcon("files\\Items\\StoneBreaker.png").getImage();
        g.drawImage(backgroundImage, 0, 0, null);
    }

    public void use(Field field, int x_position, int y_position) {
        if (x_position - 1 >= 0) {
            field.getTile(x_position - 1, y_position).setIsWall(false);
        }
        if (x_position + 1 < 25) {
            field.getTile(x_position + 1, y_position).setIsWall(false);
        }
        if (y_position - 1 >= 0) {
            field.getTile(x_position, y_position - 1).setIsWall(false);
        }
        if (y_position + 1 < 25) {
            field.getTile(x_position, y_position + 1).setIsWall(false);
        }
        if (x_position - 1 >= 0 && y_position + 1 < 25) {
            field.getTile(x_position - 1, y_position + 1).setIsWall(false);
        }
        if (x_position - 1 >= 0 && y_position - 1 >= 0) {
            field.getTile(x_position - 1, y_position - 1).setIsWall(false);
        }
        if (x_position + 1 < 25 && y_position + 1 < 25) {
            field.getTile(x_position + 1, y_position + 1).setIsWall(false);
        }
        if (x_position + 1 < 25 && y_position - 1 >= 0) {
            field.getTile(x_position + 1, y_position - 1).setIsWall(false);
        }
        System.out.println("                                      \033[32mAll neighbour walls broke!!\033[0m");
    }

    @Override
    public String toString() {
        return "Stone Breaker";
    }
}
