package org.example.userauthservice.services;

import org.example.userauthservice.exceptions.IncorrectPasswordException;
import org.example.userauthservice.exceptions.UserAlreadyExistException;
import org.example.userauthservice.exceptions.UserNotRegisteredException;
import org.example.userauthservice.models.Role;
import org.example.userauthservice.models.State;
import org.example.userauthservice.models.User;
import org.example.userauthservice.repos.RoleRepo;
import org.example.userauthservice.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class AuthService implements IAuthService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User signup(String email, String password, String name) {
        // if user exists already then return exception
        // if not present, then create a new user

        Optional<User> userOptional = userRepo.findByEmail(email);

        if(userOptional.isPresent()){
            throw new UserAlreadyExistException("User already exists");
        }

        User user = new User();
        user.setEmail(email);
        user.setPassword(bCryptPasswordEncoder.encode(password) );
        user.setName(name);
        user.setCreatedAt(new Date());
        user.setUpdatedAt(new Date());
        user.setState(State.ACTIVE);

        Optional<Role> roleOptional = roleRepo.findByValue("NON_ADMIN");

        if (roleOptional.isPresent()){
            Role role = roleOptional.get();
            List<Role> existingRoles = user.getRoles();
            existingRoles.add(role);
            user.setRoles(existingRoles);
        }  else {
            Role role = new Role();
            role.setValue("NON_ADMIN");
            role.setCreatedAt(new Date());
            role.setUpdatedAt(new Date());
            role.setState(State.ACTIVE);
            roleRepo.save(role);

            List<Role> existingRoles = user.getRoles();
            existingRoles.add(role);
            user.setRoles(existingRoles);
        }

        userRepo.save(user);

        return user;
    }

    @Override
    public User login(String email, String password) {
        Optional<User> userOptional = userRepo.findByEmail(email);
        if(userOptional.isEmpty()){
            throw new UserNotRegisteredException("user is not found, please sign up first!");
        }

        User user = userOptional.get();
//        if(!user.getPassword().equals(password)){
        if(!bCryptPasswordEncoder.matches(password,user.getPassword())) {
            throw new IncorrectPasswordException("Incorrect password");
        }

        return user;
    }
}
