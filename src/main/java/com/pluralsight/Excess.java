package com.pluralsight;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

public class Excess {

    public Excess() throws FileNotFoundException {/*This is my code without ANY comments. Not to be used for presentation*/
    }

    static ArrayList<Transaction> transactions = new ArrayList<>();
    static Scanner keyboard = new Scanner(System.in);

    FileReader filereader = new FileReader("TransactionExample.csv");
    BufferedReader bufferedReader = new BufferedReader(filereader);

    public static void main(String[] args) throws InterruptedException, FileNotFoundException {

        LocalDateTime today = LocalDateTime.now();
        TimeZone timeZone = TimeZone.getTimeZone("US/Central");

        boolean programIsRunning = true;

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
                case 'h' -> inaccessLedger = false;
            }
        }
    }

    private static void accessLedgerAll() {
    }

    private static void makePayment() {
        boolean inmakePayment = true;

        while (inmakePayment) {
            LocalDateTime today = LocalDateTime.now();

            DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            String formatDate1 = today.format(formatter1);

            DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("hh:mm:ss");
            String formatTime2 = today.format(formatter2);

            String paymentVendor = "Bank";
            String paymentDescription = "Check Deposit";

            System.out.println("To make a payment via check, please fill out the fields below.");
            System.out.println("Enter amount as it appears on your check: $");

            double paymentCheckAmount = keyboard.nextDouble();
            keyboard.nextLine();

            System.out.println("Finally, type your name to digitally sign this check: ");
            String paymentCheckSignature = keyboard.nextLine();

            writeAction(formatDate1, formatTime2, paymentDescription, paymentVendor, paymentCheckAmount);

            inmakePayment = false;
        }
    }

    private static void writeAction(String formatDate1, String formatTime2,
                                    String paymentDescription, String paymentVendor,
                                    double paymentCheckAmount) {

        LocalDateTime localDateTime = LocalDateTime.now();

        try {
            FileWriter writer = new FileWriter("TransactionsExample.csv", true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            bufferedWriter.write(formatDate1 + "|" + formatTime2 + "|" +
                    paymentDescription + "|" + paymentVendor + "|" + paymentCheckAmount);

            bufferedWriter.newLine();

            Transaction currentTransaction = new Transaction();
            currentTransaction.setDate(localDateTime.toLocalDate());
            currentTransaction.setTime(localDateTime.toLocalTime());
            currentTransaction.setDescription(paymentDescription);
            currentTransaction.setVendor(paymentVendor);
            currentTransaction.setAmount(paymentCheckAmount);

            transactions.add(currentTransaction);

        } catch (IOException e) {
            System.out.println("Unexpected error");
        }
    }

    private static void addDeposit() {
        boolean inaddDeposit = true;

        while (inaddDeposit) {
        }
    }
}