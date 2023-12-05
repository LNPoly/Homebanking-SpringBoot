package com.ar.cac.homebanking.models.dtos;


import com.ar.cac.homebanking.models.Account;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;

    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private String name;

    private String surname;

    private String dni;

    private String bornDate;

    private String address;

    private List<Account> accountList;
}
