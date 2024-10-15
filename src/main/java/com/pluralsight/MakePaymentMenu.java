package com.pluralsight;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class MakePaymentMenu {
    static Scanner scanner = new Scanner(System.in);
    static TransactionBalance totalBalance = new TransactionBalance();

    /**
     * This method is calling PromptForPayment method.
     */
    public static void main() {
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
                double updatedBalance = TransactionBalance.GetTotalPayment();
                System.out.println("------------------------------");
                System.out.printf("Current Balance: %.2f\n", updatedBalance);
                System.out.println("------------------------------");
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
        String makePayment = "";
        do{
            System.out.println("Please Enter Payment Information");

            System.out.print("Description: ");
            String description = scanner.nextLine();

            System.out.print("Vendor: ");
            String vendor = scanner.nextLine();

            System.out.print("Amount: ");
            Double amount = scanner.nextDouble();
            scanner.nextLine();

            AddPaymentToCSV(description, vendor, amount);
            System.out.println("\n-----------------------------");

        }while(makePayment.equalsIgnoreCase("Y") || makePayment.equalsIgnoreCase("YES"));
    }

    /**
     * This method is getting Date and Time and storing that into the translations.csv file.
     * @return Time & Date
     */
    public static String TimeAndDate(){
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        return localDate + "|" + localTime.getHour() + ":" + localTime.getMinute() + ":" + localTime.getSecond();
    }

    /**
     * This method is going to write the information the user passed into transactions.csv file.
     * @param description
     * @param vendor
     * @param amount
     */
    public static void AddPaymentToCSV(String description, String vendor, double amount){
        try{
            FileWriter fileWriter = new FileWriter("transactions.csv", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(String.format("Payment|%s|%s|%s|%.2f\n", TimeAndDate(), description, vendor, amount));//TimeAndDate method is called here.
            bufferedWriter.close();

            System.out.println("------------------------------");
            System.out.println("Payment Added Successfully!");

            double updatedBalance = TransactionBalance.GetTotalPayment();
            System.out.printf("Current Balance: %.2f", updatedBalance);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}