package com.manjush.todo.controller;


import com.manjush.todo.dto.UserDto;
import com.manjush.todo.entity.ToDo;
import com.manjush.todo.entity.User;
import com.manjush.todo.exception.UserNotFoundException;
import com.manjush.todo.repository.TodoRepository;
import com.manjush.todo.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping(value = "/api/users")
public class UserController {

    private final UserRepository userRepository;
    private final TodoRepository todoRepository;

    /**
     * Constructor for UserController
     *
     * @param userRepository user repository
     * @param todoRepository toodo repository
     */
    public UserController(final UserRepository userRepository, final TodoRepository todoRepository) {
        this.userRepository = userRepository;
        this.todoRepository = todoRepository;
    }

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable Long userId) throws UserNotFoundException {
        return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> addUser(@Valid @RequestBody UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        User savedUser = userRepository.save(user);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedUser);
    }


    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long userId) throws UserNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        userRepository.delete(user);
    }

}
