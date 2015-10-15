
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
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
public class Key extends JPanel {

    private final int number;

    public Key(int number, MouseListener listener) {
        this.addMouseListener(listener);
        this.number = number;
        this.setOpaque(false);
        this.setSize(new Dimension(70, 70));
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image backgroundImage = new ImageIcon("files\\Items\\Key.png").getImage();
        g.drawImage(backgroundImage, 0, 0, null);
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        g.drawString("" + number, 45, 45);
    }

    @Override
    public String toString() {
        return "Key";
    }

    /**
     * @return the number
     */
    public int getNumber() {
        return number;
    }
}
