package org.jtf.k8s.ticket.booking.controller;

import org.jtf.k8s.ticket.booking.entity.Ticket;
import org.jtf.k8s.ticket.booking.repository.TicketRepository;
import org.springframework.stereotype.Controller;

// TicketController.java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TicketController {

    @Autowired
    private TicketRepository ticketRepository;

    @GetMapping("/tickets")
    public String listTickets(Model model) {
        model.addAttribute("tickets", ticketRepository.findAll());
        return "ticket/list";
    }

    @GetMapping("/tickets/new")
    public String newTicketForm(Model model) {
        model.addAttribute("ticket", new Ticket());
        return "ticket/new";
    }

    @PostMapping("/tickets")
    public String saveTicket(@ModelAttribute("ticket") Ticket ticket) {
        ticketRepository.save(ticket);
        return "redirect:/tickets";
    }

    @GetMapping("/tickets/book/{id}")
    public String bookTicket(@PathVariable Long id) {
        Ticket ticket = ticketRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid ticket Id:" + id));

        if (ticket.getAvailableSeats() > 0) {
            ticket.setAvailableSeats(ticket.getAvailableSeats() - 1);
            ticketRepository.save(ticket);
        }

        return "redirect:/tickets";
    }
}

