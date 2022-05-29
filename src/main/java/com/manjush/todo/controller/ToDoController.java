package com.manjush.todo.controller;

import com.manjush.todo.dto.TodoDto;
import com.manjush.todo.entity.ToDo;
import com.manjush.todo.entity.User;
import com.manjush.todo.exception.ToDoNotFoundException;
import com.manjush.todo.exception.UserNotFoundException;
import com.manjush.todo.repository.TodoRepository;
import com.manjush.todo.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/users/{userId}/todos")
public class ToDoController {
    private final UserRepository userRepository;
    private final TodoRepository todoRepository;

    public ToDoController(final UserRepository userRepository, final TodoRepository todoRepository) {
        this.userRepository = userRepository;
        this.todoRepository = todoRepository;
    }

    @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ToDo> addTodo(@PathVariable Long userId, @RequestBody @Valid TodoDto todoDto) throws UserNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        ToDo todo = new ToDo();
        todo.setDescription(todoDto.getDescription());
        todo.setCompleted(todoDto.isCompleted());
        todo.setUser(user);
        ToDo savedToDo = todoRepository.save(todo);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedToDo);
    }

    @DeleteMapping("/{todoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTodo(@PathVariable Long userId, @PathVariable Long todoId)
            throws UserNotFoundException, ToDoNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        ToDo todo = todoRepository.findById(todoId).orElseThrow(ToDoNotFoundException::new);
        user.getTodoList().remove(todo);
        todoRepository.delete(todo);
    }
}
