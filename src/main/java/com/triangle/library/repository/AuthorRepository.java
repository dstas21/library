package com.triangle.library.repository;

import com.triangle.library.model.Author;
import com.triangle.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Репозиторий для сущности автор {@link Author}
 */
public interface AuthorRepository extends JpaRepository<Author, Long> {

    /**
     * Получение авторов по книге
     * <p>
     *
     * @param book книга
     */
    List<Author> findAuthorsByBooks(Book book);

    /**
     * Получение автора по имени и фамилии
     *
     * @param firstName имя автора
     * @param lastName  фамили автора
     */
    Optional<Author> findAuthorByFirstNameAndLastName(String firstName, String lastName);
}
