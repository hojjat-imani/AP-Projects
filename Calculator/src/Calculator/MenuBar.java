/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Calculator;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author Hojjat
 */
public class MenuBar extends JMenuBar {

    private JMenu view;
    private JMenu edit;
    private JMenu help;

    public MenuBar() {
        view = new JMenu("View");
        edit = new JMenu("Edit");
        help = new JMenu("Help");
        this.add(view);
        this.add(edit);
        this.add(help);
    }

}
