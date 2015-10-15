/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Calculator;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Hojjat
 */
public class ControlButtonsPanel extends JPanel {

    private JButton buttons[];
    private String buttonNames[] = {"MC", "MR", "MS", "M+", "M-", "←", "CE", "C", "±", "√"};
    private Insets buttonMargin;

    public ControlButtonsPanel() {
        buttons = new JButton[10];
        setLayout(new GridLayout(2, 5, 5, 5));
        buttonMargin = new Insets(-8, -8, -8, -8);
        for (int i = 0; i < 10; i++) {
            buttons[i] = new JButton(buttonNames[i]);
            buttons[i].setMargin(buttonMargin);
            buttons[i].setPreferredSize(new Dimension(35, 27));
            add(buttons[i]);
        }
    }

}
