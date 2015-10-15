
import java.awt.Color;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
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
public class NetworkHandler {

    private ServerSocket serverSocket;
    private Socket connection;
    private ObjectOutputStream output;

    public NetworkHandler(int port) {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException ex) {
            Logger.getLogger(NetworkHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void acceptClient() {
        try {
            connection = serverSocket.accept();
        } catch (IOException ex) {
            Logger.getLogger(NetworkHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            OutputStream out = connection.getOutputStream();
            output = new ObjectOutputStream(out);
        } catch (IOException ex) {
            Logger.getLogger(NetworkHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void informBubbleAdded(Bubble addedBubble) {
        addedBubble.setSentFor("add");
        try {
            output.writeObject(addedBubble);
        } catch (IOException ex) {
            Logger.getLogger(NetworkHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void informStart(Bubble initialBubble) {
        initialBubble.setSentFor("start");
        try {
            output.writeObject(initialBubble);
        } catch (IOException ex) {
            Logger.getLogger(NetworkHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void informBubbleRemoved(Bubble removedBubble) {
        removedBubble.setSentFor("remove");
        try {
            output.writeObject(removedBubble);
        } catch (IOException ex) {
            Logger.getLogger(NetworkHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
