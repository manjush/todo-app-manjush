package com.manjush.todo.exception;

public class ToDoNotFoundException extends Exception {
    public ToDoNotFoundException() {
        super();
    }

    public ToDoNotFoundException(String message) {
        super(message);
    }
}
