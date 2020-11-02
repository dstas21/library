package com.triangle.library.service.controller;

import com.triangle.library.service.api.AuthorBookApi;
import com.triangle.library.service.dto.AuthorBookDto;
import com.triangle.library.service.dto.AuthorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * Сервис для работы связи автора и книг
 */
@RestController
@RequestMapping("/api/author-book")
public class AuthorBookController {

    private final AuthorBookApi authorBookApi;

    public AuthorBookController(AuthorBookApi authorBookApi) {
        this.authorBookApi = authorBookApi;
    }

    /**
     * Добавление книги к автору
     *
     * @param authorBookDto dto хранящее в себе автора и книгу
     */
    @PostMapping
    public HttpStatus add(@RequestBody @Valid AuthorBookDto authorBookDto) {
        authorBookApi.add(authorBookDto);
        return HttpStatus.NO_CONTENT;
    }

    /**
     * Поиск всех авторов по названию книги
     *
     * @param bookName название книги
     */
    @GetMapping
    public ResponseEntity<List<AuthorDto>> get(@RequestParam(name = "bookName") String bookName) {
        return ResponseEntity.ok(authorBookApi.get(bookName));
    }

    /**
     * Удаление книги у автора
     *
     * @param authorBookDto dto хранящее в себе автора и книгу
     */
    @DeleteMapping
    public HttpStatus delete(@RequestBody @Valid AuthorBookDto authorBookDto) {
        authorBookApi.delete(authorBookDto);
        return HttpStatus.NO_CONTENT;
    }
}
