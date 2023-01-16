package com.test.ticketsalesapplication.facade;

import com.test.ticketsalesapplication.dto.PaymentDto;
import com.test.ticketsalesapplication.model.Payment;
import org.springframework.stereotype.Service;

@Service
public class PaymentFacade {

    public PaymentDto mapPaymentDto(Payment payment){
        PaymentDto paymentDto = PaymentDto.builder()
                .id(payment.getId())
                .paymentStatus(payment.getPaymentStatus())
                .customerName(payment.getCustomerName())
                .ticketPrice(payment.getTicketPrice())
                .ticketId(payment.getTicketId())
                .build();

      return paymentDto;
    }
}
