
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;
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
public class Chest extends JPanel {

    private final int number;
    private ArrayList<Object> including;

    public Chest(int number, MouseListener listener) {
        this.addMouseListener(listener);
        this.number = number;
        including = new ArrayList<>();
        this.setOpaque(false);
        this.setSize(new Dimension(100, 100));
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image backgroundImage = new ImageIcon("files\\Items\\Chest.png").getImage();
        g.drawImage(backgroundImage, 0, 0, null);
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        g.drawString("" + number, 22, 54);
    }

    /**
     * @return the including
     */
    public ArrayList<Object> getIncluding() {
        return including;
    }

    @Override
    public String toString() {
        return "Chest";
    }

    /**
     * @return the number
     */
    public int getNumber() {
        return number;
    }

    public void printContainigItems() {
        System.out.println("Chest containing: ");
        System.out.printf("                Small Arrow      %3d                  Big Arrow        %3d                 Fire Arrow    %d\n", getNumberOfItemsOfKind(SmallArrow.class), getNumberOfItemsOfKind(BigArrow.class), getNumberOfItemsOfKind(FireArrow.class));
        System.out.printf("                Stone Breaker    %3d                  hawk             %3d                 Shovel        %d\n", getNumberOfItemsOfKind(StoneBreaker.class), getNumberOfItemsOfKind(Hawk.class), getNumberOfItemsOfKind(Shovel.class));
        System.out.printf("                bigbag           %3d                  Revive Scroll    %3d\n", getNumberOfItemsOfKind(BigBag.class), getNumberOfItemsOfKind(ReviveScroll.class));
        System.out.println("");
    }

    private int getNumberOfItemsOfKind(Class<?> itemType) {
        int numberOfParticularItem = 0;
        for (Object item : including) {
            if (item.getClass() == itemType) {
                numberOfParticularItem++;
            }
        }
        return numberOfParticularItem;
    }

    public void addItemToInventory(Object item) {
        including.add(item);
    }
}
