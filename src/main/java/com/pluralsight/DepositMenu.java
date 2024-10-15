package com.pluralsight;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

/**
 * This Class is going to prompt the use to enter some information for Deposits. After the user
 * enters the information that information will be saved in transactions.csv file.
 */
public class DepositMenu {
    static Scanner scanner = new Scanner(System.in);
    static TransactionBalance transactionBalance = new TransactionBalance();

    /**
     * This method is calling PromptForDeposit method.
     */
    public static void main() {
        ShowDepositMenu();
    }

    /**
     * This method is going to ask the user if they just want to see the deposit balance or if they would like to
     * make a deposit.
     */
    public static void ShowDepositMenu(){
        while (true) {
            System.out.println("\nMake [D]eposit");
            System.out.println("See [B]alance");
            System.out.println("[E]xit");
            System.out.print("Choose from the options: ");
            String userChoice = scanner.nextLine().trim().toUpperCase();

            switch (userChoice) {
                case "D":
                    PromptForDeposit();
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
     * This method is going to ask the user to enter information for Deposit.
     */
    public static void PromptForDeposit(){
        String makeDeposit;
        do {
            System.out.println("Please Enter Deposit Information");

            System.out.print("Description: ");
            String description = scanner.nextLine();

            System.out.print("Vendor: ");
            String vendor = scanner.nextLine();

            System.out.print("Amount: ");
            double amount = scanner.nextDouble();
            scanner.nextLine(); // Consume newline

            AddDepositToCSV(description, vendor, amount);
            System.out.println("\n-----------------------------");

            makeDeposit = GetYesOrNo("Would you like to make another deposit? (Y/N): ");

        } while (makeDeposit.equalsIgnoreCase("Y"));
        System.out.println("Thank You");
    }

    /**
     * This method is going to make sure that the user enters Y or N to more forward.
     * @return response
     */
    public static String GetYesOrNo(String prompt){
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
        double updatedBalance = transactionBalance.GetTotalDeposits();
        System.out.printf("Current Balance: $%.2f\n", updatedBalance);
        System.out.println("------------------------------");
    }

    /**
     * Gets the current date and time.
     * @return The current date and time as a formatted string.
     */
    public static String GetTimeAndDate() {
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        return date + "|" + time.getHour() + ":" + time.getMinute() + ":" + time.getSecond();
    }

    /**
     * Writes the deposit information to the transactions.csv file.
     * @param description The deposit description.
     * @param vendor The vendor name.
     * @param amount The deposit amount.
     */
    public static void AddDepositToCSV(String description, String vendor, double amount) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("transactions.csv", true))) {
            bufferedWriter.write(String.format("Deposit|%s|%s|%s|%.2f\n", GetTimeAndDate(), description, vendor, amount));
            System.out.println("Deposit Added Successfully!");

            double updatedBalance = transactionBalance.GetTotalDeposits();
            System.out.printf("Current Balance: $%.2f\n", updatedBalance);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
