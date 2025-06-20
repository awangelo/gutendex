package com.example.gutendex.repository;

import com.example.gutendex.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByLanguage(String language);
    long countByLanguage(String language);
}
