package com.pluralsight;

import java.util.Scanner;

/**
 * When the program starts this Class is going to prompt the users to choose from different options.
 * Based on the user choice this Class is going to call other classes.
 */
public class HomeScreen {
    static Scanner scanner = new Scanner(System.in);
    static AddDeposit addDeposit = new AddDeposit();
    static MakePayment makePayment = new MakePayment();
    static Ledger ledger = new Ledger();

    /**
     * This main method is calling Operation method.
     * @param args
     */
    public static void main(String[] args) {
        Options();
    }

    /**
     * This method is prompting the user for an input. Based on the input different Classes are called.
     */
    public static void Options(){
        String userChoice = "";
        while(!userChoice.equalsIgnoreCase("E")) {
            //Use can choose from these options
            System.out.println("Add [D]eposit");
            System.out.println("Make [P]ayment");
            System.out.println("[L]edger");
            System.out.println("[E]xit");
            System.out.print("Choose from the Options: ");
            userChoice = scanner.nextLine();

            //This is checking if the used is calling AddDeposit Class
            if(userChoice.equalsIgnoreCase("D")){
                System.out.println("------------------------------");
                addDeposit.main();
                System.out.println("------------------------------");
            }
            //This is checking if the used is calling MakePayment Class
            if(userChoice.equalsIgnoreCase("P")){
                System.out.println("------------------------------");
                makePayment.main();
                System.out.println("------------------------------");
            }
            //This is checking if the used is calling Ledger Class
            if(userChoice.equalsIgnoreCase("L")){
                System.out.println("------------------------------");
                ledger.main();
                System.out.println("------------------------------");
            }
            //This is checking if the used wants to end the loop
            if(userChoice.equalsIgnoreCase("E")){
                System.out.println("------------------------------");
                System.out.println("       Program Ended          ");
                System.out.println("------------------------------");
            }
        }
    }
}