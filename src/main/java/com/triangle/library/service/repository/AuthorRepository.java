package com.triangle.library.service.repository;

import com.triangle.library.service.model.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * Репозиторий для сущности автор {@link Author}
 */
public interface AuthorRepository extends JpaRepository<Author, Long> {

    /**
     * Получение авторов по названию книги
     *
     * @param bookName название книги
     */
    @Query("select a from library.authors a \n"
           + "inner join library.author_book ab on ab.author_id = a.id\n"
           + "inner join library.books b on b.id = ab.book_id\n"
           + "where b.name = :bookName")
    Page<Author> findByBookName(@Param("bookName") String bookName);

    /**
     * Получение автора по имени и фамилии
     *
     * @param firstName имя автора
     * @param lastName  фамили автора
     */
    Optional<Author> findAuthorByFirstNameAndLastName(String firstName, String lastName);
}