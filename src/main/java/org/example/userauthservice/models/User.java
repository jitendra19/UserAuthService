package org.example.userauthservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class User extends BaseModel {
    private String email;
    private String name;
    private String password;
    @ManyToMany
    private List<Role> roles = new ArrayList<>();
}

// one user can have more than 1 role
// 1 role can have more than 1 User
