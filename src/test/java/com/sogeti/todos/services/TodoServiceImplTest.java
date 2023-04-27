package com.sogeti.todos.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.sogeti.todos.entities.Todo;
import com.sogeti.todos.exceptions.TodoNotFoundException;
import com.sogeti.todos.repositories.TodoRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TodoServiceImplTest {

    @Mock
    TodoRepository todoRepository;

    @InjectMocks
    TodoServiceImpl todoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAllTodos_shouldReturnListOfTodos() {
        // given
        List<Todo> todos = new ArrayList<>();
        Todo todo1 = new Todo();
        todo1.setId(1L);
        todo1.setTitle("Todo 1");
        todo1.setDescription("Description 1");
        todo1.setDone(false);
        Todo todo2 = new Todo();
        todo2.setId(2L);
        todo2.setTitle("Todo 2");
        todo2.setDescription("Description 2");
        todo2.setDone(false);
        todos.add(todo1);
        todos.add(todo2);

        Mockito.when(todoRepository.findAll()).thenReturn(todos);

        // when
        List<Todo> result = todoService.getAllTodos();

        // then
        assertEquals(2, result.size());
        assertEquals("Todo 1", result.get(0).getTitle());
        assertEquals("Todo 2", result.get(1).getTitle());
    }

    @Test
    public void getTodoById_shouldReturnTodo_whenTodoExists() throws TodoNotFoundException {
        // given
        Todo todo = new Todo();
        todo.setId(1L);
        todo.setTitle("Todo 1");
        todo.setDescription("Description 1");
        todo.setDone(false);

        Mockito.when(todoRepository.findById(1L)).thenReturn(Optional.of(todo));

        // when
        Todo result = todoService.getTodoById(1L);

        // then
        assertEquals("Todo 1", result.getTitle());
        assertEquals("Description 1", result.getDescription());
    }

    @Test
    public void getTodoById_shouldThrowTodoNotFoundException_whenTodoDoesNotExist() {
        // given
        Mockito.when(todoRepository.findById(1L)).thenReturn(Optional.empty());

        // when, then
        assertThrows(TodoNotFoundException.class, () -> todoService.getTodoById(1L));
    }

    @Test
    public void addTodo_shouldReturnAddedTodo() {
        // given
        Todo todoToAdd = new Todo();
        todoToAdd.setTitle("Todo to add");
        todoToAdd.setDescription("Description to add");

        Todo addedTodo = new Todo();
        addedTodo.setId(1L);
        addedTodo.setTitle("Todo to add");
        addedTodo.setDescription("Description to add");
        addedTodo.setDone(false);

        Mockito.when(todoRepository.save(Mockito.any(Todo.class))).thenReturn(addedTodo);

        // when
        Todo result = todoService.addTodo(todoToAdd);

        // then
        assertEquals(1L, result.getId());
        assertEquals("Todo to add", result.getTitle());
        assertEquals("Description to add", result.getDescription());
        assertEquals(false, result.isDone());
    }

    @Test
    public void changeDoneState_shouldReturnUpdatedTodo() throws TodoNotFoundException {
        // given
        Todo todoToUpdate = new Todo();
        todoToUpdate.setId(1L);
        todoToUpdate.setTitle("Todo to update");
    }

    @Test
    public void deleteTodo_shouldReturnDeletedTodo() throws TodoNotFoundException {
        // given
        Todo todoToDelete = new Todo();
        todoToDelete.setId(1L);
        todoToDelete.setTitle("Todo to delete");
        todoToDelete.setDescription("Description to delete");
        todoToDelete.setDone(false);

        Mockito.when(todoRepository.findById(1L)).thenReturn(Optional.of(todoToDelete));

        // when
        Todo result = todoService.deleteTodo(1L).get();

        // then
        assertEquals(1L, result.getId());
        assertEquals("Todo to delete", result.getTitle());
        assertEquals("Description to delete", result.getDescription());
        assertEquals(false, result.isDone());
        Mockito.verify(todoRepository, Mockito.times(1)).deleteById(1L);
    }

    @Test
    public void deleteTodo_shouldThrowTodoNotFoundException_whenTodoDoesNotExist() {
        // given
        Mockito.when(todoRepository.findById(1L)).thenReturn(Optional.empty());

        // when, then
        assertThrows(TodoNotFoundException.class, () -> todoService.deleteTodo(1L));
    }

    @Test
    public void editTodo_shouldReturnUpdatedTodo() throws TodoNotFoundException {
        // given
        Todo todoToUpdate = new Todo();
        todoToUpdate.setId(1L);
        todoToUpdate.setTitle("Todo to update");
        todoToUpdate.setDescription("Description to update");
        todoToUpdate.setDone(false);

        Todo updatedTodo = new Todo();
        updatedTodo.setId(1L);
        updatedTodo.setTitle("Updated todo");
        updatedTodo.setDescription("Updated description");
        updatedTodo.setDone(false);

        Mockito.when(todoRepository.findById(1L)).thenReturn(Optional.of(todoToUpdate));
        Mockito.when(todoRepository.save(Mockito.any(Todo.class))).thenReturn(updatedTodo);

        // when
        Todo result = todoService.editTodo(1L, "Updated todo", "Updated description");

        // then
        assertEquals(1L, result.getId());
        assertEquals("Updated todo", result.getTitle());
        assertEquals("Updated description", result.getDescription());
        assertEquals(false, result.isDone());
        Mockito.verify(todoRepository, Mockito.times(1)).save(Mockito.any(Todo.class));
    }

}