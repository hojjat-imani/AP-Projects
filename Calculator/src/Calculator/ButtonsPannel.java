/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Calculator;

import java.awt.BorderLayout;
import javax.swing.JPanel;

/**
 *
 * @author Hojjat
 */
public class ButtonsPannel extends JPanel {

    private ControlButtonsPanel controlButtonsPanel;
    private NumberButtonsPanel numberButtonsPanel;
    private OperatorButtonsPanel opretorButtonsPannel;
    
    public ButtonsPannel() {
        controlButtonsPanel = new ControlButtonsPanel();
        numberButtonsPanel = new NumberButtonsPanel();
        opretorButtonsPannel = new OperatorButtonsPanel();
        setLayout(new BorderLayout(5 , 5));
        add(BorderLayout.NORTH, controlButtonsPanel);
        add(BorderLayout.WEST, numberButtonsPanel);
        add(BorderLayout.CENTER, opretorButtonsPannel);
    }
}
