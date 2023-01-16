package com.test.ticketsalesapplication.dao;

import com.test.ticketsalesapplication.model.Flight;

public interface FlightDao {
    Flight updateTicketsNumber(Integer flightId);
    Flight backOneTicket(Integer flightId);
}
