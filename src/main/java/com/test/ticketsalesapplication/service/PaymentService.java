package com.test.ticketsalesapplication.service;

import com.test.ticketsalesapplication.dto.PaymentDto;
import com.test.ticketsalesapplication.model.Payment;

public interface PaymentService {
    Payment findPaymentById(Integer id);
    PaymentDto savePayment(PaymentDto paymentDto);
    Payment findByTicketId(Integer id);
    String getPaymentStatus(Integer paymentId);
}
