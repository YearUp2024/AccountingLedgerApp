package com.pluralsight;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class Transaction {
    String date;
    String time;
    String description;
    String vendor;
    double amount;

    public Transaction(String date, String time, String description, String vendor, double amount){
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }

    public String getVendor() {
        return vendor;
    }

    public double getAmount() {
        return amount;
    }

    public void PrintTransaction(){
        System.out.printf("%s|%s|%s|%s|%.2f\n", date, time, description, vendor, amount);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", description='" + description + '\'' +
                ", vendor='" + vendor + '\'' +
                ", amount=" + amount +
                '}';
    }

    // non-static members go above here...
    //------------------------
    private static ArrayList<Transaction> fullledger = new ArrayList<Transaction>();
    public static ArrayList<Transaction> FullLedger(){
        return fullledger;
    }

    public static void LoadFromFile(){
        //read from file, populate the above arraylist
        try{
            FileReader fileReader = new FileReader("transactions.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            int skip = 0;
            while((line = bufferedReader.readLine()) != null){
                String[] data = line.split("\\|");

                if(data.toString().isEmpty()){
                    continue;
                }
                if(skip == 0){
                    skip++;
                    continue;
                }
                if(data.length == 5){
                    String date = data[0].trim();
                    String time = data[1].trim();
                    String description = data[2].trim();
                    String vendor = data[3].trim();
                    double amount = Double.parseDouble(data[4].trim());

                    Transaction transaction = new Transaction(date, time, description, vendor, amount);

                    if(!fullledger.contains(transaction)){
                        fullledger.add(transaction);
                    }else{
                        System.out.println("Duplicate");
                    }
                }
            }
            bufferedReader.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void SaveToFile(ArrayList<Transaction> transactions){
        try(FileWriter fileWriter = new FileWriter("transactions.csv", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)){

            for (Transaction transaction : transactions){
                bufferedWriter.write(String.format("%s|%s|%s|%s|%.2f\n",transaction.date, transaction.time, transaction.description, transaction.vendor, transaction.amount));
            }

//            bufferedWriter.write("date|time|description|vendor|amount\n");
//            for(Transaction transaction : transactions){
//                bufferedWriter.write(String.format("%s|%s|%s|%s|%.2f\n", transaction.date, transaction.time, transaction.description, transaction.vendor, transaction.amount));
//            }
//            System.out.println("Your transactions saved in the file successfully.");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
