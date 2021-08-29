package com.learn.tacocloud.web.dto;

import com.learn.tacocloud.domain.models.Account;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

@Data
public class RegistrationForm {
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String phone;

    public Account toAccount(PasswordEncoder passwordEncoder) {
        return new Account(UUID.randomUUID(), username, passwordEncoder.encode(password), firstname, lastname, street, city, state, zip, phone);
    }
}
