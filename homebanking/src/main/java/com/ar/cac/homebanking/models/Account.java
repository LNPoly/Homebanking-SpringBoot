package com.ar.cac.homebanking.models;

import com.ar.cac.homebanking.models.enums.AccountType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "accounts")
@Getter
@Setter
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    @Column(name = "type")
    private AccountType TypeAccount;

    @Column(name = "cbu", unique = true)
    private String cbu;

    @Column(name = "alias")
    private String alias;

    @Column(name = "amount")
    private BigDecimal amount;

    @ManyToOne
    private User userAccount;

    //@OneToMany(mappedBy = "Account", cascade = CascadeType.ALL, orphanRemoval = true)
    //private List<Transfer> listTransfers;

}
