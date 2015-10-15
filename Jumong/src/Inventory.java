
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import sun.awt.CustomCursor;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Hojjat
 */
public class Inventory extends JPanel implements ActionListener {

    private JButton use_drop;
    private boolean drop_mode;
    private JButton smallArrow;
    private JButton bigArrow;
    private JButton fireArrow;
    private JButton stoneBreaker;
    private JButton shovel;
    private JButton hawk;
    private JButton key;
    private Class<?> ItemToBeUsed;

    private GameControl gameControl;
    private Player player;
    private int size;
    private ArrayList<Object> contaningItems;

    public Inventory(Player player, GameControl gameControl) {
        this.setSize(1366, 200);
        this.setBackground(Color.cyan);
        this.setLayout(null);

        this.gameControl = gameControl;
        this.player = player;
        size = 50;
        contaningItems = new ArrayList<Object>();
        for (int i = 0; i < 10; i++) {
            contaningItems.add(new SmallArrow((MouseListener) gameControl));
        }

        use_drop = new JButton(new ImageIcon("files\\Icons\\UseDrop - 1.png"));
        use_drop.setContentAreaFilled(false);
        use_drop.setBorderPainted(false);
        use_drop.setToolTipText("Click To Change");
        use_drop.setLocation(7, 137);
        use_drop.setSize(20, 45);
        use_drop.setFocusable(false);
        use_drop.addActionListener(this);
        this.add(use_drop);

        smallArrow = new JButton(new ImageIcon("files\\Icons\\SmallArrow -1.png"));
        smallArrow.setRolloverIcon(new ImageIcon("files\\Icons\\SmallArrow -2.png"));
        smallArrow.setContentAreaFilled(false);
        smallArrow.setBorderPainted(false);
        smallArrow.setToolTipText("Small Arrow - Kills A Single Enemy");
        smallArrow.setLocation(70, 60);
        smallArrow.setSize(115, 140);
        smallArrow.setFocusable(false);
        smallArrow.addActionListener(this);
        this.add(smallArrow);

        bigArrow = new JButton(new ImageIcon("files\\Icons\\BigArrow -1.png"));
        bigArrow.setRolloverIcon(new ImageIcon("files\\Icons\\BigArrow -2.png"));
        bigArrow.setContentAreaFilled(false);
        bigArrow.setBorderPainted(false);
        bigArrow.setToolTipText("Big Arrow - Kills All Enemies In The Tile");
        bigArrow.setLocation(220, 60);
        bigArrow.setSize(115, 140);
        bigArrow.setFocusable(false);
        bigArrow.addActionListener(this);
        this.add(bigArrow);

        fireArrow = new JButton(new ImageIcon("files\\Icons\\FireArrow -1.png"));
        fireArrow.setRolloverIcon(new ImageIcon("files\\Icons\\FireArrow -2.png"));
        fireArrow.setContentAreaFilled(false);
        fireArrow.setBorderPainted(false);
        fireArrow.setToolTipText("Fire Arrow - Kills A Single Enemy Also Destroys All Surrounding Walls");
        fireArrow.setLocation(382, 60);
        fireArrow.setSize(115, 140);
        fireArrow.setFocusable(false);
        fireArrow.addActionListener(this);
        this.add(fireArrow);

        stoneBreaker = new JButton(new ImageIcon("files\\Icons\\StoneBreaker -1.png"));
        stoneBreaker.setRolloverIcon(new ImageIcon("files\\Icons\\StoneBreaker -2.png"));
        stoneBreaker.setContentAreaFilled(false);
        stoneBreaker.setBorderPainted(false);
        stoneBreaker.setToolTipText("Stone Breaker - Destroys All Surrounding Walls");
        stoneBreaker.setLocation(560, 60);
        stoneBreaker.setSize(115, 140);
        stoneBreaker.setFocusable(false);
        stoneBreaker.addActionListener(this);
        this.add(stoneBreaker);

        shovel = new JButton(new ImageIcon("files\\Icons\\Shovel -1.png"));
        shovel.setRolloverIcon(new ImageIcon("files\\Icons\\Shovel -2.png"));
        shovel.setContentAreaFilled(false);
        shovel.setBorderPainted(false);
        shovel.setToolTipText("Shovel - Digs A Tunnel To Another Tile");
        shovel.setLocation(722, 60);
        shovel.setSize(115, 140);
        shovel.setFocusable(false);
        shovel.addActionListener(this);
        this.add(shovel);

        hawk = new JButton(new ImageIcon("files\\Icons\\Hawk -1.png"));
        hawk.setRolloverIcon(new ImageIcon("files\\Icons\\Hawk -2.png"));
        hawk.setContentAreaFilled(false);
        hawk.setBorderPainted(false);
        hawk.setToolTipText("Hawk - Reveals Everything In The Nearby Tiles");
        hawk.setLocation(890, 60);
        hawk.setSize(115, 140);
        hawk.setFocusable(false);
        hawk.addActionListener(this);
        this.add(hawk);

        key = new JButton(new ImageIcon("files\\Icons\\Key -1.png"));
        key.setRolloverIcon(new ImageIcon("files\\Icons\\Key -2.png"));
        key.setContentAreaFilled(false);
        key.setBorderPainted(false);
        key.setToolTipText("Key - Unlocks A Chest");
        key.setLocation(1050, 60);
        key.setSize(115, 140);
        key.setFocusable(false);
        key.addActionListener(this);
        this.add(key);
    }

    /**
     * @return the size
     */
    public int getInventorySize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setInventorySize(int size) {
        this.size = size;
    }

    /**
     * @return the contaningItems
     */
    public ArrayList<Object> getContaningItems() {
        return contaningItems;
    }

    public boolean containsItem(Class<?> itemType) {
        for (Object item : contaningItems) {
            if (item.getClass() == itemType) {
                return true;
            }
        }
        return false;
    }

    public Object giveItem(Class<?> itemType) {
        for (Object item : contaningItems) {
            if (item.getClass() == itemType) {
                Object itemToPass = item;
                contaningItems.remove(item);
                return itemToPass;
            }
        }
        return null;
    }

    public boolean hasOverload() {
        if ((float) contaningItems.size() >= 0.75 * size) {
            return true;
        }
        return false;
    }

    public boolean useKeyIfExist(int keyNumber) {
        for (Object item : contaningItems) {
            if (item instanceof Key) {
                if (((Key) item).getNumber() == keyNumber) {
                    contaningItems.remove(item);
                    return true;
                }
            }
        }
        return false;
    }

    public void declareState() {
        System.out.println("");
        System.out.println(".................................................................................................................");
        System.out.println("Size: " + size);
        System.out.println("Including:");
        System.out.printf("                Small Arrow      %3d                  Big Arrow        %3d                 Fire Arrow    %d\n", getNumberOfItemsOfKind(SmallArrow.class), getNumberOfItemsOfKind(BigArrow.class), getNumberOfItemsOfKind(FireArrow.class));
        System.out.printf("                Stone Breaker    %3d                  hawk             %3d                 Shovel        %d\n", getNumberOfItemsOfKind(StoneBreaker.class), getNumberOfItemsOfKind(Hawk.class), getNumberOfItemsOfKind(Shovel.class));
        System.out.printf("                bigbag           %3d                  Revive Scroll    %3d                 Key           %d\n", getNumberOfItemsOfKind(BigBag.class), getNumberOfItemsOfKind(ReviveScroll.class), getNumberOfItemsOfKind(Key.class));
        if (hasOverload()) {
            System.out.println("\033[31m Inventory has overload!\033[0m");
        }
        System.out.println("..................................................................................................................");
        System.out.println("");
    }

    private int getNumberOfItemsOfKind(Class<?> itemType) {
        int numberOfParticularItem = 0;
        for (Object item : contaningItems) {
            if (item.getClass() == itemType) {
                numberOfParticularItem++;
            }
        }
        return numberOfParticularItem;
    }

    public boolean isFull() {
        if (contaningItems.size() == size) {
            return true;
        }
        return false;
    }

    public void listKeyNumbers() {
        System.out.println("");
        System.out.println("..................................");
        System.out.println("You have the follwing keys: ");
        for (Object item : contaningItems) {
            if (item instanceof Key) {
                System.out.print(((Key) item).getNumber() + "  ");
            }
        }
        System.out.println("");
        System.out.println("..................................");
        System.out.println("");
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image background = new ImageIcon("files\\Bottom.png").getImage();
        g.drawImage(background, 0, 0, this);
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
        g.drawString("" + getInventorySize(), 10, 65);
        g.drawString("" + getNumberOfItemsOfKind(SmallArrow.class), 117, 50);
        g.drawString("" + getNumberOfItemsOfKind(BigArrow.class), 280, 50);
        g.drawString("" + getNumberOfItemsOfKind(FireArrow.class), 441, 50);
        g.drawString("" + getNumberOfItemsOfKind(StoneBreaker.class), 615, 50);
        g.drawString("" + getNumberOfItemsOfKind(Shovel.class), 776, 50);
        g.drawString("" + getNumberOfItemsOfKind(Hawk.class), 939, 50);
        g.drawString("" + getNumberOfItemsOfKind(Key.class), 1100, 50);
        g.drawString("" + getNumberOfItemsOfKind(ReviveScroll.class), 1290, 70);
        g.drawString("" + player.getHitPoint(), 1290, 110);
        g.drawString("" + player.getEnergy(), 1290, 150);
        g.drawString("" + player.getScore(), 1290, 187);
        updateButtons();
    }

    public void updateButtons() {
        if (getNumberOfItemsOfKind(SmallArrow.class) == 0) {
            smallArrow.setEnabled(false);
        } else {
            smallArrow.setEnabled(true);
        }
        if (getNumberOfItemsOfKind(BigArrow.class) == 0) {
            bigArrow.setEnabled(false);
        } else {
            bigArrow.setEnabled(true);
        }
        if (getNumberOfItemsOfKind(FireArrow.class) == 0) {
            fireArrow.setEnabled(false);
        } else {
            fireArrow.setEnabled(true);
        }
        if (getNumberOfItemsOfKind(StoneBreaker.class) == 0) {
            stoneBreaker.setEnabled(false);
        } else {
            stoneBreaker.setEnabled(true);
        }
        if (getNumberOfItemsOfKind(Shovel.class) == 0) {
            shovel.setEnabled(false);
        } else {
            shovel.setEnabled(true);
        }
        if (getNumberOfItemsOfKind(Hawk.class) == 0) {
            hawk.setEnabled(false);
        } else {
            hawk.setEnabled(true);
        }
        if (getNumberOfItemsOfKind(Key.class) == 0) {
            key.setEnabled(false);
        } else {
            key.setEnabled(true);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (drop_mode) {
            if (source == use_drop) {
                use_drop.setIcon(new ImageIcon("files\\Icons\\UseDrop - 1.png"));
                drop_mode = false;
            } else if (source == smallArrow) {
                gameControl.drop(SmallArrow.class);
            } else if (source == bigArrow) {
                gameControl.drop(BigArrow.class);
            } else if (source == fireArrow) {
                gameControl.drop(FireArrow.class);
            } else if (source == stoneBreaker) {
                gameControl.drop(StoneBreaker.class);
            } else if (source == shovel) {
                gameControl.drop(Shovel.class);
            } else if (source == hawk) {
                gameControl.drop(Hawk.class);
            } else if (source == key) {
                gameControl.drop(Key.class);
            }
        } else {
            if (source == use_drop) {
                use_drop.setIcon(new ImageIcon("files\\Icons\\UseDrop - 2.png"));
                drop_mode = true;
            } else if (source == smallArrow) {
                setItemToBeUsed(SmallArrow.class);
                gameControl.SetCursorToWeapon(SmallArrow.class);
            } else if (source == bigArrow) {
                setItemToBeUsed(BigArrow.class);
                gameControl.SetCursorToWeapon(BigArrow.class);
            } else if (source == fireArrow) {
                setItemToBeUsed(FireArrow.class);
                gameControl.SetCursorToWeapon(FireArrow.class);
            } else if (source == stoneBreaker) {
                setItemToBeUsed(null);
                giveItem(StoneBreaker.class);
                gameControl.breakWalls();
            } else if (source == shovel) {
                setItemToBeUsed(null);
                giveItem(Shovel.class);
                gameControl.showShovelDestinationChosser();
            } else if (source == hawk) {
                setItemToBeUsed(null);
//                giveItem(Hawk.class);
                gameControl.showHawkResult();
            } else if (source == key) {
                setItemToBeUsed(null);
                gameControl.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                JOptionPane.showMessageDialog(null, "You Have The Following Keys: \n" + getListOfKeys() + "\n Click On The Chest To Open it.", "Keys", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    /**
     * @return the ItemToBeUsed
     */
    public Class<?> getItemToBeUsed() {
        return ItemToBeUsed;
    }

    /**
     * @param ItemToBeUsed the ItemToBeUsed to set
     */
    public void setItemToBeUsed(Class<?> ItemToBeUsed) {
        this.ItemToBeUsed = ItemToBeUsed;
    }

    private String getListOfKeys() {
        String result = "";
        for (Object item : contaningItems) {
            if (item instanceof Key) {
                result += "" + ((Key) item).getNumber() + " - ";
            }
        }
        result = result.substring(0, result.length() - 3);
        return result;
    }
}
