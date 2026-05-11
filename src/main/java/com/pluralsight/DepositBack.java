package com.pluralsight;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DepositBack {

    public static void addDeposit() {
        boolean inDeposit = true;
        while (inDeposit) {
            LocalDateTime now = LocalDateTime.now();
            String date = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            String time = now.format(DateTimeFormatter.ofPattern("kk:mm:ss"));

            App.keyboard.nextLine(); // consume leftover newline
            System.out.println("Description of purchase: ");
            String desc = App.keyboard.nextLine();
            System.out.println("Vendor of purchase: ");
            String vendor = App.keyboard.nextLine();
            System.out.println("Amount spent: $");
            double amount = App.keyboard.nextDouble();

            App.writeAction(date, time, desc, vendor, amount);
            inDeposit = false;
        }
    }

}