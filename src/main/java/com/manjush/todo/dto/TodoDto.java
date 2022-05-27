package com.manjush.todo.dto;

import javax.validation.constraints.Size;

public class TodoDto {
    @Size(max = 255, message = "description can have maximum 255 characters")
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
