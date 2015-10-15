
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
public class Encryptor {

    private char[] dataToEncrypt;
    private Scanner input;

    public Encryptor() {
        this.dataToEncrypt = new char[4];
        input = new Scanner(System.in);
    }

    public void startByGettingInput() {
        String inputString;
        boolean inputStringIsValid;

        do {
            System.out.println("Enter a posetive four digit integer: ");
            inputString = input.next();
            if (checkInputValidity(inputString) == true) {
                inputStringIsValid = true;
                for (int i = 0; i < 4; i++) {
                    dataToEncrypt[i] = inputString.charAt(i);
                }
            } else {
                System.out.println("not valid! Try again");
                inputStringIsValid = false;
            }
        } while (!inputStringIsValid);
        encryptData();

    }

    private void encryptData() {
        swap();
        String result = new String();
        for (int i = 0; i < 4; i++) {
            result +=  (((int)dataToEncrypt[i] + 7) % 10);
        }
                printResult(result);
    }

    private void swap() {
        char temp;
        for (int i = 0; i < 2; i++) {
            temp = dataToEncrypt[i];
            dataToEncrypt[i] = dataToEncrypt[i + 2];
            dataToEncrypt[i + 2] = temp;
        }
    }

    private void printResult(String result) {
        System.out.println(">>>  " + result);
    }

    private boolean checkInputValidity(String inputString) {
        int inputValue = Integer.parseInt(inputString);
        if (inputString.length() == 4 && inputValue <= 9999 && inputValue >= 0) {
            return true;
        }
        return false;
    }
}
