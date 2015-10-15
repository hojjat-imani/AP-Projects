
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
public class ShovelDestinationChooser extends JFrame implements ActionListener {

    private GameControl gameControl;
    private Tile currentTile;
    private JButton[] buttons;
    private int xOfDimentions[] = {-5, -4, -4, - 4, -3, -3, -3, -3, -3, -2, -2, -2, -2, -2, -2, -2, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 4, 4, 4, 5};
    private int yOfDimentions[] = {0, -1, 0, 1, -2, -1, 0, 1, 2, -3, -2, -1, 0, 1, 2, 3, -4, -3, -2, -1, 0, 1, 2, 3, 4, -5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5, -4, -3, -2, -1, 0, 1, 2, 3, 4, -3, -2, -1, 0, 1, 2, 3, -2, -1, 0, 1, 2, -1, 0, 1, 0};

    public ShovelDestinationChooser(GameControl gameControl, Tile currentTile, Dimension screenSize) throws HeadlessException {
        System.out.println("" + yOfDimentions.length + "      " + xOfDimentions.length);
        this.gameControl = gameControl;
        this.currentTile = currentTile;
        this.setBounds(screenSize.width / 2 - 270 / 2, screenSize.height / 2 - 290 / 2, 270, 290);
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        this.setResizable(false);
        this.setUndecorated(true);
        this.getContentPane().setBackground(new Color(96, 57, 19));

        //add the top margin
        JPanel p = new JPanel();
        p.setPreferredSize(new Dimension(250, 10));
        p.setBackground(null);
        this.add(p);

        JPanel p4 = new JPanel();
        p4.setPreferredSize(new Dimension(250, 20));
        p4.setBackground(new Color(251, 245, 193));
        JLabel lable = new JLabel("Choose Tunnel Destination: ");
        lable.setPreferredSize(new Dimension(250, 20));
        p4.add(lable);
        this.add(p4);

        //add the top margin
        JPanel p2 = new JPanel();
        p2.setPreferredSize(new Dimension(250, 4));
        p2.setBackground(new Color(251, 245, 193));
        this.add(p2);

        JPanel[] panel = new JPanel[11];
        buttons = new JButton[61];
        for (int i = 0; i < 11; i++) {
            panel[i] = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
            panel[i].setPreferredSize(new Dimension(250, 22));
            panel[i].setBackground(new Color(251, 245, 193));
            this.add(panel[i]);
        }
        //add the top margin
        JPanel p3 = new JPanel();
        p3.setPreferredSize(new Dimension(250, 4));
        p3.setBackground(new Color(251, 245, 193));
        this.add(p3);

        for (int i = 0; i < 61; i++) {
            buttons[i] = new JButton(new ImageIcon("files\\Field\\tile.png"));
            buttons[i].setRolloverIcon(new ImageIcon("files\\Field\\tile2.png"));
            buttons[i].setPreferredSize(new Dimension(22, 22));
            buttons[i].addActionListener(this);
            if (buttonIsOutOfField(i)) {
                buttons[i].setEnabled(false);
            }
        }
        buttons[30].setIcon(new ImageIcon("files\\Field\\player.png"));
        int k = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < i * 2 + 1; j++) {
                panel[i].add(buttons[k]);
                k++;
            }
        }

        for (int i = 6; i < 11; i++) {
            for (int j = 0; j < 9 - (i - 6) * 2; j++) {
                panel[i].add(buttons[k]);
                k++;
            }
        }
    }

//    @Override
//    public void paint(Graphics g) {
////        super.paint(g); 
////        Image backGround = new ImageIcon("files\\Field\\ShovelChooserBackground.png").getImage();
////        g.drawImage(backGround, 0, 0, this);
//    }
    private boolean buttonIsOutOfField(int buttonNumber) {
        int x = currentTile.getX_position() + xOfDimentions[buttonNumber];
        int y = currentTile.getY_position() + yOfDimentions[buttonNumber];
        if (x < 0 || x > 24 || y < 0 || y > 24) {
            return true;
        }
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int buttonNumber = getIndexOfButton((JButton) e.getSource());
        int X_destination = currentTile.getX_position() + xOfDimentions[buttonNumber];
        int Y_Destination = currentTile.getY_position() + yOfDimentions[buttonNumber];
        gameControl.digTunnel(X_destination, Y_Destination);
    }

    private int getIndexOfButton(JButton button) {
        for (int i = 0; i < 61; i++) {
            if (buttons[i] == button) {
                return i;
            }
        }
        return 0;
    }
}
