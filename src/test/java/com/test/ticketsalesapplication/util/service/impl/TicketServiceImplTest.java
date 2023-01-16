package com.test.ticketsalesapplication.util.service.impl;

import com.test.ticketsalesapplication.dao.impl.FlightDaoImpl;
import com.test.ticketsalesapplication.dao.impl.TicketDaoImpl;
import com.test.ticketsalesapplication.dao.repository.FlightRepository;
import com.test.ticketsalesapplication.dao.repository.TicketRepository;
import com.test.ticketsalesapplication.model.Ticket;
import com.test.ticketsalesapplication.service.impl.TicketServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TicketServiceImpl.class, TicketRepository.class,
                FlightDaoImpl.class, FlightRepository.class})
public class TicketServiceImplTest {

    @Autowired
    TicketServiceImpl ticketService;

    @MockBean
    private TicketRepository ticketRepository;

    @MockBean
    private FlightDaoImpl flightDao;

    @MockBean
    private FlightRepository flightRepository;

    @MockBean
    private TicketDaoImpl testTicketDao;

    private Ticket testTicket = new Ticket();
    private int testId = 1;

    @Test
    public void findTicketByIdTest_shouldCallDaoMethod(){
        ticketService.findTicketById(testId);

        verify(ticketRepository, Mockito.times(1)).findById(Mockito.any());
    }

    @Test
    public void updateFlightInfoTest_shouldCallDaoMethod(){
        ticketService.updateFlightInfo(testId);

        verify(flightDao, Mockito.times(1)).updateTicketsNumber(Mockito.anyInt());
    }

    @Test
    public void updateTicket_shouldCallDaoMethod(){
        ticketService.updateTicket(testTicket);

        verify(testTicketDao, Mockito.times(1)).updateTicket(Mockito.any());
    }
}
