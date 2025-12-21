package org.example.userauthservice.services;

import org.example.userauthservice.models.User;

public interface IAuthService {

    User signup(String email, String password, String name);

    User login(String email, String password);
}
