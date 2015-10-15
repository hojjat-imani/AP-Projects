/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javadownloadmanagerbyhojjat;

import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

/**
 *
 * @author Hojjat
 */
public class Main {

    static MainFrame jdm;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        try {
//
//            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    UIManager.setLookAndFeel(info.getClassName());
//                    System.out.println("CHOSEN THIS");
//                    break;
//                }
//            }
//        } catch (Exception e) {
//        }

        UIManager UI = new UIManager();
        UI.put("OptionPane.background", new Color(0, 191, 191));
        UI.put("Panel.background", new Color(0, 191, 191));
//        UIManager.getLookAndFeelDefaults().put("OptionPane.background", new Color(0, 191, 191));
//        UIManager.getLookAndFeelDefaults().put("Panel.background", new Color(0, 191, 191));
////        UIManager.getLookAndFeelDefaults().put("List.background", new Color(0, 191, 191));
////        UIManager.getLookAndFeelDefaults().put("List.foreground", new Color(0, 191, 191));
//        UIManager.getLookAndFeelDefaults().put("FileChooser.background", new Color(0, 191, 191));


        jdm = new MainFrame();
        jdm.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (JOptionPane.showConfirmDialog(jdm,
                        "Are you sure to exit?", "Exit?",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
        jdm.setVisible(true);
    }
}
