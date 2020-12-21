package com.triangle.library.repository;

import com.triangle.library.model.Author;
import com.triangle.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Репозиторий для сущности книга {@link Book}
 */
public interface BookRepository extends JpaRepository<Book, Long> {

    /**
     * Получение книг по автору
     * <p>
     *
     * @param author автор
     */
    List<Book> findBooksByAuthors(Author author);

    /**
     * Получение книги по названию
     *
     * @param name название книги
     */
    Optional<Book> findBookByName(String name);
}
