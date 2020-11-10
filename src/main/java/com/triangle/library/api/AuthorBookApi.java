package com.triangle.library.api;

import com.triangle.library.dto.AuthorDto;
import com.triangle.library.mapper.AuthorMapper;
import com.triangle.library.service.AuthorBookService;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Api для работы связи автора и книг
 */
@Component
public class AuthorBookApi {

    private final AuthorBookService service;
    private final AuthorMapper mapper;

    public AuthorBookApi(AuthorBookService service,
                         AuthorMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    public void add(Long authorId, Long bookId) {
        service.add(authorId, bookId);
    }

    public List<AuthorDto> get(String bookName) {
        return mapper.toDtoList(service.get(bookName));
    }

    public void delete(Long authorId, Long bookId) {
        service.delete(authorId, bookId);
    }
}
