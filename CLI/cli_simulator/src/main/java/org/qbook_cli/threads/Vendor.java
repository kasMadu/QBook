package org.qbook_cli.threads;

import org.qbook_cli.system.TicketSystem;

public class Vendor implements Runnable{
    private final TicketSystem ticketSystem;
    private final int ticketReleaseRate;

    public Vendor(TicketSystem ticketSystem, int ticketReleaseRate) {
        this.ticketSystem = ticketSystem;
        this.ticketReleaseRate = ticketReleaseRate;
    }

    @Override
    public void run() {
        while (true) {
            ticketSystem.addTickets(ticketReleaseRate);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
