
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Hojjat
 */
public class PlayerState extends JPanel{
    private Player player;

    public PlayerState(Player player) {
        this.player = player;
        this.setSize(400, 60);
        this.setBackground(Color.yellow);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 
        g.drawString("ReviveScroll: " + "--" + "   Energy: " + player.getEnergy() + "   HitPoint: " + player.getHitPoint() + "   Score: " + player.getScore(), 30, 30);
    }
    
    
}
