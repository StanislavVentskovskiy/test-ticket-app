package com.test.ticketsalesapplication.dao.impl;

import com.test.ticketsalesapplication.dao.FlightDao;
import com.test.ticketsalesapplication.dao.repository.FlightRepository;
import com.test.ticketsalesapplication.model.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class FlightDaoImpl implements FlightDao {

    @Autowired
    FlightRepository flightRepository;

    public Flight updateTicketsNumber(Integer flightId){
        Flight flight = flightRepository.findById(flightId).get();
        flight.setAmountOfTickets(flight.getAmountOfTickets() - 1);

        return flightRepository.save(flight);
    }

    public Flight backOneTicket(Integer flightId){
        Flight flight = flightRepository.findById(flightId).get();
        flight.setAmountOfTickets(flight.getAmountOfTickets() + 1);

        return flightRepository.save(flight);
    }
}
