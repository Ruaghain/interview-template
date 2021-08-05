package com.workhuman.interview.finance.user.controller;

import com.workhuman.interview.finance.user.dto.UserDTO;
import com.workhuman.interview.finance.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    
    Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDTO> getAllUsers() {
        logger.debug("Getting all the users");
        return userService.getEntities();
    }

    @GetMapping(path = "/{id}")
    public UserDTO getUser(@PathVariable Long id) {
        logger.debug("Getting user with id {}", id);
        return userService.getEntity(id);
    }

    @GetMapping(path = "/query")
    public List<UserDTO> getUserByEmail(@RequestParam("email") String email) {
        logger.debug("Querying for user(s) with an email of {}", email);
        return userService.getUserByEmail(email);
    }

    @PutMapping(path = "/{id}")
    public UserDTO updateUser(@PathVariable Long id, @RequestBody UserDTO user) {
        logger.debug("Updating user with an id of {}", id);
        return userService.updateEntity(id, user);
    }

    @PostMapping
    public UserDTO addUser(@RequestBody UserDTO user) {
        logger.debug("Creating a new user");
        return userService.addEntity(user);
    }
}
