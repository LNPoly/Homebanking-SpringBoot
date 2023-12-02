package com.ar.cac.homebanking.models.dtos;

import com.ar.cac.homebanking.models.Account;
import com.ar.cac.homebanking.models.Transfer;
import com.ar.cac.homebanking.models.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

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

    //private LocalDate creationDate;

    //private LocalDate lastModification;

    private UserDTO userAccount;

    private List<Transfer> transfersList;

}
