package com.manjush.todo.controller;

import com.manjush.todo.dto.TodoDto;
import com.manjush.todo.entity.ToDo;
import com.manjush.todo.entity.User;
import com.manjush.todo.exception.ToDoNotFoundException;
import com.manjush.todo.exception.UserNotFoundException;
import com.manjush.todo.service.ToDoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/users/{userId}/todos")
public class ToDoController {

    private final ToDoService toDoService;


    /**
     * initialize ToDoController
     *
     * @param toDoService Service class that handles all toodo operations
     */
    public ToDoController(final ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    /**
     * add new toodo for a user
     *
     * @param todoDto {@link TodoDto}
     * @return ResponseEntity<ToDoo> {@link ResponseEntity} of {@link ToDo}
     */
    @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ToDo> addTodo(@PathVariable Long userId, @RequestBody @Valid TodoDto todoDto) throws UserNotFoundException {
        ToDo savedToDo = toDoService.createTodo(userId, todoDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedToDo);
    }

    /**
     * delete a toodo from a user
     *
     * @param userId user id
     * @param todoId toodo id
     * @throws UserNotFoundException exception thrown when user is not found
     * @throws ToDoNotFoundException exception thrown when TooDo not found
     */
    @DeleteMapping("/{todoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTodo(@PathVariable Long userId, @PathVariable Long todoId)
            throws UserNotFoundException, ToDoNotFoundException {

        toDoService.deleteTodo(userId, todoId);
    }
}
