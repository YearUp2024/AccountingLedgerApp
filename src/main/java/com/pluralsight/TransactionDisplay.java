package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

/**
 * This class is going to print All the Transactions in the screen.
 */
public class TransactionDisplay {
    /**
     * Main method to start the transaction display.
     */
    public static void main() {
        HashMap<String, String[]> allEntries = LoadAllEntries();
        DisplayAllEntries(allEntries);
    }

    /**
     * Loads the transactions from transactions.csv and stores them in a HashMap.
     * @return allEntries
     */
    public static HashMap<String, String[]> LoadAllEntries(){
        String fileToGetDataFrom = "transactions.csv";
        HashMap<String, String[]> allProducts = new HashMap<>();
        int lineCounter = 1;

        try{
            FileReader fileReader = new FileReader(fileToGetDataFrom);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String readLine;
            while((readLine = bufferedReader.readLine()) != null){
                String[] data = readLine.split("\\|");

                if(data.length == 6){
                    //This is creating a unique key that can be used to identify products.
                    String uniqueKey = data[0] + "-" + lineCounter;
                    allProducts.put(uniqueKey, new String[]{data[1], data[2], data[3], data[4], data[5]});
                    lineCounter++;
                }else{
                    System.out.println(readLine + " Some elements are missing ");
                }
            }
            bufferedReader.close();

        }catch(Exception e){
            e.printStackTrace();
        }
        return allProducts;
    }

    /**
     * Prints all the transactions to the screen.
     * @param allEntries
     */
    public static void DisplayAllEntries(HashMap<String, String[]> allEntries){
        if(allEntries.isEmpty()){
            System.out.println("No entries found");
        }else{
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("| %-20s | %-20s | %-30s | %-30s | %-20s |\n", "Tranasaction Type", "Date", "Time", "Description", "Vendor");
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
            for(String key : allEntries.keySet()){
                String[] data = allEntries.get(key);
                System.out.printf("| %-20s | %-20s | %-30s | %-30s | %-20s |\n", key.split("-")[0], data[0], data[1], data[2], data[3], data[4]);
            }
        }
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("                                                    %s\n",TransactionBalance.GetAllTotal());
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
    }
}
