package com.triangle.library.service.author.repository;

import com.triangle.library.service.author.model.Author;
import com.triangle.library.service.book.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

/**
 * Репозиторий для сущности автор {@link Author}
 */
public interface AuthorRepository extends JpaRepository<Author, Long> {

    Page<Author> findAuthorByBooks(Set<Book> bookSet);
}
