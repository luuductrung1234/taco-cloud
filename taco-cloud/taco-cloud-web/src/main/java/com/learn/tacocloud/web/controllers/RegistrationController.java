package com.learn.tacocloud.web.controllers;

import com.learn.tacocloud.domain.repositories.AccountRepository;
import com.learn.tacocloud.web.dto.RegistrationForm;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegistrationController {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    public RegistrationController(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String registerForm() {
        return "registration";
    }

    @PostMapping
    public String processRegistration(RegistrationForm form) {
        accountRepository.save(form.toAccount(passwordEncoder));
        return "redirect:/login";
    }
}
