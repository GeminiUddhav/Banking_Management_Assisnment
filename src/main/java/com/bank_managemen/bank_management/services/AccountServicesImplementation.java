package com.bank_managemen.bank_management.services;

import com.bank_managemen.bank_management.dao.AccountDao;
import com.bank_managemen.bank_management.entities.Accounts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServicesImplementation implements com.bank_managemen.bank_management.services.AccountServices {
    @Autowired
    AccountDao accountDao;

    @Override
    public Accounts openAccount(Accounts account) {

        accountDao.save(account);
        return account;
    }
    public Accounts deposit(String accountID,double depositAmount){
        Accounts account=accountDao.findById(accountID).orElse(null);
        if (account!=null){
            account.setBalance(account.getBalance()+depositAmount);
            return accountDao.save(account);
        }
        else{
            return null;
        }
    }
    public Accounts withdraw(String accountID,double withdrawAmount){
        Accounts account=accountDao.findById(accountID).orElse(null);
        if (account!=null){

            double currentBalance=account.getBalance();
            if(currentBalance>=withdrawAmount){
                account.setBalance(currentBalance-withdrawAmount);
            }
            else{
                System.out.println("Balance is not Sufficient");
            }
            return accountDao.save(account);
        }
        else{
            return null;
        }
    }
    public Double balanceEnquiry(String accountID){
        Accounts account=accountDao.findById(accountID).orElse(null);
        if (account!=null){
            return account.getBalance();
        }
        else{
            return null;
        }
    }
    public Accounts deleteAccount(String accountId){
        Accounts account=accountDao.findById(accountId).orElse(null);
        accountDao.delete(account);
        if(account!=null){
            return account;
        }
        else {
            return null;
        }
    }
    public Accounts accountDetails(String accountId){
        Accounts account=accountDao.findById(accountId).orElse(null);

            return account;
    }
    @Override
    public List<Accounts> getAccountByUserId(int userId) {
        return accountDao.findAllByUserUserId(userId);
    }
}
