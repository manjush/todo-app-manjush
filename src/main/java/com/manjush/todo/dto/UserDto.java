package com.manjush.todo.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserDto {
    @NotBlank(message = "cannot be null or empty")
    @Size(max = 50, message = "username can have maximum 50 characters")
    private String username;

    @NotBlank(message = "cannot be null or empty")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
