package com.manjush.todo.repository;

import com.manjush.todo.entity.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<ToDo,Long> {
}
