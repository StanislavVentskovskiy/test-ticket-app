package com.test.ticketsalesapplication.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tickets", schema = "ticket_sales")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "customerName")
    private String customerName;

    @Column(name="paymentStatus")
    private String paymentStatus;

    @Column(name = "flight_id", nullable = false)
    private Integer flight_id;

    @ManyToOne
    @JoinColumn(name = "flight_id", insertable = false, updatable = false)
    private Flight flight;

    public Integer getFlightId() {
        return flight_id;
    }
}
