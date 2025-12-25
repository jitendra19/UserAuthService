package org.example.userauthservice.utils.mapppers;


import org.example.userauthservice.dtos.UserDto;
import org.example.userauthservice.models.User;

public class UserDtoMapper {

    public static UserDto from(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setName(user.getName());
        userDto.setPassword(user.getPassword());
        userDto.setRoles(user.getRoles());
        return userDto;
    }

    public static User from(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());
        user.setPassword(userDto.getPassword());
        user.setRoles(userDto.getRoles());
        return user;
    }
}
