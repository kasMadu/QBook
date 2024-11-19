package org.example;

import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        long millis = System.currentTimeMillis();
        Date today = new Date(millis);

        UserManager userManager = new UserManager();

//        boolean isRegistered = userManager.register_user(3,"Mike", "securePassword123", "mike@test.com", "Vendor", today);
//        System.out.println("Registration successful: " + isRegistered);

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (userManager.authenticateUser(username, password)) {
            System.out.println("Login successful!");
        } else {
            System.out.println("Invalid username or password.");
        }

        scanner.close();
    }
}