package com.test.ticketsalesapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto {
    private Integer id;
    private Integer ticketId;
    private String customerName;
    private Integer ticketPrice;
    private String paymentStatus;
}
