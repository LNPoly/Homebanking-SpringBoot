package com.ar.cac.homebanking.models.dtos;


import com.ar.cac.homebanking.models.Account;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
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

    @Email(message = "El correo electronico debe tener un formato correcto")
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @NotNull(message = "Debe propocionar un nombre")
    private String name;

    @NotNull(message = "Debe proporcionar el apellido")
    private String surname;

    @NotNull(message = "La cuenta de usuario debe tener un DNI asociado")
    private String dni;

    @NotNull(message = "Debe proporcionar una fecha de nacimiento")
    private String bornDate;

    @NotNull(message = "Debe proporcionar un domicilio")
    private String address;

    private List<Account> accountList;
}
