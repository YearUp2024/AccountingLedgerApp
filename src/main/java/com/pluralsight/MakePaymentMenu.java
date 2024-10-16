package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class MakePaymentMenu {
    static Scanner scanner = new Scanner(System.in);

    /**
     * This method is calling PromptForPayment method.
     */
    public static void main() {
        Transaction.LoadFromFile();
        MakePayOrSeePay();
    }

    /**
     * This method is going to ask the user if they just want to see the payment balance or if they would like to
     * make a payment.
     */
    public static void MakePayOrSeePay(){
        while(true){
            System.out.println("Make [P]ayment");
            System.out.println("See [B]alance");
            System.out.println("[E]xit");
            System.out.print("Choose from the options: ");
            String userChoice = scanner.nextLine();

            if(userChoice.equalsIgnoreCase("P")){
                PromptForPayment();
            }
            if(userChoice.equalsIgnoreCase("B")){
                DisplayAllEntries();
                double runningBalance = CalculateRunningBalance();
                System.out.printf("Running Balance: %.2f\n", runningBalance);
            }
            if(userChoice.equalsIgnoreCase("E")){
                break;
            }
        }
    }

    /**
     * This method is going to ask the user to enter information for Payment.
     */
    public static void PromptForPayment(){
        String makePayment;
        do{
            System.out.println("Please Enter Payment Information");

            System.out.print("Description: ");
            String description = scanner.nextLine();

            System.out.print("Vendor: ");
            String vendor = scanner.nextLine();

            System.out.print("Amount: ");
            Double amount = scanner.nextDouble() * - 1;
            scanner.nextLine();

            Transaction newPayment = new Transaction(CurrentDate(), CurrentTime(), description, vendor, amount);
            Transaction.FullLedger().add(newPayment);

            Transaction.SaveToFile(Transaction.FullLedger());
            System.out.println("Payment added Successfully");

            System.out.println("Do you want to make another payment (Y/N): ");
            makePayment = scanner.nextLine();
        }while(makePayment.equalsIgnoreCase("Y") || makePayment.equalsIgnoreCase("YES"));
    }

    /**
     * This method is getting Date and Time and storing that into the translations.csv file.
     * @return Time & Date
     */
    public static String CurrentDate(){
        LocalDate localDate = LocalDate.now();
        return localDate.toString();
    }

    public static String CurrentTime(){
        LocalTime localTime = LocalTime.now();
        return localTime.getHour() + ":" + localTime.getMinute() + ":" + localTime.getSecond();
    }

    public static void DisplayAllEntries(){
        for(Transaction transaction : Transaction.FullLedger()){
            transaction.PrintTransaction();
        }
    }

    public static double CalculateRunningBalance(){
        double balance = 0.0;

        for(Transaction transaction : Transaction.FullLedger()){
            balance += transaction.amount;
        }
        return balance;
    }
}