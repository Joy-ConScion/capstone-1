package com.pluralsight;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PaymentBack {

    public static void makePayment() {
        boolean inPayment = true;
        while (inPayment) {
            LocalDateTime now = LocalDateTime.now();
            String date = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            String time = now.format(DateTimeFormatter.ofPattern("kk:mm:ss"));

            System.out.println("Enter payment amount: $");
            double amount = App.keyboard.nextDouble();
            App.keyboard.nextLine(); // consume newline

            System.out.println("Type your name for signature:");
            String signature = App.keyboard.nextLine();

            App.writeAction(date, time, "Check Deposit", "Bank", amount);
            System.out.println("Payment submitted!");

            inPayment = false;
        }
    }

}