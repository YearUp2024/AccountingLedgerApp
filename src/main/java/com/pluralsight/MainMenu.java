package com.pluralsight;

import java.util.Scanner;

/**
 * When the program starts this Class is going to prompt the users to choose from different options.
 * Based on the user choice this Class is going to call other classes.
 */
public class MainMenu {
    static Scanner scanner = new Scanner(System.in);
    /**
     * This main method is prompting the user for an input. Based on the input different Classes are called.
     */
    public static void main(String[] args){
        String userChoice;

        while(true) {
            System.out.println("Add [D]eposit");
            System.out.println("Make [P]ayment");
            System.out.println("[L]edger");
            System.out.println("[E]xit");
            System.out.print("Choose from the Options: ");
            userChoice = scanner.nextLine();

            switch(userChoice){
                case "D":
                    System.out.println("------------------------------");
                    DepositMenu.main();
                    System.out.println("------------------------------");
                case "P":
                    System.out.println("------------------------------");
                    MakePaymentMenu.main();
                    System.out.println("------------------------------");
                case "L":
                    System.out.println("------------------------------");
                    LedgerMenu.main();
                    System.out.println("------------------------------");
                case "E":
                    System.out.println("------------------------------");
                    System.out.println("       Program Ended          ");
                    System.out.println("------------------------------");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}