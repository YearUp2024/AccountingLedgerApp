package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;

public class TotalBalance {
    public static double Balance(){
        double balance = 0.0;

        try {
            FileReader fileReader = new FileReader("transactions.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while((line = bufferedReader.readLine()) != null) {
                String[] data = line.split("\\|");

                if(data.length == 6) {
                    String transactionType = data[0];
                    double amount = Double.parseDouble(data[5].trim());

                    if(transactionType.equalsIgnoreCase("Deposit")) {
                        balance += amount;
                    } else if(transactionType.equalsIgnoreCase("Payment")) {
                        balance -= amount;
                    }
                }
            }
            bufferedReader.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return balance;
    }

}
