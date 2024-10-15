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
public class AddDepositMenu {
    static Scanner scanner = new Scanner(System.in);
    static TotalBalance totalBalance = new TotalBalance();

    /**
     * This method is calling PromptForDeposit method.
     */
    public static void main() {
        MakeDepositOrSeeDeposits();
    }

    /**
     * This method is going to ask the user if they just want to see the deposit balance or if they would like to
     * make a deposit.
     */
    public static void MakeDepositOrSeeDeposits(){
        while(true){
            System.out.println("Make [D]eposit");
            System.out.println("See [B]alance");
            System.out.println("[E]xit");
            System.out.print("Choose from the options: ");
            String userChoice = scanner.nextLine();

            if(userChoice.equalsIgnoreCase("D")){
                PromptForDeposit();
            }
            if(userChoice.equalsIgnoreCase("B")){
                double updatedBalance = totalBalance.GetTotalDeposits();
                System.out.printf("Current Balance: %.2f\n", updatedBalance);
                System.out.println("------------------------------");
            }
            if(userChoice.equalsIgnoreCase("E")){
                break;
            }
        }
    }

    /**
     * This method is going to ask the user to enter information for Deposit.
     */
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

            AddDepositToCSV(description, vendor, amount);//This is passing description, vendor, amount to AddDepositToCSV method
            System.out.println("\n-----------------------------");

            makeDeposit = GetYesOrNo();

        }while(makeDeposit.equalsIgnoreCase("Y") || makeDeposit.equalsIgnoreCase("YES"));
        System.out.println("Thank You");
    }

    /**
     * This method is going to make sure that the user enters Y or N to more forward.
     * @return response
     */
    public static String GetYesOrNo(){
        String response = "";

        while(true){
            if(response.equalsIgnoreCase("Y") || response.equalsIgnoreCase("Yes") ||
                    response.equalsIgnoreCase("N") || response.equalsIgnoreCase("No")){
                break;
            }else{
                System.out.print("Please enter (Y or N): ");
                response = scanner.nextLine();
            }
        }
        return response;
    }

    /**
     * This method is getting Date and Time and storing that into the translations.csv file.
     * @return Time & Date
     */
    public static String TimeAndDate(){
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        return date + "|" + time.getHour() + ":" + time.getMinute() + ":" + time.getSecond();
    }

    /**
     * This method is going to write the information the user passed into transactions.csv file.
     * @param description
     * @param vendor
     * @param amount
     */
    public static void AddDepositToCSV(String description, String vendor, double amount){
        try{
            FileWriter fileWriter = new FileWriter("transactions.csv", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(String.format("Deposit|%s|%s|%s|%.2f\n", TimeAndDate(), description, vendor, amount));//TimeAndDate method is called here.
            bufferedWriter.close();

            System.out.println("Deposit Added Successfully!");

            double updatedBalance = totalBalance.GetTotalDeposits();
            System.out.printf("Current Balance: %.2f", updatedBalance);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
