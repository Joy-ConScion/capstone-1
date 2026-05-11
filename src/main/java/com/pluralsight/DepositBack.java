package com.pluralsight;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class DepositBack {

    static Scanner keyboard = new Scanner(System.in);

    public static void addDeposit() {
        boolean inaddDeposit = true;
        while (inaddDeposit) {
            LocalDateTime today = LocalDateTime.now();
            DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String formatDate1 = today.format(formatter1);
            DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("kk:mm:ss");
            String formatTime2 = today.format(formatter2);
            System.out.println("Description of purchase: ");
            keyboard.nextLine();
            String depositDescription = keyboard.nextLine();
            System.out.println("Vendor of purchase: ");
            String depositVendor = keyboard.nextLine();
            System.out.println("Amount spent on purchase: $");
            double depositAmount = keyboard.nextDouble();

            App.writeAction(formatDate1, formatTime2, depositDescription, depositVendor, depositAmount);
            inaddDeposit = false;/**/

        }


    }
}
