package com.pluralsight;

import java.util.Scanner;

public class HomeScreen {
    static Scanner scanner = new Scanner(System.in);
    static AddDeposit addDeposit = new AddDeposit();

    public static void main(String[] args) {
        Options();
    }

    public static void Options(){
        String userChoice = "";
        while(!userChoice.equalsIgnoreCase("E")) {
            System.out.println("Add [D]eposit");
            System.out.println("Make [P]ayment");
            System.out.println("[L]edger");
            System.out.println("[E]xit");
            System.out.print("Choose from the Options: ");
            userChoice = scanner.nextLine();

            if(userChoice.equalsIgnoreCase("D")){
                System.out.println("------------------------------");
                addDeposit.main();
                System.out.println("------------------------------");
            }
            if(userChoice.equalsIgnoreCase("E")){
                System.out.println("------------------------------");
                System.out.println("       Program Ended          ");
                System.out.println("------------------------------");
            }
        }
    }
}