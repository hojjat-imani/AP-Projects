
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
public class FireArrow extends JPanel {

    public FireArrow(MouseListener listener) {
        this.addMouseListener(listener);
        this.setOpaque(false);
        this.setSize(new Dimension(70, 70));
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image backgroundImage = new ImageIcon("files\\Items\\FireArrow.png").getImage();
        g.drawImage(backgroundImage, 0, 0, null);
    }

    public void useAgainstEnemy(Field field, int x_position, int y_position, int enemyNumber) {
//        field.killEnemyInTileWithNumber(x_position, y_position, enemyNumber);
        breakWalls(x_position, y_position, field);
    }

    public void useAgainstPlayer(Player player) {
        player.beShooted();
        breakWalls(player.getX_position(), player.getY_position(), null);
    }

    @Override
    public String toString() {
        return "Fire Arrow";
    }

    private void breakWalls(int x_position, int y_position, Field field) {
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
        System.out.println("                                      \033[32mAnd all surrounding walls broke!!");
    }

}
