package org.qbook_cli;

import org.qbook_cli.system.TicketSystem;
import org.qbook_cli.threads.Customer;
import org.qbook_cli.threads.Vendor;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the QBook!");
        int maxTicketCapacity = getValidInput(scanner, "Enter maximum ticket capacity (must be > 0): ");
        int ticketReleaseRate = getValidInput(scanner, "Enter ticket release rate (must be > 0): ");
        int customerRetrievalRate = getValidInput(scanner, "Enter customer retrieval rate (must be > 0): ");

        System.out.println("\n=======================================");

        System.out.println("\nSimulation Parameters:");
        System.out.println("Max Ticket Capacity: " + maxTicketCapacity);
        System.out.println("Ticket Release Rate: " + ticketReleaseRate);
        System.out.println("Customer Retrieval Rate: " + customerRetrievalRate);
        System.out.print("Do you want to start the simulation? (yes/no): ");
        String userChoice = scanner.next();

        if (!userChoice.equalsIgnoreCase("yes")) {
            System.out.println("Simulation canceled. Exiting program.");
            scanner.close();
            return;
        }

        TicketSystem ticketSystem = new TicketSystem(maxTicketCapacity);

        Thread vendorThread = new Thread(new Vendor(ticketSystem, ticketReleaseRate));
        Thread customerThread = new Thread(new Customer(ticketSystem, customerRetrievalRate));

        // Starting the threads
        vendorThread.start();
        customerThread.start();
    }

    private static int getValidInput(Scanner scanner, String prompt) {
        int value;
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                value = scanner.nextInt();
                if (value > 0) {
                    return value; // Valid input
                } else {
                    System.out.println("Error: Value must be greater than 0. Please try again.");
                }
            } else {
                System.out.println("Error: Please enter a valid integer.");
                scanner.next(); // Clear invalid input
            }
        }
    }
}
