package com.ar.cac.homebanking.models.dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {

    private Long account_id;

    private String tipeAccount; // equivalente al atributo "nombre" que pide la consigna.

    private Long cbu;

    private String alias;

    private int  amount;

    private String userAccaount; // equivalente al atributo "dueño" que pide la consigna.

}
