/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javadownloadmanagerbyhojjat;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.table.AbstractTableModel;
import jdlib.Download;

/**
 *
 * @author Hojjat
 */
public class ScheduledDownloadsTableModel extends AbstractTableModel {

    private ArrayList<Download> downloads;
    private ArrayList<String> startTimes;
    private ArrayList<Integer> sleepTimes;
    private int indexOfDownloadInProgress = -1;
    private String columnNames[] = {"File-Name", "Start at", "Status"};

    public ScheduledDownloadsTableModel() {
        downloads = new ArrayList<>();
        startTimes = new ArrayList<>();
        sleepTimes = new ArrayList<>();
    }

    public int getRowCount() {
        return downloads.size();
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public String getColumnName(int index) {
        return columnNames[index];
    }

    @Override
    public Object getValueAt(int row, int column) {
        Download download = downloads.get(row);
        switch (column) {
            case 0:
                return download.getFileName();
            case 1:
                return startTimes.get(row);
            case 2:
                if (DownloadsTableModel.contains(download)) {
                    return "Added";
                }
                return "Waiting";
            default:
                return null;
        }
    }

    public void addNewDownload(Download download, int timeToSleep) {
        downloads.add(download);
        startTimes.add(ConvertSleepTimeToStartTime(timeToSleep));
        sleepTimes.add(timeToSleep);
        fireTableRowsInserted(downloads.indexOf(download), downloads.indexOf(download));
        indexOfDownloadInProgress = downloads.indexOf(download);
        new Thread(new Runnable() {
            @Override
            public void run() {
                Download download = downloads.get(indexOfDownloadInProgress);
                int sleepTime = sleepTimes.get(indexOfDownloadInProgress);
                indexOfDownloadInProgress = -1;
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
                MainFrame.addDownload(download);
                fireTableRowsUpdated(downloads.indexOf(download), downloads.indexOf(download));
            }
        }).start();
    }

    private String ConvertSleepTimeToStartTime(int sleepTime) {
        return null;
    }
}
