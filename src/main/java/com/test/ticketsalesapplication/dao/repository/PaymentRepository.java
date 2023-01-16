package com.test.ticketsalesapplication.dao.repository;

import com.test.ticketsalesapplication.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    Optional<Payment> findByTicketId(Integer id);
}
