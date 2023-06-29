package com.bank_managemen.bank_management.dao;

import com.bank_managemen.bank_management.entities.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountDao extends JpaRepository<Accounts,String> {

    List<Accounts> findAllByUserUserId(int userId);
}
