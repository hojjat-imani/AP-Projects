/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bubblesclient;

/**
 *
 * @author Hojjat
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int port = 7645;
        String ip = "127.0.0.1";
        ClientHandler clientHandler = new ClientHandler(ip,port);
    }
    
}
