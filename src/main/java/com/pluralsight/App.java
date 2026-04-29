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
    public App() throws FileNotFoundException {
    }


    //       Establishing scanner for future use----------
    static Scanner keyboard = new Scanner(System.in);
    //        Need to calculate (if there's a) negative amount on the account. If there is offer payment opt else sout N/A
    FileReader filereader = new FileReader("TransactionExample.csv"); /*Accepts the CSV file and reads it*/
    BufferedReader bufferedReader = new BufferedReader(filereader);


    public static void main(String[] args) throws InterruptedException, FileNotFoundException {
//       Establishing clock for future use---------MOVE INTO THE PROPER METHOD WHEN USED
        LocalDateTime today = LocalDateTime.now();
        TimeZone timeZone = TimeZone.getTimeZone("US/Central");
//        System.out.println("(Ex)Today is: " + formattedDate1);
//


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
//            case 'h' -> thismainhomescreen();
            case 'x' -> programIsRunning = false;
        }
        keyboard.close();
    }

    //    Ledger Screen (accessLedger Screen)--------------------------------------------------------------------
    private static void accessLedger() {
        boolean inaccessLedger = true;
        while (inaccessLedger) {
            System.out.println("""
                    A) All
                    D) Deposits
                    P) Payments
                    R) Reports
                    H) Home
                    """);
            System.out.println("Choose: ");
            char secondCommand = keyboard.next().toLowerCase().charAt(0);
            switch (secondCommand) {
//                case 'a' -> ;
//                case 'd' -> ;
//                case 'p' -> ;
//                case 'r' -> ;
                case 'h' -> inaccessLedger = false;
            }

        }

    }
    private static void accessLedgerAll(){
        System.out.println("");
        String transactionLine = bufferedReader.readLine();
        int number = 0;

        while (transactionLine != null) {
            System.out.print(number++ + ". ");
            System.out.println(transactionLine);
            transactionLine = bufferedReader.readLine();

        }
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

    //    Payment Screen (makePayment Screen)---------------------------------------------------------------------
    private static void makePayment() {
        boolean inmakePayment = true;

//         prompt user for the debit information and save it to the csv file


    }

    //    Deposit Screen (addDeposit Screen)------------------------------------------------------------------------
    private static void addDeposit() {
        boolean inaddDeposit = true;

//      prompt user for the deposit information and save it to the csv file | For flavor, have them add acct number and signature
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
        System.out.println("Check Amount: ");
        double checkAmount = keyboard.nextInt();
        System.out.println("And finally, type your name to digitally sign this check: ");
        int checkSignature = keyboard.nextInt();
//            switch (choice) {
//                case 'r' -> boolean inaddDeposit = false;
//
//            }

//        switch (command){
//            case ""
//        }
//        while loop starts here
//        System.out.println("Does the following information look correct? " + checkRoutingNumber + " | " + checkRoutingNumber + " | " + checkNumber + " | "+ checkAmount + " | "+ checkSignature);
//        System.out.println("True (1) or False (0)");
//        int trueFalseCheck = keyboard.nextInt();
//        If false, then start over. Else, append it to CSV > Use while loop instead > back burner
//        if (trueFalseCheck == 0){
//        }else {
//        }
//          if (trueFalseCheck == 0);
//                run case 0;
//            else
//                append (formattedDate1 + formattedDate2 + depositDescription + depositVendor + checkAmount) to csv


//      Save resources. Close your stuff out

//        Method to combine all previous info and export to CSV goes here


    }


}
