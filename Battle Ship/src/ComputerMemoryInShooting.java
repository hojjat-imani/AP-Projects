/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Hojjat
 */
public class ComputerMemoryInShooting {
    private TankPeice firstTankPeicefound;
    private TankPeice secondTankPeicefound_ShowingTanksDirection;
    private boolean tankIsHitedOfOneSide;
    private boolean tankIsHitedCompeletly;
    private int numberOfPeicesThatAreHitedInOneSide;

    public ComputerMemoryInShooting() {
        firstTankPeicefound = new TankPeice();
        secondTankPeicefound_ShowingTanksDirection = new TankPeice();
        tankIsHitedOfOneSide = false;
        tankIsHitedCompeletly = false;
        numberOfPeicesThatAreHitedInOneSide = 0;
    }

    /**
     * @return the firstTankPeicefound
     */
    public TankPeice getFirstTankPeicefound() {
        return firstTankPeicefound;
    }

    /**
     * @param firstTankPeicefound the firstTankPeicefound to set
     */
    public void setFirstTankPeicefound(TankPeice firstTankPeicefound) {
        this.firstTankPeicefound = firstTankPeicefound;
    }

    /**
     * @return the secondTankPeicefound_ShowingTanksDirection
     */
    public TankPeice getSecondTankPeicefound_ShowingTanksDirection() {
        return secondTankPeicefound_ShowingTanksDirection;
    }

    /**
     * @param secondTankPeicefound_ShowingTanksDirection the secondTankPeicefound_ShowingTanksDirection to set
     */
    public void setSecondTankPeicefound_ShowingTanksDirection(TankPeice secondTankPeicefound_ShowingTanksDirection) {
        this.secondTankPeicefound_ShowingTanksDirection = secondTankPeicefound_ShowingTanksDirection;
    }

    /**
     * @return the tankIsHitedOfOneSide
     */
    public boolean tankIsHitedOfOneSide() {
        return tankIsHitedOfOneSide;
    }

    /**
     * @param tankIsHitedOfOneSide the tankIsHitedOfOneSide to set
     */
    public void setTankIsHitedOfOneSide(boolean tankIsHitedOfOneSide) {
        this.tankIsHitedOfOneSide = tankIsHitedOfOneSide;
    }

    /**
     * @return the tankIsHitedCompeletly
     */
    public boolean tankIsHitedCompeletly() {
        return tankIsHitedCompeletly;
    }

    /**
     * @param tankIsHitedCompeletly the tankIsHitedCompeletly to set
     */
    public void setTankIsHitedCompeletly(boolean tankIsHitedCompeletly) {
        this.tankIsHitedCompeletly = tankIsHitedCompeletly;
    }

    /**
     * @return the numberOfPeicesThatAreHitedInOneSide
     */
    public int getNumberOfPeicesThatAreHitedInOneSide() {
        return numberOfPeicesThatAreHitedInOneSide;
    }

    /**
     * @param numberOfPeicesThatAreHitedInOneSide the numberOfPeicesThatAreHitedInOneSide to set
     */
    public void setNumberOfPeicesThatAreHitedInOneSide(int numberOfPeicesThatAreHitedInOneSide) {
        this.numberOfPeicesThatAreHitedInOneSide = numberOfPeicesThatAreHitedInOneSide;
    }
}
