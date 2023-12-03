package com.ar.cac.homebanking.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordService {

    private final BCryptPasswordEncoder passwordEncoder;

    public PasswordService() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public String encriptarPassword(String password) {
        return passwordEncoder.encode(password);
    }

    public boolean verificarPassword(String passwordIngresada, String passwordEncriptada) {
        return passwordEncoder.matches(passwordIngresada, passwordEncriptada);
    }
}
