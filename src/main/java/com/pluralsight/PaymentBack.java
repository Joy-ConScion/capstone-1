package com.pluralsight;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class PaymentBack {

    static Scanner keyboard = new Scanner(System.in);

    public static void makePayment() {

        boolean paymentBack = true;

        while (paymentBack) {

            LocalDateTime today = LocalDateTime.now();

            DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            String formatDate1 = today.format(formatter1);

            DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("kk:mm:ss");

            String formatTime2 = today.format(formatter2);

            String paymentVendor = "Bank";
            String paymentDescription = "Check Deposit";

            System.out.println("To make a payment via check, please fill out the fields below.");

            System.out.println("Enter amount as it appears on your check: $");

            double paymentCheckAmount = keyboard.nextDouble();

            keyboard.nextLine();

            System.out.println("Finally, type your name to digitally sign this check: ");

            String paymentCheckSignature = keyboard.nextLine();

            App.writeAction(formatDate1, formatTime2, paymentDescription, paymentVendor, paymentCheckAmount);

            System.out.println("Your payment has been submitted. Have a great day!");

            paymentBack = false;
        }
    }
}