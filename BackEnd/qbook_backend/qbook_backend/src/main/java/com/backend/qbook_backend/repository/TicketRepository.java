package com.backend.qbook_backend.repository;

import com.backend.qbook_backend.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
