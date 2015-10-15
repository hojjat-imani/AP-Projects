
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Hojjat
 */
public class MainFrameC extends JFrame{
    
    private BubblesPanelC bubbles;

    public MainFrameC(Bubble initialBubble) throws HeadlessException {
//        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//        this.setSize(screenSize);
        this.setSize(500, 700);
        this.setResizable(false);
//        this.setUndecorated(true);
//        this.setOpacity(0.65f);
        
//        AWTUtilities.setWindowOpacity(this, TOP_ALIGNMENT);
        
        
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        this.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
                    System.exit(0);
                } 
            }
            
});

        bubbles = new BubblesPanelC(initialBubble);
        this.add(bubbles);
    }

    public void addBubble(Bubble bubble){
        bubbles.addBubble(bubble);
    }
    
    public void removeBubble(Bubble bubble){
        bubbles.removeBubble(bubble);
    }
    
}
