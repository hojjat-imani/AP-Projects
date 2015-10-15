
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import javax.swing.ImageIcon;
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
public class Field extends JPanel {

    private Tile[][] tiles;
    private Random randomGenerator;
    private ArrayList<Enemy> enemys;
    private Player player;
    private int x_finishPosition;
    private int y_finishPosition;
    private int difficulty;
    private GameControl gameControl;

    public Field(int difficulty, Player player, GameControl gameControl) {
        this.setSize(568, 568);

        this.player = player;
        this.gameControl = gameControl;
        tiles = new Tile[25][25];
        for (int i = 0; i < 25; i++) {
            for (int j = 0; j < 25; j++) {
                tiles[i][j] = new Tile(i, j, gameControl);
            }
        }
        randomGenerator = new Random();
        enemys = new ArrayList<>();
        this.difficulty = difficulty;
        generateMaze(1);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        Image FieldBorder = new ImageIcon("files\\Field\\FieldBorder.png").getImage();
        Image wall = new ImageIcon("files\\Field\\wall.png").getImage();
        Image empty = new ImageIcon("files\\Field\\empty.png").getImage();
        Image notVisited = new ImageIcon("files\\Field\\notVisited.png").getImage();
        Image player = new ImageIcon("files\\Field\\player.png").getImage();
        g.drawImage(FieldBorder, 0, 0, 568, 568, this);
        for (int i = 0; i < 25; i++) {
            for (int j = 0; j < 25; j++) {
                if (tiles[i][j].isVisited()) {
                    if (tiles[i][j].isWall()) {
                        g.drawImage(wall, tiles[i][j].getY_position() * 22 + 9, tiles[i][j].getX_position() * 22 + 9, null);
                    } else {
                        g.drawImage(empty, tiles[i][j].getY_position() * 22 + 9, tiles[i][j].getX_position() * 22 + 9, null);
                    }
                } else {
                    g.drawImage(notVisited, tiles[i][j].getY_position() * 22 + 9, tiles[i][j].getX_position() * 22 + 9, null);
                }
            }
        }
        //x o y too barname baraksan
        //bara hamin inja jabeja midim ta doros she!
        g.drawImage(player, this.player.getY_position() * 22 + 9, this.player.getX_position() * 22 + 9, null);
    }

    public Tile getTile(int x_position, int y_position) {
        return tiles[x_position][y_position];
    }

    public void generateMaze(int numberOfPlayers) {
        if (numberOfPlayers == 1) {
            MapAWayAndSetDestination();
        } else if (numberOfPlayers == 2) {
            do {
                MapAWayAndSetDestination();
            } while (tiles[24][24].isWall());
        } else if (numberOfPlayers == 3) {
            do {
                MapAWayAndSetDestination();
            } while (tiles[24][24].isWall() || tiles[24][0].isWall());
        } else {
            do {
                MapAWayAndSetDestination();
            } while (tiles[24][24].isWall() || tiles[24][0].isWall() || tiles[0][24].isWall());
        }
        completeMapByAddingSomeRandomLines();
        setTilesBackgroundCode();
        putRandomItems();
        putRandomGold();
        putRandomEnemies();
    }

    private void generateA_1_PlayerMaze() {
        MapAWayAndSetDestination();
    }

    private void generateA_2_PlayerMaze() {
        do {
            setAllTilesAsWall();
            MapAWayAndSetDestination();
        } while (tiles[24][24].isWall() || x_finishPosition < 7 || x_finishPosition > 18 || y_finishPosition < 7 || y_finishPosition > 18);
    }

    private void generateA_3_PlayerMaze() {
        do {
            setAllTilesAsWall();
            MapAWayAndSetDestination();
        } while (tiles[24][24].isWall() || tiles[0][24].isWall() || x_finishPosition > 18 || x_finishPosition < 7 || y_finishPosition > 18 || y_finishPosition < 7);
    }

    private void generateA_4_PlayerMaze() {
        do {
            setAllTilesAsWall();
            MapAWayAndSetDestination();
        } while (tiles[24][24].isWall() || tiles[24][0].isWall() || tiles[0][24].isWall() || x_finishPosition < 7 || x_finishPosition > 18 || y_finishPosition < 7 || y_finishPosition > 18);
    }

    private void MapAWayAndSetDestination() {
        int x, y, i, direction, length;
        do {
            x = y = i = 0;
            setAllTilesAsWall();
            tiles[0][0].setIsWall(false);
            while (i < 200) {
                direction = randomGenerator.nextInt(4);
                if (direction == 0) {
                    length = randomGenerator.nextInt(25 - x);
                    for (int j = 0; j < length; j++) {
                        x += 1;
                        tiles[x][y].setIsWall(false);
                        i++;
                    }
                } else if (direction == 1) {
                    length = randomGenerator.nextInt(x + 1);
                    for (int j = 0; j < length; j++) {
                        x -= 1;
                        tiles[x][y].setIsWall(false);
                        i++;
                    }
                } else if (direction == 2) {
                    length = randomGenerator.nextInt(25 - y);
                    for (int j = 0; j < length; j++) {
                        y += 1;
                        tiles[x][y].setIsWall(false);
                        i++;
                    }
                } else if (direction == 3) {
                    length = randomGenerator.nextInt(y + 1);
                    for (int j = 0; j < length; j++) {
                        y -= 1;
                        tiles[x][y].setIsWall(false);
                        i++;
                    }
                }
            }
        } while (x < 7 || x > 18 || y < 7 || y > 18);
        x_finishPosition = x;
        y_finishPosition = y;
    }

    private void setAllTilesAsWall() {
        for (int i = 0; i < 25; i++) {
            for (int j = 0; j < 25; j++) {
                tiles[i][j].setIsWall(true);
            }
        }
    }

    private void completeMapByAddingSomeRandomLines() {
        int x, y, i, direction, length;
        x = y = i = 0;
        while (i < 200) {
            x = randomGenerator.nextInt(25);
            y = randomGenerator.nextInt(25);
            length = randomGenerator.nextInt(10);
            direction = randomGenerator.nextInt(4);
            if (direction == 0) {
                for (int j = 0; j < length; j++) {
                    if (x + 1 < 25) {
                        x += 1;
                        tiles[x][y].setIsWall(false);
                        i++;
                    }
                }
            } else if (direction == 1) {
                for (int j = 0; j < length; j++) {
                    if (y + 1 < 25) {
                        y += 1;
                        tiles[x][y].setIsWall(false);
                        i++;
                    }
                }
            } else if (direction == 2) {
                for (int j = 0; j < length; j++) {
                    if (x - 1 > 0) {
                        x -= 1;
                        tiles[x][y].setIsWall(false);
                        i++;
                    }
                }
            } else if (direction == 3) {
                for (int j = 0; j < length; j++) {
                    if (y - 1 > 0) {
                        y -= 1;
                        tiles[x][y].setIsWall(false);
                        i++;
                    }
                }
            }
        }
    }

    private void setTilesBackgroundCode() {
        for (int i = 0; i < 25; i++) {
            for (int j = 0; j < 25; j++) {
                tiles[i][j].setBackgroundCode(GenerateBackGroundCode(tiles[i][j]));
            }
        }
    }

    private String GenerateBackGroundCode(Tile tile) {
        int i = tile.getX_position();
        int j = tile.getY_position();
        String code = "";
        if (i - 1 >= 0 && !tiles[i - 1][j].isWall()) {
            code = "1";
        } else {
            code = "0";
        }
        if (j + 1 < 25 && !tiles[i][j + 1].isWall()) {
            code = code + "1";
        } else {
            code = code + "0";
        }
        if (i + 1 < 25 && !tiles[i + 1][j].isWall()) {
            code = code + "1";
        } else {
            code = code + "0";
        }
        if (j - 1 >= 0 && !tiles[i][j - 1].isWall()) {
            code = code + "1";
        } else {
            code = code + "0";
        }
        return code;
    }

    private void putRandomItems() {
        putRandomPotions();
        putRandomWeapons();
        putRandomOtherUsefulItem();
        putRandomChestAndKey();
    }

    private void putRandomPotions() {
        int i = 0;
        int x, y, rand;
        int numberOfPotions = 100 + (difficulty - 1) * 25;
        while (i < numberOfPotions) {
            x = randomGenerator.nextInt(25);
            y = randomGenerator.nextInt(25);
            if (!tiles[x][y].isWall() && !tileIsStartPoint(x, y) && !(tiles[x][y].getNumberOfFreeLocations() < 1)) {
                rand = randomGenerator.nextInt(5);
                if (rand == 0 || rand == 1) {
                    tiles[x][y].addItem(new SmallHealthPotion((MouseListener) gameControl));
                } else if (rand == 2 || rand == 3) {
                    tiles[x][y].addItem(new EnergyPotion((MouseListener) gameControl));
                } else {
                    tiles[x][y].addItem(new BigHealthPotion((MouseListener) gameControl));
                }
                i++;
            }
        }

    }

    private void putRandomWeapons() {
        int i = 0;
        int x, y, rand;
        tiles[0][0].addWeapon(new SmallArrow((MouseListener) gameControl));
        tiles[1][0].addWeapon(new BigArrow((MouseListener) gameControl));
        tiles[0][1].addWeapon(new BigArrow((MouseListener) gameControl));
        int numberOfweapons = 100 + (difficulty - 1) * 25;
        while (i < numberOfweapons) {
            x = randomGenerator.nextInt(25);
            y = randomGenerator.nextInt(25);
            if (!tiles[x][y].isWall() && !(tiles[x][y].getNumberOfFreeLocations() < 1)) {
                rand = randomGenerator.nextInt(4);
                if (rand == 0 || rand == 1) {
                    tiles[x][y].addWeapon(new SmallArrow((MouseListener) gameControl));
                } else if (rand == 2) {
                    tiles[x][y].addWeapon(new BigArrow((MouseListener) gameControl));
                } else {
                    tiles[x][y].addWeapon(new FireArrow((MouseListener) gameControl));
                }
                i++;
            }
        }
    }

    private void putRandomOtherUsefulItem() {
        int i = 0;
        int x, y, rand;
        int numberOfItems = 300 - (difficulty - 1) * 50;
        while (i < numberOfItems) {
            x = randomGenerator.nextInt(25);
            y = randomGenerator.nextInt(25);
            if (!tiles[x][y].isWall() && !(tiles[x][y].getNumberOfFreeLocations() < 1)) {
                rand = randomGenerator.nextInt(5);
                if (rand == 0) {
                    tiles[x][y].addItem(new StoneBreaker((MouseListener) gameControl));
                } else if (rand == 1) {
                    tiles[x][y].addItem(new ReviveScroll((MouseListener) gameControl));
                } else if (rand == 2) {
                    tiles[x][y].addItem(new Hawk((MouseListener) gameControl));
                } else if (rand == 3) {
                    tiles[x][y].addItem(new Shovel((MouseListener) gameControl));
                } else {
                    tiles[x][y].addItem(new BigBag((MouseListener) gameControl));
                }
                i++;
            }
        }
    }

    private void putRandomGold() {
        for (int i = 0; i < 25; i++) {
            for (int j = 0; j < 25; j++) {
                if (!tiles[i][j].isWall() && !tileIsStartPoint(i, j)) {
                    tiles[i][j].setGoldAmount((50 - Math.abs(i - getX_finishPosition()) - Math.abs(j - getY_finish_Position())) * 100);
                }
            }
        }
    }

    private void putRandomChestAndKey() {
        int i = 0;
        int x, y;
        while (i < 50) {
            x = randomGenerator.nextInt(25);
            y = randomGenerator.nextInt(25);
            if (!tiles[x][y].isWall() && !tiles[x][y].containsItem(Chest.class)) {
                tiles[x][y].addItem(makeChest(i));
                boolean keyPositionIsGenerated = false;
                do {
                    x = randomGenerator.nextInt(25);
                    y = randomGenerator.nextInt(25);
                    if (!tiles[x][y].isWall()) {
                        keyPositionIsGenerated = true;
                    }
                } while (!keyPositionIsGenerated);
                tiles[x][y].addItem(new Key(i, (MouseListener) gameControl));
                i++;
            }
        }
    }

    private Chest makeChest(int number) {
        Chest chest = new Chest(number, (MouseListener) gameControl);
        int numberOfContaningItems = randomGenerator.nextInt(15);
        int rand;
        for (int i = 0; i < numberOfContaningItems; i++) {
            rand = randomGenerator.nextInt(14);
            if (rand == 0 || rand == 1 || rand == 2) {
                chest.addItemToInventory(new SmallArrow((MouseListener) gameControl));
            } else if (rand == 3) {
                chest.addItemToInventory(new BigArrow((MouseListener) gameControl));
            } else if (rand == 4 || rand == 5) {
                chest.addItemToInventory(new FireArrow((MouseListener) gameControl));
            } else if (rand == 6 || rand == 7) {
                chest.addItemToInventory(new StoneBreaker((MouseListener) gameControl));
            } else if (rand == 8 || rand == 9) {
                chest.addItemToInventory(new Hawk((MouseListener) gameControl));
            } else if (rand == 10 || rand == 11) {
                chest.addItemToInventory(new Shovel((MouseListener) gameControl));
            } else if (rand == 12) {
                chest.addItemToInventory(new BigBag((MouseListener) gameControl));
            } else if (rand == 13) {
                chest.addItemToInventory(new ReviveScroll((MouseListener) gameControl));
            }
        }
        return chest;
    }

    private void putRandomEnemies() {
        int i = 0;
        int x, y, rand;
        int numberOfEnemeys = 25 + difficulty * 25;
        while (i < numberOfEnemeys) {
            x = randomGenerator.nextInt(25);
            y = randomGenerator.nextInt(25);
            if (!tiles[x][y].isWall() && !tileIsStartPoint(x, y)) {
                rand = randomGenerator.nextInt(3) + 1;
                while (rand > 0 && getNumberOfEnemysInTile(x, y) < 3) {
                    Enemy enemy = new Enemy(rand, x, y, (MouseListener) gameControl);
                    enemys.add(enemy);
                    tiles[x][y].addEnemy(enemy);
                    i++;
                    rand--;
                }
            }
        }
    }

    private boolean tileIsStartPoint(int x_position, int y_position) {
        if (x_position == 0 && y_position == 0) {
            return true;
        }
        if (x_position == 24 && y_position == 24) {
            return true;
        }
        if (x_position == 24 && y_position == 0) {
            return true;
        }
        if (x_position == 0 && y_position == 24) {
            return true;
        }
        return false;
    }

    public int getNumberOfEnemysInTile(int x_position, int y_position) {
        int numberOfEnemys = 0;
        for (Enemy enemy : enemys) {
            if (enemy.getX_position() == x_position && enemy.getY_position() == y_position) {
                numberOfEnemys++;
            }
        }
        return numberOfEnemys;
    }

    public void killEnemy(Enemy enemyToBeKilled) {
        for (Enemy enemy : enemys) {
            if (enemy == enemyToBeKilled) {
                tiles[enemy.getX_position()][enemy.getY_position()].giveEnemy(enemy);
                enemys.remove(enemy);
                return;
            }
        }

    }

    public void killAllEnemiesInTile(int x_position, int y_position) {
        Iterator<Enemy> it = enemys.iterator();
        Enemy enemy;
        while (it.hasNext()) {
            enemy = it.next();
            if (enemy.getX_position() == x_position && enemy.getY_position() == y_position) {
                tiles[x_position][y_position].giveEnemy(enemy);
                it.remove();
            }
        }
    }

    public void tellAboutEnemies(int x_position, int y_position) {
        int numberOfEnemiesInTile = getNumberOfEnemysInTile(x_position, y_position);
        if (numberOfEnemiesInTile != 0) {
            System.out.println("                                     \033[31m Oh!! This is a hostile!! " + numberOfEnemiesInTile + " enemy(s) observed!!\033[0m");
        }
    }

    public void moveEnemys(int players_x_position, int players_y_position) {
        int new_x_position;
        int new_y_position;
        for (Enemy enemy : enemys) {
            if (enemy.getX_position() != players_x_position && enemy.getY_position() != players_y_position) {
                if (randomGenerator.nextBoolean()) {
                    new_x_position = enemy.getX_position() + ((players_x_position > enemy.getX_position()) ? 1 : -1);
                    new_y_position = enemy.getY_position();
                    if (!enemyGoToNewPosition(enemy, new_x_position, new_y_position)) {
                        new_x_position = enemy.getX_position();
                        new_y_position = new_y_position + ((players_y_position > enemy.getY_position()) ? 1 : -1);
                        enemyGoToNewPosition(enemy, new_x_position, new_y_position);
                    }
                } else {
                    new_x_position = enemy.getX_position();
                    new_y_position = enemy.getY_position() + ((players_y_position > enemy.getY_position()) ? 1 : -1);
                    if (!enemyGoToNewPosition(enemy, new_x_position, new_y_position)) {
                        new_y_position = enemy.getY_position();
                        new_x_position = enemy.getX_position() + ((players_x_position > enemy.getX_position()) ? 1 : -1);
                        enemyGoToNewPosition(enemy, new_x_position, new_y_position);
                    }
                }
            } else if (enemy.getY_position() != players_y_position && enemy.getX_position() == players_x_position) {
                new_x_position = enemy.getX_position();
                new_y_position = enemy.getY_position() + ((players_y_position > enemy.getY_position()) ? 1 : -1);
                enemyGoToNewPosition(enemy, new_x_position, new_y_position);
            } else if (enemy.getY_position() == players_y_position && enemy.getX_position() != players_x_position) {
                new_x_position = enemy.getX_position() + ((players_x_position > enemy.getX_position()) ? 1 : -1);
                new_y_position = enemy.getY_position();
                enemyGoToNewPosition(enemy, new_x_position, new_y_position);
            }
        }

    }

    private boolean enemyGoToNewPosition(Enemy enemy, int new_x_position, int new_y_position) {
        int numberOfEnemeisInNewPosition = getNumberOfEnemysInTile(new_x_position, new_y_position);
        if (!tiles[new_x_position][new_y_position].isWall() && numberOfEnemeisInNewPosition < 3) {
            tiles[enemy.getX_position()][enemy.getY_position()].giveEnemy(enemy);
            enemy.setX_position(new_x_position);
            enemy.setY_position(new_y_position);
            tiles[enemy.getX_position()][enemy.getY_position()].addEnemy(enemy);
            enemy.setNumber(numberOfEnemeisInNewPosition + 1);
            return true;
        }
        return false;
    }

    public void devShowMaze() {
        System.out.println("");
        System.out.println("...............................................................................");
        for (int i = 0; i < 25; i++) {
            for (int j = 0; j < 25; j++) {
                System.out.print((tiles[i][j].isWall()) ? "   " : ((i == getX_finishPosition() && j == getY_finish_Position()) ? "\033[41m * \033[0m" : " * "));
            }
            System.out.println("");
        }
        System.out.println("finish Position : " + getX_finishPosition() + "  " + getY_finish_Position());
        System.out.println("...............................................................................");
        System.out.println("");
    }

    public void devShowItemsPlace(Class<?> itemType) {
        int totalNumberOfItem = 0;
        System.out.println("");
        System.out.println("...............................................................................");
        for (int i = 0; i < 25; i++) {
            for (int j = 0; j < 25; j++) {
                System.out.printf((tiles[i][j].isWall()) ? "   " : "%2d ", tiles[i][j].getNumberOfItemsOfKind(itemType));
            }
            System.out.println("");
        }
        for (int i = 0; i < 25; i++) {
            for (int j = 0; j < 25; j++) {
                totalNumberOfItem += tiles[i][j].getNumberOfItemsOfKind(itemType);
            }
        }
        System.out.println("Total: " + totalNumberOfItem);
        System.out.println("...............................................................................");
        System.out.println("");
    }

    public void devShowEnemies() {
        int totalNumberOfEnemies = 0;
        System.out.println("");
        System.out.println("...............................................................................");
        for (int i = 0; i < 25; i++) {
            for (int j = 0; j < 25; j++) {
                System.out.printf((tiles[i][j].isWall()) ? "   " : "%2d ", getNumberOfEnemysInTile(i, j));
            }
            System.out.println("");
        }
        for (int i = 0; i < 25; i++) {
            for (int j = 0; j < 25; j++) {
                totalNumberOfEnemies += getNumberOfEnemysInTile(i, j);
            }
        }
        System.out.println("Total: " + totalNumberOfEnemies);
        System.out.println("...............................................................................");
        System.out.println("");
    }

    public void breakWalls(Tile tile) {
        int x = tile.getX_position();
        int y = tile.getY_position();
        if (x - 1 >= 0) {
            tiles[x - 1][y].setIsWall(false);
        }
        if (x + 1 < 25) {
            tiles[x + 1][y].setIsWall(false);
        }
        if (y - 1 >= 0) {
            tiles[x][y - 1].setIsWall(false);
        }
        if (y + 1 < 25) {
            tiles[x][y + 1].setIsWall(false);
        }
        if (x - 1 >= 0 && y + 1 < 25) {
            tiles[x - 1][y + 1].setIsWall(false);
        }
        if (x - 1 >= 0 && y - 1 >= 0) {
            tiles[x - 1][y - 1].setIsWall(false);
        }
        if (x + 1 < 25 && y + 1 < 25) {
            tiles[x + 1][y + 1].setIsWall(false);
        }
        if (x + 1 < 25 && y - 1 >= 0) {
            tiles[x + 1][y - 1].setIsWall(false);
        }
        tiles[x][y].setBackgroundCode(GenerateBackGroundCode(tiles[x][y]));
        if (x - 1 >= 0) {
            tiles[x - 1][y].setBackgroundCode(GenerateBackGroundCode(tiles[x - 1][y]));
        }
        if (x + 1 < 25) {
            tiles[x + 1][y].setBackgroundCode(GenerateBackGroundCode(tiles[x + 1][y]));
        }
        if (y - 1 >= 0) {
            tiles[x][y - 1].setBackgroundCode(GenerateBackGroundCode(tiles[x][y - 1]));;
        }
        if (y + 1 < 25) {
            tiles[x][y + 1].setBackgroundCode(GenerateBackGroundCode(tiles[x][y + 1]));
        }
        if (x - 1 >= 0 && y + 1 < 25) {
            tiles[x - 1][y + 1].setBackgroundCode(GenerateBackGroundCode(tiles[x - 1][y + 1]));
        }
        if (x - 1 >= 0 && y - 1 >= 0) {
            tiles[x - 1][y - 1].setBackgroundCode(GenerateBackGroundCode(tiles[x - 1][y - 1]));
        }
        if (x + 1 < 25 && y + 1 < 25) {
            tiles[x + 1][y + 1].setBackgroundCode(GenerateBackGroundCode(tiles[x + 1][y + 1]));
        }
        if (x + 1 < 25 && y - 1 >= 0) {
            tiles[x + 1][y - 1].setBackgroundCode(GenerateBackGroundCode(tiles[x + 1][y - 1]));
        }
    }

    /**
     * @return the x_finishPosition
     */
    public int getX_finishPosition() {
        return x_finishPosition;
    }

    /**
     * @return the y_finish_Position
     */
    public int getY_finish_Position() {
        return y_finishPosition;
    }
}
