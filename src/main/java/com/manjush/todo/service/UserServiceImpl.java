package com.manjush.todo.service;

import com.manjush.todo.dto.UserDto;
import com.manjush.todo.entity.User;
import com.manjush.todo.exception.UserNotFoundException;
import com.manjush.todo.repository.UserRepository;
import org.springframework.stereotype.Service;

/**
 * Service to handle all user Operations
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User addUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long userId) throws UserNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        userRepository.delete(user);
    }

    @Override
    public User getUserDetailsById(Long userId) throws UserNotFoundException {
        return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }
}
