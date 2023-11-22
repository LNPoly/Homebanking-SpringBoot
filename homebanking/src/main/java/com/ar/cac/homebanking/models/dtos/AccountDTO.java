package com.ar.cac.homebanking.models.dtos;

import com.ar.cac.homebanking.models.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {

    private Long accountId;

    private AccountType typeAccount;

    private String cbu;

    private String alias;

    private BigDecimal amount;

}
