package com.sogeti.todos.services;

import com.sogeti.todos.entities.Todo;
import com.sogeti.todos.exceptions.TodoNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Optional;

public interface TodoService {
    abstract List<Todo> getAllTodos();

    Todo getTodoById(Long todoId) throws TodoNotFoundException;

    Todo addTodo(@NotNull Todo todo);

    @Transactional
    Todo changeDoneState(long todoId) throws TodoNotFoundException;

    Optional<Todo> deleteTodo(Long todoId) throws TodoNotFoundException;

    Todo editTodo(Long todoId, String newTitle, String newDescription) throws TodoNotFoundException;
}
