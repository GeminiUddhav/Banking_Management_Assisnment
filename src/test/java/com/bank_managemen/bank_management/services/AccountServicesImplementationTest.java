package com.bank_managemen.bank_management.services;

import com.bank_managemen.bank_management.dao.AccountDao;
import com.bank_managemen.bank_management.entities.Accounts;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class AccountServicesImplementationTest {

    @Mock
    private AccountDao accountDao;

    @InjectMocks
    private AccountServicesImplementation accountServices;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testOpenAccount() {
        Accounts account = new Accounts();
        when(accountDao.save(account)).thenReturn(account);
        Accounts result = accountServices.openAccount(account);
        assertEquals(account, result);
        verify(accountDao, times(1)).save(account);
    }

    @Test
    public void testDeposit() {
        String accountId = "1";
        double depositAmount = 500;
        Accounts account = new Accounts();
        account.setAccountId(accountId);
        account.setBalance(0.0);
        when(accountDao.findById(accountId)).thenReturn(Optional.of(account));
        when(accountDao.save(account)).thenReturn(account);
        Accounts result = accountServices.deposit(accountId, depositAmount);
        assertNotNull(result);
        assertEquals(500, result.getBalance());
        verify(accountDao, times(1)).findById(accountId);
        verify(accountDao, times(1)).save(account);
    }

    @Test
    public void testWithdraw() {
        String accountId = "1";
        double withdrawAmount = 500;
        Accounts account = new Accounts();
        account.setAccountId(accountId);
        account.setBalance(1000.0);
        when(accountDao.findById(accountId)).thenReturn(Optional.of(account));
        when(accountDao.save(account)).thenReturn(account);
        Accounts result = accountServices.withdraw(accountId, withdrawAmount);
        assertNotNull(result);
        assertEquals(500, result.getBalance());
        verify(accountDao, times(1)).findById(accountId);
        verify(accountDao, times(1)).save(account);
    }



    @Test
    public void testBalanceEnquiry() {
        String accountId = "1";
        Accounts account = new Accounts();
        account.setAccountId(accountId);
        account.setBalance(1000.0);
        when(accountDao.findById(accountId)).thenReturn(Optional.of(account));
        Double balance = accountServices.balanceEnquiry(accountId);
        assertNotNull(balance);
        assertEquals(account.getBalance(), balance);
        verify(accountDao, times(1)).findById(accountId);
    }

    @Test
    public void testDeleteAccount() {
        String accountId = "1";
        Accounts account = new Accounts();
        account.setAccountId(accountId);
        when(accountDao.findById(accountId)).thenReturn(Optional.of(account));
        Accounts result = accountServices.deleteAccount(accountId);
        assertNotNull(result);
        assertEquals(account, result);
        verify(accountDao, times(1)).findById(accountId);
        verify(accountDao, times(1)).delete(account);
    }

    @Test
    public void testAccountDetails() {
        String accountId = "1";
        Accounts account = new Accounts();
        account.setAccountId(accountId);
        when(accountDao.findById(accountId)).thenReturn(Optional.of(account));
        Accounts result = accountServices.accountDetails(accountId);
        assertEquals(account, result);
        verify(accountDao, times(1)).findById(accountId);
    }
}

