package com.sogeti.todos.controller;

import com.sogeti.todos.entities.Todo;
import com.sogeti.todos.exceptions.TodoNotFoundException;
import com.sogeti.todos.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/todo")
@CrossOrigin(origins = "http://localhost:4200")
public class TodoRestControllerAPI {

    @Autowired
    private TodoService todoService;

    public TodoRestControllerAPI(TodoService todoService) {
        this.todoService = todoService;
    }

    /**
     * GET => http://localhost:8080/todo
     * API for Return all to-do's
     */
    @GetMapping
    public List<Todo> getAllTodos() {
        return todoService.getAllTodos();
    }

    /**
     * GET => http://localhost:8080/todo/{todoId}
     * API for return to-do by id
     */
    @GetMapping(path = "/{todoId}")
    public Todo getTodoById(@PathVariable("todoId") Long todoId) throws TodoNotFoundException {
        return todoService.getTodoById(todoId);
    }

    /**
     * POST => http://localhost:8080/todo
     * API for add a new to-do
     */
    @PostMapping
    public Todo addTodo(@RequestBody Todo todo) {
        return todoService.addTodo(todo);
    }

    /**
     * PUT => http://localhost:8080/todo/{todoId}/done
     * API for change done state of a to-do
     */
    @PutMapping(path = "/{todoId}/done")
    public Todo changeDoneState(@PathVariable("todoId") Long todoId) throws TodoNotFoundException {
        return todoService.changeDoneState(todoId);
    }

    /**
     * DELETE => http://localhost:8080/todo/{todoId}
     * API for delete a to-do
     */
    @DeleteMapping(path = "/{todoId}")
    public Optional<Todo> deleteTodo(@PathVariable("todoId") Long todoId) throws TodoNotFoundException {
        return todoService.deleteTodo(todoId);
    }

    /**
     * PUT => http://localhost:8080/todo/{todoId}
     * API for edit a to-do
     */
    @PutMapping(path = "/{todoId}")
    public Todo editTodo(@PathVariable("todoId") Long todoId, @RequestBody Todo todo) throws TodoNotFoundException {
        return todoService.editTodo(todoId, todo.getTitle(), todo.getDescription());
    }

}
