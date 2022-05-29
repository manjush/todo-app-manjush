package com.manjush.todo.repository;

import com.manjush.todo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for User
 */
public interface UserRepository extends JpaRepository<User,Long> {
}
