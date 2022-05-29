package com.manjush.todo.service;

import com.manjush.todo.dto.TodoDto;
import com.manjush.todo.entity.ToDo;
import com.manjush.todo.entity.User;
import com.manjush.todo.exception.ToDoNotFoundException;
import com.manjush.todo.exception.UserNotFoundException;
import com.manjush.todo.repository.TodoRepository;
import com.manjush.todo.repository.UserRepository;
import org.springframework.stereotype.Service;

/**
 * Service to handle all TooDo Operations
 */
@Service
public class ToDoServiceImpl implements ToDoService {

    private final UserRepository userRepository;
    private final TodoRepository todoRepository;

    public ToDoServiceImpl(final UserRepository userRepository, final TodoRepository todoRepository) {
        this.userRepository = userRepository;
        this.todoRepository = todoRepository;
    }

    @Override
    public ToDo createTodo(Long userId, TodoDto todoDto) throws UserNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        ToDo todo = new ToDo();
        todo.setDescription(todoDto.getDescription());
        todo.setCompleted(todoDto.isCompleted());
        todo.setUser(user);
        return todoRepository.save(todo);
    }

    @Override
    public void deleteTodo(Long userId, Long todoId) throws UserNotFoundException, ToDoNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        ToDo todo = todoRepository.findById(todoId).orElseThrow(ToDoNotFoundException::new);
        user.getTodoList().remove(todo);
        todoRepository.delete(todo);
    }
}
