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
public class MakePayment {
    static Scanner scanner = new Scanner(System.in);

    /**
     * This method is calling PromptForPayment method.
     */
    public static void main() {
        PromptForPayment();
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
            System.out.println(description);

            System.out.print("Vendor: ");
            String vendor = scanner.nextLine();
            System.out.println(vendor);

            System.out.print("Amount: ");
            Double amount = scanner.nextDouble();
            System.out.println(amount);
            scanner.nextLine();

            System.out.println("\nYour Payment: ");
            AddPaymentToCSV(description, vendor, amount);//This is passing description, vendor, amount to AddPaymentToCSV method
            System.out.println("-----------------------------");

            System.out.print("Make another Deposit (Y/N): ");
            makePayment = scanner.nextLine();
        }while(makePayment.equalsIgnoreCase("Y") || makePayment.equalsIgnoreCase("YES"));
        System.out.println("Thank You");
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

            System.out.println("Payment Added Successfully!");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
