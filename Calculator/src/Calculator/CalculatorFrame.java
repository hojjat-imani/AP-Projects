/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Calculator;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Hojjat
 */
public class CalculatorFrame extends JFrame {

    private JTextField textField;
    private JButton numberButtons[];
    private JPanel numberButtonPanel;
    private JButton controlButtons[];
    private JPanel controlButtonPanel;
    private JPanel p2;

    public CalculatorFrame() throws HeadlessException {
//        add(new MenuBar());
//        setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
//        textField = new JTextField("0");
//        textField.setPreferredSize(new Dimension(200, 50));
//        add(textField);
//        numberButtonPanel = new NumberButtonsPannel();
//        add(numberButtonPanel);
//        p2 = new But_pannel1();
//        add(p2);
        setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        setJMenuBar(new MenuBar());
        textField = new JTextField("0");
        textField.setEditable(false);
        textField.setPreferredSize(new Dimension(197, 50));
        add(textField);
        add(new ButtonsPannel());
    }

}
