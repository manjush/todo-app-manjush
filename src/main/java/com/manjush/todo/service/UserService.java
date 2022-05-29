package com.manjush.todo.service;

import com.manjush.todo.dto.UserDto;
import com.manjush.todo.entity.User;
import com.manjush.todo.exception.UserNotFoundException;

public interface UserService {

    User addUser(UserDto dto);

    void deleteUser(Long userId) throws UserNotFoundException;

    User getUserDetailsById(Long userId) throws UserNotFoundException;
}
