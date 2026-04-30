package com.pluralsight;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;


public class App {
    public App() throws FileNotFoundException {
    }

    static ArrayList<Transaction> transactions = new ArrayList<>();
    //       Establishing scanner for future use----------
    static Scanner keyboard = new Scanner(System.in);
    //        Need to calculate (if there's a) negative amount on the account. If there is offer payment opt else sout N/A
    FileReader filereader = new FileReader("TransactionExample.csv"); /*Accepts the CSV file and reads it*/
    BufferedReader bufferedReader = new BufferedReader(filereader);


    public static void main(String[] args) throws InterruptedException, FileNotFoundException {
        LocalDateTime today = LocalDateTime.now();/*Establishing clock for future use---------MOVE INTO THE PROPER METHOD WHEN USED*/
        TimeZone timeZone = TimeZone.getTimeZone("US/Central");/*System.out.println("(Ex)Today is: " + formattedDate1);/*To test formats*/

        boolean programIsRunning = true;/*Starts program*/

        while (programIsRunning) {

            System.out.println("Booting up...");/*Home Screen---------------------------------------------------------*/
//            Thread.sleep(2000);
            System.out.println("System checks running...");
//            Thread.sleep(3000);
            System.out.println("All tests have been cleared...INITIATING");
//            Thread.sleep(1000);
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
                default -> System.out.println("Invalid Input, please try again.");

            }/*Closes switch case*/
        }/*Closes while loop*/
        keyboard.close();
    }/*Closes main method*/

    private static void accessLedger() {/*Ledger Screen (accessLedger Screen)--------------------------------------------------------------------*/
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
                case 'a' -> accessLedgerAll();
                case 'd' -> accessLedgerDeposits();
                case 'p' -> accessLedgerPayments();
                case 'r' -> accessLedgerReports();
                case 'h' -> inaccessLedger = false;
                default -> System.out.println("Invalid Input, please try again.");

            }

        }

    }

    private static void accessLedgerAll() {

    }
    private static void accessLedgerDeposits() {

    }
    private static void accessLedgerPayments() {

    }
    private static void accessLedgerReports() {

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

    private static void makePayment() throws InterruptedException {/*Payment Screen (makePayment Screen)---------------------------------------------------------------------*/
        boolean inmakePayment = true;
        while (inmakePayment) {
            LocalDateTime today = LocalDateTime.now();/*prompt user for the deposit information and save it to the csv file | For flavor, have them add acct number and signature*/
            DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy/MM/dd");/*Date format for CSV appendage*/
            String formatDate1 = today.format(formatter1);
            DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("hh:mm:ss");/*Time format for CSV appendage*/
            String formatTime2 = today.format(formatter2);

            String paymentVendor = "Bank";/*Vendor format for CSV appendage - Establishing info/variables (DATE/TIME/DESCRIPTION/VENDOR/AMOUNT) needed for appending later*/
            String paymentDescription = "Check Deposit";/*Descriptor format for CSV appendage*/

            System.out.println("To make a payment via check, please fill out the fields below.");
            System.out.println("Enter amount as it appears on your check: $");
            double paymentCheckAmount = keyboard.nextDouble();
            keyboard.nextLine(); /*That one error where it jumps over occurs here without this line. Single Carriage Missed Line*/
            System.out.println("Finally, type your name to digitally sign this check: ");
            String paymentCheckSignature = keyboard.nextLine();

            writeAction(formatDate1, formatTime2, paymentDescription, paymentVendor, paymentCheckAmount); /*Calls method to write to file*/
            Thread.sleep(1500);
            System.out.println("Your payment has been submitted. Have a great day!");
            inmakePayment = false; /*Closes this whole thing*/

//    System.out.println("Check Account Number: ");/*Some flavor code was added. But the rest is required to add required info to CSV (Acct #/Rout #/Check #/Client Sig)*/
//    int checkAccountNumber = keyboard.nextInt();/*May add as bonus later. Will keep separate to keep code simple*/
//    System.out.println("Check Routing Number: ");
//    int checkRoutingNumber = keyboard.nextInt();
//    System.out.println("Check Number: ");
//    int checkNumber = keyboard.nextInt();
//    System.out.println("Check Amount: ");
//    double checkAmount = keyboard.nextInt();
//                switch (choice) {/*Below code is commented out at instructors guidance, may return and add as bonus*/
//                case 'r' -> boolean inaddDeposit = false;
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
//                append (formattedDate1 + formattedDate2 + depositDescription + depositVendor + checkAmount) to csv | Save resources. Close your stuff out
        }

//         prompt user for the debit information and save it to the csv file


    }

    private static void writeAction(String formatDate1, String formatTime2, String paymentDescription, String paymentVendor, double paymentCheckAmount) {/*Established for above write action*/
        LocalDateTime localDateTime = LocalDateTime.now();
        try {
            FileWriter writer = new FileWriter("TransactionExample.csv", true); /*This is the destination. Must also ensure that the append true is applied or will run into errors*/
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(formatDate1 + "|" + formatTime2 + "|" + paymentDescription + "|" + paymentVendor + "|" + paymentCheckAmount);/*Preps into format being used*/
            bufferedWriter.newLine();
            Transaction currentTransaction = new Transaction();
            currentTransaction.setDate(localDateTime.toLocalDate());
            currentTransaction.setTime(localDateTime.toLocalTime());
            currentTransaction.setDescription(paymentDescription);
            currentTransaction.setVendor(paymentVendor);
            currentTransaction.setAmount(paymentCheckAmount);

            transactions.add(currentTransaction);
//            sortTransaction(); /*NEED TO MAKE METHOD*/
            bufferedWriter.close();
            writer.close();/*RECOMMENDED BUT IDK WHY*/
        } catch (IOException e) {
            System.out.println("Unexpected error");
        }

    }

    private static void addDeposit() {/*Deposit Screen (addDeposit Screen)------------------------------------------------------------------------*/
        boolean inaddDeposit = false;/*CHANGE TO TRUE WHEN READY TO WORK ON IT, for now it's false to prevent loop*/
        while (inaddDeposit) {


//        Method to combine all previous info and export to CSV goes here

        }


    }


}
