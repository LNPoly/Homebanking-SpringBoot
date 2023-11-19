package com.ar.cac.homebanking.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "accounts")
@Getter
@Setter
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long account_id;

    @Column(name = "name")
    private String name;

    @Column(name = "cbu")
    private Long cbu;

    @Column(name = "alias")
    private String alias;

    @Column(name = "amount")
    private int  amount;

    //@Column(name = "user")
    //@OneToOne
    //@JoinColumn(name = "id_account",referencedColumnName = "id")
    //private User user;

}
