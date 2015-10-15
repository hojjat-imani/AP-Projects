
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Scanner;
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
public class Shovel extends JPanel {

    public Shovel(MouseListener listener) {
        this.addMouseListener(listener);
        this.setOpaque(false);
        this.setSize(new Dimension(70, 70));
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image backgroundImage = new ImageIcon("files\\Items\\Shovel.png").getImage();
        g.drawImage(backgroundImage, 0, 0, null);
    }

    public boolean use(Field field, Player player) {
        Scanner inputReader = new Scanner(System.in);
        int x_destination, y_destination;
        boolean destinationIsFarAway, inputIsInvalid;
        destinationIsFarAway = inputIsInvalid = false;
        do {
            if (destinationIsFarAway || inputIsInvalid) {
                System.out.print("Try again:     -> ");
                destinationIsFarAway = inputIsInvalid = false;
            }
            try {
                System.out.println("Enter shovel destination: \033[47m(Ex: (10 , 17) -> 10 17)\033[0m");
                System.out.print("               -> ");
                x_destination = inputReader.nextInt();
                y_destination = inputReader.nextInt();
                inputReader.nextLine();
            } catch (Exception exception) {
                System.out.println("                                      \033[31mUse pattern of example to enter input!!\033[0m");
                inputReader.nextLine();
                inputIsInvalid = true;
                continue;
            }
            if ((Math.abs(x_destination - player.getX_position()) + Math.abs(y_destination - player.getY_position())) > 5) {
                System.out.println("                                      \033[31mThats too far!!\033[0m");
                destinationIsFarAway = true;
            } else if (field.getTile(x_destination, y_destination).isWall()) {
                System.out.println("                                      \033[31mShovel was useless! Destination is wall!!\033[0m");
                return false;
            } else {
                player.setPosition(x_destination, y_destination);
            }
        } while (inputIsInvalid || destinationIsFarAway);
        return true;
    }

    @Override
    public String toString() {
        return "Shovel";
    }

}
