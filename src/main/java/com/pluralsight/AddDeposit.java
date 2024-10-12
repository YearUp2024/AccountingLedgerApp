package com.pluralsight;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class AddDeposit {
    static Scanner scanner = new Scanner(System.in);

    public static void main() {
        PromptForDeposit();
    }

    public static void PromptForDeposit(){
        String makeDeposit;
        do{
            System.out.println("Please Enter Deposit Information");

            System.out.print("Description: ");
            String description = scanner.nextLine();

            System.out.print("Vendor: ");
            String vendor = scanner.nextLine();

            System.out.print("Amount: ");
            Double amount = scanner.nextDouble();
            scanner.nextLine();

            System.out.println("\nYour deposit: ");
            AddDepositToCSV(description, vendor, amount);
            System.out.println("-----------------------------");
            System.out.print("Make another Deposit (Y/N): ");
            makeDeposit = scanner.nextLine();
        }while(makeDeposit.equalsIgnoreCase("Y") || makeDeposit.equalsIgnoreCase("YES"));
        System.out.println("Thank You");
    }

    public static String TimeAndDate(){
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        return date + "|" + time.getHour() + ":" + time.getMinute() + ":" + time.getSecond();
    }

    public static void AddDepositToCSV(String description, String vendor, double amount){
        try{
            FileWriter fileWriter = new FileWriter("transactions.csv", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(String.format("Deposit|%s|%s|%s|%.2f\n", TimeAndDate(), description, vendor, amount));
            bufferedWriter.close();

            System.out.println("Deposit Added Successfully!");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
