/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Calculator;

import javax.swing.JFrame;
import javax.swing.UIManager;

/**
 *
 * @author Hojjat
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        try {
//
//            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
//        } catch (Exception e) {
//        }
        
        try {

            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    System.out.println("CHOSEN THIS");
                    break;
                }}} catch(Exception e){}
        
        CalculatorFrame calculator = new CalculatorFrame();

        calculator.setSize(218, 313);
        calculator.setResizable(false);
        calculator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        calculator.setVisible(true);
    }

}
