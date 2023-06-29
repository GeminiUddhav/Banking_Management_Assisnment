package com.bank_managemen.bank_management.services;

import com.bank_managemen.bank_management.dao.UserDao;
import com.bank_managemen.bank_management.entities.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServicesImplementation implements com.bank_managemen.bank_management.services.UserServices {
    @Autowired
    private UserDao userDao;
    @Override
    public List<Users> getUsers() {

        return userDao.findAll();
    }
    @Override
    public Users getUserById(int userId){

        Users users=userDao.findById(userId).orElse(null);{
            if(users!=null){
                return users;
            }
            else{
                return null;
            }
        }
    }
    @Override
    public Users addUser(Users ac) {
        return userDao.save(ac);

    }
    @Override
    public Users updateUserDetails(int userId, Users user){
        Users users=userDao.findById(userId).orElse(null);
        if (users!=null){
            return  userDao.save(user);
        }
        else{
            return null;
        }
    }
    @Override
    public void deleteUser(int userId) {
        userDao.deleteById(userId);
    }
}
