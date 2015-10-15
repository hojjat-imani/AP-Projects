
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import sun.rmi.runtime.NewThreadAction;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Hojjat
 */
public class BubblesPanel extends JPanel implements Runnable {

    private ArrayList<Bubble> bubbles;
    private ArrayList<Bubble> toBeRemoved;
    private Thread tread;
    private NetworkHandler networkHandler;
    private volatile int bubblesID;

    public BubblesPanel(NetworkHandler networkHandler) {
        this.networkHandler = networkHandler;
        bubbles = new ArrayList<>();
        tread = new Thread(this);
        tread.start();
        startProgramWithFirstBubble(100, 300);
    }

    public void addBubble(int X_position, int Y_position) {
        if (noBubbleInPosition(X_position, Y_position)) {
            //for frame decoration enabled
//            bubbles.add(new Bubble(X_position - Main.bubbleSize / 2 - 4, Y_position - Main.bubbleSize / 2 - 25));
            Bubble newBubble = new Bubble(X_position - Main.bubbleSize / 2, Y_position - Main.bubbleSize / 2, bubbleIDRegisterer());
            bubbles.add(newBubble);
            networkHandler.informBubbleAdded(newBubble);
        }
    }

    public void startProgramWithFirstBubble(int X_position, int Y_position) {
        Bubble initialBubble = new Bubble(X_position - Main.bubbleSize / 2, Y_position - Main.bubbleSize / 2, bubbleIDRegisterer());
        bubbles.add(initialBubble);
        networkHandler.informStart(initialBubble);
    }

    public void removeBubble(int X_position, int Y_position) {
        try {
            for (Bubble bubble : bubbles) {
                if (bubble.isInPosition(X_position, Y_position)) {
                    bubbles.remove(bubble);
                    networkHandler.informBubbleRemoved(bubble);
                    return;
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(BubblesPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(BubblesPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                for (Bubble b : bubbles) {
                    b.move();
                }
            } catch (Exception ex) {
                Logger.getLogger(BubblesPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            handleCollisions();
            repaint();
        }

    }

    private boolean noBubbleInPosition(int x_position, int y_position) {
//        try {
        for (Bubble bubble : bubbles) {
            if (bubble.isInPosition(x_position, y_position)) {
                return false;
            }
        }
//        } catch (Exception ex) {
//            Logger.getLogger(BubblesPanel.class.getName()).log(Level.SEVERE, null, ex);
//        }
        return true;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (Bubble bubble : bubbles) {
            g.setColor(bubble.getColor());
            g.fillOval(bubble.getX_position(), bubble.getY_position(), Main.bubbleSize, Main.bubbleSize);
        }
        g.setColor(Color.BLUE);
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        g.drawString("Exit: esc", 50, 750);
    }

    private void handleCollisions() {
        try {
            for (Bubble bubble1 : bubbles) {
                for (Bubble bubble2 : bubbles) {
                    if (hasCollision(bubble1, bubble2) && bubble1 != bubble2) {
                        bubble1.changeDirection(bubble2);
                        break;
                    }
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(BubblesPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private boolean hasCollision(Bubble bubble1, Bubble bubble2) {
        if (Math.pow(bubble1.getX_position() - bubble2.getX_position(), 2) + Math.pow(bubble1.getY_position() - bubble2.getY_position(), 2) < Math.pow(Main.bubbleSize, 2) && Math.pow(bubble1.getX_position() - bubble2.getX_position(), 2) + Math.pow(bubble1.getY_position() - bubble2.getY_position(), 2) > 0.75 * Math.pow(Main.bubbleSize, 2) && bubble1.getLastCollision() != bubble2) {
            return true;
        }
        return false;
    }
    
    synchronized private int bubbleIDRegisterer(){
        bubblesID++;
        return bubblesID;
    }

}
