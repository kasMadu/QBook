package org.qbook_cli.threads;

import org.qbook_cli.system.TicketSystem;

public class Customer implements Runnable{
    private final TicketSystem ticketSystem;
    private final int customerRetrievalRate;

    public Customer(TicketSystem ticketSystem, int customerRetrievalRate) {
        this.ticketSystem = ticketSystem;
        this.customerRetrievalRate = customerRetrievalRate;
    }

    @Override
    public void run() {
        while (true) {
            ticketSystem.retrieveTickets(customerRetrievalRate);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
