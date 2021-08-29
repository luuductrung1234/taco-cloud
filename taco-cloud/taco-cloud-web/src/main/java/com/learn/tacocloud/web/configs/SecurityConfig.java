package com.learn.tacocloud.web.configs;

import com.learn.tacocloud.domain.models.Account;
import com.learn.tacocloud.domain.repositories.AccountRepository;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

@Configuration
public class SecurityConfig {
    /**
     * the password in the database is never decoded.
     * the password that user enters at login is encoded (by algorithm) then compare with encoded password in database.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
//        return new Pbkdf2PasswordEncoder();
//        return new SCryptPasswordEncoder();
//        return new StandardPasswordEncoder();
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
//        var users = new ArrayList<UserDetails>() {{
//            add(new User("buzz", encoder.encode("password"), Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))));
//            add(new User("woody", encoder.encode("password"), Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))));
//        }};
//        return new InMemoryUserDetailsManager(users);
//    }


    @Bean
    public UserDetailsService userDetailsService(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        if (IterableUtils.toList(accountRepository.findAll()).isEmpty()) {
            accountRepository.save(new Account(UUID.randomUUID(), "ga", passwordEncoder.encode("123"), "global account", "", "", "", "", "", ""));
        }

        return username -> {
            var account = accountRepository.findByUsername(username);
            if (account != null) return account;
            throw new UsernameNotFoundException("User '" + username + "' not found");
        };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                    .formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/design")
                    .failureUrl("/login-error")
                .and()
                    .logout()
                    .logoutSuccessUrl("/")
                .and()
                    .authorizeRequests()
//                    .antMatchers("/design", "/orders").access("hasRole('USER')")
//                    .antMatchers("/", "/**").access("permitAll()")
                    .antMatchers("/design/**", "/orders/**").hasRole("USER")
                    .antMatchers("/", "/**").permitAll()
                .and()
                    // ignore security for H2-console
                    .csrf()
                    .ignoringAntMatchers("/h2-console/**")
                .and()
                    // config needed for H2-console
                    .headers()
                    .frameOptions()
                    .sameOrigin()
                .and()
                .build();
    }
}
