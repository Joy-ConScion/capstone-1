package com.pluralsight;

import java.io.*;
import java.time.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;


public class App {

    public static ArrayList<Transaction> transactions = new ArrayList<>();
    static Scanner keyboard = new Scanner(System.in);
    static FileReader filereader;

    static {
        try {
            filereader = new FileReader("TransactionExample.csv");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    static BufferedReader bufferedReader = new BufferedReader(filereader);


    public static void main(String[] args) {

        boolean programIsRunning = true;

        while (programIsRunning) {

            System.out.println("Booting up...");
            System.out.println("System checks running...");
            System.out.println("All tests have been cleared...INITIATING");
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
                case 'l' -> callLedger();
                case 'x' -> programIsRunning = false;
                default -> System.out.println("Invalid Input, please try again.");

            }
        }
        keyboard.close();
    }

    private static void addDeposit() {

        DepositBack.addDeposit();
    }

    private static void makePayment() {

        PaymentBack.makePayment();
    }

    private static void callLedger() {
        try {
            LedgerBack.accessLedger();
        } catch (IOException e) {
            System.out.println("Error involving ledger");
        }
    }

    public static ArrayList<Transaction> fetchTransLog() {
        try {
            FileReader fileReader = new FileReader("TransactionExample.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String input;

            DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("kk:mm:ss");
            bufferedReader.readLine();
            while ((input = bufferedReader.readLine()) != null) {
                String[] tokAttrib = input.split("\\|");
                Transaction currentTransaction = new Transaction();
                currentTransaction.setDate(LocalDate.parse(tokAttrib[0], formatter1));
                currentTransaction.setTime(LocalTime.parse(tokAttrib[1], formatter2));
                currentTransaction.setDescription(tokAttrib[2]);
                currentTransaction.setVendor(tokAttrib[3]);
                currentTransaction.setAmount(Double.parseDouble(tokAttrib[4]));
                transactions.add(currentTransaction);
            }

            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("Error - Couldn't retrieve request");
            e.printStackTrace();
        }
        return transactions;
    }

    public static void writeAction(String formatDate1, String formatTime2, String paymentDescription, String paymentVendor, double paymentCheckAmount) {
        LocalDateTime localDateTime = LocalDateTime.now();
        try {
            FileWriter writer = new FileWriter("TransactionExample.csv", true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(formatDate1 + "|" + formatTime2 + "|" + paymentDescription + "|" + paymentVendor + "|" + paymentCheckAmount);
            bufferedWriter.newLine();
            Transaction currentTransaction = new Transaction();
            currentTransaction.setDate(localDateTime.toLocalDate());
            currentTransaction.setTime(localDateTime.toLocalTime());
            currentTransaction.setDescription(paymentDescription);
            currentTransaction.setVendor(paymentVendor);
            currentTransaction.setAmount(paymentCheckAmount);

            transactions.add(currentTransaction);
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("Unexpected Error | Entry could not be stored");
        }

    }

}
