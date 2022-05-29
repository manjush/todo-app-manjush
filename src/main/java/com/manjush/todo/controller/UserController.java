package com.manjush.todo.controller;


import com.manjush.todo.dto.UserDto;
import com.manjush.todo.entity.User;
import com.manjush.todo.exception.UserNotFoundException;
import com.manjush.todo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping(value = "/api/users")
public class UserController {

    private final UserService userService;

    /**
     * Constructor for UserController
     *
     * @param userService user service
     */
    public UserController(final UserService userService) {
        this.userService = userService;
    }

    /**
     * Get user details by id
     *
     * @param userId user id
     *               ResponseEntity<User> {@link ResponseEntity} of {@link User}
     * @throws UserNotFoundException is thrown when user is not found
     */
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) throws UserNotFoundException {
        User user = userService.getUserDetailsById(userId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(user);
    }


    /**
     * add new user
     *
     * @param userDto {@link UserDto}
     * @return ResponseEntity<User> {@link ResponseEntity} of {@link User}
     */
    @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> addUser(@Valid @RequestBody UserDto userDto) {
        User savedUser = userService.addUser(userDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedUser);
    }


    /**
     * delete user by Id
     *
     * @param userId user id
     * @throws UserNotFoundException is thrown when user is not found
     */
    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long userId) throws UserNotFoundException {
        userService.deleteUser(userId);
    }

}
