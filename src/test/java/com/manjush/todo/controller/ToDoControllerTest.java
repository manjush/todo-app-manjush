package com.manjush.todo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.manjush.todo.dto.TodoDto;
import com.manjush.todo.entity.ToDo;
import com.manjush.todo.service.ToDoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ToDoController.class)
class ToDoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ToDoService toDoService;

    @Test
    void addTodo() throws Exception {
        TodoDto todoDto = new TodoDto();
        todoDto.setDescription("testDesc");

        when(toDoService.createTodo(1L, todoDto)).thenReturn(new ToDo());

        mockMvc.perform(post("/api/users/1/todos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(todoDto)))
                .andExpect(status().isCreated());
    }

    @Test
    void deleteTodo() throws Exception {


        mockMvc.perform(delete("/api/users/1/todos/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}