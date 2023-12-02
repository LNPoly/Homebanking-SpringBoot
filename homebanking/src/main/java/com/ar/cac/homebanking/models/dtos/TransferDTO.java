package com.ar.cac.homebanking.models.dtos;

import com.ar.cac.homebanking.models.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransferDTO {

    private Long transferId;

    private BigDecimal transferAmount;

    private Account originAccount;

    private Account targetAccount;

    private LocalDate date;
}
