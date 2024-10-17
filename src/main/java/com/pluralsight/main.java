package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class main {
    static Scanner scanner = new Scanner(System.in);
    static String fileName = "transactions.csv";

    /**
     * This Method is going to ask the user to choose from different options.
     * @param args
     */
    public static void main(String[] args) {
        ArrayList<Transactions> transactions = Transactions.loadTransaction(fileName);

        String choice;
        do{
            System.out.println("D) Add Deposit");
            System.out.println("P) Make Payment");
            System.out.println("L) Ledger");
            System.out.println("X) Exit");
            System.out.print("Choose an option: ");
            choice = scanner.nextLine().toUpperCase();

            switch(choice){
                //This method is going to call addDeposit method.
                case "D":
                    addDeposit(transactions);
                    break;
                //This method is going to call addPayment method.
                case "P":
                    addPayment(transactions);
                    break;
                //This method is going to call showLedger method.
                case "L":
                    showLedger(transactions);
                    break;
                //This is going to end the loop
                case "X":
                    System.out.println("Exiting application.");
                    break;
                //This is going to be printed if there is an error
                default:
                    System.out.println("Please Try Again.");
            }
        }while(!choice.equalsIgnoreCase("X"));
    }

    /**
     * This Method is going to add a new Deposit
     * @param transactions
     */
    public static void addDeposit(ArrayList<Transactions> transactions){
        //This is going to prompt the user for "Deposit" details
        Transactions transaction = promptTransaction("Deposit");
        //This is going to add the deposit to the ArrayList.
        transactions.add(transaction);
        //This is going to save the transaction to the .csv file.
        Transactions.saveTransaction("transactions.csv", transaction);
        System.out.println("\nYour Deposit Added Successfully\n");
    }

    /**
     * This Method is going to add a Payment
     * @param transactions
     */
    public static void addPayment(ArrayList<Transactions> transactions){
        //This is going to prompt the user for "Payment" details
        Transactions transaction = promptTransaction("Payment");
        //This is converting the amount into a negative number.
        double negativeAmount = transaction.getAmount() * -1;
        //This is creating a new Transaction object with negative amount.
        transaction = new Transactions(transaction.getDate(), transaction.getTime(), transaction.getDescription(), transaction.getVendor(), negativeAmount);
        //This is adding the transaction to the list.
        transactions.add(transaction);
        //This is saving the transaction to the file.
        Transactions.saveTransaction("transactions.csv", transaction);
        System.out.println("\nYour Payment Added Successfully\n");
    }

    /**
     * This Method is going to prompt the user to enter Transaction information.
     * @param transactionType
     * @return
     */
    private static Transactions promptTransaction(String transactionType) {
        System.out.println("\n");
        System.out.print("Enter date (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        System.out.print("Enter time (HH:MM:SS): ");
        String time = scanner.nextLine();
        System.out.print("Enter description: ");
        String description = scanner.nextLine();
        System.out.print("Enter vendor: ");
        String vendor = scanner.nextLine();
        System.out.print("Enter amount: ");
        double amount = Double.parseDouble(scanner.nextLine());

        //This is returning a new Transaction object with the information.
        return new Transactions(date, time, description, vendor, amount);
    }

    /**
     * This Method is going to prompt the user to choose from the Ledger menue.
     * @param transactions
     */
    public static void showLedger(ArrayList<Transactions> transactions){
        System.out.println("\n");
        System.out.println("Ledger Screen");
        System.out.println("A) All");
        System.out.println("D) Deposits");
        System.out.println("P) Payments");
        System.out.print("Choose an option: ");
        String userChoice = scanner.nextLine().toUpperCase();

        switch(userChoice){
            case "A":
                Transactions.displayTransactions(transactions, "A");
                break;
            case "D":
                Transactions.displayTransactions(filterTransactions(transactions, true), "D");
                break;
            case "P":
                Transactions.displayTransactions(filterTransactions(transactions, false), "P");
                break;
            default:
                System.out.println("Your input is invalid");
        }
    }

    /**
     * This is going to filter transactions by type(Payment/Deposit)
     * @param transactions
     * @param deposit
     * @return
     */
    public static ArrayList<Transactions> filterTransactions(ArrayList<Transactions> transactions, boolean deposit){
        ArrayList<Transactions> filterByType = new ArrayList<>();

        for(Transactions transaction : transactions){
            if(transaction.isDeposit() == deposit){
                filterByType.add(transaction);
            }
        }
        return filterByType;
    }

    /**
     * This Method is going to load transactions from .csv file.
     * @return
     */
    public static ArrayList<Transactions> loadTransaction(String fileName){
        ArrayList<Transactions> transactions = new ArrayList<>();

        try{
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            boolean isFirstLine = true;
            while((line = bufferedReader.readLine()) != null){
                if(isFirstLine){
                    isFirstLine = false;
                    continue;
                }
                String[] newValue = line.split("\\|");

                if(newValue.length == 5){
                    String date = newValue[0];
                    String time = newValue[1];
                    String description = newValue[2];
                    String vendor = newValue[3];
                    double amount = Double.parseDouble(newValue[4]);

                    //This is adding a new transaction to the ArrayList.
                    transactions.add(new Transactions(date, time, description, vendor, amount));
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return transactions;
    }
}
