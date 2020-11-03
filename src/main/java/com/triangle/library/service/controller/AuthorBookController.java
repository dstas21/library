package com.triangle.library.service.controller;

import com.triangle.library.service.api.AuthorBookApi;
import com.triangle.library.service.dto.AuthorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Сервис для работы связи автора и книг
 */
@RestController
@RequestMapping("/api/authors")
public class AuthorBookController {

    private final AuthorBookApi authorBookApi;

    public AuthorBookController(AuthorBookApi authorBookApi) {
        this.authorBookApi = authorBookApi;
    }

    /**
     * Добавление книги к автору
     *
     * @param authorId идентификатор автора
     * @param bookId   идентификатор книги
     */
    @PostMapping("/{authorId}/books/{bookId}")
    public HttpStatus add(@PathVariable Long authorId,
                          @PathVariable Long bookId) {
        authorBookApi.add(authorId, bookId);
        return HttpStatus.NO_CONTENT;
    }

    /**
     * Поиск всех авторов по названию книги
     *
     * @param bookName название книги
     */
    @GetMapping("/books")
    public ResponseEntity<List<AuthorDto>> get(@RequestParam(name = "bookName") String bookName) {
        return ResponseEntity.ok(authorBookApi.get(bookName));
    }

    /**
     * Удаление книги у автора
     *
     * @param authorId идентификатор автора
     * @param bookId   идентификатор книги
     */
    @DeleteMapping("/{authorId}/books/{bookId}")
    public HttpStatus delete(@PathVariable Long authorId,
                             @PathVariable Long bookId) {
        authorBookApi.delete(authorId, bookId);
        return HttpStatus.NO_CONTENT;
    }
}
