package org.example.userauthservice.controllers;

import jakarta.websocket.server.PathParam;
import org.example.userauthservice.dtos.UserDto;
import org.example.userauthservice.models.User;
import org.example.userauthservice.services.UserService;
import org.example.userauthservice.utils.mapppers.UserDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return UserDtoMapper.from(user);


    }

    @PostMapping("/")
    public UserDto createUser(@RequestBody UserDto userDto) {
        User user = userService.SaveUser(UserDtoMapper.from(userDto));
        return UserDtoMapper.from(user);
    }
}
