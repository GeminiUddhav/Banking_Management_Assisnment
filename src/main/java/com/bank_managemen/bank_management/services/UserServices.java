package com.bank_managemen.bank_management.services;
import com.bank_managemen.bank_management.entities.Users;


import java.util.List;

public interface UserServices{

    public List<Users> getUsers();
    public Users getUserById(int userId);
    public Users addUser(Users ac);
    Users updateUserDetails(int userId, Users user);
    public void deleteUser(int userId);
}
