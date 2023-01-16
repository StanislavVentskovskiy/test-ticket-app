package com.test.ticketsalesapplication.schedulestatuschecker;

import com.test.ticketsalesapplication.dao.repository.TicketRepository;
import com.test.ticketsalesapplication.dao.impl.FlightDaoImpl;
import com.test.ticketsalesapplication.dao.impl.TicketDaoImpl;
import com.test.ticketsalesapplication.model.Ticket;
import com.test.ticketsalesapplication.util.PaymentStatusGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ScheduleStatusChecker {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private TicketDaoImpl ticketDao;

    @Autowired
    private FlightDaoImpl flightDao;

    @Autowired
    private PaymentStatusGenerator paymentStatusGenerator;

    Logger logger = LoggerFactory.getLogger(ScheduleStatusChecker.class);

    private static String newStatus = "NEW";

    private List<Ticket> findTicketWithNewStatus(){
        logger.info("enter findTicketWithNewStatus");
        List<Ticket> ticketWithNewStatus = ticketRepository.findAllByPaymentStatus(newStatus);
        return ticketWithNewStatus;
    }

    private List<Ticket> setNewRandomStatus(List<Ticket> tickets){
        logger.info("enter setNewRandomStatus");
        tickets.forEach(ticket -> ticket.setPaymentStatus(paymentStatusGenerator.getRandomStatus(ticket.getId())));
        return tickets;
    }

    private void updateTicketsStatus(List<Ticket> tickets){
        logger.info("enter updateTicketsStatus");
        tickets.forEach(ticket -> ticketDao.updateTicket(ticket));
    }

    private void updateTicketNumber(Ticket ticket){
        logger.info("enter updateTicketNumber");
        if (ticket.getPaymentStatus() == "FAILED"){
            flightDao.backOneTicket(ticket.getFlight_id());
        }
    }

    @Scheduled(cron="0 0/1 * * * ?")
    private void updateAllFlightsTicketNumber(){
        logger.info("enter updateAllFlightsTicketNumber");
        List<Ticket> ticketsWithNewStatus = findTicketWithNewStatus();
        ticketsWithNewStatus = setNewRandomStatus(ticketsWithNewStatus);
        updateTicketsStatus(ticketsWithNewStatus);
        ticketsWithNewStatus.forEach(ticket -> updateTicketNumber(ticket));
    }
}
