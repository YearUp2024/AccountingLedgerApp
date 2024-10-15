package com.pluralsight;

import java.util.Scanner;

/**
 * This class is going to prompt the users to choose from different choices. Based on the user choice different
 * information will be printed.
 */
public class LedgerMenu {
    static Scanner scanner = new Scanner(System.in);
    static TransactionBalance transactionBalance = new TransactionBalance();

    /**
     * This main method is calling Options method.
     */
    public static void main() {
        ShowOptions();
    }

    /**
     * Displays the options menu and handles user choices.
     */
    public static void ShowOptions() {
        while (true) {
            DisplayMenu();
            String userChoice = scanner.nextLine().trim().toUpperCase();

            switch (userChoice) {
                case "A":
                    DisplayAllEntries();
                    break;
                case "D":
                    DisplayDepositsOnly();
                    break;
                case "P":
                    DisplayPaymentsOnly();
                    break;
                case "R":
                    DisplayReports();
                    break;
                case "H":
                case "HOME":
                    System.out.println("Returning to home.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    /**
     * Displays the menu options.
     */
    private static void DisplayMenu() {
        System.out.println("\nDisplay [A]ll Entries");
        System.out.println("[D]eposits Only");
        System.out.println("[P]ayments Only");
        System.out.println("[R]eports");
        System.out.println("[H]ome");
        System.out.print("Please choose from the options: ");
    }

    /**
     * Displays all entries.
     */
    private static void DisplayAllEntries() {
        System.out.println("------------------------------");
        TransactionDisplay.main();
        System.out.println("------------------------------");
    }

    /**
     * Displays deposits only.
     */
    private static void DisplayDepositsOnly() {
        System.out.println("------------------------------");
        DepositsOnly.main();
        System.out.println("------------------------------");
    }

    /**
     * Displays payments only.
     */
    private static void DisplayPaymentsOnly() {
        System.out.println("Payments only feature is not yet implemented.");
    }

    /**
     * Displays reports.
     */
    private static void DisplayReports() {
        System.out.println("Reports feature is not yet implemented.");
    }
}
