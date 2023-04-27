package com.sogeti.todos.repositories;

import com.sogeti.todos.entities.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository  extends JpaRepository<Todo, Long> {
}

