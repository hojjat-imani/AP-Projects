
import java.util.Random;
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
public class ComputerPlayer extends Player {

    private Random randomGenerator;
    private ComputerMemoryInShooting memory;

    public ComputerPlayer(String playerdName) {
        super(playerdName);
        randomGenerator = new Random();
        memory = new ComputerMemoryInShooting();
    }

    @Override
    public void placeTanks() {
        int numberOfTanks = 0;
        while (numberOfTanks != 5) {
            if (randomGenerator.nextBoolean()) {
                if (placeAHorrizentalTank()) {
                    numberOfTanks++;
                }
            } else {
                if (placeAVerticalTank()) {
                    numberOfTanks++;
                }
            }
        }
    }

    private boolean placeAVerticalTank() {
        int x1, y1, x2, y2;
        y1 = y2 = randomGenerator.nextInt(10) + 1;
        x1 = randomGenerator.nextInt(10) + 1;
        x2 = y1 + randomGenerator.nextInt(9) - 4;
        if (areaIsInvalid(x1, y1, x2, y2)) {
            return false;
        }
        for (int i = Math.min(x1, x2); i <= Math.max(x1, x2); i++) {
            for (int j = Math.min(y1, y2); j <= Math.max(y1, y2); j++) {
                field.getCell(i, j).putShipInCell();
                numberOfTankPeices++;
            }
        }
        return true;
    }

    private boolean placeAHorrizentalTank() {
        int x1, y1, x2, y2;
        x1 = x2 = randomGenerator.nextInt(10) + 1;
        y1 = randomGenerator.nextInt(10) + 1;
        y2 = y1 + randomGenerator.nextInt(9) - 4;
        if (areaIsInvalid(x1, y1, x2, y2)) {
            return false;
        }
        for (int i = Math.min(x1, x2); i <= Math.max(x1, x2); i++) {
            for (int j = Math.min(y1, y2); j <= Math.max(y1, y2); j++) {
                field.getCell(i, j).putShipInCell();
                numberOfTankPeices++;
            }
        }
        return true;
    }

    private boolean areaIsInvalid(int x1, int y1, int x2, int y2) {
        if (areaIsOutOfField(x1, y1, x2, y2)) {
            return true;
        }
        if (areaIsDiagonal(x1, y1, x2, y2)) {
            return true;
        }
        if (sizeOfAreaIsNotValid(x1, y1, x2, y2)) {
            return true;
        }
        if (areaHasOverlabWithPreviosTanks(x1, y1, x2, y2)) {
            return true;
        }
        return false;
    }

    @Override
    public void turnToShoot(Player playerToBeShooted) {
        if (memory.getFirstTankPeicefound().IsFound()) {
            if (memory.getSecondTankPeicefound_ShowingTanksDirection().IsFound()) {
                if (memory.tankIsHitedCompeletly()) {
                    memory = new ComputerMemoryInShooting();
                    randomShoot(playerToBeShooted);
                } else {
                    if (memory.tankIsHitedOfOneSide()) {
                        shootFoundedTankInOtherSide(playerToBeShooted);
                    } else {
                        shootFoundedTank(playerToBeShooted);
                    }
                }

            } else {
                randomShootToFindTanksDirection(playerToBeShooted);
            }
        } else {
            randomShoot(playerToBeShooted);
        }
    }

    private void randomShoot(Player playerToBeShooted) {
        boolean cellIsShootedBefore;
        int x, y;
        do {
            cellIsShootedBefore = false;
            x = randomGenerator.nextInt(10);
            y = randomGenerator.nextInt(10);
            if (playerToBeShooted.getField().getCell(x, y).isShooted()) {
                cellIsShootedBefore = true;
            }
        } while (cellIsShootedBefore);
        if (playerToBeShooted.beShooted(x, y)) {
            memory.getFirstTankPeicefound().setIsFound(true);
            memory.getFirstTankPeicefound().setX_position(x);
            memory.getFirstTankPeicefound().setY_position(y);
        }
    }

    private void randomShootToFindTanksDirection(Player playerToBeShooted) {
        int firstPeice_x = memory.getFirstTankPeicefound().getX_position();
        int firstPiece_y = memory.getFirstTankPeicefound().getY_position();
        int new_x, new_y;
        int random;
        boolean cellIsShootedBefore;
        boolean cellIsOutOfField;
        boolean[] sidesThatAreHitedBefore = new boolean[4];
        for (boolean hitedSide : sidesThatAreHitedBefore) {
            hitedSide = false;
        }
        int numberOfSidesThatAreHitedBefore;
        do {
            numberOfSidesThatAreHitedBefore = 0;
            cellIsOutOfField = cellIsShootedBefore = false;
            random = randomGenerator.nextInt(4);
            if (random == 0) {
                new_x = firstPeice_x;
                new_y = firstPiece_y - 1;
                if (new_x > 9 || new_x < 0 || new_y > 9 || new_y < 0) {
                    cellIsOutOfField = true;
                    sidesThatAreHitedBefore[0] = true;
                } else if (playerToBeShooted.getField().getCell(new_x, new_y).isShooted()) {
                    cellIsShootedBefore = true;
                    sidesThatAreHitedBefore[0] = true;
                }

            } else if (random == 1) {
                new_x = firstPeice_x;
                new_y = firstPiece_y + 1;
                if (new_x > 9 || new_x < 0 || new_y > 9 || new_y < 0) {
                    cellIsOutOfField = true;
                    sidesThatAreHitedBefore[1] = true;

                } else if (playerToBeShooted.getField().getCell(new_x, new_y).isShooted()) {
                    cellIsShootedBefore = true;
                    sidesThatAreHitedBefore[1] = true;

                }
            } else if (random == 2) {
                new_x = firstPeice_x - 1;
                new_y = firstPiece_y;
                if (new_x > 9 || new_x < 0 || new_y > 9 || new_y < 0) {
                    cellIsOutOfField = true;
                    sidesThatAreHitedBefore[2] = true;
                } else if (playerToBeShooted.getField().getCell(new_x, new_y).isShooted()) {
                    cellIsShootedBefore = true;
                    sidesThatAreHitedBefore[2] = true;

                }
            } else {
                new_x = firstPeice_x + 1;
                new_y = firstPiece_y;
                if (new_x > 9 || new_x < 0 || new_y > 9 || new_y < 0) {
                    cellIsOutOfField = true;
                    sidesThatAreHitedBefore[3] = true;

                } else if (playerToBeShooted.getField().getCell(new_x, new_y).isShooted()) {
                    cellIsShootedBefore = true;
                    sidesThatAreHitedBefore[3] = true;
                }
            }
            //masalan yeki az char tarafesh ye tank boode ke to ye jahate dg khorde se tarafe dg ham mised hit dashtan
            for (boolean hitedSide : sidesThatAreHitedBefore) {
                if (hitedSide) {
                    numberOfSidesThatAreHitedBefore++;
                }
            }
            if (numberOfSidesThatAreHitedBefore == 4) {
                memory = new ComputerMemoryInShooting();
                randomShoot(playerToBeShooted);
                return;
            }
        } while (cellIsShootedBefore || cellIsOutOfField);
        if (playerToBeShooted.beShooted(new_x, new_y)) {
            memory.getSecondTankPeicefound_ShowingTanksDirection().setIsFound(true);
            memory.getSecondTankPeicefound_ShowingTanksDirection().setX_position(new_x);
            memory.getSecondTankPeicefound_ShowingTanksDirection().setY_position(new_y);
        }
    }

    private void shootFoundedTank(Player playerToBeShooted) {
        int x1 = memory.getFirstTankPeicefound().getX_position();
        int y1 = memory.getFirstTankPeicefound().getY_position();
        int x2 = memory.getSecondTankPeicefound_ShowingTanksDirection().getX_position();
        int y2 = memory.getSecondTankPeicefound_ShowingTanksDirection().getY_position();
        int x_toShoot;
        int y_toShoot;
        if (x1 == x2) {
            x_toShoot = x1;
            y_toShoot = Math.max(y1, y2) + memory.getNumberOfPeicesThatAreHitedInOneSide() + 1;
        } else {
            y_toShoot = y1;
            x_toShoot = Math.max(x1, x2) + memory.getNumberOfPeicesThatAreHitedInOneSide() + 1;
        }
        memory.setNumberOfPeicesThatAreHitedInOneSide(memory.getNumberOfPeicesThatAreHitedInOneSide() + 1);
        if (x_toShoot > 9 || y_toShoot > 9) {
            memory.setTankIsHitedOfOneSide(true);
            memory.setNumberOfPeicesThatAreHitedInOneSide(0);
            shootFoundedTankInOtherSide(playerToBeShooted);
        } else {
            if (!playerToBeShooted.beShooted(x_toShoot, y_toShoot)) {
                memory.setTankIsHitedOfOneSide(true);
                memory.setNumberOfPeicesThatAreHitedInOneSide(0);
            }
        }
    }

    private void shootFoundedTankInOtherSide(Player playerToBeShooted) {
        int x1 = memory.getFirstTankPeicefound().getX_position();
        int y1 = memory.getFirstTankPeicefound().getY_position();
        int x2 = memory.getSecondTankPeicefound_ShowingTanksDirection().getX_position();
        int y2 = memory.getSecondTankPeicefound_ShowingTanksDirection().getY_position();
        int x_toShoot;
        int y_toShoot;
        if (x1 == x2) {
            x_toShoot = x1;
            y_toShoot = Math.min(y1, y2) - memory.getNumberOfPeicesThatAreHitedInOneSide() - 1;
        } else {
            y_toShoot = y1;
            x_toShoot = Math.min(x1, x2) - memory.getNumberOfPeicesThatAreHitedInOneSide() - 1;
        }
        memory.setNumberOfPeicesThatAreHitedInOneSide(memory.getNumberOfPeicesThatAreHitedInOneSide() + 1);
        if (x_toShoot < 0 || y_toShoot < 0) {
            memory.setTankIsHitedCompeletly(true);
            randomShoot(playerToBeShooted);
        } else {
            //oon tarafesh dafaate ghabl ke randomshoottofinddirection run shode mised hit khorde
            if (playerToBeShooted.getField().getCell(x_toShoot, y_toShoot).isShooted()) {
                if (playerToBeShooted.getField().getCell(x_toShoot, y_toShoot).isFree()) {
                    memory.setTankIsHitedCompeletly(true);
                    randomShoot(playerToBeShooted);
                } else {
                    shootFoundedTankInOtherSide(playerToBeShooted);
                }
            } else if (!playerToBeShooted.beShooted(x_toShoot, y_toShoot)) {
                memory.setTankIsHitedCompeletly(true);
            }

        }
    }

}
