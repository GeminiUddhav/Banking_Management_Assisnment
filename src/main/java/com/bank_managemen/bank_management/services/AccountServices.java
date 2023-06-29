package com.bank_managemen.bank_management.services;

import com.bank_managemen.bank_management.entities.Accounts;

import java.util.List;

public interface AccountServices {
    public Accounts openAccount(Accounts account);
    public Accounts deposit(String accountID,double depositAmount);
    public Accounts withdraw(String accountID,double withdrawAmount);
    public Double balanceEnquiry(String accountID);
    Accounts deleteAccount(String accountId);
    Accounts accountDetails(String accountId);
    List<Accounts> getAccountByUserId(int userId);
}
