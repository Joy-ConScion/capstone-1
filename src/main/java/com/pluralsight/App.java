package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;


public class App {
    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
//       Establishing scanner for future use----------
        Scanner keyboard = new Scanner(System.in);
//       Establishing clock for future use---------
        LocalDateTime today = LocalDateTime.now();
        TimeZone timeZone = TimeZone.getTimeZone("US/Central");
//        System.out.println("(Ex)Today is: " + formattedDate1);
//


//        Accepts the CSV file and reads it
        FileReader filereader = new FileReader("TransactionExample.csv");
        BufferedReader bufferedReader = new BufferedReader(filereader);
//        Starts program
        boolean programIsRunning = true;

//        Home Screen
        System.out.println("Booting up...");
        Thread.sleep(2000);
        System.out.println("System checks running...");
        Thread.sleep(3000);
        System.out.println("All tests have been cleared...INITIATING");
        Thread.sleep(1000);
        System.out.println("""
                D) Add Deposit
                P) Make Payment (Debit)
                L) Access Ledger
                X) Exit
                """);
        System.out.println("Choose: ");

        char command = keyboard.next().toLowerCase().charAt(0);

        switch (command) {
            case 'd' -> addDeposit();
            case 'p' -> makePayment();
            case 'l' -> accessLedger();
            case 'x' -> programIsRunning = false;
        }
        keyboard.close();
    }

    //    Ledger Screen (accessLedger Screen)---------------------------------------------------------
    private static void accessLedger() {
        //       Establishing scanner for use
        Scanner keyboard = new Scanner(System.in);
        System.out.println("""
                A) All
                D) Deposits
                P) Payments
                R) Reports
                H) Home
                """);
        System.out.println("Choose: ");
//        Add code to turn char into lowercased
        char secondCommand = keyboard.next().charAt(0);
        System.out.println(secondCommand);


        keyboard.close();
    }
//                BONUS | Custom Reports Screen
//        System.out.println("""
//                1) Month To Date
//                2) Previous Month
//                3) Year to Date
//                4) Previous Year
//                5) Search by Vendor
//                0) Back
//                """();

    //    Payment Screen (makePayment Screen)------------------------------------------------------------
    private static void makePayment() {
//         prompt user for the debit information and save it to the csv file


    }

    //    Deposit Screen (addDeposit Screen)-------------------------------------------------------------
    private static void addDeposit() {
//      prompt user for the deposit information and save it to the csv file | For flavor, have them add acct number and signature
        Scanner keyboard = new Scanner(System.in);
        LocalDateTime today = LocalDateTime.now();

//        Vendor format for CSV appendage - Establishing info/variables (date/time/description/vendor/amount) needed for appending later
        String depositVendor = "Bank";
//        Descriptor format for CSV appendage
        String depositDescription = "Check Deposit";
        //      Date format for CSV appendage
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String formattedDate1 = today.format(formatter1);
//      Time format for CSV appendage
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("hh:mm:ss");
        String formattedDate2 = today.format(formatter2);

        System.out.println("To deposit your check, please fill out the fields below.");

//        Some flavor code was added. But the rest is required to add required info to CSV (Acct #/Rout #/Check #/Client Sig)
        System.out.println("Check Account Number: ");
        int checkAccountNumber = keyboard.nextInt();
        System.out.println("Check Routing Number: ");
        int checkRoutingNumber = keyboard.nextInt();
        System.out.println("Check Number: ");
        int checkNumber = keyboard.nextInt();
        System.out.println("And finally, type your name to digitally sign this check: ");
        int checkSignature = keyboard.nextInt();
        System.out.println("Does the following information look correct? " + checkRoutingNumber + " | " + checkRoutingNumber + " | " + checkNumber + " | " + checkSignature);
        System.out.println("True (1) or False (0)");
        int trueFalseCheck = keyboard.nextInt();

//        If false, then start over. Else, append it to CSV
        if (trueFalseCheck == 0);
                run case 0;
            else

//      Save resources. Close your stuff out
        keyboard.close();

//        Method to combine all previous info and export to CSV goes here


    }


}
