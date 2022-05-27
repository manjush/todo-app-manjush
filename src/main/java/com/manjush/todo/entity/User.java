package com.manjush.todo.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class User extends BaseEntity {

    @Column(name = "user_name", nullable = false, unique = true, length = 50)
    private String username;

    @Column(nullable = false, length = 50)
    private String password;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ToDo> todoList = new ArrayList<>();

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

    public List<ToDo> getTodoList() {
        return todoList;
    }

    public void setTodoList(List<ToDo> todoList) {
        this.todoList = todoList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return username.equals(user.username) &&
                password.equals(user.password) &&
                Objects.equals(todoList, user.todoList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), username, password, todoList);
    }

    @Override
    public String toString() {
        return "User{" +
                "super='" + super.toString() + '\'' +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", todoList=" + todoList +
                '}';
    }
}
