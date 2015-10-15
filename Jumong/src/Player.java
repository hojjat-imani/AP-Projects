
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Hojjat
 */
public class Player {

    private final String name;
    private final int number;
    private int x_position;
    private int y_position;
    private Inventory inventory;
    private int energy;
    private int hitPoint;
    private int score;
    private boolean isAlive;

    public Player(int number, String name, int x_startPosition, int y_startPosition, GameControl gameControl) {
        this.name = name;
        this.number = number;
        x_position = x_startPosition;
        y_position = y_startPosition;
        inventory = new Inventory(this, gameControl);
        energy = 100;
        hitPoint = 100;
        score = 0;
        isAlive = true;
    }

    public boolean moveUpIfPossible(Field field) {
        if (getX_position() - 1 < 0) {
            return false;
        }
        if (!field.getTile(x_position - 1, y_position).isWall()) {
            x_position -= 1;
            return true;
        }
        return false;
    }

    public boolean moveDownIfPossible(Field field) {
        if (getX_position() + 1 > 24) {
            return false;
        }
        if (!field.getTile(x_position + 1, y_position).isWall()) {
            x_position += 1;
            return true;
        }
        return false;
    }

    public boolean moveRightIfPossible(Field field) {
        if (getY_position() + 1 > 24) {
            return false;
        }
        if (!field.getTile(x_position, y_position + 1).isWall()) {
            y_position += 1;
            return true;
        }
        return false;
    }

    public boolean moveLeftIfPossible(Field field) {
        if (getY_position() - 1 < 0) {
            return false;
        }
        if (!field.getTile(x_position, y_position - 1).isWall()) {
            y_position -= 1;
            return true;
        }
        return false;
    }

    public void applyPotions(Field field) {
        score += field.getTile(x_position, y_position).getGoldAmountAndSetToZero();
    }

    public void applyDamages(Field field) {
//    public void applyDamages(Field field, Player[] players) {
        if (getInventory().hasOverload()) {
            setEnergy(getEnergy() - 2);
        } else {
            setEnergy(getEnergy() - 1);
        }
        setHitPoint(getHitPoint() - 10 * field.getNumberOfEnemysInTile(x_position, y_position));
//        for (Player player : players) {
//            if (player != this && player.getX_position() == this.x_position && player.getY_position() == this.y_position) {
//                this.hitPoint -= 10;
//            }
//        }
        if (getEnergy() < 1) {
            isAlive = false;
        } else if (getHitPoint() < 1) {
            if (getInventory().containsItem(ReviveScroll.class)) {
                ReviveScroll reviveScroll = (ReviveScroll) getInventory().giveItem(ReviveScroll.class);
                reviveScroll.use(this);
                System.out.println("                                      \033[31mLethal damage! You was nearly dead!! \033[32mA revive scroll saved you!\033[0m");
            } else {
                hitPoint = 0;
                isAlive = false;
            }
        }
        System.out.println("===========================================================================================================================");
    }

    public void declareState() {
        System.out.println("");
        System.out.println("......................................");
        System.out.println("Position: ( " + x_position + " , " + y_position + " )");
        System.out.print("Energy: " + getEnergy() + "  ");
        System.out.println((getEnergy() > 70) ? "\033[42mgood" : ((getEnergy() > 35) ? "\033[43mmediocre" : "\033[41mlow"));
        System.out.print("\033[0mHit Point: " + getHitPoint() + "  ");
        System.out.println((getHitPoint() > 70) ? "\033[42mgood" : ((getHitPoint() > 35) ? "\033[43mmediocre" : "\033[41mlow"));
        System.out.println("\033[0mScore: " + getScore());
        System.out.println("......................................");
        System.out.println("");
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

    public void pickItemIfExist(Class<?> itemType, Field field) {
        if (!field.getTile(x_position, y_position).containsItem(itemType)) {
            System.out.println("                                      \033[31mNo such item is available in current tile!!\033[0m");
        } else {
            if (!inventory.isFull()) {
                Object item = field.getTile(x_position, y_position).giveItemToPick(itemType);
                getInventory().getContaningItems().add(item);
                System.out.println("                                      \033[34m" + item + "\033[0m added to inventory!");
            } else {
                System.out.println("                                      \033[31mInventory is full!!\033[0m");
            }
        }
    }

    public void dropItemIfExist(Class<?> itemType, Field field) {
        if (!inventory.containsItem(itemType)) {
            System.out.println("                                      \033[31mNo such item is available in your inventory!!\033[0m");
        } else {
            Object item = getInventory().giveItem(itemType);
            field.getTile(x_position, y_position).addItem(item);
            System.out.println("                                      \033[34m" + item + "\033[0m dropped!!");
        }
    }

    /**
     *
     * @param itemType
     * @param field
     * @return true if players position changes and false otherwise
     */
    public boolean useItemIfExist(Class<?> itemType, Field field) {
        if (!inventory.containsItem(itemType)) {
            System.out.println("                                      \033[31mYou do not have this item!!\033[0m");
        } else {
            Object item = getInventory().giveItem(itemType);
            if (item instanceof StoneBreaker) {
                ((StoneBreaker) item).use(field, x_position, y_position);
            } else if (item instanceof Hawk) {
                ((Hawk) item).use(field, x_position, y_position);
            } else if (item instanceof Shovel) {
                if (((Shovel) item).use(field, this)) {
                    return true;
                }
            } else if (item instanceof BigBag) {
                ((BigBag) item).use(getInventory());
            } else {
                System.out.println("revive scroll is called!!");
            }
        }
        return false;
    }

    public void killEnemy(Class<?> weaponType, int enemyNumber, Field field) {
        if (!inventory.containsItem(weaponType)) {
            System.out.println("                                      \033[31mYou dont have this weapon!!\033[0m");
        } else if (field.getNumberOfEnemysInTile(x_position, y_position) == 0) {
            System.out.println("                                      \033[31mNo Enemy!!\033[0m");
        } else {
            Weapon weapon = (Weapon) getInventory().giveItem(weaponType);
            weapon.useAgainstEnemy(field, x_position, y_position, enemyNumber);
        }
    }

    public void unlockChestIfExistAndHaveAkey(Field field) {
        if (!field.getTile(x_position, y_position).containsItem(Chest.class)) {
            System.out.println("                                      \033[31mNo Chest is in this Tile!!\033[0m");
        } else if (!inventory.containsItem(Key.class)) {
            System.out.println("                                      \033[31mNo Key!!\033[0m");
        } else {
            int chestNumber = field.getTile(x_position, y_position).getChestNumber();
            if (getInventory().useKeyIfExist(chestNumber)) {
                field.getTile(x_position, y_position).unlockTheChest();
            } else {
                System.out.println("                                      \033[31mNone of your keys open this chest!!\033[0m");
            }
        }
    }

    public void setPosition(int newX_position, int newY_position) {
        x_position = newX_position;
        y_position = newY_position;
    }

    public void declareInventoryState() {
        getInventory().declareState();
    }

    public void whatKeysYouHave() {
        if (!inventory.containsItem(Key.class)) {
            System.out.println("                                      \033[31mYou do not have any key!!\033[0m");
        } else {
            getInventory().listKeyNumbers();
        }
    }

    public boolean devSetPosition(Field field) {
        Scanner inputReader = new Scanner(System.in);
        int new_x_position, new_y_position;
        System.out.println("Enter new Position: \033[47m(Ex: (10 , 17) -> 10 17)\033[0m");
        System.out.print("               -> ");
        try {
            new_x_position = inputReader.nextInt();
            new_y_position = inputReader.nextInt();
            inputReader.nextLine();
        } catch (Exception exception) {
            System.out.println("                                      \033[31minvalid!!\033[0m");
            inputReader.nextLine();
            return false;
        }
        if (new_x_position < 0 || new_x_position >= 25 || new_y_position < 0 || new_y_position >= 25) {
            System.out.println("                                      \033[31mOut of field!!\033[0m");
        } else if (field.getTile(new_x_position, new_y_position).isWall()) {
            System.out.println("                                      \033[31mDestination is a wall!!\033[0m");
        } else {
            x_position = new_x_position;
            y_position = new_y_position;
            System.out.println("                                      \033[32mDone!!\033[0m");
            return true;
        }
        return false;
    }

    /**
     * @param hitPoint the hitPoint to set
     */
    public void setHitPoint(int hitPoint) {
        this.hitPoint = hitPoint;
    }

    /**
     * @return the isAlive
     */
    public boolean IsAlive() {
        return isAlive;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the number
     */
    public int getNumber() {
        return number;
    }

    public boolean shootContenderIfPossible(Class<?> weaponType, Player contender) {
        if (!contender.isAlive) {
            System.out.println("                                      \033[31mHe is dead!!\033[0m");
        } else if (contender == this) {
            System.out.println("                                      \033[31mWanna kill yourself?! Do it outside of game!!\033[0m");
        } else if (contender.getX_position() != this.x_position || contender.getY_position() != this.getY_position()) {
            System.out.println("                                      \033[31mHe is not here!!\033[0m");
        } else if (!inventory.containsItem(weaponType)) {
            System.out.println("                                      \033[31mYou dont have this weapon!!\033[0m");
        } else {
            Weapon weapon = (Weapon) getInventory().giveItem(weaponType);
            weapon.useAgainstPlayer(contender);
            return true;
        }
        return false;
    }

    public void beShooted() {
        hitPoint -= 10;
        System.out.println("                                      \033[32mNice shoot!!\033[0m");
        if (getHitPoint() < 1) {
            System.out.println("                                      \033[31mYou killed him!!\033[0m");
            System.out.println("---------------------------------------------------------------------------------------------------------------------------");
            System.out.println("                                      \033[31mGame is over for you!! you are dead " + name + "!\033[0m");
            System.out.println("");
            System.out.print("Final Status:");
            declareState();
            System.out.println("---------------------------------------------------------------------------------------------------------------------------");
            isAlive = false;
        }
    }

    /**
     * @return the energy
     */
    public int getEnergy() {
        return energy;
    }

    /**
     * @param energy the energy to set
     */
    public void setEnergy(int energy) {
        this.energy = energy;
    }

    /**
     * @return the hitPoint
     */
    public int getHitPoint() {
        return hitPoint;
    }

    /**
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * @return the inventory
     */
    public Inventory getInventory() {
        return inventory;
    }
}
