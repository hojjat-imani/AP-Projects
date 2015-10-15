
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Hojjat
 */
public class BattleField {

    private Cell[][] cells;

    public BattleField() {
        cells = new Cell[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                cells[i][j] = new Cell();
            }
        }
    }

    public Cell getCell(int X_position, int Y_position) {
        if (X_position < 10 && X_position >= 0 && Y_position < 10 && Y_position >= 0) {
            return getCells()[X_position][Y_position];
        }
        return null;
    }

    public void printMapShowingTanksAndHits() {
        System.out.print("    ");
        for (int i = 0; i < 10; System.out.printf("%2d  ", i + 1), i++);
        System.out.printf("\n   ");
        for (int i = 0; i < 10; System.out.print("|---"), i++) ;
        System.out.println("|");
        for (int i = 0; i < 10; i++) {
            System.out.printf("%2d ", i + 1);
            for (int j = 0; j < 10; j++) {
                System.out.print("|" + (getCells()[i][j].isFree() ? (getCells()[i][j].isShooted() ? " 0 " : "   ") : (getCells()[i][j].isShooted() ? "\033[41m T \033[0m" : "\033[42m T \033[0m")));
            }
            System.out.printf("|\n   ");
            for (int k = 0; k < 10; System.out.print("|---"), k++) ;
            System.out.println("|");
        }
    }

    public void printMapShowingHits() {
        System.out.print("    ");
        for (int i = 0; i < 10; System.out.printf("%2d  ", i + 1), i++);
        System.out.printf("\n   ");
        for (int i = 0; i < 10; System.out.print("|---"), i++) ;
        System.out.println("|");
        for (int i = 0; i < 10; i++) {
            System.out.printf("%2d ", i + 1);
            for (int j = 0; j < 10; j++) {
                System.out.print("|" + (getCells()[i][j].isShooted() ? (getCells()[i][j].isFree() ? "\033[47m m \033[0m" : "\033[41m H \033[0m") : "   "));
            }
            System.out.printf("|\n   ");
            for (int k = 0; k < 10; System.out.print("|---"), k++) ;
            System.out.println("|");

        }
    }

    /**
     * @return the cells
     */
    public Cell[][] getCells() {
        return cells;
    }

}
