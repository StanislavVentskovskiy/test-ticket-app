package com.test.ticketsalesapplication.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "payments", schema = "ticket_sales")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ticket_id")
    private Integer ticketId;

    @Column(name = "customerName")
    private String customerName;

    @Column(name = "ticketPrice")
    private Integer ticketPrice;

    @Column(name="paymentStatus")
    private String paymentStatus;

    public Payment(Integer ticketId, String customerName, Integer ticketPrice, String paymentStatus) {
        this.ticketId = ticketId;
        this.customerName = customerName;
        this.ticketPrice = ticketPrice;
        this.paymentStatus = paymentStatus;
    }
}
