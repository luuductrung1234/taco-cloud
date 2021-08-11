package com.learn.tacocloud.domain.models;

import lombok.Data;

@Data
public class BankInfo {
    private String bankAccountName;
    private String bankAccountNumber;
    private String bankCode;
    private String bankName;
}
