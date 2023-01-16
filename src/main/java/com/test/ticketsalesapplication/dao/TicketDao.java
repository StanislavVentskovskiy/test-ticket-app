package com.test.ticketsalesapplication.dao;

import com.test.ticketsalesapplication.model.Ticket;
import java.util.Optional;

public interface TicketDao {
    Optional<Ticket> updateTicket(Ticket ticket);
}
