
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Hojjat
 */
public class ClientHandler extends Thread{
    
    private Socket connection;
    private ObjectInputStream input;
    private MainFrameC mainFrame;

    public ClientHandler(String ip, int port) {
        connection = new Socket();

        InetSocketAddress address = new InetSocketAddress(ip, port);

        try {
            connection.connect(address, 7000);
        } catch (SocketTimeoutException ex) {
            System.err.println(ex);
        } catch (IOException ex) {
            System.err.println(ex);
        }
        InputStream in = null;
        try {
            in = connection.getInputStream();
        } catch (IOException ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            input = new ObjectInputStream(in);
        } catch (IOException ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        new Thread(this).start();
    }

    @Override
    public void run() {
        Bubble bubble = null;
        while (true) {
            try {
                bubble = (Bubble) input.readObject();
                Object o = input.readObject();
            } catch (IOException ex) {
                Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (bubble.getSentFor().equals("start")) {
                startClient(bubble);
            } else if (bubble.getSentFor().equals("add")) {
                addBubble(bubble);
            } else if (bubble.getSentFor().equals("remove")) {
                removeBubble(bubble);
            }
        }
    }

    private void startClient(Bubble initialBubble) {
        mainFrame = new MainFrameC(initialBubble);
        mainFrame.setVisible(true);
    }

    private void addBubble(Bubble bubble) {
        mainFrame.addBubble(bubble);
    }

    private void removeBubble(Bubble bubble) {
        mainFrame.removeBubble(bubble);;
        
    }
    
}
