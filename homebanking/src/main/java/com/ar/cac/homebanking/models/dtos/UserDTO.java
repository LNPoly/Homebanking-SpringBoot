package com.ar.cac.homebanking.models.dtos;


import com.ar.cac.homebanking.models.Account;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
    @NotNull(message = "Debe proporcionar una contraseña")
    @NotBlank(message = "Debe proporcionar una contraseña")
    @Pattern(regexp ="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%&*?])[a-zA-Z\\d!@#$%&*?]{8,20}$",
            message = "La contraseña debe tener 8 a 20 caracteres con al menos una letra minúscula, una letra mayúscula y un caracter especial")
    private String password;

    @NotNull(message = "Debe propocionar un nombre")
    @NotBlank(message = "Debe propocionar un nombre")
    private String name;

    @NotNull(message = "Debe proporcionar el apellido")
    @NotBlank(message = "Debe proporcionar el apellido")
    private String surname;

    @NotNull(message = "La cuenta de usuario debe tener un DNI asociado")
    @NotBlank(message = "La cuenta de usuario debe tener un DNI asociado")
    private String dni;

    @NotNull(message = "Debe proporcionar una fecha de nacimiento válida")
    @NotBlank(message = "Debe proporcionar una fecha de nacimiento válida")
    @Pattern(regexp = "[0-31]{2}[-.][0-12]{2}[-.][0-9]{4}", message = "La fecha de nacimiento no es correcta o debe tener formato dia-mes-año")
    private String bornDate;

    @NotNull(message = "Debe proporcionar un domicilio")
    @NotBlank(message = "Debe proporcionar un domicilio")
    private String address;

    private List<Account> accountList;
}
