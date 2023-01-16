package com.test.ticketsalesapplication.restcontroller;

import com.test.ticketsalesapplication.facade.PaymentFacade;
import com.test.ticketsalesapplication.model.Payment;
import com.test.ticketsalesapplication.dto.PaymentDto;
import com.test.ticketsalesapplication.service.impl.PaymentServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/payments")
public class PaymentRestController {

    @Autowired
    private PaymentServiceImpl paymentService;

    @Autowired
    private PaymentFacade paymentFacade;

    private RestTemplate restTemplate = new RestTemplate();
    private static String ticketNumberUrl= "http://localhost:8080/api/flight-tickets-number/";

    @RequestMapping(value = "")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PaymentDto> savePayment(@RequestBody @Valid PaymentDto paymentDto){
        HttpHeaders headers = new HttpHeaders();
        if (paymentDto == null) {

            return new ResponseEntity<PaymentDto>(HttpStatus.BAD_REQUEST);
        }
        PaymentDto returnPayment = paymentService.savePayment(paymentDto);

        return new ResponseEntity<PaymentDto>(returnPayment, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public PaymentDto getPaymentDetails(@PathVariable Integer id) {
        try {
            Payment payment = paymentService.findPaymentById(id);
            PaymentDto paymentDto = paymentFacade.mapPaymentDto(payment);

            return paymentDto;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Flight not found", e);
        }
    }

    @GetMapping("/ticketId/{id}")
    public PaymentDto getPaymentByTicketId(@PathVariable Integer id){
        try {
            Payment payment = paymentService.findByTicketId(id);
            PaymentDto paymentDto = paymentFacade.mapPaymentDto(payment);

            return paymentDto;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Flight not found", e);
        }
    }
}
