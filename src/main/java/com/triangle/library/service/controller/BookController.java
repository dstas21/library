package com.triangle.library.service.controller;

import com.triangle.library.service.api.BookApi;
import com.triangle.library.service.dto.BookDto;
import com.triangle.library.service.model.Book;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * Сервис для сущности книга {@link Book}
 */
@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookApi bookApi;

    public BookController(BookApi bookApi) {
        this.bookApi = bookApi;
    }

    /**
     * Создаем автора
     *
     * @param book книга
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<BookDto> create(@RequestBody @Valid BookDto book) {
        return ResponseEntity.ok(bookApi.create(book));
    }

    /**
     * Получаем все книги
     *
     * @param page страница
     * @param size количество сущностей на страницу
     * @param sort параметр сортироваки
     */
    @GetMapping
    public ResponseEntity<List<BookDto>> getAll(@RequestParam(name = "page", defaultValue = "0") int page,
                                                @RequestParam(name = "size", defaultValue = "50") int size,
                                                @RequestParam(name = "sort", defaultValue = "name") String sort) {
        return ResponseEntity.ok(bookApi.getAll(PageRequest.of(page, size, Sort.by(sort))));
    }

    /**
     * Получаем книгу по id
     *
     * @param id идентификатор
     */
    @GetMapping("/{id}")
    public ResponseEntity<BookDto> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(bookApi.getById(id));
    }

    /**
     * Обновляем книгу
     *
     * @param id   идентификатор автора
     * @param book обновленные поля книги
     */
    @PutMapping("/{id}")
    public ResponseEntity<BookDto> update(@PathVariable Long id,
                                          @RequestBody @Valid BookDto book) {
        return ResponseEntity.ok(bookApi.update(id, book));
    }

    /**
     * Удаляем книгу по id
     *
     * @param id идентификатор
     */
    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable("id") Long id) {
        bookApi.delete(id);
        return HttpStatus.NO_CONTENT;
    }
}
