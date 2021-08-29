package com.learn.tacocloud.persistence.jpa.entities;

import com.learn.tacocloud.domain.models.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "ACCOUNTS")
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "USERNAME")
    private String username;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "FIRSTNAME")
    private String firstname;
    @Column(name = "LASTNAME")
    private String lastname;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String phone;

    public AccountEntity(final Account account) {
        this.id = account.getId();
        this.username = account.getUsername();
        this.password = account.getPassword();
        this.firstname = account.getFirstname();
        this.lastname = account.getLastname();
        this.street = account.getStreet();
        this.city = account.getCity();
        this.state = account.getState();
        this.zip = account.getZip();
        this.phone = account.getPhone();
    }

    public Account toAccount(){
        return new Account(id, username, password, firstname, lastname, street, city, state, zip, phone);
    }
}
