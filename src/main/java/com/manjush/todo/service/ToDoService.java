package com.manjush.todo.service;

import com.manjush.todo.dto.TodoDto;
import com.manjush.todo.entity.ToDo;
import com.manjush.todo.exception.ToDoNotFoundException;
import com.manjush.todo.exception.UserNotFoundException;

public interface ToDoService {

    ToDo createTodo(Long userId, TodoDto todoDto) throws UserNotFoundException;

    void deleteTodo(Long userId, Long todoId) throws UserNotFoundException, ToDoNotFoundException;
}
