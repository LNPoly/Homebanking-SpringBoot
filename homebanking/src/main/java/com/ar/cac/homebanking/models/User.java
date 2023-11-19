package com.ar.cac.homebanking.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "contrasena")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "apellido")
    private String surname;

    @Column(name = "dni")
    private String dni;


}
