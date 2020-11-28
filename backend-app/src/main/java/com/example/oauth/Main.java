package com.example.oauth;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Main {

    public static void main(String[] args) {
        //Password encoder - mainly purpose for creating user through flyway script when we use this code to
        //add hashed password to flyway script
        String password = "12345";
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);

        System.out.println(hashedPassword);
    }
}
