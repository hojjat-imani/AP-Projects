
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
public class HawkResult extends JFrame implements ActionListener {

    private String tilesState;
//    private Tile topTile;
//    private Tile rightTile;
//    private Tile bottomTile;
//    private Tile leftTile;
    private String topTileState;
    private String topTileState1;
    private String topTileState2;
    private String topTileState3;
    private String topTileState4;
    private String rightTileState;
    private String rightTileState1;
    private String rightTileState2;
    private String rightTileState3;
    private String rightTileState4;
    private String bottomTileState;
    private String bottomTileState1;
    private String bottomTileState2;
    private String bottomTileState3;
    private String bottomTileState4;
    private String leftTileState;
    private String leftTileState1;
    private String leftTileState2;
    private String leftTileState3;
    private String leftTileState4;
    
    private int topTileEnemys,  rightTileEnemys,  bottomTileEnemys, leftTileEnemys;
    
    public HawkResult(Tile topTile, Tile rightTile, Tile bottomTile, Tile leftTile, int topTileEnemys, int rightTileEnemys, int bottomTileEnemys, int leftTileEnemys) {
        this.setSize(600, 700);
        
        this.topTileEnemys = topTileEnemys;
        this.rightTileEnemys = rightTileEnemys;
        this.bottomTileEnemys = bottomTileEnemys;
        this.leftTileEnemys = leftTileEnemys;
        
//        this.topTile = topTile;
//        this.rightTile = rightTile;
//        this.bottomTile = bottomTile;
//        this.leftTile = leftTile;
//        JLabel lablel = new JLabel("label");
//        lablel.setPreferredSize(new Dimension(520, 580));
//        this.add(lablel);
//        String topTileState = "Top Tile: \n\r";
//        String rightTileState = "Right Tile: \r\n";
//        String bottomTileState = "Bottom Tile: \n";
//        String leftTileState = "Left Tile: \n";
        if (topTile == null) {
            topTileState = "Out Of Field! \n\n\n";
        } else {
            topTileState = topTile.getState();
        }

        if (rightTile == null) {
            rightTileState = "Out Of Field! \n\n\n";
        } else {
            rightTileState = rightTile.getState();
        }

        if (bottomTile == null) {
            bottomTileState = "Out Of Field! \n\n\n";
        } else {
            bottomTileState = bottomTile.getState();
        }

        if (leftTile == null) {
            leftTileState = "Out Of Field! \n\n\n";
        } else {
            leftTileState = leftTile.getState();
        }

        topTileState += "                                                                                                                                                                                                                                                ";
        rightTileState += "                                                                                                                                                                                                                                                ";
        bottomTileState += "                                                                                                                                                                                                                                                ";
        leftTileState += "                                                                                                                                                                                                                                                ";
        
        
        topTileState1 = topTileState.substring(0, 63);
        topTileState2 = topTileState.substring(63, 126);
        topTileState3 = topTileState.substring(126, 189);
        topTileState4 = topTileState.substring(189, 252);
        
        rightTileState1 = rightTileState.substring(0, 63);
        rightTileState2 = rightTileState.substring(63, 126);
        rightTileState3 = rightTileState.substring(126, 189);
        rightTileState4 = rightTileState.substring(189, 252);
        
        bottomTileState1 = bottomTileState.substring(0, 63);
        bottomTileState2 = bottomTileState.substring(63, 126);
        bottomTileState3 = bottomTileState.substring(126, 189);
        bottomTileState4 = bottomTileState.substring(189, 252);
        
        leftTileState1 = leftTileState.substring(0, 63);
        leftTileState2 = leftTileState.substring(63, 126);
        leftTileState3 = leftTileState.substring(126, 189);
        leftTileState4 = leftTileState.substring(189, 252);
        
        
        
        tilesState = topTileState + rightTileState + bottomTileState + leftTileState;
//        lablel.setText(tilesState);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Image background = new ImageIcon("files\\Icons\\howkResult.jpg").getImage();
        g.drawImage(background, 0, 0, 600, 700, this);
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
        g.drawString("Top Tile: ", 80, 120);
        g.drawString(topTileState1, 100, 140);
        g.drawString(topTileState2, 100, 160);
        g.drawString(topTileState3, 100, 180);
        g.drawString(topTileState4, 100, 200);
        
        
        if(topTileEnemys > 0){
            g.drawString("Number Of Enemys In Tile: " + topTileEnemys, 150, 220);
        }
        
        g.drawString("Right Tile: ", 80, 240);
        g.drawString(rightTileState1, 100, 260);
        g.drawString(rightTileState2, 100, 280);
        g.drawString(rightTileState3, 100, 300);
        g.drawString(rightTileState4, 100, 320);
        
        if(rightTileEnemys > 0){
            g.drawString("Number Of Enemys In Tile: " + rightTileEnemys, 150, 340);
        }
        
        g.drawString("bottom Tile: ", 80, 360);
        g.drawString(bottomTileState1, 100, 380);
        g.drawString(bottomTileState2, 100, 400);
        g.drawString(bottomTileState3, 100, 420);
        g.drawString(bottomTileState4, 100, 440);
        
        if(bottomTileEnemys > 0){
            g.drawString("Number Of Enemys In Tile: " + bottomTileEnemys, 150, 460);
        }
        
        
        g.drawString("left Tile: ", 80, 480);
        g.drawString(leftTileState1, 100, 500);
        g.drawString(leftTileState2, 100, 520);
        g.drawString(leftTileState3, 100, 540);
        g.drawString(leftTileState4, 100, 560);
        
        if(leftTileEnemys > 0){
            g.drawString("Number Of Enemys In Tile: " + leftTileEnemys, 150, 580);
        }
    }

}
