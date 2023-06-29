package com.bank_managemen.bank_management.controller;

import com.bank_managemen.bank_management.entities.Accounts;
import com.bank_managemen.bank_management.services.AccountServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class AccountControllerTest {
    @Mock
    private AccountServices accountServices;

    @InjectMocks
    private AccountController accountController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testOpenAccount() {
        // Create a test account
        Accounts account = new Accounts();
        account.setAccountId("ACC001");
        account.setBalance(100.0);

        when(accountServices.openAccount(any(Accounts.class))).thenReturn(account);

        // Call the openAccount() method
        ResponseEntity<Accounts> response = accountController.openAccount(account);

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("ACC001", response.getBody().getAccountId());
        assertEquals(100.0, response.getBody().getBalance());

        verify(accountServices, times(1)).openAccount(any(Accounts.class));
    }

    @Test
    void testDeposit() {
        // Create a test account
        Accounts account = new Accounts();
        account.setAccountId("ACC001");
        account.setBalance(100.0);

        when(accountServices.deposit(eq("ACC001"), anyDouble())).thenReturn(account);

        // Call the deposit() method
        ResponseEntity<String> response = accountController.deposit("ACC001", 50.0);

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Deposited Successful \nAccount Balance : 100.0", response.getBody());

        verify(accountServices, times(1)).deposit(eq("ACC001"), eq(50.0));
    }
    @Test

    void testWithdraw() {
        // Create a test account
        Accounts account = new Accounts();
        account.setAccountId("ACC001");
        account.setBalance(100.0);

        when(accountServices.withdraw(eq("ACC001"), anyDouble())).thenReturn(account);

        // Call the withdraw() method
        ResponseEntity<String> response = accountController.withdraw("ACC001", 50.0);

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Withdrawal Successful \nAccount Balance : 100.0", response.getBody());

        verify(accountServices, times(1)).withdraw(eq("ACC001"), eq(50.0));
    }

    @Test
    void testBalanceEnquiry() {
        Double balance = 100.0;

        when(accountServices.balanceEnquiry(eq("ACC001"))).thenReturn(balance);

        // Call the balanceEnquiry() method
        ResponseEntity<Double> response = accountController.balanceEnquiry("ACC001");

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(100.0, response.getBody());

        verify(accountServices, times(1)).balanceEnquiry(eq("ACC001"));
    }

    @Test
    void testDeleteAccount() {
        // Create a test account
        Accounts account = new Accounts();
        account.setAccountId("ACC001");
        account.setBalance(100.0);

        when(accountServices.deleteAccount(eq("ACC001"))).thenReturn(account);

        // Call the deleteAccount() method
        // Call the deleteAccount() method
        ResponseEntity<String> response = accountController.deleteAccount("ACC001");

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Account deleted successfully!", response.getBody());

        verify(accountServices, times(1)).deleteAccount(eq("ACC001"));
    }

    @Test
    void testAccountDetails() {
        // Create a test account
        Accounts account = new Accounts();
        account.setAccountId("ACC001");
        account.setBalance(100.0);

        when(accountServices.accountDetails(eq("ACC001"))).thenReturn(account);

        // Call the accountDetails() method
        Accounts result = accountController.accountDetails("ACC001");

        // Verify the result
        assertNotNull(result);
        assertEquals("ACC001", result.getAccountId());
        assertEquals(100.0, result.getBalance());

        verify(accountServices, times(1)).accountDetails(eq("ACC001"));
    }

    @Test
    void testGetAccountsByUserId() {
        // Create a test account list
        List<Accounts> accountList = new ArrayList<>();
        Accounts account1 = new Accounts();
        account1.setAccountId("ACC001");
        account1.setBalance(100.0);
        accountList.add(account1);

        when(accountServices.getAccountByUserId(eq(123))).thenReturn(accountList);

        // Call the getAccountsByUserId() method
        ResponseEntity<List<Accounts>> response = accountController.getAccountsByUserId(123);

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals("ACC001", response.getBody().get(0).getAccountId());
        assertEquals(100.0, response.getBody().get(0).getBalance());

        verify(accountServices, times(1)).getAccountByUserId(eq(123));
    }
}
