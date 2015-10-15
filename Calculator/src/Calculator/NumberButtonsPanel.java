/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Calculator;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Hojjat
 */
public class NumberButtonsPanel extends JPanel {

    private JPanel p1;
    private JPanel p2;
    private JButton buttons[];
    private String buttonNames[] = {"7","8","9","4","5","6","1","2","3"};
    private Insets buttonMargin;

    public NumberButtonsPanel() {
        buttons = new JButton[11];
        buttonMargin = new Insets(0, 0, 0, 0);
        p1 = new JPanel(new GridLayout(3, 3, 5, 5));
        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton(buttonNames[i]);
            buttons[i].setMargin(buttonMargin);
            buttons[i].setPreferredSize(new Dimension(35, 27));
            p1.add(buttons[i]);
        }
        p2 = new JPanel(new BorderLayout(0, 0));
        buttons[9] = new JButton("0");
        buttons[9].setPreferredSize(new Dimension(75, 27));
        buttons[10] = new JButton(".");
        buttons[10].setMargin(buttonMargin);
        buttons[10].setPreferredSize(new Dimension(35, 27));
        p2.add(BorderLayout.WEST, buttons[9]);
        p2.add(BorderLayout.EAST, buttons[10]);
        this.setLayout(new BorderLayout(0,5));
        this.add(BorderLayout.NORTH, p1);
        this.add(BorderLayout.CENTER, p2);
    }

}
