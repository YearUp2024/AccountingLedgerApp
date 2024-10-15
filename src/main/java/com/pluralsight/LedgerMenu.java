package com.pluralsight;

import java.util.Scanner;

/**
 * This class is going to prompt the users to choose from different choices. Based on the user choice different
 * information will be printed.
 */
public class LedgerMenu {
    static Scanner scanner = new Scanner(System.in);
    static DisplayAllEntries displayAllEntries = new DisplayAllEntries();
    static DepositsOnly depositsOnly = new DepositsOnly();
    static TotalBalance totalBalance = new TotalBalance();

    /**
     * This main method is calling Options method.
     */
    public static void main() {
        Options();
    }

    /**
     * This method will allow the users to choose from different options.
     */
    public static void Options(){
        while(true) {
            System.out.println("Display [A]ll Entries");
            System.out.println("[D]eposits Only");
            System.out.println("[P]ayments Only");
            System.out.println("[R]eports");
            System.out.println("[H]ome");
            System.out.print("Please choose from the options: ");
            String userChoose = scanner.nextLine();

            if(userChoose.equalsIgnoreCase("A")){
                System.out.println("------------------------------");
                displayAllEntries.main();
                System.out.println("------------------------------");
            }

            if(userChoose.equalsIgnoreCase("D")){
                System.out.println("------------------------------");
                depositsOnly.main();
                System.out.println("------------------------------");
            }

            if(userChoose.equalsIgnoreCase("H") || userChoose.equalsIgnoreCase("Home")){
                break;
            }
        }
    }
}
