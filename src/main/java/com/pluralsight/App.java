package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.SQLOutput;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws FileNotFoundException {
//       Establishing scanner for future use
        Scanner keyboard = new Scanner(System.in);
//        Accepts the CSV file and reads it
        FileReader filereader = new FileReader("TransactionExample.csv");
        BufferedReader bufferedReader = new BufferedReader(filereader);
//Starts program
        boolean programIsRunning = true;

//        Home Screen
        System.out.println("Booting up...");
        System.out.println("System checks running...");
        System.out.println("All tests have been cleared...INITIATING");
        System.out.println("""
                D) Add Deposit
                P) Make Payment (Debit)
                L) Ledger
                X) Exit
                """);

//        Add code to turn char into lowercased
        char command = keyboard.next().charAt(0);
        System.out.println(command);

        char d = 0;
        char p = 1;
        char l = 2;
        char x = 3;

        switch (command){
            case d -> addDeposit();
            case p -> makePayment();
            case l -> accessLedger();
            case x ->  programIsRunning = false;
        }

    }
//    Ledger Screen (accessLedger Screen)
    private static void accessLedger() {
        System.out.println("""
                    A) All
                    D) Deposits
                    P) Payments
                    R) Reports
                    H) Home
                    """);
    }
//                BONUS | Custom Reports Screen
//        System.out.println("""
//                1) Month To Date
//                2) Previous Month
//                3) Year to Date
//                4) Previous Year
//                5) Search by Vendor
//                0) Back
//                """);
}
