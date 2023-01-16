package com.test.ticketsalesapplication.dao.repository;

import com.test.ticketsalesapplication.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket,Integer> {
    Optional<Ticket> findById(Integer id);
    List<Ticket> findAllByPaymentStatus(String status);
}
