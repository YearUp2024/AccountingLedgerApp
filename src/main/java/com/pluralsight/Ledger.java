package com.pluralsight;

import java.util.Scanner;

public class Ledger {
    static Scanner scanner = new Scanner(System.in);
    static DisplayAllEntries displayAllEntries = new DisplayAllEntries();

    public static void main() {
        Options();
    }

    public static void Options(){
        while(true) {
            System.out.println("Display [A]ll Entries");
            System.out.println("[D]eposits Only");
            System.out.println("[P]ayments Only");
            System.out.println("[R]eports");
            System.out.println("[H]ome");
            System.out.print("Please choose from the options: ");
            String userChoose = scanner.nextLine();

            if(userChoose.equalsIgnoreCase("A")){
                System.out.println("------------------------------");
                displayAllEntries.main();
                System.out.println("------------------------------");
            }

            if(userChoose.equalsIgnoreCase("H") || userChoose.equalsIgnoreCase("Home")){
                break;
            }
        }
    }
}
