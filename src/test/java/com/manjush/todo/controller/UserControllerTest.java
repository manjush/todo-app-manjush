package com.manjush.todo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.manjush.todo.dto.UserDto;
import com.manjush.todo.entity.User;
import com.manjush.todo.repository.TodoRepository;
import com.manjush.todo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private TodoRepository todoRepository;

    @Test
    void testCreateUser() throws Exception {
        UserDto request = new UserDto();
        request.setUsername("myusername");
        request.setPassword("mypassword");

        when(userRepository.save(any(User.class))).thenReturn(new User());


        mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isCreated());

    }

    @Test
    void testAddUserWhenUserNameIsBlank() throws Exception {
        UserDto request = new UserDto();
        request.setUsername("");
        request.setPassword("mypassword");

        when(userRepository.save(any(User.class))).thenReturn(new User());


        mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isBadRequest());

    }

    @Test
    void testDeleteUser() throws Exception {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(new User()));


        mockMvc.perform(delete("/api/users/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

    }

}