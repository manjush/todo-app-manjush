package com.manjush.todo.repository;

import com.manjush.todo.entity.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for Toodo
 */
public interface TodoRepository extends JpaRepository<ToDo,Long> {
}
