package org.example;

import java.sql.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("-----------Welcome to QBook booking system-----------\n" +
                "100 or REG: Register. \n" +
                "101 or LOG: Login. \n" +
                "999 or EXT: Exit the Program.");

        String signingInput;
        String nameOfUser = null;
        String userRole = null;
        boolean isSuccess = false;

        Scanner scanner = new Scanner(System.in);
        signingInput = scanner.nextLine();

        UserManager userManager = new UserManager();

        switch(signingInput) {
            case "100", "REG":
                long millis = System.currentTimeMillis();
                Date today = new Date(millis);

                System.out.print("Enter name: ");
                nameOfUser = scanner.nextLine();

                System.out.print("Enter username: ");
                String usernameReg = scanner.nextLine();

                System.out.print("Enter password: ");
                String passwordReg = scanner.nextLine();

                System.out.print("Enter email: ");
                String emailReg = scanner.nextLine();

                System.out.print("Enter role (Customer / Vendor): ");
                String roleReg = scanner.nextLine();
                userRole = roleReg.toLowerCase();

                boolean isRegistered = userManager.register_user(0, nameOfUser, usernameReg, passwordReg, emailReg, userRole, today);
                isSuccess = isRegistered;
                System.out.println("Registration successful: " + isRegistered);
                break;

            case "101", "LOG":
                System.out.print("Enter username: ");
                String usernameLog = scanner.nextLine();

                System.out.print("Enter password: ");
                String passwordLog = scanner.nextLine();

                nameOfUser = userManager.getName(usernameLog);

                if (userManager.authenticateUser(usernameLog, passwordLog)) {
                    System.out.println("Login successful!");
                    isSuccess = true;
                } else {
                    System.out.println("Invalid username or password.");
                    isSuccess = false;
                }
                break;

            case "999", "EXT":
                System.exit(0);
                break;

            default:
                System.out.println("----------INCORRECT----------");

        }
        System.out.println("\n-----------------------------------------------------------------------------------------------------");

        if(isSuccess == true) {
            System.out.println("\nHi " + nameOfUser + "!");

            TicketPool ticketPool = new TicketPool();

            String operatorInput;
            if (userRole == "customer") {
                System.out.println(
                        "102 or VAT: View available ticket count. \n" +
                        "103 or PT: Purchase tickets. \n" +
                        "104 or SEP: Save / Edit payment method. \n" +
                        "999 or EXT: Exit the Program.");

                do {
                    operatorInput = scanner.nextLine();

                    switch (operatorInput) {
                        case "102", "VAT":
                            ticketPool.viewAllTickets();
                            break;

                        case "103", "PT":
                            break;

                        case "104", "SEP":
                            break;

                        case "999", "EXT":
                            System.exit(0);
                            break;

                        default:
                            System.out.println("----------INCORRECT----------");
                    }
                    System.out.println("\n-----------------------------------------------------------------------------------------------------");
                    System.out.println("\nType the menu option if you want to use another option or type \"999\" or \"EXT\" if you want to exit...");

                }
                while (operatorInput != "999" || operatorInput != "EXT");

            } else {
                System.out.println(
                        "102 or AT: Add tickets. \n" +
                                "103 or VTL: View tickets list . \n" +
                                "104 or STP: Set ticket price. \n" +
                                "105 or VAS: View all sales. \n" +
                                "999 or EXT: Exit the Program.");

                do {
                    operatorInput = scanner.nextLine();

                    switch (operatorInput) {
                        case "102", "AT":
                            break;

                        case "103", "VTL":
                            break;

                        case "104", "STP":
                            break;

                        case "105", "VAS":
                            break;

                        case "999", "EXT":
                            System.exit(0);
                            break;

                        default:
                            System.out.println("----------INCORRECT----------");
                    }
                    System.out.println("\n-----------------------------------------------------------------------------------------------------");
                    System.out.println("\nType the menu option if you want to use another option or type \"999\" or \"EXT\" if you want to exit...");

                }
                while (operatorInput != "999" || operatorInput != "EXT");
            }
        }

        scanner.close();
    }
}