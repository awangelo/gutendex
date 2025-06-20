package com.example.gutendex.model;

import jakarta.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String title;

    @ManyToOne(cascade = CascadeType.ALL)
    private Author author;

    private String language;

    public Book() {}

    public Book(BookData bookData) {
        this.title = bookData.title();
        this.author = new Author(bookData.authors().get(0));
        this.language = bookData.languages().get(0);
    }

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

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return "----------- LIVRO -----------\n" +
                "Título: " + title + "\n" +
                "Autor: " + author.getName() + "\n" +
                "Idioma: " + language + "\n" +
                "-----------------------------\n";
    }
}
