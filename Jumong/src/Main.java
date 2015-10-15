
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

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

    public static Scanner inputReader = new Scanner(System.in);
    public static String input;

    public static void main(String[] args) {
        
        //** add this into your application code as appropriate
// Open an input stream  to the audio file.
InputStream in = null;
//        try {
//            in = new FileInputStream("C:\\Users\\Hojjat\\Desktop\\gui\\sound.wav");
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//// Create an AudioStream object from the input stream.
//AudioStream as = null;         
//        try {
//            as = new AudioStream(in);
//        } catch (IOException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//// Use the static class member "player" from class AudioPlayer to play
//// clip.
//AudioPlayer.player.start(as);            

// Similarly, to stop the audio.
//AudioPlayer.player.stop(as); 
        
        
//        showLogo();
//        int numberOfPlayers = askForNumberOfPlayers();
//        String[] names = new String[numberOfPlayers];
//        names = askForNames(numberOfPlayers);
//        int difficulty = askForDifficulty();
//        showTheManual();
//        System.out.print("\033[31mPress \"Enter\" to start game!\033[0m");
//        inputReader.nextLine();
//        System.out.println("===========================================================================================================================");
//        GameControl game = new GameControl(numberOfPlayers, names, difficulty);
        GameControl game = new GameControl("hojjat", 2);
        game.setVisible(true);
//        game.start();
    }

    private static void showLogo() {
        System.out.println("                                \033[41m J \033[0m \033[42m O \033[0m \033[43m M \033[0m \033[44m U \033[0m \033[45m N \033[0m \033[46m G \033[0m \033[31m!!!\033[0m");
        System.out.println("\033[35m============================================================================================\033[0m");
    }

    private static int askForNumberOfPlayers() {
        String input;
        boolean inputIsInvalid = false;
        int numberOfPlayers = 0;
        System.out.print("[1] 1 Player          ");
        System.out.println("[2] 2 Player");
        System.out.print("[3] 3 Player          ");
        System.out.println("[4] 4 Player");
        System.out.print("Choose:        -> ");
        do {
            if (inputIsInvalid) {
                System.out.print("Try again:     -> ");
                inputIsInvalid = false;
            }
            input = inputReader.nextLine();
            if (input.equals("1")) {
                numberOfPlayers = 1;
            } else if (input.equals("2")) {
                numberOfPlayers = 2;
            } else if (input.equals("3")) {
                numberOfPlayers = 3;
            } else if (input.equals("4")) {
                numberOfPlayers = 4;
            } else {
                System.out.println("                                      \033[31minvalid!!\033[0m");
                inputIsInvalid = true;
            }
        } while (inputIsInvalid);
        System.out.println("                                      \033[32m" + numberOfPlayers + " Player game choosed!!\033[0m");
        System.out.println("--------------------------------------------------------------------------------------------");
        return numberOfPlayers;
    }

    private static String[] askForNames(int numberOfPlayers) {
        Scanner inputReader = new Scanner(System.in);
        String[] names = new String[numberOfPlayers];
        boolean inputIsInvalid = false;
        for (int i = 0; i < numberOfPlayers; i++) {
            if (numberOfPlayers > 1) {
                System.out.println("\033[31m>> Player" + (i + 1) + "!\033[0m");
            }
            System.out.print("Enter your name-> ");
            do {
                if (inputIsInvalid) {
                    System.out.print("Try again:     -> ");
                    inputIsInvalid = false;
                }
                names[i] = inputReader.nextLine();
                if (names[i].length() < 1) {
                    System.out.println("                                      \033[31mName can not be blank!!\033[0m");
                    inputIsInvalid = true;
                }
            } while (inputIsInvalid);
            System.out.println("                                      \033[32mGood luck " + names[i] + "!\033[0m");
        }
        System.out.println("--------------------------------------------------------------------------------------------");
        return names;
    }

    private static int askForDifficulty() {
        String input;
        boolean inputIsInvalid = false;
        int difficulty = 0;
        System.out.println("[1] \033[32mEasy\033[0m");
        System.out.println("[2] \033[33mMedium\033[0m");
        System.out.println("[3] \033[31mHard\033[0m");
        System.out.print("Choose:        -> ");
        do {
            if (inputIsInvalid) {
                System.out.print("Try again:     -> ");
                inputIsInvalid = false;
            }
            input = inputReader.nextLine();
            if (input.equals("1")) {
                difficulty = 1;
            } else if (input.equals("2")) {
                difficulty = 2;
            } else if (input.equals("3")) {
                difficulty = 3;
            } else {
                System.out.println("                                      \033[31minvalid!!\033[0m");
                inputIsInvalid = true;
            }
        } while (inputIsInvalid);
        System.out.println("                                      \033[32mOK! :-)\033[0m");
        return difficulty;
    }

    public static void showTheManual() {
        System.out.println("\033[35m============================================================================================\033[0m");
        System.out.println("                                 \033[31mManual\033[0m");
        System.out.println("");
        System.out.println("\033[34mMain valid commands:\033[0m");
        System.out.println("up                down               left              right");
        System.out.println("pick<itemName>    drop<itemName>     kill<weaponName><enemyNumber>");
        System.out.println("inventory         status             unlock");
        System.out.println("*** manual: shows the manual         *** tile: shows current tile including");
        System.out.println("*** keys: shows a list of your keys");
        System.out.println("*** shoot<weaponName><playerNumber> : use to shoot other players");
        System.out.println("");
        System.out.println("\033[34mDeveloper commands:\033[0m");
        System.out.println("*** dev<maze> : shows the maze");
        System.out.println("*** dev<SetPosition> : use to change your position");
        System.out.println("*** dev<enemy> : shows number of enemys in each tile");
        System.out.println("*** dev<itemName> : shows number of the particalar item in each tile");
        System.out.println("*** dev<player> : shows all players status");
        System.out.println("");
        System.out.println("\033[34mCo0o0o0o0o0o0ol Tip!!\033[0m");
        System.out.println("use the following abbreviations to speed up!");
        System.out.println("SmallArrow: SA         BigArrow: BA           FireArrow: FA            StoneBreaker: SB");
        System.out.println("Key: K                 Hawk: H                Shovel: S                BigBag: BB");
        System.out.println("EnergyPotion: EP       BigHealthPotion: BHP   SmallHealthPotion: SHP   ReviveScroll: RS");
        System.out.println("Chest: C               Maze: M                Enemy: E                 SetPosition: SP");
        System.out.println("Player: P");
        System.out.println("");
        System.out.println("\033[34mAnd!\033[0m");
        System.out.println("Commands are not case sensitive!");
        System.out.println("\033[35m============================================================================================\033[0m");
    }
}
