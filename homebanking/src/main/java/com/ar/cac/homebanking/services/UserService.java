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

        String encryptedPassword = passwordService.encryptPassword(userDTO.getPassword());
        userDTO.setPassword(encryptedPassword);

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
            return "User with id:" + id + " has been deleted.";
        } else {
           throw  new UserNotExistsException("The user doesn't exists.");
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

            if (dto.getDni() != null){
                userToModify.setDni(dto.getDni());
            }
            if (dto.getAddress() != null){
                userToModify.setAddress(dto.getAddress());
            }
            if (dto.getBornDate() != null){
                userToModify.setBornDate(dto.getBornDate());
            }

            User userModified = repository.save(userToModify);

            return UserMapper.userToDto(userModified);
        }
        return null;
    }

    public UserDTO restorePassword(Long id, String password) {

        User entity = repository.findById(id).get();

        //String passwordBBDD =  entity.getPassword();
        //System.out.println("Hosted password: " + passwordBBDD);

        PasswordService passwordService = new PasswordService();

        String encryptedPassword = passwordService.encryptPassword(password);
        entity.setPassword(encryptedPassword);

        User userModified = repository.save(entity);
        String modifiedPassword = userModified.getPassword();
        /*
        if (passwordBBDD.equals(modifiedPassword)){
            User newPassword = repository.save(entity);
            String changePass = newPassword.getPassword();

            System.out.println("Password is the same as the previous one. Type a new password.");
            try {
                new PasswordAuthentication(entity.getName(), password.toCharArray());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return UserMapper.userToDto(newPass);
        } else {
            System.out.println("Password has been modified.");
            return UserMapper.userToDto(userModified);
        }

        String modifiedPassword = userModified.getPassword();
        System.out.println("New password: " + modifiedPassword);

        if(passwordBBDD.equals(modifiedPassword)){
            System.out.println("Are the same");
        } else {
            System.out.println("Are different");
        } */

        return UserMapper.userToDto(userModified);
    }
}

