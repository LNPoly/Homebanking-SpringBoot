package com.ar.cac.homebanking.mappers;

import com.ar.cac.homebanking.models.User;
import com.ar.cac.homebanking.models.dtos.UserDTO;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {

    public static User dtoToUser(UserDTO dto){
        User user = new User();
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setDni(dto.getDni());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setBornDate(dto.getBornDate());
        user.setAddress(dto.getAddress());
        user.setAccountList(dto.getAccountList());
        return user;

    }

    public static UserDTO userToDto(User user){
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setSurname(user.getSurname());
        dto.setDni(user.getDni());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        dto.setBornDate(user.getBornDate());
        dto.setAddress(user.getAddress());
        dto.setAccountList(user.getAccountList());
        return dto;

    }
}
