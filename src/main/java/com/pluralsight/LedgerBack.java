package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class LedgerBack {

    static Scanner keyboard = new Scanner(System.in);

    public static void accessLedger() throws IOException {
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

    public static void accessLedgerAll() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("TransactionExample.csv"));
        String transactionLine = bufferedReader.readLine();
        while (transactionLine != null) {
            System.out.println(transactionLine);
            transactionLine = bufferedReader.readLine();
        }
        bufferedReader.close();
        System.out.println("---End of Current Records---");
    }

    private static void accessLedgerDeposits() {
        for (Transaction transaction : App.transactions)
            if (transaction.getAmount() > 0)
                System.out.println(transaction.getDate() + "|" + transaction.getTime() + "|" + transaction.getDescription() + "|" + transaction.getVendor() + "|" + transaction.getAmount());
    }

    private static void accessLedgerPayments() {
        for (Transaction transaction : App.transactions)
            if (transaction.getAmount() < 0)
                System.out.println(transaction.getDate() + "|" + transaction.getTime() + "|" + transaction.getDescription() + "|" + transaction.getVendor() + "|" + transaction.getAmount());
    }

    private static void accessLedgerReports() {
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

    private static void MonthDate() {
        App.fetchTransLog();
        System.out.println("Please input the month to review. Format: YYYY-MM");
        keyboard.nextLine(); // consume leftover newline
        LocalDate givenDate = LocalDate.parse(keyboard.nextLine() + "-01");
        for (Transaction transaction : App.transactions) {
            LocalDate storedDate = transaction.getDate();
            if (storedDate.getYear() == givenDate.getYear() &&
                    storedDate.getMonth() == givenDate.getMonth()) {
                System.out.println(String.format("%s|%s|%s|%s|%.2f", transaction.getDate(), transaction.getTime(), transaction.getDescription(), transaction.getVendor(), transaction.getAmount()));
            }
        }
    }

    private static void PrevMonth() {
        App.fetchTransLog();
        LocalDate now = LocalDate.now();
        LocalDate prevMonth = now.minusMonths(1);
        for (Transaction transaction : App.transactions) {
            LocalDate storedDate = transaction.getDate();
            if (storedDate.getYear() == prevMonth.getYear() &&
                    storedDate.getMonth() == prevMonth.getMonth()) {
                System.out.println(String.format("%s|%s|%s|%s|%.2f", transaction.getDate(), transaction.getTime(), transaction.getDescription(), transaction.getVendor(), transaction.getAmount()));
            }
        }
    }

    private static void YearDate() {
        App.fetchTransLog();
        int currentYear = LocalDate.now().getYear();
        for (Transaction transaction : App.transactions) {
            LocalDate storedDate = transaction.getDate();
            if (storedDate.getYear() == currentYear) {
                System.out.println(String.format("%s|%s|%s|%s|%.2f", transaction.getDate(), transaction.getTime(), transaction.getDescription(), transaction.getVendor(), transaction.getAmount()));
            }
        }
    }

    private static void PrevYear() {
        App.fetchTransLog();
        int prevYear = LocalDate.now().getYear() - 1;
        for (Transaction transaction : App.transactions) {
            LocalDate storedDate = transaction.getDate();
            if (storedDate.getYear() == prevYear) {
                System.out.println(String.format("%s|%s|%s|%s|%.2f", transaction.getDate(), transaction.getTime(), transaction.getDescription(), transaction.getVendor(), transaction.getAmount()));
            }
        }
    }

    private static void SearchVendor() {
        App.fetchTransLog();
        keyboard.nextLine();
        System.out.println("Please type which vendor you would like to see entries for: ");
        String vendor = keyboard.nextLine().toLowerCase();

        for (Transaction transaction : App.transactions) {
            if (transaction.getVendor().toLowerCase().equals(vendor)) {
                System.out.println(String.format("%s|%s|%s|%s|%.2f", transaction.getDate(), transaction.getTime(), transaction.getDescription(), transaction.getVendor(), transaction.getAmount()));
            }
        }
    }
}

