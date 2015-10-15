

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import static java.lang.Math.E;
import java.util.ArrayList;
import javax.print.attribute.standard.Media;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import sun.applet.Main;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Hojjat
 */
public class GameControl extends JFrame implements KeyListener, MouseListener {

    private Field field;
    private static Tile tile;
    private Player player;
    private static Inventory inventory;
    private PlayerState playerState;
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private Cursor smallArrowCursor = Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon("files\\Icons\\SmallArrow -2.png").getImage(), new Point(0, 0), "smallArrowCursor");
    private Cursor bigArrowCursor = Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon("files\\Icons\\BigArrow -2.png").getImage(), new Point(0, 0), "bigArrowCursor");
    private Cursor fireArrowCursor = Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon("files\\Icons\\FireArrow -2.png").getImage(), new Point(0, 0), "fireArrowCursor");
    private JFrame ShovelDestinationChooser;

    public GameControl(String name, int difficulty) {
        playSound();
//        play();
        this.setSize(screenSize);
        System.out.println("" + screenSize);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setUndecorated(true);
        this.setResizable(false);
        this.setLayout(null);
        this.addMouseListener(this);

        player = new Player(1, name, 0, 0, this);
        playerState = new PlayerState(player);
        playerState.setLocation((int) screenSize.getWidth() - 400, 0);
        inventory = player.getInventory();
        inventory.setLocation(0, 568);
        field = new Field(difficulty, player, this);
        field.setLocation(798, 0);
        tile = field.getTile(player.getX_position(), player.getY_position());
        setKnownTiles(tile);
        this.add(field);
        this.add(playerState);
        System.out.println("1");
        this.add(inventory);
        System.out.println("2");
        this.add(tile);
        addKeyListener(this);
        System.out.println("3");
    }

//    public void start() {
//        int numberOfPlayers = players.length;
//        field.generateMaze(numberOfPlayers);
//        if (players.length == 1) {
//            runSinglePlayerGameLoop();
//        } else {
//            runMultiPlayerGameLoop(numberOfPlayers);
//        }
//    }
//
//    private void runSinglePlayerGameLoop() {
//        int players_last_x_position, players_last_y_position;
//        while (true) {
//            players_last_x_position = players[0].getX_position();
//            players_last_y_position = players[0].getY_position();
//            field.getTile(players[0].getX_position(), players[0].getY_position()).declareState();
//            field.tellAboutEnemies(players[0].getX_position(), players[0].getY_position());
//            doSomethingAndMove(players[0]);
//            field.moveEnemys(players_last_x_position, players_last_y_position);
//            players[0].applyPotions(field);
//            players[0].applyDamages(field, players);
//            if (checkWinning(players[0])) {
//                return;
//            }
//            if (checkLosting(players[0])) {
//                return;
//            }
//        }
//
//    }
//    private void runMultiPlayerGameLoop(int numberOfPlayers) {
//        int turn = 0;
//        while (true) {
//            if (!players[turn].IsAlive()) {
//                turn = (turn + 1) % numberOfPlayers;
//                continue;
//            }
//            showTurn(players[turn]);
//            field.getTile(players[turn].getX_position(), players[turn].getY_position()).declareState();
//            field.tellAboutEnemies(players[turn].getX_position(), players[turn].getY_position());
//            tellAboutOtherPlayers(players[turn]);
//            doSomethingAndMove(players[turn]);
//            players[turn].applyPotions(field);
//            players[turn].applyDamages(field, players);
//            if (checkWinning(players[turn])) {
//                return;
//            }
//            checkLosting(players[turn]);
//            if (allPlayersAreDead(numberOfPlayers)) {
//                return;
//            }
//            turn = (turn + 1) % numberOfPlayers;
//        }
//    }
//    private void showTurn(Player player) {
//        System.out.println(">> Turn by: " + player.getNumber() + " " + player.getName());
//        System.out.println("....................................");
//    }
//    private boolean checkWinning(Player player) {
//        if (player.getX_position() == field.getX_finishPosition() && player.getY_position() == field.getY_finish_Position()) {
//            System.out.println("");
//            System.out.println("                                             \033[32mYOU WOOOOOON " + player.getName() + " !!!\033[0m");
//            System.out.println("");
//            System.out.print("Final Status:");
//            player.declareState();
//            return true;
//        }
//        return false;
//    }
//    private boolean checkLosting(Player player) {
//        if (!player.IsAlive()) {
//            field.tellAboutEnemies(player.getX_position(), player.getY_position());
//            tellAboutOtherPlayers(player);
//            System.out.println("");
//            System.out.println("                                      \033[31m>> Game Over" + ((players.length != 1) ? " for you " : "") + "!!  You are dead " + player.getName() + "!\033[0m");
//            System.out.println("");
//            System.out.print("Final Status:");
//            player.declareState();
//            System.out.println("===========================================================================================================================");
//            return true;
//        }
//        return false;
//    }
//    private boolean allPlayersAreDead(int numberOfPlayers) {
//        int deadPlayers = 0;
//        for (int i = 0; i < numberOfPlayers; i++) {
//            if (!players[i].IsAlive()) {
//                deadPlayers++;
//            }
//        }
//        if (deadPlayers == numberOfPlayers) {
//            System.out.println("                                      \033[31mGame Over!! No winner!\033[0m");
//            return true;
//        }
//        return false;
//    }
//    private void tellAboutOtherPlayers(Player player) {
//        for (Player otherPlayer : players) {
//            if (otherPlayer != player && otherPlayer.getX_position() == player.getX_position() && otherPlayer.getY_position() == player.getY_position()) {
//                System.out.println("                                      \033[31mPlayer" + otherPlayer.getNumber() + " (" + otherPlayer.getName() + ") is also here!!\033[0m");
//            }
//        }
//    }
//    private void listPlayersPositions() {
//        System.out.println("......................................................................................");
//        for (Player player : players) {
//            System.out.println("");
//            System.out.println("Player" + player.getNumber() + " (" + player.getName() + ") -> position: (" + player.getX_position() + " , " + player.getY_position() + ")   Energy: " + player.getEnergy() + "   Hit Point: " + player.getHitPoint() + "   Score: " + player.getScore());
//        }
//        System.out.println("");
//        System.out.println("......................................................................................");
//    }
    private void doSomethingAndMove(Player player) {
        int numberOfShootCommadUsageInCurrentTurn = 0;
        boolean playerMoved = false;
        boolean inputIsInvalid = false;
        do {
            if (inputIsInvalid) {
                System.out.println("                                      \033[31mInvalid input!\033[0m");
                System.out.print("Try again:     -> ");
                inputIsInvalid = false;
            } else {
                System.out.print("What to do??   -> ");
            }
            String input = inputReader.nextLine();
            if (input.equalsIgnoreCase("up")) {
                if (player.moveUpIfPossible(field)) {
                    playerMoved = true;
                }
            } else if (input.equalsIgnoreCase("down")) {
                if (player.moveDownIfPossible(field)) {
                    playerMoved = true;
                }
            } else if (input.equalsIgnoreCase("left")) {
                if (player.moveLeftIfPossible(field)) {
                    playerMoved = true;
                }
            } else if (input.equalsIgnoreCase("right")) {
                if (player.moveRightIfPossible(field)) {
                    playerMoved = true;
                }
            } else if (input.equalsIgnoreCase("inventory")) {
                player.declareInventoryState();
            } else if (input.equalsIgnoreCase("status")) {
                player.declareState();
            } else if (input.equalsIgnoreCase("unlock")) {
                player.unlockChestIfExistAndHaveAkey(field);
            } else if (input.equalsIgnoreCase("keys")) {
                player.whatKeysYouHave();
            } else if (input.equalsIgnoreCase("manual")) {
                Main.showTheManual();
            } else if (input.equalsIgnoreCase("tile")) {
                field.getTile(player.getX_position(), player.getY_position()).declareState();
                field.tellAboutEnemies(player.getX_position(), player.getY_position());
            } else if (input.toLowerCase().startsWith("pick")) {
                if (input.equalsIgnoreCase("pick<smallArrow>") || input.equalsIgnoreCase("pick<SA>")) {
                    player.pickItemIfExist(SmallArrow.class, field);
                } else if (input.equalsIgnoreCase("pick<bigArrow>") || input.equalsIgnoreCase("pick<BA>")) {
                    player.pickItemIfExist(BigArrow.class, field);
                } else if (input.equalsIgnoreCase("pick<fireArrow>") || input.equalsIgnoreCase("pick<FA>")) {
                    player.pickItemIfExist(FireArrow.class, field);
                } else if (input.equalsIgnoreCase("pick<stoneBreaker>") || input.equalsIgnoreCase("pick<SB>")) {
                    player.pickItemIfExist(StoneBreaker.class, field);
                } else if (input.equalsIgnoreCase("pick<key>") || input.equalsIgnoreCase("pick<K>")) {
                    player.pickItemIfExist(Key.class, field);
                } else if (input.equalsIgnoreCase("pick<hawk>") || input.equalsIgnoreCase("pick<H>")) {
                    player.pickItemIfExist(Hawk.class, field);
                } else if (input.equalsIgnoreCase("pick<shovel>") || input.equalsIgnoreCase("pick<S>")) {
                    player.pickItemIfExist(Shovel.class, field);
                } else if (input.equalsIgnoreCase("pick<reviveScroll>") || input.equalsIgnoreCase("pick<RS>")) {
                    player.pickItemIfExist(ReviveScroll.class, field);
                } else if (input.equalsIgnoreCase("pick<bigBag>") || input.equalsIgnoreCase("pick<BB>")) {
                    player.pickItemIfExist(BigBag.class, field);
                } else {
                    inputIsInvalid = true;
                }
            } else if (input.toLowerCase().startsWith("drop")) {
                if (input.equalsIgnoreCase("drop<smallArrow>") || input.equalsIgnoreCase("drop<SA>")) {
                    player.dropItemIfExist(SmallArrow.class, field);
                } else if (input.equalsIgnoreCase("drop<bigArrow>") || input.equalsIgnoreCase("drop<BA>")) {
                    player.dropItemIfExist(BigArrow.class, field);
                } else if (input.equalsIgnoreCase("drop<fireArrow>") || input.equalsIgnoreCase("drop<FA>")) {
                    player.dropItemIfExist(FireArrow.class, field);
                } else if (input.equalsIgnoreCase("drop<stoneBreaker>") || input.equalsIgnoreCase("drop<SB>")) {
                    player.dropItemIfExist(StoneBreaker.class, field);
                } else if (input.equalsIgnoreCase("drop<key>") || input.equalsIgnoreCase("drop<K>")) {
                    player.dropItemIfExist(Key.class, field);
                } else if (input.equalsIgnoreCase("drop<hawk>") || input.equalsIgnoreCase("drop<H>")) {
                    player.dropItemIfExist(Hawk.class, field);
                } else if (input.equalsIgnoreCase("drop<shovel>") || input.equalsIgnoreCase("drop<S>")) {
                    player.dropItemIfExist(Shovel.class, field);
                } else if (input.equalsIgnoreCase("drop<reviveScroll>") || input.equalsIgnoreCase("dop<RS>")) {
                    player.dropItemIfExist(ReviveScroll.class, field);
                } else if (input.equalsIgnoreCase("drop<bigBag>") || input.equalsIgnoreCase("drop<BB>")) {
                    player.dropItemIfExist(BigBag.class, field);
                } else {
                    inputIsInvalid = true;
                }
            } else if (input.toLowerCase().startsWith("use")) {
                if (input.equalsIgnoreCase("use<stoneBreaker>") || input.equalsIgnoreCase("use<SB>")) {
                    player.useItemIfExist(StoneBreaker.class, field);
                } else if (input.equalsIgnoreCase("use<hawk>") || input.equalsIgnoreCase("use<H>")) {
                    player.useItemIfExist(Hawk.class, field);
                } else if (input.equalsIgnoreCase("use<shovel>") || input.equalsIgnoreCase("use<S>")) {
                    if (player.useItemIfExist(Shovel.class, field)) {
                        playerMoved = true;
                    }
                } else if (input.equalsIgnoreCase("use<reviveScroll>")) {
                    player.useItemIfExist(ReviveScroll.class, field);
                } else if (input.equalsIgnoreCase("use<bigBag>") || input.equalsIgnoreCase("use<BB>")) {
                    player.useItemIfExist(BigBag.class, field);
                } else {
                    inputIsInvalid = true;
                }
            } else if (input.toLowerCase().startsWith("kill")) {
                if (input.toLowerCase().startsWith("kill<smallarrow>")) {
                    if (input.length() < 17) {
                        inputIsInvalid = true;
                    } else if (Character.isDigit(input.charAt(17))) {
                        player.killEnemy(SmallArrow.class, Character.digit(input.charAt(17), 10), field);
                    } else {
                        inputIsInvalid = true;
                    }
                } else if (input.toLowerCase().startsWith("kill<sa>")) {
                    if (input.length() < 9) {
                        inputIsInvalid = true;
                    } else if (Character.isDigit(input.charAt(9))) {
                        player.killEnemy(SmallArrow.class, Character.digit(input.charAt(9), 10), field);
                    } else {
                        inputIsInvalid = true;
                    }
//                } else if (input.toLowerCase().startsWith("kill<bigarrow>")) {
//                    if (input.length() < 15) {
//                        inputIsInvalid = true;
//                    } else if (Character.isDigit(input.charAt(15))) {
//                        player.killEnemy(BigArrow.class, Character.digit(input.charAt(15), 10), field);
//                    } else {
//                        inputIsInvalid = true;
//                    }
//                } else if (input.toLowerCase().startsWith("kill<ba>")) {
//                    if (input.length() < 9) {
//                        inputIsInvalid = true;
//                    } else if (Character.isDigit(input.charAt(9))) {
//                        player.killEnemy(BigArrow.class, Character.digit(input.charAt(9), 10), field);
//                    } else {
//                        inputIsInvalid = true;
//                    }
                } else if (input.equalsIgnoreCase("kill<bigArrow>") || input.equalsIgnoreCase("kill<BA>")) {
                    player.killEnemy(BigArrow.class, 1, field);
                } else if (input.toLowerCase().startsWith("kill<firearrow>")) {
                    if (input.length() < 16) {
                        inputIsInvalid = true;
                    } else if (Character.isDigit(input.charAt(16))) {
                        player.killEnemy(FireArrow.class, Character.digit(input.charAt(16), 10), field);
                    } else {
                        inputIsInvalid = true;
                    }
                } else if (input.toLowerCase().startsWith("kill<fa>")) {
                    if (input.length() < 9) {
                        inputIsInvalid = true;
                    } else if (Character.isDigit(input.charAt(9))) {
                        player.killEnemy(FireArrow.class, Character.digit(input.charAt(9), 10), field);
                    } else {
                        inputIsInvalid = true;
                    }
                } else {
                    inputIsInvalid = true;
                }
            } else if (input.toLowerCase().startsWith("dev")) {
                if (input.equalsIgnoreCase("dev<maze>") || input.equalsIgnoreCase("dev<M>")) {
                    field.devShowMaze();
                } else if (input.equalsIgnoreCase("dev<enemy>") || input.equalsIgnoreCase("dev<E>")) {
                    field.devShowEnemies();
                } else if (input.equalsIgnoreCase("dev<player>") || input.equalsIgnoreCase("dev<P>")) {
//                    listPlayersPositions();
                } else if (input.equalsIgnoreCase("dev<shovel>") || input.equalsIgnoreCase("dev<S>")) {
                    field.devShowItemsPlace(Shovel.class);
                } else if (input.equalsIgnoreCase("dev<stoneBreaker>") || input.equalsIgnoreCase("dev<SB>")) {
                    field.devShowItemsPlace(StoneBreaker.class);
                } else if (input.equalsIgnoreCase("dev<hawk>") || input.equalsIgnoreCase("dev<H>")) {
                    field.devShowItemsPlace(Hawk.class);
                } else if (input.equalsIgnoreCase("dev<bigBag>") || input.equalsIgnoreCase("dev<BB>")) {
                    field.devShowItemsPlace(BigBag.class);
                } else if (input.equalsIgnoreCase("dev<smallArrow>") || input.equalsIgnoreCase("dev<SA>")) {
                    field.devShowItemsPlace(SmallArrow.class);
                } else if (input.equalsIgnoreCase("dev<bigArrow>") || input.equalsIgnoreCase("dev<BA>")) {
                    field.devShowItemsPlace(BigArrow.class);
                } else if (input.equalsIgnoreCase("dev<fireArrow>") || input.equalsIgnoreCase("dev<FA>")) {
                    field.devShowItemsPlace(FireArrow.class);
                } else if (input.equalsIgnoreCase("dev<key>") || input.equalsIgnoreCase("dev<K>")) {
                    field.devShowItemsPlace(Key.class);
                } else if (input.equalsIgnoreCase("dev<chest>") || input.equalsIgnoreCase("dev<C>")) {
                    field.devShowItemsPlace(Chest.class);
                } else if (input.equalsIgnoreCase("dev<reviveScroll>") || input.equalsIgnoreCase("dev<RS>")) {
                    field.devShowItemsPlace(ReviveScroll.class);
                } else if (input.equalsIgnoreCase("dev<smallHealthPotion>") || input.equalsIgnoreCase("dev<SHP>")) {
                    field.devShowItemsPlace(SmallHealthPotion.class);
                } else if (input.equalsIgnoreCase("dev<bigHealthPotion>") || input.equalsIgnoreCase("dev<BHP>")) {
                    field.devShowItemsPlace(BigHealthPotion.class);
                } else if (input.equalsIgnoreCase("dev<energyPotion>") || input.equalsIgnoreCase("dev<EP>")) {
                    field.devShowItemsPlace(EnergyPotion.class);
                } else if (input.equalsIgnoreCase("dev<setPosition>") || input.equalsIgnoreCase("dev<SP>")) {
                    if (player.devSetPosition(field)) {
                        playerMoved = true;
                    }
                } else {
                    inputIsInvalid = true;
                }
//            } else if (input.toLowerCase().startsWith("shoot")) {
//                if (players.length == 1) {
//                    System.out.println("                                      \033[31mShooting is just for multiplayer game!!\033[0m");
//                } else if (numberOfShootCommadUsageInCurrentTurn >= 3) {
//                    System.out.println("                                      \033[31mYou can not shoot others more than 3 times in one turn!!\033[0m");
//                } else if (input.toLowerCase().startsWith("shoot<smallarrow>")) {
//                    if (input.length() < 18) {
//                        inputIsInvalid = true;
//                    } else if (Character.isDigit(input.charAt(18)) && Character.digit(input.charAt(18), 10) <= players.length) {
//                        if (player.shootContenderIfPossible(SmallArrow.class, players[Character.digit(input.charAt(18), 10) - 1])) {
//                            numberOfShootCommadUsageInCurrentTurn++;
//                        }
//                    } else {
//                        inputIsInvalid = true;
//                    }
//                } else if (input.toLowerCase().startsWith("shoot<sa>")) {
//                    if (input.length() < 10) {
//                        inputIsInvalid = true;
//                    } else if (Character.isDigit(input.charAt(10)) && Character.digit(input.charAt(10), 10) <= players.length) {
//                        if (player.shootContenderIfPossible(SmallArrow.class, players[Character.digit(input.charAt(10), 10) - 1])) {
//                            numberOfShootCommadUsageInCurrentTurn++;
//                        }
//                    } else {
//                        inputIsInvalid = true;
//                    }
//                } else if (input.toLowerCase().startsWith("shoot<bigarrow>")) {
//                    if (input.length() < 16) {
//                        inputIsInvalid = true;
//                    } else if (Character.isDigit(input.charAt(16)) && Character.digit(input.charAt(16), 10) <= players.length) {
//                        if (player.shootContenderIfPossible(BigArrow.class, players[Character.digit(input.charAt(16), 10) - 1])) {
//                            numberOfShootCommadUsageInCurrentTurn++;
//                        }
//                    } else {
//                        inputIsInvalid = true;
//                    }
//                } else if (input.toLowerCase().startsWith("shoot<ba>")) {
//                    if (input.length() < 10) {
//                        inputIsInvalid = true;
//                    } else if (Character.isDigit(input.charAt(10)) && Character.digit(input.charAt(10), 10) <= players.length) {
//                        if (player.shootContenderIfPossible(BigArrow.class, players[Character.digit(input.charAt(10), 10) - 1])) {
//                            numberOfShootCommadUsageInCurrentTurn++;
//                        }
//                    } else {
//                        inputIsInvalid = true;
//                    }
//                } else if (input.toLowerCase().startsWith("shoot<firearrow>")) {
//                    if (input.length() < 17) {
//                        inputIsInvalid = true;
//                    } else if (Character.isDigit(input.charAt(17)) && Character.digit(input.charAt(17), 10) <= players.length) {
//                        if (player.shootContenderIfPossible(FireArrow.class, players[Character.digit(input.charAt(17), 10) - 1])) {
//                            numberOfShootCommadUsageInCurrentTurn++;
//                        }
//                    } else {
//                        inputIsInvalid = true;
//                    }
//                } else if (input.toLowerCase().startsWith("shoot<fa>")) {
//                    if (input.length() < 10) {
//                        inputIsInvalid = true;
//                    } else if (Character.isDigit(input.charAt(10)) && Character.digit(input.charAt(10), 10) <= players.length) {
//                        if (player.shootContenderIfPossible(FireArrow.class, players[Character.digit(input.charAt(10), 10) - 1])) {
//                            numberOfShootCommadUsageInCurrentTurn++;
//                        }
//                    } else {
//                        inputIsInvalid = true;
//                    }
//                } else {
//                    inputIsInvalid = true;
//                }
//            } else {
                inputIsInvalid = true;
            }
        } while (!playerMoved);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_UP:
                if (player.moveUpIfPossible(field)) {
                    playerMoved();
                }
                break;
            case KeyEvent.VK_DOWN:
                if (player.moveDownIfPossible(field)) {
                    playerMoved();
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (player.moveRightIfPossible(field)) {
                    playerMoved();
                }
                break;
            case KeyEvent.VK_LEFT:
                if (player.moveLeftIfPossible(field)) {
                    playerMoved();
                }
                break;
            case KeyEvent.VK_ESCAPE:
                System.exit(0);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    private void playerMoved() {
        field.moveEnemys(player.getX_position(), player.getY_position());
        player.applyPotions(field);
        player.applyDamages(field);
        this.remove(tile);
        tile = field.getTile(player.getX_position(), player.getY_position());
        tile.setLocation(0, 0);
        this.add(tile);
        setKnownTiles(tile);
        this.repaint();
        checkWinLose();
    }

    public void pickItem(Object item) {
        if (inventory.isFull() && !(item instanceof BigBag) && !(item instanceof BigHealthPotion) && !(item instanceof SmallHealthPotion) && !(item instanceof EnergyPotion)) {
            JOptionPane.showMessageDialog(this, "Inventory Is Full!!");
        } else if (item instanceof Chest) {
            if (inventory.useKeyIfExist(((Chest) item).getNumber())) {
                if (tile.giveItem(item) == null) {
                    System.exit(1);
                }
                ArrayList<Object> ChestIncluding = ((Chest) item).getIncluding();
                String chestItems = "";
                for (Object chestItem : ChestIncluding) {
                    inventory.getContaningItems().add(chestItem);
                    chestItems += chestItem.toString() + ", ";
                }
                JOptionPane.showMessageDialog(this, "You Gaind The Followings: " + chestItems);
            } else {
                JOptionPane.showMessageDialog(this, "You Do Not Have The Corresopnding Key!!", "Cant Opten The Chest!", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            if (tile.giveItem(item) == null) {
                System.exit(1);
            }
            if (item instanceof BigBag) {
                inventory.setInventorySize(inventory.getInventorySize() + 10);
            } else if (item instanceof SmallHealthPotion) {
                player.setHitPoint(player.getHitPoint() + 20);
            } else if (item instanceof BigHealthPotion) {
                player.setHitPoint(player.getHitPoint() + 50);
            } else if (item instanceof EnergyPotion) {
                player.setEnergy(player.getEnergy() + 10);
            } else {
                inventory.getContaningItems().add(item);
            }
        }
        this.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() instanceof Enemy) {
            killEnemy((Enemy) e.getSource());
        } else if (e.getSource() != this) {
            pickItem(e.getSource());
        }
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        inventory.setItemToBeUsed(null);
    }

    private void killEnemy(Enemy enemy) {
        if (inventory.getItemToBeUsed() == null) {
            JOptionPane.showMessageDialog(this, "First Choose An Arrow Then Click On Enemy To Kill it!", "Choose Arrow", JOptionPane.INFORMATION_MESSAGE);
        } else {
            inventory.giveItem(inventory.getItemToBeUsed());
            if (inventory.getItemToBeUsed() == SmallArrow.class) {
                field.killEnemy(enemy);
            } else if (inventory.getItemToBeUsed() == BigArrow.class) {
                field.killAllEnemiesInTile(tile.getX_position(), tile.getY_position());
            } else {
            }
            inventory.setItemToBeUsed(null);
            this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            this.repaint();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    private void setKnownTiles(Tile currentTile) {
        field.getTile(currentTile.getX_position(), currentTile.getY_position()).setVisited(true);
        if (currentTile.getX_position() + 1 < 25) {
            field.getTile(currentTile.getX_position() + 1, currentTile.getY_position()).setVisited(true);
        }
        if (currentTile.getY_position() + 1 < 25) {
            field.getTile(currentTile.getX_position(), currentTile.getY_position() + 1).setVisited(true);
        }
        if (currentTile.getX_position() - 1 >= 0) {
            field.getTile(currentTile.getX_position() - 1, currentTile.getY_position()).setVisited(true);
        }
        if (currentTile.getY_position() - 1 >= 0) {
            field.getTile(currentTile.getX_position(), currentTile.getY_position() - 1).setVisited(true);
        }
    }

    public void drop(Class<?> itemType) {
        if (tile.isFull()) {
            JOptionPane.showMessageDialog(this, "No More Space On This Tile!!", "Tile Is Full", JOptionPane.ERROR_MESSAGE);
        } else {
            Object item = inventory.giveItem(itemType);
            tile.addItem(item);
        }
        this.repaint();
    }

    public void SetCursorToWeapon(Class<?> weaponType) {
        if (weaponType == SmallArrow.class) {
            this.setCursor(smallArrowCursor);
        } else if (weaponType == BigArrow.class) {
            this.setCursor(bigArrowCursor);
        } else {
            this.setCursor(fireArrowCursor);
        }
    }

    public void breakWalls() {
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        field.breakWalls(tile);
        this.repaint();
    }

    public void showShovelDestinationChosser() {
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        this.setEnabled(false);
        ShovelDestinationChooser = new ShovelDestinationChooser(this, tile, screenSize);
        ShovelDestinationChooser.setVisible(true);
    }

    public void digTunnel(int X_Destination, int Y_Destination) {
        this.setEnabled(true);
        ShovelDestinationChooser.setVisible(false);
        ShovelDestinationChooser = null;
        if (field.getTile(X_Destination, Y_Destination).isWall()) {
            JOptionPane.showMessageDialog(this, "Destination Was Wall!!", "Useless Shovel!", JOptionPane.ERROR_MESSAGE);
            field.getTile(X_Destination, Y_Destination).setVisited(true);
            this.repaint();
        } else {
            player.setPosition(X_Destination, Y_Destination);
            playerMoved();
        }
    }

    public void showHawkResult() {
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        Tile topTile, rightTile, bottomTile, leftTile;
        int topEnemy, rightEnemy, bottomEnemy, leftEnemy;
        topTile = rightTile = bottomTile = leftTile = null;
        topEnemy = rightEnemy = bottomEnemy = leftEnemy = 0;

        if (tile.getX_position() - 1 >= 0) {
            topTile = field.getTile(tile.getX_position() - 1, tile.getY_position());
            topEnemy = field.getNumberOfEnemysInTile(topTile.getX_position(), topTile.getY_position());
        }
        if (tile.getX_position() + 1 < 25) {
            bottomTile = field.getTile(tile.getX_position() + 1, tile.getY_position());
            bottomEnemy = field.getNumberOfEnemysInTile(bottomTile.getX_position(), bottomTile.getY_position());
        }
        if (tile.getY_position() - 1 >= 0) {
            leftTile = field.getTile(tile.getX_position(), tile.getY_position() - 1);
            leftEnemy = field.getNumberOfEnemysInTile(leftTile.getX_position(), leftTile.getY_position());
        }
        if (tile.getY_position() + 1 < 25) {
            rightTile = field.getTile(tile.getX_position(), tile.getY_position() + 1);
            rightEnemy = field.getNumberOfEnemysInTile(rightTile.getX_position(), rightTile.getY_position());
        }

        HawkResult hawkResult = new HawkResult(topTile, rightTile, bottomTile, leftTile, topEnemy, rightEnemy, bottomEnemy, leftEnemy);
        hawkResult.setVisible(true);
    }

    private class shovelDestinationChooserListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
        }

    }

    private void checkWinLose() {
        if (player.getEnergy() < 1 || player.getHitPoint() < 1) {
            JOptionPane.showMessageDialog(this, "You Lose!!", "Game Over!", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        } else if (player.getX_position() == field.getX_finishPosition() && player.getY_position() == field.getY_finish_Position()) {
            JOptionPane.showMessageDialog(this, "You Wiiiiiiin!!", "Game Over!", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }

    public static synchronized void playSound() {
        new Thread(new Runnable() {
  // The wrapper thread is unnecessary, unless it blocks on the
            // Clip finishing; see comments.
            @Override
            public void run() {
                try {
                    Clip clip = AudioSystem.getClip();
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                            Main.class.getResourceAsStream("C:/Users/Hojjat/Desktop/New folder (2)/Feeling Good - Nina Simone.wav"));
                    clip.open(inputStream);
                    clip.start();
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }).start();
    }
    
    private void play(){
        
    AudioClip clip = Applet.newAudioClip(Main.class.getResource("sound.wav"));
    
    clip.play();
    
    }

}
