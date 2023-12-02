package com.ar.cac.homebanking.models;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "transfers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transferId;

    @Column(name = "amount")
    private BigDecimal transferAmount;

    @ManyToOne
    @JoinColumn(name="accountId", referencedColumnName="id")
    private Long originAccount;

    private Long targetAccount;

    @Column(name = "date")
    private Date date;

}
