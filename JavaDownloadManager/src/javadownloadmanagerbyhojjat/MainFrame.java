package javadownloadmanagerbyhojjat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import jdlib.Download;

/**
 *
 * @author Hojjat
 */
public class MainFrame extends JFrame {

    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    private static JMenuBar menuBar;
    private static JMenuItem resumeMenuButton;
    private static JMenuItem pauseMenuButton;
    private static JMenuItem cancelMenuButton;
    private static JMenuItem removeMenuButton;
    private static JMenuItem settingsMenuButton;
    private static JMenuItem queueingMenuButton;
    private JMenuItem aboutMenuButton;

    private JFrame moreOptionFrame;
    private JFrame settingsFrame;
    private JFrame aboutFrame;
    private JFrame scheduledDownloadsFrame;
    private JScrollPane scheduledDownloadsFrameScrollPane;
    
    private ScheduledDownloadsTableModel scheduledDownloadsTableModel;

    private JTextField specificSavePathField;
    private JTextField defaultSavePathField;

    private JLabel maxNumberOfActiveDownloadsLable;
    private int maxNumberOfActiveDownloadsToBeSet;
    static private int maxNumberOfDownloadsAtOnce = -1;
    private JPanel addNewDownloadPanel;
    private JTextField newDownloadURLField;
    private JButton addDownloadButton;
    private JButton moreOptionOnNewDownload;

    private JPanel mainButtonsPanel;
    private JButton settingsButton;
    private JButton queueingButton;

    private JPanel controlButtonsPanel;
    private static JButton resumeButton;
    private static JButton pauseButton;
    private static JButton cancelButton;
    private static JButton removeButton;

    private JLabel startAfterLabel;
    private int startAfterTimeToBeSet;
    private int startAfterTime;

    private JPanel downloadsTablePanel;
    private JTable downloadsTable;
    static private DownloadsTableModel downloadsTableModel;

    private String defaultSavePath = "E:\\Study\\Java\\jdbdownload";
    private String specificSavePath;

    private URL url;
    public static Download selectedDownload;

    private JPanel chooseBackgroundColorPanel;
    private JLabel orange;
    private JLabel lightBlue;
    private JLabel purple;
    private JLabel blue;
    private JLabel red;
    private JLabel darkGray;
    private JLabel green;

    private Color framesColor = new Color(0, 191, 191);

    public MainFrame() {
//        preparing JFrame
        this.setTitle("Java Download Manager - JDM");
        this.setIconImage((new ImageIcon(getClass().getResource("icons/mainIcon1.png"))).getImage());
        this.setLayout(new FlowLayout());
        this.getContentPane().setBackground(framesColor);
//        give size to frame and make it centered
        Dimension frameSize = new Dimension(850, 480);
        this.setBounds(screenSize.width / 2 - frameSize.width / 2, screenSize.height / 2 - frameSize.height / 2, frameSize.width, frameSize.height);

        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setResizable(false);

        //      declaring a buttonHandler for all buttons in Main Frame
        ButtonHandler buttonHandler = new ButtonHandler();

//        preparing menu bar
        menuBar = new JMenuBar();

//      prepating the download menu
        JMenu menuDownload = new JMenu("Download");

        resumeMenuButton = new JMenuItem("Resume", KeyEvent.VK_R);
        resumeMenuButton.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.ALT_MASK));
        resumeMenuButton.addActionListener(buttonHandler);

        pauseMenuButton = new JMenuItem("Pause", KeyEvent.VK_P);
        pauseMenuButton.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.ALT_MASK));
        pauseMenuButton.addActionListener(buttonHandler);

        cancelMenuButton = new JMenuItem("Cancel", KeyEvent.VK_C);
        cancelMenuButton.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.ALT_MASK));
        cancelMenuButton.addActionListener(buttonHandler);

        removeMenuButton = new JMenuItem("Remove", KeyEvent.VK_E);
        removeMenuButton.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.ALT_MASK));
        removeMenuButton.addActionListener(buttonHandler);

        settingsMenuButton = new JMenuItem("Settings", KeyEvent.VK_S);
        settingsMenuButton.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.ALT_MASK));
        settingsMenuButton.addActionListener(buttonHandler);

        queueingMenuButton = new JMenuItem("Queueing", KeyEvent.VK_Q);
        queueingMenuButton.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.ALT_MASK));
        queueingMenuButton.addActionListener(buttonHandler);

        menuDownload.add(resumeMenuButton);
        menuDownload.add(pauseMenuButton);
        menuDownload.add(cancelMenuButton);
        menuDownload.add(removeMenuButton);
        menuDownload.addSeparator();
        menuDownload.add(settingsMenuButton);
        menuDownload.addSeparator();
        menuDownload.add(queueingMenuButton);

//        preparing help menu
        JMenu menuHelp = new JMenu("Help");

        aboutMenuButton = new JMenuItem("About", KeyEvent.VK_A);
        aboutMenuButton.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.ALT_MASK));
        aboutMenuButton.addActionListener(buttonHandler);

        menuHelp.add(aboutMenuButton);

        menuBar.add(menuDownload);
        menuBar.add(menuHelp);
        this.setJMenuBar(menuBar);

//        preparing addnewdownloadpanel
        addNewDownloadPanel = new JPanel(null);
        addNewDownloadPanel.setPreferredSize(new Dimension(600, 110));
        addNewDownloadPanel.setBackground(null);
        addNewDownloadPanel.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.white, 3), "new Download", 1, TitledBorder.DEFAULT_JUSTIFICATION, null, Color.white));
        newDownloadURLField = new JTextField("Enter URL");
        newDownloadURLField.setSize(450, 30);
        newDownloadURLField.setLocation(30, 35);
        addDownloadButton = new JButton(new ImageIcon(getClass().getResource("icons/add1.png")));
        addDownloadButton.setRolloverIcon(new ImageIcon(getClass().getResource("icons/add2.png")));
        addDownloadButton.setSize(67, 67);
        addDownloadButton.setLocation(510, 15);
        addDownloadButton.setBorderPainted(false);
        addDownloadButton.setContentAreaFilled(false);
        addDownloadButton.setToolTipText("Add New Download");
        moreOptionOnNewDownload = new JButton(new ImageIcon(getClass().getResource("icons/moreOptions1.png")));
        moreOptionOnNewDownload.setRolloverIcon(new ImageIcon(getClass().getResource("icons/moreOptions2.png")));
        moreOptionOnNewDownload.setBorderPainted(false);
        moreOptionOnNewDownload.setContentAreaFilled(false);
//        moreOptionOnNewDownload.setText("more options");
        moreOptionOnNewDownload.setToolTipText("More Options");
        moreOptionOnNewDownload.setSize(30, 30);
        moreOptionOnNewDownload.setLocation(560, 75);
//        adding handlers
        newDownloadURLField.addActionListener(buttonHandler);
        addDownloadButton.addActionListener(buttonHandler);
        moreOptionOnNewDownload.addActionListener(buttonHandler);
        addNewDownloadPanel.add(newDownloadURLField);
        addNewDownloadPanel.add(addDownloadButton);
        addNewDownloadPanel.add(moreOptionOnNewDownload);
        this.add(addNewDownloadPanel);

//        clearing Enter Url when textfeild was clicked
//        newDownloadURLField.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                newDownloadURLField.setText("");
//            }
//        });
//        preparing mainButtonsPanel
        mainButtonsPanel = new JPanel();
        mainButtonsPanel.setBackground(null);
        settingsButton = new JButton(new ImageIcon(getClass().getResource("icons/settings1.png")));
        settingsButton.setRolloverIcon(new ImageIcon(getClass().getResource("icons/settings2.png")));
        settingsButton.setToolTipText("Settings");
        settingsButton.setPreferredSize(new Dimension(100, 100));
        settingsButton.setBorderPainted(false);
        settingsButton.setContentAreaFilled(false);
        queueingButton = new JButton(new ImageIcon(getClass().getResource("icons/queueing1.png")));
        queueingButton.setRolloverIcon(new ImageIcon(getClass().getResource("icons/queueing2.png")));
        queueingButton.setToolTipText("Queueing");
        queueingButton.setBorderPainted(false);
        queueingButton.setContentAreaFilled(false);
        queueingButton.setPreferredSize(new Dimension(100, 100));

        settingsButton.addActionListener(buttonHandler);
        queueingButton.addActionListener(buttonHandler);
        mainButtonsPanel.add(settingsButton);
        mainButtonsPanel.add(queueingButton);
        this.add(mainButtonsPanel);

//        
////        adding a free panel to add some gap
//        JPanel gapPanel = new JPanel();
//        gapPanel.setPreferredSize(new Dimension(850, 10));
//        this.add(gapPanel);
//        
//        preparing controlButtonsPanel
        controlButtonsPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        controlButtonsPanel.setBackground(null);
//        controlButtonsPanel.setBorder(new LineBorder(Color.WHITE, 3));
        resumeButton = new JButton(new ImageIcon(getClass().getResource("icons/resume1.png")));
        resumeButton.setRolloverIcon(new ImageIcon(getClass().getResource("icons/resume2.png")));
        resumeButton.setToolTipText("Resume");
        resumeButton.setBorderPainted(false);
        resumeButton.setContentAreaFilled(false);
        pauseButton = new JButton(new ImageIcon(getClass().getResource("icons/pause1.png")));
        pauseButton.setRolloverIcon(new ImageIcon(getClass().getResource("icons/pause2.png")));
        pauseButton.setToolTipText("Pause");
        pauseButton.setBorderPainted(false);
        pauseButton.setContentAreaFilled(false);
        cancelButton = new JButton(new ImageIcon(getClass().getResource("icons/cancel1.png")));
        cancelButton.setRolloverIcon(new ImageIcon(getClass().getResource("icons/cancel2.png")));
        cancelButton.setToolTipText("Cancel");
        cancelButton.setBorderPainted(false);
        cancelButton.setContentAreaFilled(false);
        removeButton = new JButton(new ImageIcon(getClass().getResource("icons/remove1.png")));
        removeButton.setRolloverIcon(new ImageIcon(getClass().getResource("icons/remove2.png")));
        removeButton.setToolTipText("Remove");
        removeButton.setBorderPainted(false);
        removeButton.setContentAreaFilled(false);

        resumeButton.addActionListener(buttonHandler);
        pauseButton.addActionListener(buttonHandler);
        cancelButton.addActionListener(buttonHandler);
        removeButton.addActionListener(buttonHandler);
        controlButtonsPanel.add(resumeButton);
        controlButtonsPanel.add(pauseButton);
        controlButtonsPanel.add(cancelButton);
        controlButtonsPanel.add(removeButton);
        updateControlButtonsState();
        this.add(controlButtonsPanel);

//        preparing choose color panel
        chooseBackgroundColorPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 2));
        chooseBackgroundColorPanel.setPreferredSize(new Dimension(800, 12));
        chooseBackgroundColorPanel.setBackground(null);
        orange = new JLabel();
        orange.setPreferredSize(new Dimension(10, 10));
        orange.setBackground(Color.ORANGE);
        orange.setOpaque(true);
        chooseBackgroundColorPanel.add(orange);
        lightBlue = new JLabel();
        lightBlue.setPreferredSize(new Dimension(10, 10));
        lightBlue.setBackground(Color.CYAN);
        lightBlue.setOpaque(true);
        chooseBackgroundColorPanel.add(lightBlue);
        purple = new JLabel();
        purple.setPreferredSize(new Dimension(10, 10));
        purple.setBackground(Color.MAGENTA);
        purple.setOpaque(true);
        chooseBackgroundColorPanel.add(purple);
        blue = new JLabel();
        blue.setPreferredSize(new Dimension(10, 10));
        blue.setBackground(Color.BLUE);
        blue.setOpaque(true);
        chooseBackgroundColorPanel.add(blue);
        red = new JLabel();
        red.setPreferredSize(new Dimension(10, 10));
        red.setBackground(Color.RED);
        red.setOpaque(true);
        chooseBackgroundColorPanel.add(red);
        darkGray = new JLabel();
        darkGray.setPreferredSize(new Dimension(10, 10));
        darkGray.setBackground(Color.DARK_GRAY);
        darkGray.setOpaque(true);
        chooseBackgroundColorPanel.add(darkGray);
        green = new JLabel();
        green.setPreferredSize(new Dimension(10, 10));
        green.setBackground(Color.GREEN);
        green.setOpaque(true);
        chooseBackgroundColorPanel.add(green);
        ColorChooseHandler colorChooseHandler = new ColorChooseHandler();
        orange.addMouseListener(colorChooseHandler);
        lightBlue.addMouseListener(colorChooseHandler);
        purple.addMouseListener(colorChooseHandler);
        blue.addMouseListener(colorChooseHandler);
        red.addMouseListener(colorChooseHandler);
        darkGray.addMouseListener(colorChooseHandler);
        green.addMouseListener(colorChooseHandler);

//        preparing downloadsTablePanel
        downloadsTablePanel = new JPanel(new BorderLayout());

        downloadsTablePanel.setBackground(
                null);
        downloadsTablePanel.setPreferredSize(
                new Dimension(720, 280));
        downloadsTablePanel.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.white, 3), "Downloads", 1, TitledBorder.DEFAULT_JUSTIFICATION, null, Color.white));
        downloadsTableModel = new DownloadsTableModel();
        downloadsTable = new JTable(downloadsTableModel);
        JScrollPane downloadsTableScrollPane = new JScrollPane(downloadsTable);

        downloadsTableScrollPane.getViewport().setBackground(Color.WHITE);
        downloadsTablePanel.add(downloadsTableScrollPane);

        this.add(downloadsTablePanel);

        this.add(chooseBackgroundColorPanel);

        downloadsTable.setAutoCreateRowSorter(true);
        ProgressRenderer renderer = new ProgressRenderer();

        downloadsTable.setDefaultRenderer(JProgressBar.class, renderer);
        downloadsTable.setRowHeight(25);
        downloadsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        downloadsTable.getSelectionModel()
                .addListSelectionListener(new ListSelectionListener() {

                    @Override
                    public void valueChanged(ListSelectionEvent e
                    ) {
                        int selectedRow = downloadsTable.getSelectedRow();
                        if (selectedRow == -1) {
                            selectedDownload = null;
                        } else {
                            selectedDownload = downloadsTableModel.getDownload(selectedRow);
                        }
                        updateControlButtonsState();
                    }
                }
                );

        downloadsTable.addMouseListener(
                new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent mouseEvent
                    ) {
                        if (mouseEvent.getClickCount() > 1) {
//                    JOptionPane.showMessageDialog(null, "must be open");
                            try {
                                Desktop.getDesktop().open(new File(selectedDownload.getFilePath()));
                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(null, "Can not open!");
                            }
                        }
                    }
                }
        );
        newDownloadURLField.addMouseListener(
                new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e
                    ) {
                        newDownloadURLField.setText("");
                    }
                }
        );

    }

    private class ColorChooseHandler extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            JLabel clickedColor = (JLabel) mouseEvent.getSource();
            if (clickedColor == orange) {
                changeColorOfFrame(245, 147, 49);
            } else if (clickedColor == lightBlue) {
                changeColorOfFrame(0, 191, 191);
            } else if (clickedColor == purple) {
                changeColorOfFrame(155, 89, 182);
            } else if (clickedColor == blue) {
                changeColorOfFrame(68, 108, 179);
            } else if (clickedColor == red) {
                changeColorOfFrame(245, 91, 92);
            } else if (clickedColor == darkGray) {
                changeColorOfFrame(48, 49, 53);
            } else if (clickedColor == green) {
                changeColorOfFrame(0, 155, 37);
            }
        }
    }

    private class ButtonHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            Object eventSource = actionEvent.getSource();
            if (eventSource == addDownloadButton || eventSource == newDownloadURLField) {
                addNewDownload();
            } else if (eventSource == moreOptionOnNewDownload) {
                showMoreOptionsFrame();
            } else if (eventSource == settingsButton || eventSource == settingsMenuButton) {
                showSettingsFrame();
            } else if (eventSource == queueingButton) {
            } else if (eventSource == resumeButton || eventSource == resumeMenuButton) {
                resumeDownload();
            } else if (eventSource == pauseButton || eventSource == pauseMenuButton) {
                pauseDownload();
            } else if (eventSource == cancelButton || eventSource == cancelMenuButton) {
                cancelDownload();
            } else if (eventSource == removeButton || eventSource == removeMenuButton) {
                removeDownload();
            } else if (eventSource == aboutMenuButton) {
                showAboutFrame();
            }
        }
    }

    private void addNewDownload() {
        try {
            url = new URL(newDownloadURLField.getText());
        } catch (MalformedURLException ex) {
            newDownloadURLField.setText("Enter URL");
            JOptionPane.showMessageDialog(this, "invalid URL!", "invalid", JOptionPane.ERROR_MESSAGE, null);
            return;
        }
        Download newDownload = new Download(url, specificSavePath == null ? defaultSavePath : specificSavePath);
        if (startAfterTime > 0) {
            if (scheduledDownloadsFrame == null) {
                makeSchduledDownloadsFrame();
            }
            scheduledDownloadsTableModel.addNewDownload(newDownload, startAfterTime * 1000);
            scheduledDownloadsFrame.setVisible(true);
        } else {
            addDownload(newDownload);
        }
        newDownloadURLField.setText("Enter URL");
        startAfterTime = 0;
        specificSavePath = null;
    }

    static public void addDownload(Download newDownload) {
        downloadsTableModel.addDownload(newDownload);
        if (!(maxNumberOfDownloadsAtOnce < 0) && downloadsTableModel.getNumberOfActiveDownloads() + 1 > maxNumberOfDownloadsAtOnce) {
            JOptionPane.showMessageDialog(null, "Number of downloading items is at maximum! downloads add but do not start!");
        } else {
            newDownload.start();
        }
    }

    private void addNewDownloadexpired() {
        try {
            url = new URL(newDownloadURLField.getText());
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Download newDownload = new Download(url, specificSavePath == null ? defaultSavePath : specificSavePath);
                    specificSavePath = null;
                    if (startAfterTime > 0) {
                        JOptionPane.showMessageDialog(null, "Desired download will start automaticly in " + startAfterTime + " seconds", "Schedualed download", JOptionPane.INFORMATION_MESSAGE);
                        int sleepTime = startAfterTime * 1000;
                        startAfterTime = 0;
                        try {
                            Thread.sleep(sleepTime);
                        } catch (InterruptedException ex) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    downloadsTableModel.addDownload(newDownload);
                    if (!(maxNumberOfDownloadsAtOnce < 0) && downloadsTableModel.getNumberOfActiveDownloads() + 1 > maxNumberOfDownloadsAtOnce) {
                        JOptionPane.showMessageDialog(null, "Number of downloading items is at maximum! download does not start!");
                    } else {
                        newDownload.start();
                    }
                }
            }).start();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "invalid URL!", "invalid", JOptionPane.ERROR_MESSAGE, null);
            specificSavePath = null;
            startAfterTime = 0;
        } finally {
            newDownloadURLField.setText("Enter URL");
        }
    }

    private void scheduleDownload() {

    }

    private void showScheduledDownloadsFrame() {

    }

    private void resumeDownload() {
        if (!(maxNumberOfDownloadsAtOnce < 0) && downloadsTableModel.getNumberOfActiveDownloads() + 1 > maxNumberOfDownloadsAtOnce) {
            JOptionPane.showMessageDialog(null, "Can't resume! number of downloading items is at maximum!");
        } else {
            selectedDownload.resume();
            updateControlButtonsState();
        }
    }

    private void pauseDownload() {
        selectedDownload.pause();
        updateControlButtonsState();
    }

    private void cancelDownload() {
        selectedDownload.cancel();
        updateControlButtonsState();
    }

    private void removeDownload() {
        if (selectedDownload.getStatus().equalsIgnoreCase("paused")) {
            selectedDownload.cancel();
        }
        downloadsTableModel.removeDownload(selectedDownload);
        selectedDownload = null;
        updateControlButtonsState();
    }

    public static void updateControlButtonsState() {
        if (selectedDownload == null) {
            resumeButton.setEnabled(false);
            resumeMenuButton.setEnabled(false);
            pauseButton.setEnabled(false);
            pauseMenuButton.setEnabled(false);
            cancelButton.setEnabled(false);
            cancelMenuButton.setEnabled(false);
            removeButton.setEnabled(false);
            removeMenuButton.setEnabled(false);
        } else if (selectedDownload.getStatus().equalsIgnoreCase("downloading")) {
            resumeButton.setEnabled(false);
            resumeMenuButton.setEnabled(false);
            pauseButton.setEnabled(true);
            pauseMenuButton.setEnabled(true);
            cancelButton.setEnabled(true);
            cancelMenuButton.setEnabled(true);
            removeButton.setEnabled(false);
            removeMenuButton.setEnabled(false);
        } else if (selectedDownload.getStatus().equalsIgnoreCase("paused")) {
            resumeButton.setEnabled(true);
            resumeMenuButton.setEnabled(true);
            pauseButton.setEnabled(false);
            pauseMenuButton.setEnabled(false);
            cancelButton.setEnabled(true);
            cancelMenuButton.setEnabled(true);
            removeButton.setEnabled(true);
            removeMenuButton.setEnabled(true);
        } else if (selectedDownload.getStatus().equalsIgnoreCase("complete")) {
            resumeButton.setEnabled(false);
            resumeMenuButton.setEnabled(false);
            pauseButton.setEnabled(false);
            pauseMenuButton.setEnabled(false);
            cancelButton.setEnabled(false);
            cancelMenuButton.setEnabled(false);
            removeButton.setEnabled(true);
            removeMenuButton.setEnabled(true);
        } else if (selectedDownload.getStatus().equalsIgnoreCase("canceled")) {
            resumeButton.setEnabled(true);
            resumeMenuButton.setEnabled(true);
            pauseButton.setEnabled(false);
            pauseMenuButton.setEnabled(false);
            cancelButton.setEnabled(false);
            cancelMenuButton.setEnabled(false);
            removeButton.setEnabled(true);
            removeMenuButton.setEnabled(true);
        } else if (selectedDownload.getStatus().equalsIgnoreCase("error")) {
            resumeButton.setEnabled(true);
            resumeMenuButton.setEnabled(true);
            pauseButton.setEnabled(false);
            pauseMenuButton.setEnabled(false);
            cancelButton.setEnabled(true);
            cancelMenuButton.setEnabled(true);
            removeButton.setEnabled(true);
            removeMenuButton.setEnabled(true);
        }
    }

    private void changeColorOfFrame(int r, int g, int b) {
        framesColor = new Color(r, g, b);
        this.getContentPane().setBackground(framesColor);
        if(scheduledDownloadsFrameScrollPane != null){
        scheduledDownloadsFrameScrollPane.getViewport().setBackground(framesColor);}
        UIManager UI = new UIManager();
        UI.put("OptionPane.background", new Color(r, g, b));
        UI.put("Panel.background", new Color(r, g, b));
    }

    private void showMoreOptionsFrame() {
//        disabling the main frame
        this.setEnabled(false);

        startAfterTimeToBeSet = startAfterTime;

        moreOptionFrame = new JFrame("More Options");
        moreOptionFrame.setLayout(new FlowLayout());
        moreOptionFrame.getContentPane().setBackground(framesColor);
        //        give size to frame and make it centered
        Dimension frameSize = new Dimension(500, 250);
        moreOptionFrame.setBounds(screenSize.width / 2 - frameSize.width / 2, screenSize.height / 2 - frameSize.height / 2, frameSize.width, frameSize.height);
        moreOptionFrame.setResizable(false);
        moreOptionFrame.setVisible(true);

        JLabel savePathLable = new JLabel("Save to: ");
        savePathLable.setForeground(Color.white);
        specificSavePathField = new JTextField(specificSavePath == null ? "default: " + defaultSavePath : specificSavePath, 30);
        JButton browseButton = new JButton(new ImageIcon(getClass().getResource("icons/browse1.png")));
        browseButton.setRolloverIcon(new ImageIcon(getClass().getResource("icons/browse2.png")));
        browseButton.setToolTipText("browse");
        browseButton.setContentAreaFilled(false);
        browseButton.setBorderPainted(false);

        startAfterLabel = new JLabel("This download will start after:      " + startAfterTime + " seconds");
        startAfterLabel.setForeground(Color.WHITE);
        startAfterLabel.setPreferredSize(new Dimension(380, 20));

        JButton changeButton = new JButton(new ImageIcon(getClass().getResource("icons/schedule1.png")));
        changeButton.setRolloverIcon(new ImageIcon(getClass().getResource("icons/schedule2.png")));
        changeButton.setToolTipText("Change");
        changeButton.setBorderPainted(false);
        changeButton.setContentAreaFilled(false);

        JButton applyButton = new JButton(new ImageIcon(getClass().getResource("icons/apply1.png")));
        applyButton.setRolloverIcon(new ImageIcon(getClass().getResource("icons/apply2.png")));
        applyButton.setToolTipText("Apply");
        applyButton.setContentAreaFilled(false);
        applyButton.setBorderPainted(false);
        JButton abortButton = new JButton(new ImageIcon(getClass().getResource("icons/abort1.png")));
        abortButton.setRolloverIcon(new ImageIcon(getClass().getResource("icons/abort2.png")));
        abortButton.setToolTipText("abort");
        abortButton.setContentAreaFilled(false);
        abortButton.setBorderPainted(false);

        moreOptionFrame.add(savePathLable);
        moreOptionFrame.add(specificSavePathField);
        moreOptionFrame.add(browseButton);
        moreOptionFrame.add(startAfterLabel);
        moreOptionFrame.add(changeButton);
        moreOptionFrame.add(applyButton);
        moreOptionFrame.add(abortButton);

        changeButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                SpinnerNumberModel spinnerModel = new SpinnerNumberModel(10, 1, null, 1);
                JSpinner spinner = new JSpinner(spinnerModel);
                JOptionPane.showMessageDialog(settingsFrame, spinner, "Change Start After", JOptionPane.PLAIN_MESSAGE);
                startAfterTimeToBeSet = (int) spinner.getValue();
                startAfterLabel.setText("This download will start after:      " + startAfterTimeToBeSet + " seconds");
            }
        });

        applyButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                specificSavePath = (specificSavePathField.getText().equals("default: " + defaultSavePath)) ? null : specificSavePathField.getText();
                startAfterTime = startAfterTimeToBeSet;
                setEnabled(true);
                moreOptionFrame.dispose();
            }
        });

        abortButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setEnabled(true);
                moreOptionFrame.dispose();
            }
        });
        browseButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String newPath = showSaveLocationChoosingFrame();
                if (newPath != null) {
                    specificSavePathField.setText(newPath);
                }
            }
        }
        );

        moreOptionFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                setEnabled(true);
                moreOptionFrame.dispose();
            }
        });
    }

    private String showSaveLocationChoosingFrame() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);
        int pressedButton = fileChooser.showSaveDialog(moreOptionFrame);
        if (pressedButton == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getCurrentDirectory().getAbsolutePath();
        }
        return null;
    }

    private void showSettingsFrame() {
//        disabling the main frame
        this.setEnabled(false);

        settingsFrame = new JFrame("Settings");
        settingsFrame.setLayout(new FlowLayout());
        settingsFrame.getContentPane().setBackground(framesColor);
        //        give size to frame and make it centered
        Dimension frameSize = new Dimension(550, 250);
        settingsFrame.setBounds(screenSize.width / 2 - frameSize.width / 2, screenSize.height / 2 - frameSize.height / 2, frameSize.width, frameSize.height);
        settingsFrame.setResizable(false);
        settingsFrame.setVisible(true);

        JLabel savePathLable = new JLabel("Default Save Path: ");
        savePathLable.setForeground(Color.white);
        defaultSavePathField = new JTextField(defaultSavePath, 30);
        JButton browseButton = new JButton(new ImageIcon(getClass().getResource("icons/browse1.png")));
        browseButton.setRolloverIcon(new ImageIcon(getClass().getResource("icons/browse2.png")));
        browseButton.setToolTipText("browse");
        browseButton.setContentAreaFilled(false);
        browseButton.setBorderPainted(false);

        maxNumberOfActiveDownloadsToBeSet = maxNumberOfDownloadsAtOnce;
        maxNumberOfActiveDownloadsLable = new JLabel("Maximum Number Of Active Downloads:   " + (maxNumberOfDownloadsAtOnce < 0 ? "infinite" : maxNumberOfDownloadsAtOnce));
        maxNumberOfActiveDownloadsLable.setPreferredSize(new Dimension(440, 20));
        maxNumberOfActiveDownloadsLable.setForeground(Color.WHITE);

        JButton changeButton = new JButton(new ImageIcon(getClass().getResource("icons/change1.png")));
        changeButton.setRolloverIcon(new ImageIcon(getClass().getResource("icons/change2.png")));
        changeButton.setToolTipText("Change");
        changeButton.setBorderPainted(false);
        changeButton.setContentAreaFilled(false);

        JButton restoreToDefault = new JButton(new ImageIcon(getClass().getResource("icons/restore1.png")));
        restoreToDefault.setRolloverIcon(new ImageIcon(getClass().getResource("icons/restore2.png")));
        restoreToDefault.setToolTipText("Restore to defaults");
        restoreToDefault.setBorderPainted(false);
        restoreToDefault.setContentAreaFilled(false);

        JButton applyButton = new JButton(new ImageIcon(getClass().getResource("icons/apply1.png")));
        applyButton.setRolloverIcon(new ImageIcon(getClass().getResource("icons/apply2.png")));
        applyButton.setToolTipText("Apply");
        applyButton.setContentAreaFilled(false);
        applyButton.setBorderPainted(false);
        JButton abortButton = new JButton(new ImageIcon(getClass().getResource("icons/abort1.png")));
        abortButton.setRolloverIcon(new ImageIcon(getClass().getResource("icons/abort2.png")));
        abortButton.setToolTipText("Abort");
        abortButton.setContentAreaFilled(false);
        abortButton.setBorderPainted(false);

        settingsFrame.add(savePathLable);
        settingsFrame.add(defaultSavePathField);
        settingsFrame.add(browseButton);
        settingsFrame.add(maxNumberOfActiveDownloadsLable);
        settingsFrame.add(changeButton);
        settingsFrame.add(restoreToDefault);
        settingsFrame.add(applyButton);
        settingsFrame.add(abortButton);

        changeButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                SpinnerNumberModel spinnerModel = new SpinnerNumberModel(10, 1, null, 1);
                JSpinner spinner = new JSpinner(spinnerModel);
                JOptionPane.showMessageDialog(settingsFrame, spinner, "Change Maximum", JOptionPane.PLAIN_MESSAGE);
                maxNumberOfActiveDownloadsToBeSet = (int) spinner.getValue();
                maxNumberOfActiveDownloadsLable.setText("Maximum Number Of Active Downloads:   " + maxNumberOfActiveDownloadsToBeSet);
            }
        });

        restoreToDefault.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                defaultSavePathField.setText("E:\\Study\\Java\\jdbdownload");
                maxNumberOfActiveDownloadsToBeSet = -1;
                maxNumberOfActiveDownloadsLable.setText("Maximum Number Of Active Downloads:   infinite");
            }
        });
        applyButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                defaultSavePath = defaultSavePathField.getText();
                maxNumberOfDownloadsAtOnce = maxNumberOfActiveDownloadsToBeSet;
                setEnabled(true);
                settingsFrame.dispose();
            }
        });

        abortButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setEnabled(true);
                settingsFrame.dispose();
            }
        });
        browseButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String newPath = showSaveLocationChoosingFrame();
                if (newPath != null) {
                    defaultSavePathField.setText(newPath);
                }
            }
        }
        );

        settingsFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
//                reactive the main frame
                setEnabled(true);
                settingsFrame.dispose();
            }
        });
    }

    private void showAboutFrame() {
        //        disabling the main frame
        this.setEnabled(false);

        aboutFrame = new JFrame("About");
        aboutFrame.setLayout(new FlowLayout());
        aboutFrame.getContentPane().setBackground(framesColor);
        //        give size to frame and make it centered
        Dimension frameSize = new Dimension(300, 235);
        aboutFrame.setBounds(screenSize.width / 2 - frameSize.width / 2, screenSize.height / 2 - frameSize.height / 2, frameSize.width, frameSize.height);
        aboutFrame.setResizable(false);
        aboutFrame.setVisible(true);

        JButton icon = new JButton(new ImageIcon(getClass().getResource("icons/mainIcon1.png")));
        icon.setBorderPainted(false);
        icon.setContentAreaFilled(false);
        JLabel aboutLabel1 = new JLabel("Java Download Managaer - JDM");
        aboutLabel1.setForeground(Color.white);

        JLabel aboutLabel3 = new JLabel("Aouthor: Hojjat Imani 9231062 :-)");
        aboutLabel3.setForeground(Color.white);

        JLabel aboutLabel2 = new JLabel("Version 1.0 (4/25/2014)");
        aboutLabel2.setForeground(Color.white);

        aboutFrame.add(aboutLabel1);
        aboutFrame.add(icon);
        aboutFrame.add(aboutLabel2);
        aboutFrame.add(aboutLabel3);

        aboutFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
//                reactive the main frame
                setEnabled(true);
                aboutFrame.dispose();
            }
        });
    }

    private void makeSchduledDownloadsFrame() {
        scheduledDownloadsFrame = new JFrame("Scheduled Downloads Table");
        Dimension frameSize = new Dimension(600, 300);
        scheduledDownloadsFrame.setBounds(screenSize.width / 2 - frameSize.width / 2, screenSize.height / 2 - frameSize.height / 2, frameSize.width, frameSize.height);
        scheduledDownloadsFrame.setResizable(false);
        scheduledDownloadsFrame.setVisible(true);

        scheduledDownloadsTableModel = new ScheduledDownloadsTableModel();
        JTable scheduledDownloadsTable = new JTable(scheduledDownloadsTableModel);
        scheduledDownloadsTable.setRowHeight(25);
        scheduledDownloadsFrameScrollPane = new JScrollPane(scheduledDownloadsTable);
        scheduledDownloadsFrameScrollPane.getViewport().setBackground(framesColor);
        scheduledDownloadsFrame.add(scheduledDownloadsFrameScrollPane, BorderLayout.CENTER);
    }
}
