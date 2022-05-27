package com.manjush.todo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.manjush.todo.dto.TodoDto;
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
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ToDoController.class)
class ToDoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private TodoRepository todoRepository;

    @Test
    void addTodo() throws Exception {
        TodoDto todoDto = new TodoDto();
        todoDto.setDescription("testDesc");

        when(userRepository.findById(any())).thenReturn(Optional.of(new User()));

        mockMvc.perform(post("/api/users/1/todos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(todoDto)))
                .andExpect(status().isCreated());
    }

    @Test
    void deleteTodo() {
    }
}