package com.bank_managemen.bank_management.testdao;


import com.bank_managemen.bank_management.dao.AccountDao;
import com.bank_managemen.bank_management.entities.Accounts;
import com.bank_managemen.bank_management.entities.Users;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import org.springframework.test.context.TestPropertySource;


import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:test_application.properties")

public class AccountDaoTest {

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private TestEntityManager entityManager;

    private Users user;

    @BeforeEach
    public void setup() {
        user = new Users();
        user.setUserId(100);

        entityManager.persist(user);
        entityManager.flush();
    }

    @Test
    public void testFindAllByUserUserId() {
        //Arrange
        Accounts account1 = new Accounts("1", "1", "Savings", 1000.0, "Branch A", Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now()), user);
        Accounts account2 = new Accounts("2", "2","Savings", 2000.0, "Branch B", Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now()), user);
        entityManager.persist(account1);
        entityManager.persist(account2);
        entityManager.flush();

        //Act
        List<Accounts> accounts = accountDao.findAllByUserUserId(user.getUserId());

        //Assert
        Assertions.assertFalse(accounts.isEmpty());
        Assertions.assertEquals(2, accounts.size());
    }



}
