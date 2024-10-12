package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class MakePayment {
    static Scanner scanner = new Scanner(System.in);
    public static void main() {
        PromptForPayment();
    }

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
            AddPaymentToCSV(description, vendor, amount);
            System.out.println("-----------------------------");
            System.out.print("Make another Deposit (Y/N): ");
            makePayment = scanner.nextLine();
        }while(makePayment.equalsIgnoreCase("Y") || makePayment.equalsIgnoreCase("YES"));
        System.out.println("Thank You");
    }

    public static String TimeAndDate(){
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        return localDate + "|" + localTime.getHour() + ":" + localTime.getMinute() + ":" + localTime.getSecond();
    }

    public static void AddPaymentToCSV(String description, String vendor, double amount){
        System.out.printf("Payment|%s|%s|%s|%.2f\n", TimeAndDate(), description, vendor, amount);
    }
}
