
import java.util.Scanner;

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
    public static void main(String[] args) {
        Scanner inputReader = new Scanner(System.in);
        String input;
        String player1_Name;
        String player2_Name;
        System.out.println("\033[31mB \033[32mA \033[33mT \033[34mT \033[35mL \033[36mE   \033[32mS \033[31mH \033[34mI \033[33mP\033[0m");
        System.out.println("===============================");
        System.out.println("[1] Human vs Human Game");
        System.out.println("[2] Human vs Computer Game");
        System.out.print("choose         -> ");
        boolean inputIsInvalid = false;
        do {
            if(inputIsInvalid){
                System.out.print(">>Try again    -> ");
                inputIsInvalid = false;
            }
            input = inputReader.nextLine();
            if (input.equals("1")) {
                System.out.println("");
                System.out.println("\033[31mPlayer1!!\033[0m");
                System.out.print("Enter your name-> ");
                player1_Name = inputReader.nextLine();
                System.out.println("");
                System.out.println("\033[31mPlayer2!!\033[0m");
                System.out.print("Enter your name-> ");
                player2_Name = inputReader.nextLine();
                Game_2Player newGame = new Game_2Player(player1_Name, player2_Name);
                newGame.startGame();
            } else if (input.equals("2")) {
                System.out.println("");
                System.out.print("Enter your name-> ");
                player1_Name = inputReader.nextLine();
                Game_1Player newGame = new Game_1Player(player1_Name);
                newGame.startGame();
            } else {
                System.out.println("                        \033[31mInvalid!!\033[0m");
                inputIsInvalid = true;
            }
        } while (inputIsInvalid);
    }

}
