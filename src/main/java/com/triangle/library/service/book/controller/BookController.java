package com.triangle.library.service.book.controller;

import com.triangle.library.service.book.dto.BookDto;
import com.triangle.library.service.book.model.Book;
import com.triangle.library.service.book.service.BookService;
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
 * Сервис для сущности книга {@link Book}
 */
@RestController
@RequestMapping("/api/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @Async
    @PostMapping
    public ResponseEntity<BookDto> create(@RequestBody @Valid BookDto book) {
        return new ResponseEntity<>(bookService.create(book), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<BookDto>> getAll(@RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "50") int size,
                                                @RequestParam(defaultValue = "name") String sortParam) {
        return new ResponseEntity<>(bookService.getAll(PageRequest.of(page, size, Sort.by(sortParam))), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> get(@PathVariable("id") Long bookId) {
        return new ResponseEntity<>(bookService.getById(bookId), HttpStatus.OK);
    }

    @Async
    @PutMapping("/{id}")
    public ResponseEntity<BookDto> update(@PathVariable Long bookId,
                                          @RequestBody @Valid BookDto book) {
        return new ResponseEntity<>(bookService.update(bookId, book), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable("id") Long bookId) {
        bookService.delete(bookId);
        return HttpStatus.NO_CONTENT;
    }
}
