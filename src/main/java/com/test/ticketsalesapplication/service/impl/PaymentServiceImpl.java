package com.test.ticketsalesapplication.service.impl;

import com.test.ticketsalesapplication.dao.repository.PaymentRepository;
import com.test.ticketsalesapplication.dto.PaymentDto;
import com.test.ticketsalesapplication.facade.PaymentFacade;
import com.test.ticketsalesapplication.model.Payment;
import com.test.ticketsalesapplication.service.PaymentService;
import com.test.ticketsalesapplication.util.PaymentStatusGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private PaymentStatusGenerator paymentStatusGenerator;

    @Autowired
    private PaymentFacade paymentFacade;

    @Override
    public Payment findPaymentById(Integer id) {
        return paymentRepository.getById(id);
    }

    @Override
    public PaymentDto savePayment(PaymentDto paymentDto) {
        Payment payment = new Payment(paymentDto.getTicketId(),
                paymentDto.getCustomerName(),
                paymentDto.getTicketPrice(),
                getPaymentStatus(paymentDto.getId()));
        Payment returnPayment = paymentRepository.save(payment);

        return paymentFacade.mapPaymentDto(returnPayment);
    }

    @Override
    public String getPaymentStatus(Integer paymentId){
        return paymentStatusGenerator.getRandomStatus(paymentId);
    }

    @Override
    public Payment findByTicketId(Integer id) {
        return paymentRepository.findByTicketId(id).orElse(null);
    }
}
