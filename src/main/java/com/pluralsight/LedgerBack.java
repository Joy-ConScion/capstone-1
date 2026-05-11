package com.pluralsight;

import java.io.IOException;
import java.time.LocalDate;

public class LedgerBack {

    public static void accessLedger() throws IOException {
        boolean inLedger = true;
        while (inLedger) {
            System.out.println("""
                    A) All
                    D) Deposits
                    P) Payments
                    R) Reports
                    H) Home
                    """);
            System.out.println("Choose: ");
            char command = App.keyboard.next().toLowerCase().charAt(0);

            switch (command) {
                case 'a' -> accessLedgerAll();
                case 'd' -> accessLedgerDeposits();
                case 'p' -> accessLedgerPayments();
                case 'r' -> accessLedgerReports();
                case 'h' -> inLedger = false;
                default -> System.out.println("Invalid Input, please try again.");
            }
        }
    }

    private static void accessLedgerAll() {
        App.fetchTransLog();
        for (Transaction t : App.transactions) {
            System.out.println(String.format("%s|%s|%s|%s|%.2f", t.getDate(), t.getTime(), t.getDescription(), t.getVendor(), t.getAmount()));
        }
        System.out.println("---End of Current Records---");
    }

    private static void accessLedgerDeposits() {
        App.fetchTransLog();
        for (Transaction t : App.transactions)
            if (t.getAmount() > 0)
                System.out.println(String.format("%s|%s|%s|%s|%.2f", t.getDate(), t.getTime(), t.getDescription(), t.getVendor(), t.getAmount()));
    }

    private static void accessLedgerPayments() {
        App.fetchTransLog();
        for (Transaction t : App.transactions)
            if (t.getAmount() < 0)
                System.out.println(String.format("%s|%s|%s|%s|%.2f", t.getDate(), t.getTime(), t.getDescription(), t.getVendor(), t.getAmount()));
    }

    private static void accessLedgerReports() {
        boolean inReports = true;
        while (inReports) {
            System.out.println("""
                    1) Month To Date
                    2) Previous Month
                    3) Year to Date
                    4) Previous Year
                    5) Search by Vendor
                    0) Back
                    """);
            System.out.println("Choose: ");
            char command = App.keyboard.next().toLowerCase().charAt(0);

            switch (command) {
                case '1' -> MonthDate();
                case '2' -> PrevMonth();
                case '3' -> YearDate();
                case '4' -> PrevYear();
                case '5' -> SearchVendor();
                case '0' -> inReports = false;
            }
        }
    }

    private static void MonthDate() {
        App.fetchTransLog();
        App.keyboard.nextLine(); // consume newline
        System.out.println("Enter month YYYY-MM: ");
        LocalDate date = LocalDate.parse(App.keyboard.nextLine() + "-01");
        for (Transaction t : App.transactions)
            if (t.getDate().getYear() == date.getYear() && t.getDate().getMonth() == date.getMonth())
                System.out.println(String.format("%s|%s|%s|%s|%.2f", t.getDate(), t.getTime(), t.getDescription(), t.getVendor(), t.getAmount()));
    }

    private static void PrevMonth() {
        App.fetchTransLog();
        LocalDate prev = LocalDate.now().minusMonths(1);
        for (Transaction t : App.transactions)
            if (t.getDate().getYear() == prev.getYear() && t.getDate().getMonth() == prev.getMonth())
                System.out.println(String.format("%s|%s|%s|%s|%.2f", t.getDate(), t.getTime(), t.getDescription(), t.getVendor(), t.getAmount()));
    }

    private static void YearDate() {
        App.fetchTransLog();
        int year = LocalDate.now().getYear();
        for (Transaction t : App.transactions)
            if (t.getDate().getYear() == year)
                System.out.println(String.format("%s|%s|%s|%s|%.2f", t.getDate(), t.getTime(), t.getDescription(), t.getVendor(), t.getAmount()));
    }

    private static void PrevYear() {
        App.fetchTransLog();
        int year = LocalDate.now().getYear() - 1;
        for (Transaction t : App.transactions)
            if (t.getDate().getYear() == year)
                System.out.println(String.format("%s|%s|%s|%s|%.2f", t.getDate(), t.getTime(), t.getDescription(), t.getVendor(), t.getAmount()));
    }

    private static void SearchVendor() {
        App.fetchTransLog();
        App.keyboard.nextLine();
        System.out.println("Type vendor: ");
        String vendor = App.keyboard.nextLine();
        for (Transaction t : App.transactions)
            if (t.getVendor().equalsIgnoreCase(vendor))
                System.out.println(String.format("%s|%s|%s|%s|%.2f", t.getDate(), t.getTime(), t.getDescription(), t.getVendor(), t.getAmount()));
    }

}