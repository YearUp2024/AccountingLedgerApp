package com.pluralsight;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

/**
 * This Class is going to prompt the use to enter some information for Payment. After the user
 * enters the information that information will be saved in transactions.csv file.
 */
public class MakePaymentMenu {
    static Scanner scanner = new Scanner(System.in);

    /**
     * Main method to start the payment menu.
     */
    public static void main() {
        ShowPaymentMenu();
    }

    /**
     * Displays the payment menu and handles user choices.
     */
    public static void ShowPaymentMenu() {
        while (true) {
            DisplayMenu();
            String userChoice = scanner.nextLine().trim().toUpperCase();

            switch (userChoice) {
                case "P":
                    promptForPayment();
                    break;
                case "B":
                    ShowBalance();
                    break;
                case "E":
                    System.out.println("Exiting the application. Goodbye!");
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
        System.out.println("\nMake [P]ayment");
        System.out.println("See [B]alance");
        System.out.println("[E]xit");
        System.out.print("Choose from the options: ");
    }

    /**
     * Prompts the user to enter payment information.
     */
    public static void promptForPayment() {
        String makePayment;
        do {
            System.out.println("Please Enter Payment Information");

            System.out.print("Description: ");
            String description = scanner.nextLine();

            System.out.print("Vendor: ");
            String vendor = scanner.nextLine();

            System.out.print("Amount: ");
            double amount = scanner.nextDouble();
            scanner.nextLine(); // Consume newline

            AddPaymentToCSV(description, vendor, amount);
            System.out.println("\n-----------------------------");

            makePayment = GetYesOrNo("Would you like to make another payment? (Y/N): ");

        } while (makePayment.equalsIgnoreCase("Y"));
        System.out.println("Thank You");
    }

    /**
     * Prompts the user for a yes or no response.
     * @param prompt The prompt message.
     * @return The user's response.
     */
    public static String GetYesOrNo(String prompt) {
        String response;
        while (true) {
            System.out.print(prompt);
            response = scanner.nextLine().trim().toUpperCase();
            if (response.equals("Y") || response.equals("N")) {
                break;
            } else {
                System.out.println("Invalid input. Please enter Y or N.");
            }
        }
        return response;
    }

    /**
     * Displays the current balance.
     */
    public static void ShowBalance() {
        double updatedBalance = TransactionBalance.GetTotalPayment();
        System.out.printf("Current Balance: %.2f\n", updatedBalance);
        System.out.println("------------------------------");
    }

    /**
     * Gets the current date and time.
     * @return The current date and time as a formatted string.
     */
    public static String GetTimeAndDate() {
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        return localDate + "|" + localTime.getHour() + ":" + localTime.getMinute() + ":" + localTime.getSecond();
    }

    /**
     * Writes the payment information to the transactions.csv file.
     * @param description The payment description.
     * @param vendor The vendor name.
     * @param amount The payment amount.
     */
    public static void AddPaymentToCSV(String description, String vendor, double amount) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("transactions.csv", true))) {
            bufferedWriter.write(String.format("Payment|%s|%s|%s|%.2f\n", GetTimeAndDate(), description, vendor, amount));
            System.out.println("Payment Added Successfully!");

            double updatedBalance = TransactionBalance.GetTotalPayment();
            System.out.printf("Current Balance: %.2f\n", updatedBalance);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
