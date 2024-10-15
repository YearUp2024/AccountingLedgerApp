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
            System.out.println("\nPlease choose from the following options:");
            System.out.println("[D] Add Deposit - Add money to your account");
            System.out.println("[P] Make Payment - Pay your bills or transfer money");
            System.out.println("[L] Ledger - View your transaction history");
            System.out.println("[E] Exit - Exit the application");
            System.out.print("Enter your choice: ");
            userChoice = scanner.nextLine().trim().toUpperCase();

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
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}