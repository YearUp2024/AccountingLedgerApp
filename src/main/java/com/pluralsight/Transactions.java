package com.pluralsight;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class Transactions {
    private String date;
    private String time;
    private String description;
    private String vendor;
    private double amount;

    /**
     * This Constructor initializes the Transaction object with date, time, description, vendor, and amount.
     * @param date
     * @param time
     * @param description
     * @param vendor
     * @param amount
     */
    public Transactions(String date, String time, String description, String vendor, double amount) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    /**
     * This Method is converting the transaction details to the way I want it to be saved.
     * @return
     */
    public String convertToCSV(){
        return date + "|" + time + "|" + description + "|" + vendor + "|" + amount + "\n";
    }

    /**
     * This Method is checking if the transaction is a deposit
     * @return
     */
    public boolean isDeposit(){
        return amount > 0;
    }

    /**
     * Method to get the date
     * @return
     */
    public String getDate() {
        return date;
    }

    /**
     * Method to get the time
     * @return
     */
    public String getTime() {
        return time;
    }

    /**
     * Method to get the description
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * Method to get the cendor
     * @return
     */
    public String getVendor() {
        return vendor;
    }

    /**
     * Method to get the amount
     * @return
     */
    public double getAmount() {
        return amount;
    }

    /**
     * This Method is loading transactions from the .csv file.
     * @param fileName
     * @return
     */
    public static ArrayList<Transactions> loadTransaction(String fileName){
        //This ArrayList is going to store transactions.
        ArrayList<Transactions> transaction = new ArrayList<>();

        try{
            //These likes are reading the .csv file
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            //This is holding every line that is being read.
            String line;

            boolean firstLine = true;

            //This while loop is going to read every line until the end of the file.
            while((line = bufferedReader.readLine()) != null){
                if(firstLine){
                    firstLine = false;
                    continue;
                }
                //This is splitting a line by '|' and storing the values into an array.
                String[] newValue = line.split("\\|");

                if(newValue.length == 5){
                    String date = newValue[0];
                    String time = newValue[1];
                    String description = newValue[2];
                    String vendor = newValue[3];
                    double amount = Double.parseDouble(newValue[4]);

                    //This is adding a new transaction to the ArrayList.
                    transaction.add(new Transactions(date, time, description, vendor, amount));
                }
            }

            bufferedReader.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return transaction;
    }

    //This Method is saving the transaction to .csv file.
    public static void saveTransaction(String fileName, Transactions transactions){
        try{
            FileWriter fileWriter = new FileWriter(fileName, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            //Writing the transaction in CSV format.
            bufferedWriter.write(transactions.convertToCSV());

            bufferedWriter.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void displayTransactions(ArrayList<Transactions> transactions, String transactionType){
        double total = 0;
        System.out.println("\n-----------------------------------------------------------------------------------------------------------------------------");
        switch (transactionType) {
            case "D":
                System.out.println("|                                                       Deposit entries                                                      |");
                break;
            case "P":
                System.out.println("|                                                       Payment entries                                                      |");
                break;
            default:
                System.out.println("|                                                       All entries                                                          |");
                break;
        }

        System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-20s | %-20s | %-30s | %-20s | %-20s |\n", "Date", "Time", "Description", "Vendor", "Amount");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------");

        // Loop through the transactions and print each in the desired format
        for (Transactions transaction : transactions) {
            System.out.printf("| %-20s | %-20s | %-30s | %-20s | %-20.2f |\n",transaction.getDate(), transaction.getTime(), transaction.getDescription(), transaction.getVendor(), transaction.getAmount());
            total += transaction.getAmount();
        }
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
        switch (transactionType) {
            case "D":
                System.out.printf("|                                                   Total Deposits: %.2f                                                   |", total);
                System.out.println("\n-----------------------------------------------------------------------------------------------------------------------------\n");
                break;
            case "P":
                System.out.printf("|                                                   Total Deposits: %.2f                                                   |", total);
                System.out.println("\n-----------------------------------------------------------------------------------------------------------------------------\n");
                break;
            default:
                System.out.printf("|                                                   Total Deposits: %.2f                                                   |", total);
                System.out.println("\n-----------------------------------------------------------------------------------------------------------------------------\n");
                break;
        }
    }
}
