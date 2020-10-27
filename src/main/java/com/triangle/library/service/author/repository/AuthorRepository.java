package com.triangle.library.service.author.repository;

import com.triangle.library.service.author.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий для сущности автор {@link Author}
 */
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
