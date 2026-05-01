package com.pluralsight;

import java.io.*;
import java.time.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;


public class App {
//    public App() throws FileNotFoundException {} /*Below code replaces the need for this code*/

    public static ArrayList<Transaction> transactions = new ArrayList<>();
    static Scanner keyboard = new Scanner(System.in);/*Establishing scanner for future use*/
    static FileReader filereader; /*Accepts the CSV file and reads it*/

    static {
        try {
            filereader = new FileReader("TransactionExample.csv");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    static BufferedReader bufferedReader = new BufferedReader(filereader); /*bufferedReader to be referred to for the whole system*/


    public static void main(String[] args) throws InterruptedException, IOException {
        LocalDateTime today = LocalDateTime.now();/*Establishing clock for future use---------MOVE INTO THE PROPER METHOD WHEN USED*/
        TimeZone timeZone = TimeZone.getTimeZone("US/Central");/*System.out.println("(Ex)Today is: " + formattedDate1);/*To test formats*/

        boolean programIsRunning = true;/*Starts program*/

        while (programIsRunning) {/*Establish while loop for the program to operate more than once and allows it to be manually closed*/

            System.out.println("Booting up...");/*Home Screen---------------------------------------------------------*/
            Thread.sleep(2000);
            System.out.println("System checks running...");
            Thread.sleep(3000);
            System.out.println("All tests have been cleared...INITIATING");
            Thread.sleep(1000);
            System.out.println("""
                    ~~~~~~~~~~~~~~~~
                    ~Super Bank Inc~
                    ~~~~~~~~~~~~~~~~
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

    private static void accessLedger() throws IOException {/*Ledger Screen (accessLedger Screen)--------------------------------------------------------------------*/
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

    private static void accessLedgerAll() throws IOException {
        System.out.println("");
        String transactionLine = bufferedReader.readLine();
        int number = 0;

        while (transactionLine != null) {
            System.out.println(transactionLine);
            transactionLine = bufferedReader.readLine();
        }
        System.out.println("---End of Current Records---");
    }

    private static ArrayList<Transaction> fetchTransLog() {
        try {
            FileReader fileReader = new FileReader("TransactionExample.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String input;

            DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd"); /*Callback: https://medium.com/@AlexanderObregon/javas-localdate-parse-method-explained-d2c2bb7322cb*/
            DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("kk:mm:ss");/*Need formatters again to work with the parsers*/
            bufferedReader.readLine(); /*Added to avoid reading the header*/
            while ((input = bufferedReader.readLine()) != null) {
                String[] tokAttrib = input.split("\\|");/*Shorthand for Token Attributes*/
                Transaction currentTransaction = new Transaction();
                currentTransaction.setDate(LocalDate.parse(tokAttrib[0], formatter1));
                currentTransaction.setTime(LocalTime.parse(tokAttrib[1], formatter2));
                currentTransaction.setDescription(tokAttrib[2]);/*Already string and therefore doesn't require parsing*/
                currentTransaction.setVendor(tokAttrib[3]);
                currentTransaction.setAmount(Double.parseDouble(tokAttrib[4]));
                transactions.add(currentTransaction);
            }

            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("Error - Couldn't retrieve request");
            e.printStackTrace();/*Leaves a trail of data so that error can be tracked and reviewed*/
        }
        return transactions;
    }


    private static void accessLedgerDeposits() {/*Loop through file backwards to help sort*/
        fetchTransLog();
        for (Transaction transaction : transactions) {/*Class | Ref in Datafield : Array*/
            if (transaction.getAmount() > 0) {/*This determines how data is sorted*/
                System.out.println((transaction.getDate() + "|" + transaction.getTime() + "|" + transaction.getDescription() + "|" + transaction.getVendor() + "|" + transaction.getAmount()));

            }


        }
    }

    private static void accessLedgerPayments() {
        fetchTransLog();
        for (Transaction transaction : transactions) {
            if (transaction.getAmount() < 0) {
                System.out.println((transaction.getDate() + "|" + transaction.getTime() + "|" + transaction.getDescription() + "|" + transaction.getVendor() + "|" + transaction.getAmount()));

            }


        }
    }

    private static void accessLedgerReports() {/*BONUS | Custom Reports Screen | NOT COMPLETED*/
        boolean inAccessLedgerReports = true;

        while (inAccessLedgerReports) {
            System.out.println("""
                    1) Month To Date
                    2) Previous Month
                    3) Year to Date
                    4) Previous Year
                    5) Search by Vendor
                    0) Back
                    """);
            System.out.println("Choose: ");
            char secondCommand = keyboard.next().toLowerCase().charAt(0);

            switch (secondCommand) {
                case '1' -> MonthDate();
                case '2' -> PrevMonth();
                case '3' -> YearDate();
                case '4' -> PrevYear();
                case '5' -> SearchVendor();
                case '0' -> inAccessLedgerReports = false;


            }

        }

    }

    private static void MonthDate() {/*Empty methods*/
    }

    private static void PrevMonth() {
    }

    private static void YearDate() {
    }

    private static void PrevYear() {
    }

    private static void SearchVendor() {
    }


    private static void makePayment() throws InterruptedException {/*Payment Screen (makePayment Screen)---------------------------------------------------------------------*/
        boolean inmakePayment = true;
        while (inmakePayment) {
            LocalDateTime today = LocalDateTime.now();/*prompt user for the deposit information and save it to the csv file | For flavor, have them add acct number and signature*/
            DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");/*Date format for CSV appendage*/
            String formatDate1 = today.format(formatter1);
            DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("kk:mm:ss");/*Time format for CSV appendage*/
            String formatTime2 = today.format(formatter2);

            String paymentVendor = "Bank";/*Vendor format for CSV appendage - Establishing info/variables (DATE/TIME/DESCRIPTION/VENDOR/AMOUNT) needed for appending later*/
            String paymentDescription = "Check Deposit";/*Descriptor format for CSV appendage*/

            System.out.println("To make a payment via check, please fill out the fields below.");/*And ensure that it's a minimum of $20. > Comment for realism but create it so that it kicks user if below $20*/
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

    private static void writeAction(String formatDate1, String formatTime2, String paymentDescription, String paymentVendor, double paymentCheckAmount) {/*Method to combine all previous info and export to CSV | Established for above write action*/
        LocalDateTime localDateTime = LocalDateTime.now();
        try {
            FileWriter writer = new FileWriter("TransactionExample.csv", true); /*This is the destination. Must also ensure that the append true is applied or will run into errors*/
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(formatDate1 + "|" + formatTime2 + "|" + paymentDescription + "|" + paymentVendor + "|" + paymentCheckAmount);/*Preps into format being used*/
            bufferedWriter.newLine();/*Starts writing in new line | Could've used  + "\n"*/
            Transaction currentTransaction = new Transaction();/*Create instance (object?)of transaction that will be added*/
            currentTransaction.setDate(localDateTime.toLocalDate());/*Rest of this is a snowball of the info*/
            currentTransaction.setTime(localDateTime.toLocalTime());/*.*/
            currentTransaction.setDescription(paymentDescription);/*..o*/
            currentTransaction.setVendor(paymentVendor);/*.oO0*/
            currentTransaction.setAmount(paymentCheckAmount);/*oO00*/

            transactions.add(currentTransaction);
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("Unexpected Error | Entry could not be stored");
        }

    }

    private static void addDeposit() {/*Deposit Screen (addDeposit Screen)------------------------------------------------------------------------*/
        boolean inaddDeposit = true;
        while (inaddDeposit) {/*(DATE/TIME/DESCRIPTION/VENDOR/AMOUNT)*/
            LocalDateTime today = LocalDateTime.now();/*BACKBURNER BONUS: Method for time | INTERESTING PIECE: Share experience with time format issues*/
            DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String formatDate1 = today.format(formatter1);/*Date*/
            DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("kk:mm:ss");
            String formatTime2 = today.format(formatter2);/*Time*/
            System.out.println("Description of purchase: ");/*Description*/
            keyboard.nextLine();
            String depositDescription = keyboard.nextLine();
            System.out.println("Vendor of purchase: ");/*Vendor*/
            String depositVendor = keyboard.nextLine();
            System.out.println("Amount spent on purchase: $"); /*Amount*/
            double depositAmount = keyboard.nextDouble();

            writeAction(formatDate1, formatTime2, depositDescription, depositVendor, depositAmount); /*Refer to method*/
            inaddDeposit = false;/*Closes loop*/

        }


    }


}
