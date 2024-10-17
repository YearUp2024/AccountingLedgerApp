package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
            System.out.println("R) Reports");
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
                //This method is going to call showLedger method.
                case "R":
                    showReports(transactions);
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
        Transactions transaction = promptTransaction();
        //This is going to add the deposit to the ArrayList.
        transactions.add(transaction);
        //This is going to save the transaction to the .csv file.
        Transactions.saveTransaction("transactions.csv", transaction);

        System.out.println("\n-----------------------------------------------------------------------------------------------------------------------------");
        System.out.println("|                                                       Your Deposit Added Successfully                                      |");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------\n");
    }

    /**
     * This Method is going to add a Payment
     * @param transactions
     */
    public static void addPayment(ArrayList<Transactions> transactions){
        //This is going to prompt the user for "Payment" details
        Transactions transaction = promptTransaction();
        //This is converting the amount into a negative number.
        double negativeAmount = transaction.getAmount() * -1;
        //This is creating a new Transaction object with negative amount.
        transaction = new Transactions(transaction.getDate(), transaction.getTime(), transaction.getDescription(), transaction.getVendor(), negativeAmount);
        //This is adding the transaction to the list.
        transactions.add(transaction);
        //This is saving the transaction to the file.
        Transactions.saveTransaction("transactions.csv", transaction);
        System.out.println("\n-----------------------------------------------------------------------------------------------------------------------------");
        System.out.println("|                                                       Your Payment Added Successfully                                      |");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------\n");
    }

    /**
     * This Method is going to prompt the user to enter Transaction information.
     * @return
     */
    private static Transactions promptTransaction() {
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
     * This Method is going to prompt the user to choose from the Ledger menu.
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

    /**
     * This Method is going to prompt the user to choose from the Report menu
     * @param transactions
     */
    private static void showReports(ArrayList<Transactions> transactions) {
        String userChoice;
        System.out.println("\n");
        do {
            System.out.println("1) Month To Date");
            System.out.println("2) Previous Month");
            System.out.println("3) Year To Date");
            System.out.println("4) Previous Year");
            System.out.println("5) Search by Vendor");
            System.out.println("0) Back");
            System.out.print("Choose a report: ");
            userChoice = scanner.nextLine();

            switch (userChoice) {
                case "1":
                    monthToDateReport(transactions);
                    break;
                case "2":
                    previousMonthReport(transactions);
                    break;
                case "3":
                    yearTodate(transactions);
                    break;
                case "4":
                    previousYear(transactions);
                    break;
                case "5":
                    searchByVendor(transactions);
                    break;
                case "0":
                    System.out.println("Returning to the main menu.\n");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (!userChoice.equals("0"));
    }

    /**
     * This Method is going to print the transaction from the first day of the month to date.
     * @param transactions
     */
    public static void monthToDateReport(ArrayList<Transactions> transactions){
        //This is getting today's date
        LocalDate date = LocalDate.now();
        //This is getting the first date of the month.
        LocalDate dayOfMonth = date.withDayOfMonth(1);
        //This is going to display transactions from the first day of the month to date.
        finteredByDate(transactions, dayOfMonth, date);
    }

    /**
     * This Method is going to print the transaction from the first day of the month to date.
     * @param transactions
     */
    public static void previousMonthReport(ArrayList<Transactions> transactions){
        //This is getting today's date
        LocalDate date = LocalDate.now();
        //This is getting the first date of the previous month.
        LocalDate firstDateOfPreviousMonth = date.minusMonths(1).withDayOfMonth(1);
        //This is getting the last date of the previous month.
        LocalDate lastDateOfPreviousMonth = date.withDayOfMonth(1).minusDays(1);
        //This is going to display transactions from the first day of the month to date.
        finteredByDate(transactions, firstDateOfPreviousMonth, lastDateOfPreviousMonth);
    }

    /**
     * This Method is going to print the transaction from the first day of current year.
     * @param transactions
     */
    public static void yearTodate(ArrayList<Transactions> transactions){
        //This is getting today's date
        LocalDate date = LocalDate.now();
        //This is getting the first date of the current year.
        LocalDate firstDateOfYear = date.withDayOfYear(1);
        //This is going to display transactions from the first day of the month to date.
        finteredByDate(transactions, firstDateOfYear, date);
    }

    /**
     * This Method is going to print the transaction from the previous year.
     * @param transactions
     */
    public static void previousYear(ArrayList<Transactions> transactions){
        //This is getting today's date
        LocalDate date = LocalDate.now();
        //This is getting the first date of the previous month.
        LocalDate firstDateOfPreviousYear = date.minusYears(1).withDayOfYear(1);
        //This is getting the last date of the previous month.
        LocalDate lastDateOfPreviousYear = date.withDayOfYear(1).minusDays(1);
        //This is going to display transactions from the first day of the month to date.
        finteredByDate(transactions, firstDateOfPreviousYear, lastDateOfPreviousYear);
    }

    /**
     * This Method is going to print transaction based on Vendor.
     * @param transactions
     */
    public static void searchByVendor(ArrayList<Transactions> transactions){
        System.out.print("Please enter vendor name: ");
        String vendor = scanner.nextLine();

        System.out.println("\n-----------------------------------------------------------------------------------------------------------------------------");
        System.out.println("|                                                       Vendor entries                                                      |");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-20s | %-20s | %-30s | %-20s | %-20s |\n", "Date", "Time", "Description", "Vendor", "Amount");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
        //This is going to iterate over each transaction.
        for(Transactions transaction : transactions){
            //This is checking if the transaction vendor matches the entered vendor name.
            if(transaction.getVendor().equalsIgnoreCase(vendor)){
                System.out.printf("| %-20s | %-20s | %-30s | %-20s | %-20.2f |\n", transaction.getDate(), transaction.getTime(), transaction.getDescription(), transaction.getVendor(), transaction.getAmount());
            }
        }
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------\n");
    }

    /**
     * This Method is printing out the based of month
     * @param transactions
     * @param startDate
     * @param endDate
     */
    public static void finteredByDate(ArrayList<Transactions> transactions, LocalDate startDate, LocalDate endDate){
        //This is defining the date format.
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        System.out.println("\n-----------------------------------------------------------------------------------------------------------------------------");
        System.out.println("|                                                       Entries by Date                                                      |");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-20s | %-20s | %-30s | %-20s | %-20s |\n", "Date", "Time", "Description", "Vendor", "Amount");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
        //This is going to iterate over every transaction.
        for(Transactions transaction : transactions){
            LocalDate transactionDate = LocalDate.parse(transaction.getDate(), formatter);
            //This checks if the transaction date is within a specific range.
            if ((transactionDate.isEqual(startDate) || transactionDate.isAfter(startDate)) &&
                    (transactionDate.isEqual(endDate) || transactionDate.isBefore(endDate))) {
                System.out.printf("| %-20s | %-20s | %-30s | %-20s | %-20.2f |\n",
                        transaction.getDate(), transaction.getTime(), transaction.getDescription(), transaction.getVendor(), transaction.getAmount());
            }
        }
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------\n");
    }
}