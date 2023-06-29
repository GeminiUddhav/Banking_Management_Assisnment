package com.bank_managemen.bank_management.controller;

import com.bank_managemen.bank_management.entities.Users;
import com.bank_managemen.bank_management.services.UserServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class UserControllerTest {
    @Mock
    private UserServices userServices;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetUsers() {
        List<Users> userList = new ArrayList<>();
        Users user1 = new Users();
        user1.setUserId(1);
        userList.add(user1);

        when(userServices.getUsers()).thenReturn(userList);

        List<Users> response = userController.getUsers();

        assertEquals(userList, response);

        verify(userServices, times(1)).getUsers();
    }

    @Test
    void testGetUser() {
        int userId = 1;

        Users user = new Users();
        user.setUserId(userId);

        when(userServices.getUserById(userId)).thenReturn(user);

        Users response = userController.getUser(userId);

        assertEquals(user, response);

        verify(userServices, times(1)).getUserById(userId);
    }

    @Test
    void testGetUser_UserNotFound() {
        int userId = 1;

        when(userServices.getUserById(userId)).thenReturn(null);

        Users response = userController.getUser(userId);

        assertNull(response);

        verify(userServices, times(1)).getUserById(userId);
    }

    @Test
    void testAddUser() {
        Users user = new Users();

        when(userServices.addUser(any(Users.class))).thenReturn(user);

        userController.addUser(user);

        verify(userServices, times(1)).addUser(any(Users.class));
    }

    @Test
    void testPutUser() {
        int userId = 1;
        Users user = new Users();
        user.setUserId(userId);

        when(userServices.updateUserDetails(eq(userId), any(Users.class))).thenReturn(user);

        Users response = userController.putUser(userId, user);

        assertEquals(user, response);

        verify(userServices, times(1)).updateUserDetails(eq(userId), any(Users.class));
    }

    @Test
    void testPutUser_UserNotFound() {
        int userId = 1;
        Users user = new Users();
        user.setUserId(userId);

        when(userServices.updateUserDetails(eq(userId), any(Users.class))).thenReturn(null);

        Users response = userController.putUser(userId, user);

        assertNull(response);

        verify(userServices, times(1)).updateUserDetails(eq(userId), any(Users.class));
    }

    @Test
    void testDeleteUser() {
        int userId = 1;

        doNothing().when(userServices).deleteUser(userId);

        ResponseEntity<HttpStatus> response = userController.deleteUser(String.valueOf(userId));

        assertEquals(HttpStatus.OK, response.getStatusCode());

        verify(userServices, times(1)).deleteUser(userId);
    }

    @Test
    void testDeleteUser_UserNotFound() {
        int userId = 1;

        doThrow(new RuntimeException("User not found")).when(userServices).deleteUser(userId);

        ResponseEntity<HttpStatus> response = userController.deleteUser(String.valueOf(userId));

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());

        verify(userServices, times(1)).deleteUser(userId);
    }
}

