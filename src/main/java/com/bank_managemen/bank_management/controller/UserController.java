package com.bank_managemen.bank_management.controller;

import com.bank_managemen.bank_management.entities.Users;
import com.bank_managemen.bank_management.services.UserServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserServices userServices;

    @GetMapping("/users")
    public List<Users> getUsers(){
        logger.info("Fetching all users");
        return userServices.getUsers();
    }
    @GetMapping("/users/{userId}")
    public Users getUser(@PathVariable int userId){
        logger.info("Fetching user with id: {}",userId);
        return this.userServices.getUserById(userId);
    }
    @PostMapping("/users")
    public void addUser(@RequestBody Users acc){
        logger.info("Adding new user: {}", acc);
        userServices.addUser(acc);
    }

    @PutMapping("/users/{userId}")
    public Users putUser(@RequestParam int userId,@RequestBody Users acc)    {
        logger.info("Updating user with id: {}", acc);
        return this.userServices.updateUserDetails(userId,acc);
    }
    @DeleteMapping("/users/{userId}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable String userId){
        logger.info("Deleting user with id: {}", userId);
        try {
            this.userServices.deleteUser((int) Long.parseLong(userId));
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception exception){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
