package org.jtf.k8s.ticket.booking.repository;

import org.jtf.k8s.ticket.booking.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
}

