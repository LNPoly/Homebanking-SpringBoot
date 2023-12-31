package com.ar.cac.homebanking.models;

import com.ar.cac.homebanking.models.enums.AccountType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"userAccount"})
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
    @JoinColumn(name="id_Usuario", referencedColumnName="id")
    @JsonManagedReference
    private User userAccount;


}
