package com.ar.cac.homebanking.services;

import com.ar.cac.homebanking.exceptions.UserNotExistsException;
import com.ar.cac.homebanking.mappers.UserMapper;
import com.ar.cac.homebanking.models.User;
import com.ar.cac.homebanking.models.dtos.UserDTO;
import com.ar.cac.homebanking.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        PasswordService passwordService = new PasswordService();

        String contrasenaEncriptada = passwordService.encriptarPassword(userDTO.getPassword());
        userDTO.setPassword(contrasenaEncriptada);

        User user = repository.save(UserMapper.dtoToUser(userDTO));
        return UserMapper.userToDto(user);
    }

    public UserDTO getUsersById(Long id) {
        User entity = repository.findById(id).get();
        return UserMapper.userToDto(entity);
    }
    public String deleteUser(Long id) {
        if(repository.existsById(id)) {
            repository.deleteById(id);
            return "Usuario con id:" + id + " ha sido eliminado.";
        } else {
           throw  new UserNotExistsException("El usuario elegido para eliminar, no existe.");
        }

    }
    public UserDTO updateUser(Long id, UserDTO dto) {
        if (repository.existsById(id)){
            User userToModify = repository.findById(id).get();
            // Validar qué datos no vienen en null para setearlos al objeto ya creado

            // Logica del Patch
            if (dto.getName() != null){
                userToModify.setName(dto.getName());
            }

            if (dto.getSurname() != null){
                userToModify.setSurname(dto.getSurname());
            }

            if (dto.getEmail() != null){
                userToModify.setEmail(dto.getEmail());
            }

            if (dto.getPassword() != null){
                userToModify.setPassword(dto.getPassword());
            }

            if (dto.getDni() != null){
                userToModify.setDni(dto.getDni());
            }

            User userModified = repository.save(userToModify);

            return UserMapper.userToDto(userModified);
        }
        return null;
    }

    public UserDTO restorePassword(Long id, String password) {

        User entity = repository.findById(id).get();

        // String contraBBDD =  entity.getPassword();
        // System.out.println("Contrasena de la BBDD: " + contraBBDD);

        PasswordService passwordService = new PasswordService();

        String contrasenaEncriptada = passwordService.encriptarPassword(password);
        entity.setPassword(contrasenaEncriptada);

        User userModified = repository.save(entity);
        /*

         String contraModificada =userModified.getPassword();
         System.out.println("Contrasena modificada: " + contraModificada);

        if(contraBBDD.equals(contraModificada)){
            System.out.println("Son iguales");
        } else {
            System.out.println("No son iguales");
        } */
        return UserMapper.userToDto(userModified);

    }
}
