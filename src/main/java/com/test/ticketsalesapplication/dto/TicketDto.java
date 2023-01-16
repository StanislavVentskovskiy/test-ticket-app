package com.test.ticketsalesapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketDto {
    private Integer id;
    private String customerName;
    private Integer flight_id;
    private Integer ticketPrice;
    private String paymentStatus;
}
