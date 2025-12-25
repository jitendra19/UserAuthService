package org.example.userauthservice.controllers;

import org.example.userauthservice.dtos.LoginRequestDto;
import org.example.userauthservice.dtos.SignupRequestDto;
import org.example.userauthservice.dtos.UserDto;
import org.example.userauthservice.models.User;
import org.example.userauthservice.services.IAuthService;
import org.example.userauthservice.utils.mapppers.UserDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private IAuthService authService;

    //signup
    @PostMapping("/signup")
    public ResponseEntity<UserDto> signup(@RequestBody SignupRequestDto signupRequestDto) {
        User user = authService.signup(signupRequestDto.getEmail(), signupRequestDto.getPassword(),signupRequestDto.getName());

        return new ResponseEntity<>(UserDtoMapper.from(user),  HttpStatus.CREATED);
    }

    //login
    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        User user = authService.login(loginRequestDto.getEmail(), loginRequestDto.getPassword());
        return new ResponseEntity<>(UserDtoMapper.from(user),  HttpStatus.OK);
    }
}
