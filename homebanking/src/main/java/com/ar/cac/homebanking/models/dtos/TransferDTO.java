package com.ar.cac.homebanking.models.dtos;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransferDTO {

    private Long transferId;

    private BigDecimal transferAmount;

    private Long originAccount;

    private Long targetAccount;

    private LocalDate date;
}
