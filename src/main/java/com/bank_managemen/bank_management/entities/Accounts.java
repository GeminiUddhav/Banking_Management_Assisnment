package com.bank_managemen.bank_management.entities;


import javax.persistence.*;

import java.util.Date;
@Entity
@Table
public class Accounts {
    @Id
    private String accountId;
    private String accountNumber;
    private String accountType;
    private Double balance;
    private String branch;
    private java.sql.Date accountCreatedOn;
    private Date accountUpdatedAt;


    @ManyToOne
    @JoinColumn(name = "userId")
    private Users user;

    public Accounts(String accountId, String accountNumber, String accountType, Double balance, String branch, java.sql.Date accountCreatedOn, Date accountUpdatedAt, Users user) {
        this.accountId = accountId;
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.balance = balance;
        this.branch = branch;
        this.accountCreatedOn = accountCreatedOn;
        this.accountUpdatedAt = accountUpdatedAt;
        this.user = user;
    }

    public Accounts() {
        super();
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
    public Double getBalance() {
        return balance;
    }
    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public java.sql.Date getAccountCreatedOn() {
        return accountCreatedOn;
    }

    public void setAccountCreatedOn(java.sql.Date accountCreatedOn) {
        this.accountCreatedOn = accountCreatedOn;
    }

    public Date getAccountUpdatedAt() {
        return accountUpdatedAt;
    }

    public void setAccountUpdatedAt(Date accountUpdatedAt) {
        this.accountUpdatedAt = accountUpdatedAt;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

}