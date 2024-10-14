package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

/**
 * This class is going to print All the Transactions in the screen.
 */
public class DisplayAllEntries {
    static TotalBalance totalBalance = new TotalBalance();
    /**
     * In this main method a HashMap is calling LoadAllEntries method to get the transactions and store it in
     * a HashMap. And then the transactions are being passed onto DisplayAllEntries method so it can be
     * printed in the screen.
     */
    public static void main() {
        HashMap<String, String[]> allEntries = LoadAllEntries();
        DisplayAllEntries(allEntries);
    }

    /**
     * This method is loading the transactions from transactions.csv and storing it in a HashMap.
     * @return allProducts
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
     * This method is printing all the transactions to the screen.
     * @param allEntries
     */
    public static void DisplayAllEntries(HashMap<String, String[]> allEntries){
        if(allEntries.isEmpty()){
            System.out.println("No entries found");
        }else{
            for(String key : allEntries.keySet()){
                String[] data = allEntries.get(key);
                //key.split("-")[0] gets unique identifier
                System.out.printf("%s|%s|%s|%s|%s|%s\n", key.split("-")[0], data[0], data[1], data[2], data[3], data[4]);
            }
        }
        totalBalance.GetAllTotal();
    }
}
