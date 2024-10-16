package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class is going to print All the Transactions in the screen.
 */
public class TransactionDisplay {
    /**
     * Main method to start the transaction display.
     */
    public static void main() {
        Transaction.LoadFromFile();
        DisplayAllEntries(Transaction.FullLedger());
    }

    /**
     * Prints all the transactions to the screen.
     * @param transactions
     */
    public static void DisplayAllEntries(ArrayList<Transaction> transactions){
        double runningBalance = 0.0;

        if(transactions.isEmpty()){
            System.out.println("No entries found");
        }else{
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("| %-20s | %-20s | %-30s | %-30s |\n", "Date", "Time", "Description", "Vendor");
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
            for(Transaction transaction : transactions){
                runningBalance += transaction.getAmount();
                System.out.printf("| %-20s | %-20s | %-30s | %-30s |\n", transaction.getDate(), transaction.getTime(), transaction.getDescription(), transaction.getVendor());
            }
        }
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("                                                    %s\n", runningBalance);
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
    }
}
