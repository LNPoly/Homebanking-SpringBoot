package com.ar.cac.homebanking.models.dtos;

import com.ar.cac.homebanking.models.Account;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransferDTO {

    private Long transferId;

    private BigDecimal transferAmount;

    private Account originAccount;

    private Account targetAccount;

    private LocalDate date;
}
