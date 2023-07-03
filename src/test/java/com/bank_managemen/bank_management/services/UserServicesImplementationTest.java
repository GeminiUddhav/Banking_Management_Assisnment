package com.bank_managemen.bank_management.services;
import com.bank_managemen.bank_management.dao.UserDao;
import com.bank_managemen.bank_management.entities.Users;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserServicesImplementationTest {

    @Mock
    private UserDao userDao;

    @InjectMocks
    private UserServicesImplementation userServices;

    @Test
    public void testGetUsers() {
        Users user1 = new Users();
        Users user2 = new Users();
        List<Users> userList = Arrays.asList(user1, user2);
        when(userDao.findAll()).thenReturn(userList);
        List<Users> result = userServices.getUsers();
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(userDao, times(1)).findAll();
    }

    @Test
    public void testGetUserById() {
        int userId = 1;
        Users user = new Users();
        user.setUserId(userId);
        when(userDao.findById(userId)).thenReturn(Optional.of(user));
        Users result = userServices.getUserById(userId);
        assertNotNull(result);
        assertEquals(userId, result.getUserId());
        verify(userDao, times(1)).findById(userId);
    }

    @Test
    public void testGetUserById_UserNotFound() {
        int userId = 1;
        when(userDao.findById(userId)).thenReturn(Optional.empty());
        Users result = userServices.getUserById(userId);
        assertNull(result);
        verify(userDao, times(1)).findById(userId);
    }

    @Test
    public void testAddUser() {
        Users user = new Users();
        user.setUserId(1);
        when(userDao.save(user)).thenReturn(user);
        Users result = userServices.addUser(user);
        assertNotNull(result);
        assertEquals(1, result.getUserId());
        verify(userDao, times(1)).save(user);
    }

    @Test
    public void testUpdateUserDetails() {
        int userId = 1;
        Users existingUser = new Users();
        existingUser.setUserId(userId);
        Users updatedUser = new Users();
        updatedUser.setUserId(userId);
        updatedUser.setFirstName("Uddhav");
        updatedUser.setLastName("Rohan");
        when(userDao.findById(userId)).thenReturn(Optional.of(existingUser));
        when(userDao.save(updatedUser)).thenReturn(updatedUser);
        Users result = userServices.updateUserDetails(userId, updatedUser);
        assertNotNull(result);
        assertEquals("Uddhav", result.getFirstName());
        assertEquals("Rohan", result.getLastName());
        verify(userDao, times(1)).findById(userId);
        verify(userDao, times(1)).save(updatedUser);
    }

    @Test
    public void testUpdateUserDetails_UserNotFound() {
        int userId = 1;
        Users updatedUser = new Users();
        updatedUser.setUserId(userId);
        updatedUser.setFirstName("Uddhav");
        updatedUser.setLastName("Rohan");
        when(userDao.findById(userId)).thenReturn(Optional.empty());
        Users result = userServices.updateUserDetails(userId, updatedUser);
        assertNull(result);
        verify(userDao, times(1)).findById(userId);
        verify(userDao, never()).save(updatedUser);
    }

    @Test
    public void testDeleteUser() {
        int userId = 1;
        userServices.deleteUser(userId);
        verify(userDao, times(1)).deleteById(userId);
    }
}
