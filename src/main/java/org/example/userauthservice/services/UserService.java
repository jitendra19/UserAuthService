package org.example.userauthservice.services;

import org.example.userauthservice.exceptions.UserAlreadyExistException;
import org.example.userauthservice.exceptions.UserNotRegisteredException;
import org.example.userauthservice.models.User;
import org.example.userauthservice.repos.RoleRepo;
import org.example.userauthservice.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RoleRepo roleRepo;

    public User getUserById(Long id) {
        Optional<User> userOptional = userRepo.findById(id);
        if(userOptional.isEmpty()) {
            throw new UserNotRegisteredException("User is not found!");
        }
         return userOptional.get();
    }
    public User SaveUser(User user) {
        Optional<User> userOptional = userRepo.findByEmail(user.getEmail());
        if(userOptional.isPresent()) {
            throw new UserAlreadyExistException("User already registered!");
        }
        return userRepo.save(user);
    }
}
