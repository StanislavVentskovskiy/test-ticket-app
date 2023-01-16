package com.test.ticketsalesapplication.service.impl;

import com.test.ticketsalesapplication.dao.repository.FlightRepository;
import com.test.ticketsalesapplication.dao.repository.TicketRepository;
import com.test.ticketsalesapplication.dao.impl.FlightDaoImpl;
import com.test.ticketsalesapplication.dao.impl.TicketDaoImpl;
import com.test.ticketsalesapplication.dto.TicketDto;
import com.test.ticketsalesapplication.model.Ticket;
import com.test.ticketsalesapplication.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private FlightDaoImpl flightDao;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private TicketDaoImpl ticketDao;

    @Override
    public Ticket findTicketById(Integer id){
        Ticket ticket = ticketRepository.findById(id).orElse(null);
        return ticket;
    }

    @Override
    public TicketDto saveTicket(TicketDto ticketDto) {
        Ticket ticket = new Ticket();
        ticket.setCustomerName(ticketDto.getCustomerName());
        ticket.setFlight_id(ticketDto.getFlight_id());
        ticket = ticketRepository.save(ticket);
        Integer ticketPrice = flightRepository.findById(ticket.getFlight_id()).get().getPrice();
        updateFlightInfo(ticketDto.getFlight_id());
        return TicketDto.builder()
               .id(ticket.getId())
               .customerName(ticket.getCustomerName())
               .flight_id(ticket.getFlight_id())
               .ticketPrice(ticketPrice)
               .build();
        }

    @Override
    public void updateFlightInfo(Integer flight_id){
        flightDao.updateTicketsNumber(flight_id);
    }

    @Override
    public Ticket updateTicket(Ticket ticket){
        return ticketDao.updateTicket(ticket).orElse(null);
    }
}
