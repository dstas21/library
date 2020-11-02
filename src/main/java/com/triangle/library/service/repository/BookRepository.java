package com.triangle.library.service.repository;

import com.triangle.library.service.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Репозиторий для сущности книга {@link Book}
 */
public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findBookByName(String name);
}