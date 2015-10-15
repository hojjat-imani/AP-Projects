
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.ImageIcon;
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
public class Tile extends JPanel {

    private int x_position;
    private int y_position;
    private ArrayList<Object> containingItems;
    private boolean isWall;
    private int goldAmount;
    private int addedWeapons;
    private String backgroundCode;
    private GameControl gameControl;
    private boolean visited;

    private ArrayList<Point> freeItemLocations;
    private ArrayList<Point> freeEnemyLocations;

    public Tile(int x_position, int y_position, GameControl gameControl) {
        this.setBackground(Color.pink);
        this.setSize(798, 568);
        this.setLayout(null);

        this.visited = false;
        this.gameControl = gameControl;
        freeItemLocations = new ArrayList<>();
        freeItemLocations.add(new Point(160, 110));
        freeItemLocations.add(new Point(160, 200));
        freeItemLocations.add(new Point(160, 300));
        freeItemLocations.add(new Point(160, 385));
        freeItemLocations.add(new Point(290, 110));
        freeItemLocations.add(new Point(290, 200));
        freeItemLocations.add(new Point(290, 300));
        freeItemLocations.add(new Point(290, 385));
        freeItemLocations.add(new Point(430, 110));
        freeItemLocations.add(new Point(430, 200));
        freeItemLocations.add(new Point(430, 300));
        freeItemLocations.add(new Point(430, 385));
        freeItemLocations.add(new Point(560, 110));
        freeItemLocations.add(new Point(560, 200));
        freeItemLocations.add(new Point(560, 300));
        freeItemLocations.add(new Point(560, 385));
        freeEnemyLocations = new ArrayList<>();
        freeEnemyLocations.add(new Point(680, 10));
        freeEnemyLocations.add(new Point(680, 110));
        freeEnemyLocations.add(new Point(680, 200));
        this.x_position = x_position;
        this.y_position = y_position;
        containingItems = new ArrayList<Object>();
        isWall = true;
        goldAmount = 0;

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        Image background = new ImageIcon("files\\map\\" + backgroundCode + ".jpeg").getImage();
        g.drawImage(background, 0, 0, 798, 568, this);
    }

    public boolean isWall() {
        return isWall;
    }

    public void setIsWall(boolean isWall) {
        this.isWall = isWall;
    }

    public void declareState() {
//        System.out.println("---------------------------------------------------------------------------------------------------------------------------");
        System.out.println("position: ( " + getX_position() + " , " + getY_position() + " )");
        if (!isWall) {
            System.out.printf("                Small Arrow      %3d                  Big Arrow        %3d                 Fire Arrow    %d\n", getNumberOfItemsOfKind(SmallArrow.class), getNumberOfItemsOfKind(BigArrow.class), getNumberOfItemsOfKind(FireArrow.class));
            System.out.printf("                Stone Breaker    %3d                  hawk             %3d                 Shovel        %d\n", getNumberOfItemsOfKind(StoneBreaker.class), getNumberOfItemsOfKind(Hawk.class), getNumberOfItemsOfKind(Shovel.class));
            System.out.printf("                bigbag           %3d                  Revive Scroll    %3d                 Key           %d\n", getNumberOfItemsOfKind(BigBag.class), getNumberOfItemsOfKind(ReviveScroll.class), getNumberOfItemsOfKind(Key.class));
            System.out.println("                Chest              " + (containsItem(Chest.class) ? ("Yes" + "  (num: " + getChestNumber() + ")") : "No"));
        } else {
            System.out.println("                                      \033[31mThis tile is wall!!\033[0m");
        }
        System.out.println("");
    }

    public void unlockTheChest() {
        for (Object item : containingItems) {
            if (item instanceof Chest) {
                ((Chest) item).printContainigItems();
                ArrayList<Object> chestContaining = ((Chest) item).getIncluding();
                for (Object chestItem : chestContaining) {
                    this.containingItems.add(chestItem);
                }
                this.containingItems.remove(item);
                System.out.println("                                      \033[32mChest unlocked and its containing added to Tile!\033[0m");
                return;
            }
        }
    }

    public boolean containsItem(Class<?> itemType) {
        for (Object item : containingItems) {
            if (item.getClass() == itemType) {
                return true;
            }
        }
        return false;
    }

    public Object giveItemToPick(Class<?> itemType) {
        for (Object item : containingItems) {
            if (item.getClass() == itemType) {
                Object itemToPass = item;
                containingItems.remove(item);
                this.remove((JPanel) item);
                return itemToPass;
            }
        }
        return null;
    }

    public void addEnemy(Enemy enemy) {
        Point location = freeEnemyLocations.get((int) (Math.random() * freeEnemyLocations.size()));
        freeEnemyLocations.remove(location);
        enemy.setLocation(location);
        this.add(enemy);
    }

    public void giveEnemy(Enemy enemy) {
        freeEnemyLocations.add(enemy.getLocation());
        this.remove(enemy);
    }

    public void addItem(Object item) {
        containingItems.add(item);
        Point location = freeItemLocations.get((int) (Math.random() * freeItemLocations.size()));
        freeItemLocations.remove(location);
        ((JPanel) item).setLocation(location);
        this.add((JPanel) item);
        addedWeapons++;
    }

    public Object giveItem(Object itemToReturn) {
        for (Object item : containingItems) {
            if (item == itemToReturn) {
                freeItemLocations.add(((JPanel) item).getLocation());
                this.remove((JPanel) item);
                return item;
            }
        }
        return null;
    }

    public void addWeapon(Object item) {
        containingItems.add(item);
        Point location = freeItemLocations.get((int) (Math.random() * freeItemLocations.size()));
        freeItemLocations.remove(location);
        ((JPanel) item).setLocation(location);
        this.add((JPanel) item);
        addedWeapons++;
    }

    public void addPanels() {
        for (Object item : containingItems) {
            if (item instanceof SmallArrow || item instanceof BigArrow || item instanceof FireArrow) {
                this.add((JPanel) item);
            }
        }

    }

    /**
     * @return the goldAmount And set it as 0
     */
    public int getGoldAmountAndSetToZero() {
        int goldAmountToPass = goldAmount;
        setGoldAmount(0);
        return goldAmountToPass;
    }

    public int giveNumberOfPotionsAndUse(Class<?> potionType) {
        int numberOfPotions = 0;
        Iterator<Object> it = containingItems.iterator();
        while (it.hasNext()) {
            if (it.next().getClass() == potionType) {
                it.remove();
                numberOfPotions++;
            }
        }
        return numberOfPotions;
    }

    public int getNumberOfItemsOfKind(Class<?> itemType) {
        int numberOfParticularItem = 0;
        for (Object item : containingItems) {
            if (item.getClass() == itemType) {
                numberOfParticularItem++;
            }
        }
        return numberOfParticularItem;
    }

    public int getChestNumber() {
        for (Object item : containingItems) {
            if (item instanceof Chest) {
                return ((Chest) item).getNumber();
            }
        }
        return 0;
    }

    /**
     * @param goldAmount the goldAmount to set
     */
    public void setGoldAmount(int goldAmount) {
        this.goldAmount = goldAmount;
    }

    public void tellAboutPotionsAndGoldAmount() {
        if (!isWall) {
            System.out.println("Gold in Tile: " + goldAmount);
            System.out.print("Potions: ");
            int numberOfBigHealthPotions = getNumberOfItemsOfKind(BigHealthPotion.class);
            int numberOfSmallHealthPotions = getNumberOfItemsOfKind(SmallHealthPotion.class);
            int numberOfEnergyPotions = getNumberOfItemsOfKind(EnergyPotion.class);
            if (numberOfBigHealthPotions + numberOfSmallHealthPotions + numberOfEnergyPotions > 0) {
                System.out.println(((numberOfBigHealthPotions > 0) ? numberOfBigHealthPotions + " Big Health Potion   " : "") + ((numberOfSmallHealthPotions > 0) ? numberOfSmallHealthPotions + " Small Health Potion   " : "") + ((numberOfEnergyPotions > 0) ? numberOfEnergyPotions + " Energy Potion" : ""));
            } else {
                System.out.println("No potion");
            }
            System.out.println("");
        }
    }

    /**
     * @return the x_position
     */
    public int getX_position() {
        return x_position;
    }

    /**
     * @return the y_position
     */
    public int getY_position() {
        return y_position;
    }

    /**
     * @param backgroundCode the backgroundCode to set
     */
    public void setBackgroundCode(String backgroundCode) {
        this.backgroundCode = backgroundCode;
    }

    public int getNumberOfFreeLocations() {
        return freeItemLocations.size();
    }

    /**
     * @return the visited
     */
    public boolean isVisited() {
        return visited;
    }

    /**
     * @param vidited the visited to set
     */
    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public boolean isFull() {
        if (freeItemLocations.size() == 0) {
            return true;
        }
        return false;
    }

    public String getState() {
        String tileState = "";
        if (this.isWall()) {
            tileState = "Is Wall!! \n\n\n";
        } else {
            tileState = "Items In Tile: ";
            for (Object item : containingItems) {
                tileState += item.toString() + ", ";
            }
        }
        return tileState;
    }
}
