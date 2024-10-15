package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * This class is going to Calculate Total balance after making a deposit or a payment.
 */
public class TransactionBalance {
    /**
     * Calculates the total balance for deposits.
     * @return Total deposits.
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
     * Calculates the total balance for payments.
     * @return Total payments.
     */
    public static double GetTotalPayment(){
        double totalPayment = 0.0;
        try{
            FileReader fileReader = new FileReader("transactions.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while((line = bufferedReader.readLine()) != null){
                String[] data = line.split("\\|");
                if(data[0].equalsIgnoreCase("Payment")){
                    double amount = Double.parseDouble(data[5].trim());
                    totalPayment -= amount;
                }
            }
            bufferedReader.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return totalPayment;
    }

    /**
     * Calculates the total balance after subtracting payments from deposits.
     */
    public static double GetAllTotal() {
        double balance = 0.0; // Initialize the balance variable

        try{
            FileReader fileReader = new FileReader("transactions.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split("\\|");

                if (data.length == 6) {
                    String transactionType = data[0];
                    double amount = Double.parseDouble(data[5].trim());

                    if (transactionType.equalsIgnoreCase("deposit")) {
                        balance += amount;
                    } else if (transactionType.equalsIgnoreCase("payment")) {
                        balance -= amount;
                    }
                } else {
                    System.out.println("Skipping malformed line: " + line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return balance;
    }
}


