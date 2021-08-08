package com.learn.tacoclouddomain.models;

import lombok.Data;

@Data
public class Customer {
    private String name;
    private String address;
    private BankInfo bankInfo;
}
