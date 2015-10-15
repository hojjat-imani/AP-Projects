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

    private final String playerName;
    protected BattleField field;
    protected int numberOfTankPeices;
    protected int numberOfTankPeicesThatAreHited;

    public Player(String playerName) {
        this.playerName = playerName;
        field = new BattleField();
        numberOfTankPeices = 0;
        numberOfTankPeicesThatAreHited = 0;
    }

    public void placeTanks() {
    }

    public void printFieldForYourSelf() {
        field.printMapShowingTanksAndHits();
    }

    public void printFieldForContender() {
        field.printMapShowingHits();
    }

    protected boolean sizeOfAreaIsNotValid(int x1, int y1, int x2, int y2) {
        if ((x1 == x2 && (Math.abs(y2 - y1) > 4 || Math.abs(y2 - y1) < 1)) || (y1 == y2 && (Math.abs(x2 - x1) > 4 || Math.abs(x2 - x1) < 1))) {
            return true;
        }
        return false;
    }

    protected boolean areaIsOutOfField(int x1, int y1, int x2, int y2) {
        if (x1 < 0 || y1 < 0 || x2 < 0 || y2 < 0 || x1 > 9 || y1 > 9 || x2 > 9 || y2 > 9) {
            return true;
        }
        return false;
    }

    protected boolean areaHasOverlabWithPreviosTanks(int x1, int y1, int x2, int y2) {
        for (int i = Math.min(x1, x2); i <= Math.max(x1, x2); i++) {
            for (int j = Math.min(y1, y2); j <= Math.max(y1, y2); j++) {
                if (!field.getCell(i, j).isFree()) {
                    return true;
                }
            }
        }
        return false;
    }

    protected boolean areaIsDiagonal(int x1, int y1, int x2, int y2) {
        if ((x1 != x2) && (y1 != y2)) {
            return true;
        }
        return false;
    }

    /**
     *
     * @param X_position
     * @param Y_position
     * @return true if the shoot was a hit and false if it was missed
     */
    public boolean beShooted(int X_position, int Y_position) {
        if (!getField().getCell(X_position, Y_position).isShooted()) {
            if (getField().getCell(X_position, Y_position).shoot()) {
                numberOfTankPeicesThatAreHited++;
                return true;
            } else {
                return false;
            }
        } else {
            if (getField().getCell(X_position, Y_position).shoot()) {
                return true;
            }
            return false;
        }
    }

    public boolean isLost() {
        if (getNumberOfTankPeices() == getNumberOfTankPeicesThatAreHited()) {
            return true;
        }
        return false;
    }

    /**
     * @return the field
     */
    public BattleField getField() {
        return field;
    }

    /**
     * @return the playerName
     */
    public String getPlayerName() {
        return playerName;
    }

    public void turnToShoot(Player playerToBeShooted) {
    }

    /**
     * @return the numberOfTankPeices
     */
    public int getNumberOfTankPeices() {
        return numberOfTankPeices;
    }

    /**
     * @return the numberOfTankPeicesThatAreHited
     */
    public int getNumberOfTankPeicesThatAreHited() {
        return numberOfTankPeicesThatAreHited;
    }
}
