package com.triangle.library.service.author.controller;

import com.triangle.library.service.author.dto.AuthorDto;
import com.triangle.library.service.author.model.Author;
import com.triangle.library.service.author.service.AuthorService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * Сервис для сущности автор {@link Author}
 */
@RestController
@RequestMapping("/api/author")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @Async
    @PostMapping
    public ResponseEntity<AuthorDto> create(@RequestBody @Valid AuthorDto author) {
        return new ResponseEntity<>(authorService.create(author), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AuthorDto>> getAll(@RequestParam(defaultValue = "0") int page,
                                               @RequestParam(defaultValue = "50") int size,
                                               @RequestParam(defaultValue = "firstName") String sortParam) {
        return new ResponseEntity<>(authorService.getAll(PageRequest.of(page, size, Sort.by(sortParam))), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDto> get(@PathVariable("id") Long authorId) {
        return new ResponseEntity<>(authorService.getById(authorId), HttpStatus.OK);
    }

    @Async
    @PutMapping("/{id")
    public ResponseEntity<AuthorDto> update(@PathVariable Long authorId,
                                            @RequestBody @Valid AuthorDto author) {
        return new ResponseEntity<>(authorService.update(authorId, author), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable("id") Long authorId) {
        authorService.delete(authorId);
        return HttpStatus.NO_CONTENT;
    }
}
