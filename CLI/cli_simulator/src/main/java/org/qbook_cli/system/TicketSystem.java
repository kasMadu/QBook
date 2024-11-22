package org.qbook_cli.system;

import java.util.LinkedList;
import java.util.Queue;

public class TicketSystem {
    private final int maxTicketCapacity;
    private final Queue<Integer> tickets = new LinkedList<>();

    public TicketSystem(int maxTicketCapacity) {
        this.maxTicketCapacity = maxTicketCapacity;
    }

    public synchronized void addTickets(int count) {
        while (tickets.size() + count > maxTicketCapacity) {
            try {
                System.out.println("Vendor waiting, capacity full.");
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        for (int i = 0; i < count; i++) {
            tickets.add(1);
        }
        System.out.println("Vendor added " + count + " tickets. Total tickets: " + tickets.size());
        notifyAll();
    }

    public synchronized void retrieveTickets(int count) {
        while (tickets.size() < count) {
            try {
                System.out.println("Customer waiting, not enough tickets.");
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        for (int i = 0; i < count; i++) {
            tickets.poll(); // Removing a ticket
        }
        System.out.println("Customer purchased " + count + " tickets. Remaining tickets: " + tickets.size());
        notifyAll();
    }
}
