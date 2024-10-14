package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * This class is going to Calculate Total balance after making a deposit or a payment.
 */
public class TotalBalance {
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
    public static double GetTotalPayment(){
        double totalPayment = 0.0;
        try{
            FileReader fileReader = new FileReader("transactions.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while((line = bufferedReader.readLine()) != null){
                String[] data = line.split("\\|");
                if(data[0].equalsIgnoreCase("Payment")){
                    //This is convergent the number from string to double and trim is removing any space before and after.
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
     * This is calculating the total amount after subtracting Payment from Deposit.
     */
    public static void GetAllTotal(){
        double balance = 0.0;

        try {
            FileReader fileReader = new FileReader("transactions.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while((line = bufferedReader.readLine()) != null) {
                String[] data = line.split("\\|");

                // Ensure the line has exactly 6 parts (TransactionType, Date, Time, Description, Vendor, Amount)
                if(data.length == 6) {
                    String transactionType = data[0];
                    double amount = Double.parseDouble(data[5].trim()); // Trim to avoid whitespace issues

                    if(transactionType.equalsIgnoreCase("deposit")) {
                        balance += amount;
                    } else if(transactionType.equalsIgnoreCase("payment")) {
                        balance -= amount;
                    }
                } else {
                    System.out.println("Skipping malformed line: " + line); // Debugging info
                }
            }

            bufferedReader.close();
        } catch(Exception e) {
            e.printStackTrace();
        }

        System.out.println(balance);
    }
}
