package com.test.ticketsalesapplication.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "flights", schema = "ticket_sales")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "amountOfTickets")
    private Integer amountOfTickets;

    @Column(name = "startLocation")
    private String startLocation;

    @Column(name = "finalLocation")
    private String finalLocation;

    @Column(name = "departureTime")
    private LocalDateTime departureTime;

    @Column(name = "price")
    private Integer price;
}
