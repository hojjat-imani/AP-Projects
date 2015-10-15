
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
public class Hawk extends JPanel {

    public Hawk(MouseListener listener) {
        this.addMouseListener(listener);
        this.setOpaque(false);
        this.setSize(new Dimension(70, 70));
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image backgroundImage = new ImageIcon("files\\Items\\Hawk.png").getImage();
        g.drawImage(backgroundImage, 0, 0, null);
    }

    public void use(Field field, int x_position, int y_position) {
        System.out.println("");
        if (x_position - 1 >= 0) {
            System.out.println("(up)");
            field.getTile(x_position - 1, y_position).declareState();
            field.getTile(x_position - 1, y_position).tellAboutPotionsAndGoldAmount();
            field.tellAboutEnemies(x_position - 1, y_position);
        }
        if (x_position + 1 < 25) {
            System.out.println("(down)");
            field.getTile(x_position + 1, y_position).declareState();
            field.getTile(x_position + 1, y_position).tellAboutPotionsAndGoldAmount();
            field.tellAboutEnemies(x_position + 1, y_position);
        }
        if (y_position - 1 >= 0) {
            System.out.println("(left)");
            field.getTile(x_position, y_position - 1).declareState();
            field.getTile(x_position, y_position - 1).tellAboutPotionsAndGoldAmount();
            field.tellAboutEnemies(x_position, y_position - 1);
        }
        if (y_position + 1 < 25) {
            System.out.println("(right)");
            field.getTile(x_position, y_position + 1).declareState();
            field.getTile(x_position, y_position + 1).tellAboutPotionsAndGoldAmount();
            field.tellAboutEnemies(x_position, y_position + 1);
        }
        if (x_position - 1 >= 0 && y_position + 1 < 25) {
            field.getTile(x_position - 1, y_position + 1).declareState();
            field.getTile(x_position - 1, y_position + 1).tellAboutPotionsAndGoldAmount();
            field.tellAboutEnemies(x_position - 1, y_position + 1);
        }
        if (x_position - 1 >= 0 && y_position - 1 >= 0) {
            field.getTile(x_position - 1, y_position - 1).declareState();
            field.getTile(x_position - 1, y_position - 1).tellAboutPotionsAndGoldAmount();
            field.tellAboutEnemies(x_position - 1, y_position - 1);
        }
        if (x_position + 1 < 25 && y_position + 1 < 25) {
            field.getTile(x_position + 1, y_position + 1).declareState();
            field.getTile(x_position + 1, y_position + 1).tellAboutPotionsAndGoldAmount();
            field.tellAboutEnemies(x_position + 1, y_position + 1);
        }
        if (x_position + 1 < 25 && y_position - 1 >= 0) {
            field.getTile(x_position + 1, y_position - 1).declareState();
            field.getTile(x_position + 1, y_position - 1).tellAboutPotionsAndGoldAmount();
            field.tellAboutEnemies(x_position + 1, y_position - 1);
        }
        System.out.println("---------------------------------------------------------------------------------------------------------------------------");
    }

    @Override
    public String toString() {
        return "Hawk";
    }
}
