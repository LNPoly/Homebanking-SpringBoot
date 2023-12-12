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

    @Email(message = "The email must be correctly formatted.")
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(message = "You must enter a password.")
    @NotBlank(message = "You must enter a password.")
    @Pattern(regexp ="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%&*?])[a-zA-Z\\d!@#$%&*?]{8,20}$",
            message = "Password must have within 8-20 characters and at least a lowercase letter, an uppercase letter, a number and a special character.")
    private String password;

    @NotNull(message = "You must enter your name.")
    @NotBlank(message = "You must enter your name.")
    private String name;

    @NotNull(message = "You must enter your surname.")
    @NotBlank(message = "You must enter your surname.")
    private String surname;

    @NotNull(message = "The user account must have an associated DNI.")
    @NotBlank(message = "The user account must have an associated DNI.")
    private String dni;

    @NotNull(message = "You must enter a valid date of birth.")
    @NotBlank(message = "You must enter a valid date of birth.")
    @Pattern(regexp = "[0-9]{2}[-.][0-9]{2}[-.][0-9]{4}", message = "The date of birth in not correct or must be in DD-MM-YY format.")
    private String bornDate;

    @NotNull(message = "You must enter an address.")
    @NotBlank(message = "You must enter an address.")
    private String address;

    private List<Account> accountList;
}
