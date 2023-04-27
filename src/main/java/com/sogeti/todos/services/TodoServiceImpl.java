package com.sogeti.todos.services;

import com.sogeti.todos.entities.Todo;
import com.sogeti.todos.exceptions.TodoNotFoundException;
import com.sogeti.todos.repositories.TodoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoServiceImpl implements TodoService{
    private final TodoRepository todoRepository;

    public TodoServiceImpl(TodoRepository todoRepository) { this.todoRepository = todoRepository; }

    /**
     * return all todos
     */
    @Override
    public List<Todo> getAllTodos() { return todoRepository.findAll(); }

    /**
     * Return to-do by id
     */
    @Override
    public Todo getTodoById(Long todoId) throws TodoNotFoundException {
        Todo currentTodo = todoRepository.findById(todoId).orElseThrow(() -> new TodoNotFoundException("Todo not found with this ID"));
        return currentTodo;
    }

    /**
     * To add a new to-do
     */
    @Override
    public Todo addTodo(@NotNull Todo todo) {
        Todo currentTodo = todo;
        return todoRepository.save(currentTodo);
    }

    /**
     * To change done state of a to-do
     */
    @Override
    @Transactional
    public Todo changeDoneState(long todoId) throws TodoNotFoundException {
        Todo currentTodo = todoRepository.findById(todoId).orElseThrow(() -> new TodoNotFoundException("Todo not found with this ID"));
        currentTodo.setDone(!currentTodo.isDone());
        return currentTodo;
    }

    /**
     * To delete a to-do
     */
    @Override
    public Optional<Todo> deleteTodo(Long todoId) throws TodoNotFoundException {
        Optional<Todo> deletedTodo = todoRepository.findById(todoId);
        if (deletedTodo.isEmpty()) {
            throw new TodoNotFoundException("This Todo doesn't exist");
        }
        todoRepository.deleteById(todoId);
        return deletedTodo;
    }

    /**
     * To edit title and description of a to-do
     */
    @Override
    public Todo editTodo(Long todoId, String newTitle, String newDescription) throws TodoNotFoundException {
        Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new TodoNotFoundException("Todo not found with this ID"));
        todo.setTitle(newTitle);
        todo.setDescription(newDescription);
        return todoRepository.save(todo);
    }
}
