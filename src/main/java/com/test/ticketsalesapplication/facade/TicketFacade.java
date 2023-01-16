package com.test.ticketsalesapplication.facade;

import com.test.ticketsalesapplication.dto.PaymentDto;
import com.test.ticketsalesapplication.dto.TicketDetailsDto;
import com.test.ticketsalesapplication.dto.TicketDto;
import com.test.ticketsalesapplication.dto.TicketPaymentDto;
import com.test.ticketsalesapplication.model.Ticket;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TicketFacade {

    public TicketPaymentDto mapTicketPaymentDto(TicketDto ticketDto){
        TicketPaymentDto ticketPaymentDto = TicketPaymentDto.builder()
                     .ticketId(ticketDto.getId())
                     .customerName(ticketDto.getCustomerName())
                     .ticketPrice(ticketDto.getTicketPrice())
                     .build();

        return ticketPaymentDto;
    }

    public TicketDetailsDto mapTicketDetailsDto(Ticket ticket, ResponseEntity<PaymentDto> responseEntityPaymentDto){
        TicketDetailsDto ticketDetailsDto = TicketDetailsDto.builder()
                .id(ticket.getId())
                .customerName(ticket.getCustomerName())
                .startLocation(ticket.getFlight().getStartLocation())
                .finalLocation(ticket.getFlight().getFinalLocation())
                .departureTime(ticket.getFlight().getDepartureTime())
                .price(ticket.getFlight().getPrice())
                .flight_id(ticket.getFlight_id())
                .paymentStatus(responseEntityPaymentDto.getBody().getPaymentStatus())
                .build();

        return ticketDetailsDto;
    }

    public Ticket mapTicket(TicketDto ticketDto){
        Ticket ticket = new Ticket();
        ticket.setPaymentStatus(ticketDto.getPaymentStatus());
        ticket.setId(ticketDto.getId());
        ticket.setFlight_id(ticketDto.getFlight_id());
        ticket.setCustomerName(ticketDto.getCustomerName());

        return ticket;
    }
}
