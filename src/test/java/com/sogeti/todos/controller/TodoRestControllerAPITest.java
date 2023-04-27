package com.sogeti.todos.controller;

import com.sogeti.todos.TestConfig;
import com.sogeti.todos.WebConfig;
import com.sogeti.todos.entities.Todo;
import com.sogeti.todos.services.TodoService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {TestConfig.class, WebConfig.class})

public class TodoRestControllerAPITest {

    private MockMvc mockMvc;

    @Autowired
    private TodoService todoService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testGetAllTodos() throws Exception {
        // Mock le comportement de todoService.getAllTodos()
        List<Todo> expectedTodos = new ArrayList<>();
        expectedTodos.add(new Todo("Title1", "Description1"));
        expectedTodos.add(new Todo("Title2", "Description2"));
        when(todoService.getAllTodos()).thenReturn(expectedTodos);

        // Exécute la requête GET pour récupérer toutes les todos
        mockMvc.perform(get("/todo").header("Accept", MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].title", is("Title1")))
                .andExpect(jsonPath("$[0].description", is("Description1")))
                .andExpect(jsonPath("$[1].title", is("Title2")))
                .andExpect(jsonPath("$[1].description", is("Description2")));
    }
}