package com.bank_managemen.bank_management.controller;

import com.bank_managemen.bank_management.entities.Users;
import com.bank_managemen.bank_management.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserServices userServices;

    @GetMapping("/users")
    public List<Users> getUsers(){

        return userServices.getUsers();
    }
    @GetMapping("/users/{userId}")
    public Users getUser(@PathVariable int userId){

        return this.userServices.getUserById(userId);
    }
    @PostMapping("/users")
    public void addUser(@RequestBody Users ac){
        userServices.addUser(ac);
    }
    @PutMapping("/users/{userId}")
    public Users putUser(@RequestParam int userId,@RequestBody Users acc)    {
        return this.userServices.updateUserDetails(userId,acc);
    }
    @DeleteMapping("/users/{userId}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable String userId){
        try {
            this.userServices.deleteUser((int) Long.parseLong(userId));
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception exception){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
