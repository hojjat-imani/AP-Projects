/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javadownloadmanagerbyhojjat;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JProgressBar;
import javax.swing.table.AbstractTableModel;
import jdlib.Download;

/**
 *
 * @author Hojjat
 */
public class DownloadsTableModel extends AbstractTableModel implements Observer {

    private static ArrayList<Download> downloads;
    private String columnNames[] = {"File-Name", "Progress", "Size", "Speed", "Status"};
    private Class[] columnClasses = {String.class,
        JProgressBar.class, String.class, String.class, String.class};

    public DownloadsTableModel() {
        downloads = new ArrayList<Download>();
    }

    public int getRowCount() {
        return downloads.size();
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public Class getColumnClass(int col) {
        return columnClasses[col];
    }

    public String getColumnName(int index) {
        return columnNames[index];
    }

    @Override
    public Object getValueAt(int row, int column) {
        switch (column) {
            case 0:
                return downloads.get(row).getFileName();
            case 1:
                return (int) downloads.get(row).getProgress();
            case 2:
                long size = downloads.get(row).getSize();
                if (size < 0) {
                    return "Unknown";
                } else if (size < 1024) {
                    return size + " B";
                } else if (size < 1048576) {
                    return (((int) ((size / (float) 1024) * 100)) / (float) 100) + " KB";
                } else if (size < 1073741824) {
                    return (((int) ((size / (float) 1048576) * 100)) / (float) 100) + " MB";
                } else {
                    return (((int) ((size / (float) 1073741824) * 100)) / (float) 100) + " GB";
                }
            case 3:
                return downloads.get(row).getSpeed();
            case 4:
                return downloads.get(row).getStatus();
            default:
                return null;
        }
    }

    public void update(Observable observable, Object object) {
        if (observable == MainFrame.selectedDownload) {
            MainFrame.updateControlButtonsState();
        }
        int indexOfUpdatedRow = downloads.indexOf(observable);
        fireTableRowsUpdated(indexOfUpdatedRow, indexOfUpdatedRow);
    }

    public void addDownload(Download newDownload) {
        downloads.add(newDownload);
        newDownload.addObserver(this);
        fireTableRowsInserted(downloads.indexOf(newDownload), downloads.indexOf(newDownload));
    }

    public Download getDownload(int index) {
        return downloads.get(index);
    }

    public void removeDownload(Download downloadToBeRemoved) {
        int indexOfDownloadToBeRemoved = downloads.indexOf(downloadToBeRemoved);
        downloads.remove(downloadToBeRemoved);
        fireTableRowsDeleted(indexOfDownloadToBeRemoved, indexOfDownloadToBeRemoved);
    }

    public int getNumberOfActiveDownloads() {
        int activeDownloadsCount = 0;
        for (Download download : downloads) {
            if (download.getStatus().equalsIgnoreCase("downloading")) {
                activeDownloadsCount++;
            }
        }
        return activeDownloadsCount;
    }

    static public boolean contains(Download download) {
        if (downloads.indexOf(download) == -1) {
            return false;
        }
        return true;
    }
}
