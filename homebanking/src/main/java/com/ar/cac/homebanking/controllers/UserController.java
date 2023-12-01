package com.ar.cac.homebanking.controllers;

import com.ar.cac.homebanking.exceptions.UserNotExistsException;
import com.ar.cac.homebanking.models.dtos.UserDTO;
import com.ar.cac.homebanking.models.dtos.UserGetDTO;
import com.ar.cac.homebanking.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    // Definir la url de origen para cada solicitud
    // Para cada método HTTP permitido, debemos realizar una acción
    //Definir el DTO y el Service(Inyeccion de Dependencia)

    @Autowired
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<UserGetDTO>> getUsers(){
        List<UserGetDTO> lista = service.getUsers();
        return ResponseEntity.status(HttpStatus.OK).body(lista);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserGetDTO> getUserById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.getUsersById(id));

    }
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO user){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createUser(user));
    }
    @PutMapping(value="/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO user){
        return ResponseEntity.status(HttpStatus.OK).body(service.updateUser(id, user));
    }

    @PatchMapping(value = "/{id}")
    public void updateUser(@PathVariable Long id){ }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
            return ResponseEntity.status(HttpStatus.OK).body(service.deleteUser(id));

    }
}
