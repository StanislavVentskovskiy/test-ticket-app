package com.test.ticketsalesapplication.dao.repository;

import com.test.ticketsalesapplication.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface FlightRepository extends JpaRepository<Flight,Integer> {
    Optional<Flight> findById(Integer id);
}
