/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javadownloadmanagerbyhojjat;

import java.awt.Component;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Hojjat
 */
public class ProgressRenderer extends JProgressBar implements TableCellRenderer {

    public ProgressRenderer() {
        super();
        setStringPainted(true);
    }

    public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int column) {

        setValue((int) value);
        return this;
    }

}
