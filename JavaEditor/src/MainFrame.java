
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Hojjat
 */
public class MainFrame extends JFrame implements ActionListener {

    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private Color textColor = Color.BLACK;
    private Color backgroundColor = Color.WHITE;
    private JColorChooser colorChooser;
    private JTextPane textArea;
    private JCheckBox bold;
    private JCheckBox italic;
    private JCheckBox underlined;
    private JComboBox fontChooser;
    private JSpinner fontSizeChooser;
    private JButton newFile;
    private JButton open;
    private JButton save;
    private JButton saveAs;
    private JButton chooseTextColorButton;
    private JButton chooseBackgroundColorButton;
    private Font currentFont;

    private String savePath;

    public MainFrame() throws HeadlessException {
        colorChooser = new JColorChooser();
        currentFont = new Font("", Font.PLAIN, 25);
        newFile = new JButton("New");
        open = new JButton("open");
        save = new JButton("Save");
        save.setEnabled(false);
        saveAs = new JButton("save as");

        save.addActionListener(this);
        open.addActionListener(this);
        saveAs.addActionListener(this);

        bold = new JCheckBox("bold");
        italic = new JCheckBox("italic");
        underlined = new JCheckBox("underlined");

        chooseTextColorButton = new JButton("Text Color");
        chooseTextColorButton.addActionListener(this);
        chooseBackgroundColorButton = new JButton("Background Color");
        chooseBackgroundColorButton.addActionListener(this);

        fontChooser = new JComboBox();
        //add all available fonts of system to the fontComboBox
        GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
        for (String font : e.getAvailableFontFamilyNames()) {
            fontChooser.addItem(font);
        }

        fontChooser.addActionListener(this);

        SpinnerNumberModel spinnerNumberModel = new SpinnerNumberModel(25, 1, 999, 1);
        fontSizeChooser = new JSpinner(spinnerNumberModel);
        fontSizeChooser.setPreferredSize(new Dimension(40, (int) fontChooser.getPreferredSize().getHeight()));
        fontSizeChooser.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                updateFontSize();
            }
        });

        JPanel toolBar = new JPanel();
        toolBar.add(newFile);
        toolBar.add(open);
        toolBar.add(save);
        toolBar.add(saveAs);
        toolBar.add(fontChooser);
        toolBar.add(fontSizeChooser);
        toolBar.add(bold);
        toolBar.add(italic);
        toolBar.add(underlined);
        toolBar.add(chooseTextColorButton);
        toolBar.add(chooseBackgroundColorButton);

        this.add(BorderLayout.NORTH, toolBar);
        textArea = new JTextPane();
        //this is because notepad skips any \n that is not preceded by a \r while
        //textpans default for new lines is \n (not \r\n)
        textArea.getDocument().putProperty(DefaultEditorKit.EndOfLineStringProperty, "\r\n");
        JScrollPane textAreaScrollPane = new JScrollPane(textArea);
        textArea.setFont(currentFont);
        textArea.setText("hojjat");
        this.add(BorderLayout.CENTER, textAreaScrollPane);
        Dimension frameSize = new Dimension(1000, 480);
        this.setBounds(screenSize.width / 2 - frameSize.width / 2, screenSize.height / 2 - frameSize.height / 2, frameSize.width, frameSize.height);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        bold.addActionListener(this);
        italic.addActionListener(this);
        underlined.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == bold) {
            updateBoldState();
        } else if (actionEvent.getSource() == italic) {
            updateItalicState();
        } else if (actionEvent.getSource() == underlined) {
            updateUnderlineState();
        } else if (actionEvent.getSource() == fontChooser) {
            updateFont();
        } else if (actionEvent.getSource() == chooseTextColorButton) {
            updateTextColor();
        } else if (actionEvent.getSource() == chooseBackgroundColorButton) {
            updateBackgroundColor();
        } else if (actionEvent.getSource() == save) {
            save();
        } else if (actionEvent.getSource() == open) {
            open();
        } else if (actionEvent.getSource() == saveAs) {
            saveAs();
        }
    }

    public void updateBoldState() {
        if (bold.isSelected()) {
            currentFont = new Font(currentFont.getName(), currentFont.getStyle() + Font.BOLD, currentFont.getSize());
        } else {
            currentFont = new Font(currentFont.getName(), currentFont.getStyle() - Font.BOLD, currentFont.getSize());
        }
        textArea.setFont(currentFont);

    }

    public void updateItalicState() {
        if (italic.isSelected()) {
            currentFont = new Font(currentFont.getName(), currentFont.getStyle() + Font.ITALIC, currentFont.getSize());
        } else {
            currentFont = new Font(currentFont.getName(), currentFont.getStyle() - Font.ITALIC, currentFont.getSize());
        }
        textArea.setFont(currentFont);
    }

    public void updateUnderlineState() {
        SimpleAttributeSet underlineAttribute = new SimpleAttributeSet();
        if (underlined.isSelected()) {
            StyleConstants.setUnderline(underlineAttribute, true);
        } else {
            StyleConstants.setUnderline(underlineAttribute, false);
        }
        textArea.getStyledDocument().setCharacterAttributes(0, textArea.getText().length(),
                underlineAttribute, false);
    }

    private void updateFont() {
        currentFont = new Font((String) fontChooser.getSelectedItem(), currentFont.getStyle(), currentFont.getSize());
        textArea.setFont(currentFont);
    }

    private void updateFontSize() {
        currentFont = new Font(currentFont.getName(), currentFont.getStyle(), (int) fontSizeChooser.getValue());
        textArea.setFont(currentFont);
    }

    public void updateTextColor() {
        textColor = colorChooser.showDialog(null, "Choose Color", textColor);
        if (textColor == null) {
            textColor = textArea.getForeground();
        } else {
            textArea.setForeground(textColor);
        }
    }

    private void updateBackgroundColor() {
        backgroundColor = colorChooser.showDialog(null, "Choose Color", backgroundColor);
        if (backgroundColor == null) {
            backgroundColor = textArea.getBackground();
        } else {
            textArea.setBackground(backgroundColor);
        }
    }

    private void save() {
        BufferedWriter outFile = null;
        try {
            File f = new File(savePath);
            outFile = new BufferedWriter(new FileWriter(f));
            textArea.write(outFile);
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Problem in saving file!", "Error #1", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                outFile.close();
            } catch (IOException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Problem in saving file!", "Error #2", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void open() {
        if (!askForSaving()) {
            return;
        }
        String savePath = showOpenBrowsingFrame();
        if (savePath == null) {
            return;
        }
        this.savePath = savePath;
        BufferedReader inFile = null;
        try {
            inFile = new BufferedReader(new FileReader(savePath));
            try {
                textArea.read(inFile, null);
            } catch (IOException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Problem in loading file!", "Error #3", JOptionPane.ERROR_MESSAGE);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "File not Found! ", "Error #4", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                inFile.close();
            } catch (IOException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Problem in loading file!", "Error #5", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private boolean saveAs() {
        String savePath = showSaveLocationChoosingFrame();
        if (savePath != null) {
            this.savePath = savePath;
            save.setEnabled(true);
            save();
            JOptionPane.showMessageDialog(this, "Saved successfully!!", "saving", JOptionPane.INFORMATION_MESSAGE);
            return true;
        }
        return false;
    }

    private String showSaveLocationChoosingFrame() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text ", new String[]{"txt"});
        fileChooser.addChoosableFileFilter(filter);
        fileChooser.setFileFilter(filter);
        File selectedFile = new File("MyText.txt");
        fileChooser.setSelectedFile(selectedFile);
        int pressedButton = fileChooser.showSaveDialog(this);
        if (pressedButton == JFileChooser.APPROVE_OPTION) {
            if (fileChooser.getSelectedFile().exists()) {
                int choice = JOptionPane.showConfirmDialog(fileChooser, "Replace with last file?", "Replace?", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.NO_OPTION) {
                    saveAs();
                    return null;
                }
            }
            return fileChooser.getSelectedFile().getPath();
        }

        return null;
    }

    private String showOpenBrowsingFrame() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text ", new String[]{"txt"});
        fileChooser.addChoosableFileFilter(filter);
        fileChooser.setFileFilter(filter);
//        File selectedFile = new File("MyText.txt");
//        fileChooser.setSelectedFile(selectedFile);
        int pressedButton = fileChooser.showOpenDialog(this);
        if (pressedButton == JFileChooser.APPROVE_OPTION) {
//            if (fileChooser.getSelectedFile().exists()) {
//                int choice = JOptionPane.showConfirmDialog(fileChooser, "Replace with last file?", "Replace?", JOptionPane.YES_NO_OPTION);
//                if (choice == JOptionPane.NO_OPTION) {
//                    saveAs();
//                    return null;
//                }
//            }
            return fileChooser.getSelectedFile().getPath();
        }

        return null;
    }

    private boolean askForSaving() {
        int choice = JOptionPane.showConfirmDialog(this, "Do yoou watn to save this document", "Save?", JOptionPane.YES_NO_CANCEL_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            if (savePath != null) {
                save();
                return true;
            } else {
                Boolean saved = saveAs();
                if (saved) {
                    return true;
                }
            }
        } else if (choice == JOptionPane.NO_OPTION) {
            return true;
        }
        return false;
    }
//
//    private void setStyle() {
//
//    }
}
