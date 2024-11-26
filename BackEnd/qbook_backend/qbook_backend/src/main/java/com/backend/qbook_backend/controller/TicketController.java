package com.backend.qbook_backend.controller;

import com.backend.qbook_backend.entity.Ticket;
import com.backend.qbook_backend.service.TicketService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping
    public List<Ticket> getAllTickets() {
        return ticketService.getAllTickets();
    }

    @GetMapping("/{id}")
    public Ticket getTicketById(@PathVariable Long id) {
        return ticketService.getTicketById(id).orElseThrow(() -> new RuntimeException("Ticket not found"));
    }

    @PostMapping
    public Ticket addTicket(@RequestBody Ticket ticket) {
        return ticketService.addTicket(ticket);
    }

    @PutMapping("/{id}")
    public Ticket updateTicket(@PathVariable Long id, @RequestBody int availableTickets) {
        return ticketService.updateTicket(id, availableTickets);
    }

    @DeleteMapping("/{id}")
    public void deleteTicket(@PathVariable Long id) {
        ticketService.deleteTicket(id);
    }
}