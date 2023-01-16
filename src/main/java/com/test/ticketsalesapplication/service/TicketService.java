package com.test.ticketsalesapplication.service;

import com.test.ticketsalesapplication.dto.TicketDto;
import com.test.ticketsalesapplication.model.Ticket;

public interface TicketService {
    Ticket findTicketById(Integer id);
    TicketDto saveTicket(TicketDto ticket);
    void updateFlightInfo(Integer flight_id);
    Ticket updateTicket(Ticket ticket);
}
