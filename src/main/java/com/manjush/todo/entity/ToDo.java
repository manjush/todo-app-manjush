package com.manjush.todo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.util.Objects;

@Entity
public class ToDo extends BaseEntity {

    @Column(nullable = false, length = 50)
    private String description;
    private boolean completed;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ToDo)) return false;
        if (!super.equals(o)) return false;
        ToDo todo = (ToDo) o;
        return completed == todo.completed &&
                Objects.equals(description, todo.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), description, completed);
    }

    @Override
    public String toString() {
        return "Todo{" +
                "super='" + super.toString() + '\'' +
                "description='" + description + '\'' +
                ", completed=" + completed +
                '}';
    }
}
