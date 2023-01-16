package com.test.ticketsalesapplication.restcontroller;

import com.test.ticketsalesapplication.dto.*;
import com.test.ticketsalesapplication.facade.TicketFacade;
import com.test.ticketsalesapplication.model.Ticket;
import com.test.ticketsalesapplication.service.impl.TicketServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class TicketRestController {

    @Autowired
    private TicketServiceImpl ticketService;

    @Autowired
    private TicketFacade ticketFacade;

    static RestTemplate restTemplate = new RestTemplate();
    static String postPaymentURL = "http://localhost:8080/api/payments";
    static String getPaymentStatusUrl = "http://localhost:8080/api/payments/ticketId/";

    @RequestMapping("/api/get-ticket")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<TicketDto> createTicket(@RequestBody TicketDto ticketDto) {
        if (ticketDto == null) {

            return new ResponseEntity<TicketDto>(HttpStatus.BAD_REQUEST);
        }
        try {
            TicketDto ticketDtoResponse = ticketService.saveTicket(ticketDto);
            TicketPaymentDto ticketPaymentDto = ticketFacade.mapTicketPaymentDto(ticketDtoResponse);
            TicketPaymentDto returnPaymentDto = restTemplate.postForEntity(postPaymentURL, ticketPaymentDto, TicketPaymentDto.class).getBody();
            String status = returnPaymentDto.getPaymentStatus();
            ticketDtoResponse.setPaymentStatus(status);
            ticketService.updateTicket(ticketFacade.mapTicket(ticketDtoResponse));

            return new ResponseEntity<>(ticketDtoResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Flight not found", e);
        }
    }

    @GetMapping("/api/ticket-details/{id}")
    public TicketDetailsDto getTicketDetails(@PathVariable Integer id) {
        try {
            Ticket ticket = ticketService.findTicketById(id);
            ResponseEntity<PaymentDto> paymentDto = restTemplate.getForEntity(getPaymentStatusUrl + ticket.getId(), PaymentDto.class);
            TicketDetailsDto ticketDetailsDto = ticketFacade.mapTicketDetailsDto(ticket, paymentDto);

            return ticketDetailsDto;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ticket not found", e);
        }
    }
}