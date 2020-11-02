package com.triangle.library.service.api;

import com.triangle.library.service.dto.AuthorBookDto;
import com.triangle.library.service.dto.AuthorDto;
import com.triangle.library.service.model.Author;
import com.triangle.library.service.service.AuthorBookService;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Api для работы связи автора и книг
 */
@Component
public class AuthorBookApi extends TransformationApi<Author, AuthorDto> {

    private final AuthorBookService service;

    public AuthorBookApi(AuthorBookService service) {
        super(Author.class, AuthorDto.class);
        this.service = service;
    }

    public void add(AuthorBookDto authorBookDto) {
        service.add(authorBookDto.getAuthorId(), authorBookDto.getBookId());
    }

    public List<AuthorDto> get(String bookName) {
        return toDtoList(service.get(bookName));
    }

    public void delete(AuthorBookDto authorBookDto) {
        service.delete(authorBookDto.getAuthorId(), authorBookDto.getBookId());
    }
}
