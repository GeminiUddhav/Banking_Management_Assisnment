package com.bank_managemen.bank_management.dao;

import com.bank_managemen.bank_management.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<Users, Integer> {

}
