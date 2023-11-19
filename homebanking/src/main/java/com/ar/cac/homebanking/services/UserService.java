package com.ar.cac.homebanking.services;

import com.ar.cac.homebanking.exceptions.UserNotExistsException;
import com.ar.cac.homebanking.mappers.UserMapper;
import com.ar.cac.homebanking.models.User;
import com.ar.cac.homebanking.models.dtos.UserDTO;
import com.ar.cac.homebanking.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    //inyectar una instancia del Repositorio
    @Autowired
    private final UserRepository repository;

    public UserService(UserRepository repository){
        this.repository = repository;
    }

    public List<UserDTO> getUsers(){
        List<User> users = repository.findAll();
        List<UserDTO> usersDtos = users.stream()
                .map(UserMapper::userToDto)
                .collect(Collectors.toList());
        return usersDtos;
    }

    public UserDTO createUser(UserDTO userDTO){
        User user = repository.save(UserMapper.dtoToUser(userDTO));
        return UserMapper.userToDto(user);
    }

    public UserDTO getUsersById(Long id) {
        User entity = repository.findById(id).get();
        return UserMapper.userToDto(entity);
    }
    public String deleteUser(Long id){
        if(repository.existsById(id)) {
            repository.deleteById(id);
            return "Usuario con id:" + id + "ha sido eliminado.";
        } else {
            throw  new UserNotExistsException("El usuario elegido para eliminar, no existe.");
        }

    }
}
