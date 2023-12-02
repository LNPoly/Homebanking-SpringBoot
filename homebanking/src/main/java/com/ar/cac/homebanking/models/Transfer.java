package com.ar.cac.homebanking.models;


import com.ar.cac.homebanking.models.enums.AccountType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "transfers")
@Getter
@Setter
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transferId;

    @Column(name = "amount")
    private BigDecimal transferAmount;

    private Account originAccount;

    private Account targetAccount;

    @Column(name = "date")
    private LocalDate date;

}
