package com.test.ticketsalesapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketDetailsDto {
    private Integer id;
    private String customerName;
    private String startLocation;
    private String finalLocation;
    private LocalDateTime departureTime;
    private Integer price;
    private Integer flight_id;
    private String paymentStatus;
}

