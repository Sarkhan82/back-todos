package com.sogeti.todos;

import com.sogeti.todos.services.TodoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.mockito.Mockito.mock;

@Configuration
public class TestConfig {

    @Bean
    public TodoService todoService() {
        return mock(TodoService.class);
    }
}