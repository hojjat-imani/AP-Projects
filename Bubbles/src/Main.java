/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Hojjat
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    
    static int bubbleSize = 70;
    public static void main(String[] args) {
        
//        startServer();
        
        startClient();
    }
    
    private static void startServer(){
        System.out.println("initiating server ...");
        NetworkHandler networkHandler = new NetworkHandler(7645);
        System.out.println("Watting for client to connect ...");
        networkHandler.acceptClient();
        System.out.println("done!");
        System.out.println("Staring ...");
        MainFrame bubbles = new MainFrame(networkHandler);
        bubbles.setVisible(true);
    }
    
    private  static void startClient(){
        int port = 7645;
        String ip = "127.0.0.1";
//        String ip = "192.168.81.241";
        ClientHandler clientHandler = new ClientHandler(ip, port);
    }
    
}
