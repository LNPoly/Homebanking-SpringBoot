package com.ar.cac.homebanking.controllers;

import com.ar.cac.homebanking.models.dtos.UserDTO;
import com.ar.cac.homebanking.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getUsers(){
        List<UserDTO> lista = service.getUsers();
        return ResponseEntity.status(HttpStatus.OK).body(lista);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.getUsersById(id));

    }
    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDTO user, BindingResult result){
        if (result.hasErrors()) {
            // Construir un mensaje de error personalizado
            Map<String, String> errors = new HashMap<>();
            result.getFieldErrors().forEach(fieldError -> {
                String fieldName = fieldError.getField();
                String errorMessage = fieldError.getDefaultMessage();
                errors.put(fieldName, errorMessage);
            });

            // Devolver una respuesta de error con el mensaje personalizado
            return ResponseEntity.badRequest().body(errors);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createUser(user));
    }
    @PutMapping(value="/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO user){
        return ResponseEntity.status(HttpStatus.OK).body(service.updateUser(id, user));
    }

    @PutMapping(value = "/restore/{id}")
    public ResponseEntity<UserDTO> restorePassword (@PathVariable Long id, @RequestBody String password){
        return ResponseEntity.status(HttpStatus.OK).body(service.restorePassword(id,password));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id)  {
            return ResponseEntity.status(HttpStatus.OK).body(service.deleteUser(id));
    }

}
