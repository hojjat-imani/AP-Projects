
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
public class BigArrow extends JPanel {

    public BigArrow(MouseListener listener) {
        this.addMouseListener(listener);
        this.setOpaque(false);
        this.setSize(new Dimension(70, 70));
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image backgroundImage = new ImageIcon("files\\Items\\BigArrow.png").getImage();
        g.drawImage(backgroundImage, 0, 0, null);
    }

    public void useAgainstEnemy(Field field, int x_position, int y_position, int enemyNumber) {
        field.killAllEnemiesInTile(x_position, y_position);
    }

    public void useAgainstPlayer(Player player) {
        player.beShooted();
    }

    @Override
    public String toString() {
        return "Big Arrow";
    }

}
