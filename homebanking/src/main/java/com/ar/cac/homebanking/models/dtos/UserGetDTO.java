package com.ar.cac.homebanking.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserGetDTO {

    private Long id; // se muestra solo en el postman

    private String email;


    private String name;

    private String surname;

    private String dni;

}