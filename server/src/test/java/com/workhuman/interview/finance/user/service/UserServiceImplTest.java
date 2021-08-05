package com.workhuman.interview.finance.user.service;

import com.workhuman.interview.common.exception.FinanceException;
import com.workhuman.interview.finance.user.dao.UserDAO;
import com.workhuman.interview.finance.user.dto.UserDTO;
import com.workhuman.interview.finance.user.mapper.UserMapper;
import com.workhuman.interview.finance.user.model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    UserDAO userDAO;
    @Spy
    UserMapper mapper = Mappers.getMapper(UserMapper.class);

    @InjectMocks
    UserServiceImpl subject;

    @Test
    @DisplayName("When requesting all users expect all of them to be returned.")
    void getAllUsers() {
        List<User> userList = mapper.userDTOListToUserList(getUsers());
        when(userDAO.findAll()).thenReturn(userList);
        List<UserDTO> result = subject.getEntities();
        assertEquals(3, result.size());
    }

    @Test
    @DisplayName("Given a valid id when requesting a user expect it to be correctly returned")
    void getAccount() {
        UserDTO newUser = createUser("one.person", "Passw0rd1", "One", "Person", "one.person@test.com");
        User user = mapper.userDTOToUser(newUser);
        when(userDAO.findById(anyLong())).thenReturn(java.util.Optional.of(user));
        UserDTO result = subject.getEntity(1L);
        assertEquals(newUser.getUserName(), result.getUserName());
        assertEquals(newUser.getPassword(), result.getPassword());
        assertEquals(newUser.getFirstName(), result.getFirstName());
        assertEquals(newUser.getLastName(), result.getLastName());
        assertEquals(newUser.getEmail(), result.getEmail());
    }

    @Test
    @DisplayName("Given an invalid id when requesting a user expect null to be returned")
    void getInvalidUser() {
        when(userDAO.findById(anyLong())).thenReturn(Optional.empty());
        UserDTO result = subject.getEntity(1L);
        assertNull(result);
    }

    @Test
    @DisplayName("Given a valid user name when requesting accounts expect them to be returned")
    void getUserByEmail() {
        List<User> userList = mapper.userDTOListToUserList(getUsers());
        when(userDAO.findByEmail("one.person@test.com")).thenReturn(userList);
        List<UserDTO> result = subject.getUserByEmail("one.person@test.com");
        assertEquals(3, result.size());
        assertEquals("one.person", result.get(0).getUserName());
    }

    @Test
    @DisplayName("Given an existing user when updating expect it to be successfully updated")
    void updateEntity() {
        UserDTO existingUser = createUser("two.person", "Passw0rd2", "Two", "Person", "two.person@test.com");
        User user = mapper.userDTOToUser(existingUser);
        when(userDAO.findById(anyLong())).thenReturn(Optional.of(user));
        when(userDAO.save(any())).thenReturn(user);
        
        UserDTO result = subject.updateEntity(anyLong(), existingUser);
        assertEquals(existingUser.getUserName(), result.getUserName());
        assertEquals(existingUser.getPassword(), result.getPassword());
        assertEquals(existingUser.getFirstName(), result.getFirstName());
        assertEquals(existingUser.getLastName(), result.getLastName());
        assertEquals(existingUser.getEmail(), result.getEmail());
    }

    @Test
    @DisplayName("Given a non-existing user when updating expect exception to be returned")
    void updateInvalidEntity() {
        UserDTO existingUser = createUser("three.person", "Passw0rd3", "Three", "Person", "three.person@test.com");
        when(userDAO.findById(anyLong())).thenReturn(Optional.empty());
        Exception exception = assertThrows(FinanceException.class, () -> {
            subject.updateEntity(anyLong(), existingUser);
        });
        assertEquals("Error updating user", exception.getMessage());
    }

    @Test
    @DisplayName("Given valid user details when creating a user expect saved user to be returned")
    void addEntity() {
        UserDTO newUser = createUser("four.person", "Passw0rd3", "Four", "Person", "four.person@test.com");
        User user = mapper.userDTOToUser(newUser);
        when(userDAO.save(any())).thenReturn(user);
        
        UserDTO result = subject.addEntity(newUser);
        assertEquals(newUser.getUserName(), result.getUserName());
        assertEquals(newUser.getPassword(), result.getPassword());
        assertEquals(newUser.getFirstName(), result.getFirstName());
        assertEquals(newUser.getLastName(), result.getLastName());
        assertEquals(newUser.getEmail(), result.getEmail());
    }

    @Test
    @DisplayName("Given saving error when creating a user expect exception to be thrown")
    void addErrorEntity() {
        UserDTO newUser = createUser("five.person", "Passw0rd3", "Five", "Person", "five.person@test.com");
        when(userDAO.save(any())).thenThrow(new RuntimeException("Unknown error saving user"));
        Exception exception = assertThrows(FinanceException.class, () -> {
            subject.addEntity(newUser);
        });
        assertEquals("java.lang.RuntimeException: Unknown error saving user", exception.getMessage());
    }

    private List<UserDTO> getUsers() {
        List<UserDTO> userList = new ArrayList<>();
        userList.add(createUser("one.person", "Passw0rd1", "One", "Person", "one.person@test.com"));
        userList.add(createUser("two.person", "Passw0rd2", "Two", "Person", "two.person@test.com"));
        userList.add(createUser("three.person", "Passw0rd3", "Three", "Person", "three.person@test.com"));

        return userList;
    }

    private UserDTO createUser(String userName, String password, String firstName, String lastName, String emailAddress) {
        UserDTO user = new UserDTO();

        user.setUserName(userName);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(emailAddress);

        return user;
    }

}