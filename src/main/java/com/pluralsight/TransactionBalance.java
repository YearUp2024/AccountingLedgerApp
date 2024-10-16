package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * This class is going to Calculate Total balance after making a deposit or a payment.
 */
public class TransactionBalance {
    /**
     * This GetTotalDeposits method is calculating Total Balance for Deposit.
     * @return
     */
    public static double GetTotalDeposits(){
        double totalDeposit = 0.0;

        try {
            FileReader fileReader = new FileReader("transactions.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split("\\|");
                if (data[0].equalsIgnoreCase("Deposit")) {
                    //This is convergent the number from string to double and trim is removing any space before and after.
                    double amount = Double.parseDouble(data[5].trim());
                    totalDeposit += amount;
                }
            }
            bufferedReader.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return totalDeposit;
    }

    /**
     * This GetTotalPayment method is calculating Total Balance for Payment.
     * @return
     */
    public static double GetTotalPayment() {
        double totalPayment = 0.0;
        try {
            FileReader fileReader = new FileReader("transactions.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split("\\|");
                if (data[0].equalsIgnoreCase("Payment")) {
                    double amount = Double.parseDouble(data[5].trim());
                    totalPayment -= amount;
                }
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalPayment;  // Return the total payment value
    }


    /**
     * This is calculating the total amount after subtracting Payment from Deposit.
     */
    public static double GetAllTotal() {
        double balance = 0.0;

        try {
            FileReader fileReader = new FileReader("transactions.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split("\\|");

                if (data.length == 5) {
                    String transactionType = data[0];
                    double amount = Double.parseDouble(data[4].trim());

                } else {
                    System.out.println("Skipping malformed line: " + line);
                }
            }

            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return balance;
    }
}
