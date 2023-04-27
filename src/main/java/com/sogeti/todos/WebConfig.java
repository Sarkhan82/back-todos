 package com.sogeti.todos;

import com.sogeti.todos.controller.TodoRestControllerAPI;
import com.sogeti.todos.services.TodoService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {

    @Bean
    public TodoService todoService() {
        // retourne une instance de TodoService mockée pour les tests
        return Mockito.mock(TodoService.class);
    }

    @Bean
    public TodoRestControllerAPI todoRestControllerAPI(TodoService todoService) {
        // retourne une instance de TodoRestControllerAPI qui utilise le TodoService mocké
        return new TodoRestControllerAPI(todoService);
    }

}
