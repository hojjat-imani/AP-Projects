/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Calculator;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Hojjat
 */
public class OperatorButtonsPanel extends JPanel {
    
    private JPanel p1;
    private JPanel p2;
    private JButton buttons[];
    private String buttonlables[] = {"÷", "%", "×", "1/x", "-", "+", "="};
    private Insets buttonMargin;
    
    public OperatorButtonsPanel() {
        buttons = new JButton[7];
        p1 = new JPanel(new GridLayout(2, 2, 5, 5));
        p2 = new JPanel(new GridLayout(2, 1, 5, 5));
        buttonMargin = new Insets(-5, -5, -5, -5);
        for (int i = 0; i < 6; i++) {
            buttons[i] = new JButton(buttonlables[i]);
            buttons[i].setPreferredSize(new Dimension(35, 27));
            buttons[i].setMargin(buttonMargin);
            if (i < 4) {
                p1.add(buttons[i]);
            } else {
                p2.add(buttons[i]);
            }
        }
        buttons[6] = new JButton(buttonlables[6]);
        buttons[6].setPreferredSize(new Dimension(35, 59));
        buttons[6].setMargin(buttonMargin);
        this.setLayout(new BorderLayout(5, 5));
        this.add(BorderLayout.NORTH, p1);
        this.add(BorderLayout.WEST, p2);
        this.add(BorderLayout.CENTER, buttons[6]);
        
    }
    
}
