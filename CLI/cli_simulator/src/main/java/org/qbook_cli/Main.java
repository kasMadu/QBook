package org.qbook_cli;

import org.qbook_cli.system.TicketSystem;
import org.qbook_cli.threads.Customer;
import org.qbook_cli.threads.Vendor;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Taking input from the user
        System.out.println("Welcome to the QBook!");
        System.out.print("Enter maximum ticket capacity : ");
        int maxTicketCapacity = scanner.nextInt();

        System.out.print("Enter ticket release rate : ");
        int ticketReleaseRate = scanner.nextInt();

        System.out.print("Enter customer retrieval rate : ");
        int customerRetrievalRate = scanner.nextInt();

        TicketSystem ticketSystem = new TicketSystem(maxTicketCapacity);

        // Creating vendor and customer threads
        Thread vendorThread = new Thread(new Vendor(ticketSystem, ticketReleaseRate));
        Thread customerThread = new Thread(new Customer(ticketSystem, customerRetrievalRate));

        // Starting the threads
        vendorThread.start();
        customerThread.start();
    }
}
