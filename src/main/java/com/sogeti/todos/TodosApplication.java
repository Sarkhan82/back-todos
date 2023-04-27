package com.sogeti.todos;
import com.sogeti.todos.entities.Todo;
import com.sogeti.todos.services.TodoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TodosApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodosApplication.class, args);
	}
	@Bean
	CommandLineRunner start(TodoService todoService) {
		return args -> {
			todoService.addTodo(new Todo("test1", "description1"));
			todoService.addTodo(new Todo("test2", "description2"));
			todoService.addTodo(new Todo("test3", "description3"));
			todoService.addTodo(new Todo("test4", "description4"));
		};
	}

}