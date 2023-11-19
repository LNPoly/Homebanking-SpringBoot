package com.ar.cac.homebanking.models.dtos;

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

    private Long id; // se muestra solo en el postman

    private String email;

    private String password;

    private String name;

    private String surname;

    private String dni;

    //private String bornDate;
    //private String adress;
    //private List<AccountDTO>;


}
