package com.test.ticketsalesapplication.dao.impl;

import com.test.ticketsalesapplication.dao.TicketDao;
import com.test.ticketsalesapplication.dao.repository.TicketRepository;
import com.test.ticketsalesapplication.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Repository
@Transactional
public class TicketDaoImpl implements TicketDao {

    @Autowired
    private TicketRepository ticketRepository;

    public Optional<Ticket> updateTicket(Ticket ticket){
        Optional<Ticket> other = ticketRepository.findById(ticket.getId());
        if(other.isPresent()) {
            other.get().setPaymentStatus(ticket.getPaymentStatus());
            ticketRepository.save(other.get());
        }

        return other;
    }
}
