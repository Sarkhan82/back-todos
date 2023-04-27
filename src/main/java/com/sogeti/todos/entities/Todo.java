package com.sogeti.todos.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "todos")

public class Todo {

    //------------------------------------------------------
    // ATTRIBUTS
    //------------------------------------------------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column
    private String description;

    @Column(nullable = false)
    private boolean done = false;

    //------------------------------------------------------
    // CONSTRUCTEUR
    //------------------------------------------------------
    public Todo() {}

    public Todo(String title, String description) {
        this.title = title;
        this.description = description;
    }

    //------------------------------------------------------
    // GETTER & SETTER
    //------------------------------------------------------


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
