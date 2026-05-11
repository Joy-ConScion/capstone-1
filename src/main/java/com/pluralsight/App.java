package com.pluralsight;

import java.io.*;
import java.time.*;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;

public class App {

    public static ArrayList<Transaction> transactions = new ArrayList<>();
    public static final java.util.Scanner keyboard = new java.util.Scanner(System.in);

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
                case 'd' -> DepositBack.addDeposit();
                case 'p' -> PaymentBack.makePayment();
                case 'l' -> {
                    try {
                        LedgerBack.accessLedger();
                    } catch (IOException e) {
                        System.out.println("Error involving ledger");
                    }
                }
                case 'x' -> programIsRunning = false;
                default -> System.out.println("Invalid Input, please try again.");
            }
        }

        keyboard.close();
    }

    public static ArrayList<Transaction> fetchTransLog() {
        transactions.clear(); // Prevent duplicates
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("TransactionExample.csv"))) {

            String input;
            DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("kk:mm:ss");

            bufferedReader.readLine(); // skip header
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

        } catch (IOException e) {
            System.out.println("Error - Couldn't retrieve request");
            e.printStackTrace();
        }
        return transactions;
    }

    public static void writeAction(String formatDate1, String formatTime2, String description, String vendor, double amount) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("TransactionExample.csv", true))) {
            bufferedWriter.write(formatDate1 + "|" + formatTime2 + "|" + description + "|" + vendor + "|" + amount);
            bufferedWriter.newLine();

            Transaction currentTransaction = new Transaction();
            currentTransaction.setDate(LocalDate.parse(formatDate1));
            currentTransaction.setTime(LocalTime.parse(formatTime2));
            currentTransaction.setDescription(description);
            currentTransaction.setVendor(vendor);
            currentTransaction.setAmount(amount);
            transactions.add(currentTransaction);

        } catch (IOException e) {
            System.out.println("Unexpected Error | Entry could not be stored");
        }
    }

}