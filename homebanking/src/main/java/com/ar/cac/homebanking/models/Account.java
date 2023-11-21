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
    private Long account_id;

    @Column(name = "type_account")
    private AccountType tipeAccount;

    @Column(name = "cbu")
    private String cbu;

    @Column(name = "alias")
    private String alias;

    @Column(name = "amount")
    private BigDecimal amount;

    // TODO: faltan incorporar relaciones entre users/accounts
    //@Column(name = "userAccount")
    //private User userAccount;

    //@OneToOne
    //@JoinColumn(name = "id_account",referencedColumnName = "account_id")
    //private User user;

}
