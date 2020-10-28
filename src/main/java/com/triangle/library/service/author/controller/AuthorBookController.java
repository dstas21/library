package com.triangle.library.service.author.controller;

import com.triangle.library.service.author.dto.AuthorBookDto;
import com.triangle.library.service.author.dto.AuthorDto;
import com.triangle.library.service.author.service.AuthorBookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * Сервис для работы связи автора и книг
 */
@RestController
@RequestMapping("/api/author-book")
public class AuthorBookController {

    private final AuthorBookService authorBookService;

    public AuthorBookController(AuthorBookService authorBookService) {
        this.authorBookService = authorBookService;
    }

    @PostMapping
    public HttpStatus add(@RequestBody @Valid AuthorBookDto authorBookDto) {
        authorBookService.add(authorBookDto);
        return HttpStatus.NO_CONTENT;
    }

    @GetMapping("/{name}")
    public ResponseEntity<List<AuthorDto>> get(@PathVariable String bookName) {
        return new ResponseEntity<>(authorBookService.get(bookName), HttpStatus.OK);
    }

    @DeleteMapping
    public HttpStatus delete(@RequestBody @Valid AuthorBookDto authorBookDto) {
        authorBookService.delete(authorBookDto);
        return HttpStatus.NO_CONTENT;
    }
}
