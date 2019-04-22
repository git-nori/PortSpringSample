package com.example.demo.login.domain.model;

import lombok.Data;

@Data
public class User {

    private String userId;
    private String password;
    private String userName;
    private boolean gender;
    private String role;
}
