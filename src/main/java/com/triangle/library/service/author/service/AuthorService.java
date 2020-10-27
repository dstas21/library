package com.triangle.library.service.author.service;

import com.triangle.library.service.author.dto.AuthorDto;
import com.triangle.library.service.author.model.Author;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Интерфейс сервиса для сущности автор {@link Author}
 */
public interface AuthorService {

    AuthorDto create(AuthorDto author);

    List<AuthorDto> getAll(Pageable pageable);

    AuthorDto getById(Long id);

    AuthorDto update(Long id, AuthorDto author);

    void delete(Long id);
}
