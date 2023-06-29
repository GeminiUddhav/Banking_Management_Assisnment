package com.bank_managemen.bank_management.controller;

import com.bank_managemen.bank_management.entities.Accounts;
import com.bank_managemen.bank_management.services.AccountServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class AccountController {
    @Autowired
    private AccountServices accountServices;

    @PostMapping("/openAccount")
    public ResponseEntity<Accounts> openAccount(@RequestBody Accounts account) {
        Accounts createdAccount = accountServices.openAccount(account);
        return ResponseEntity.ok(createdAccount);
    }
    @PostMapping("/deposit/{accountId}")
    public ResponseEntity<String> deposit(@PathVariable String accountId, @RequestParam double depositAmount) {
        Accounts account = accountServices.deposit(accountId, depositAmount);
        String depositbalance = String.valueOf(account.getBalance());
        if (account != null) {
            return ResponseEntity.ok("Deposited Successful \nAccount Balance : " + depositbalance);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/withdraw/{accountId}")
    public ResponseEntity<String> withdraw(@PathVariable String accountId, @RequestParam double withdrawAmount) {

        Accounts account = accountServices.withdraw(accountId, withdrawAmount);
        String withdrawbalance = String.valueOf(account.getBalance());

        if (account != null) {
            return ResponseEntity.ok("Withdrawal Successful \nAccount Balance : " + withdrawbalance);
        } else {
            return ResponseEntity.badRequest().body("Insufficient Balance");
        }
    }
    @GetMapping("/balance/{accountId}")
    public ResponseEntity<Double> balanceEnquiry(@PathVariable String accountId) {
        Double balance = accountServices.balanceEnquiry(accountId);
        if (balance != null) {
            return ResponseEntity.ok(balance);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/delete/{accountId}")
    public ResponseEntity<String> deleteAccount(@PathVariable String accountId) {
        Accounts account = accountServices.deleteAccount(accountId);
        if (account != null) {
            return ResponseEntity.ok("Account deleted successfully!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/details/{accountId}")
    public Accounts accountDetails(@PathVariable("accountId") String accountId) {
        Accounts account = accountServices.accountDetails(accountId);
            return account;
    }
    @GetMapping("accounts/{userId}")
    public ResponseEntity<List<Accounts>> getAccountsByUserId(@PathVariable("userId") int userId)
    {
        List<Accounts> accountsList=accountServices.getAccountByUserId(userId);
        if(!accountsList.isEmpty())
        {
            return ResponseEntity.ok(accountsList);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
}