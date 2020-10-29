package com.triangle.library.service.book.controller;

import com.triangle.library.service.book.api.BookApi;
import com.triangle.library.service.book.dto.BookDto;
import com.triangle.library.service.book.model.Book;
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

    private final BookApi bookApi;

    public BookController(BookApi bookApi) {
        this.bookApi = bookApi;
    }

    /**
     * Создаем автора
     *
     * @param book книга
     */
    @Async
    @PostMapping
    public ResponseEntity<BookDto> create(@RequestBody @Valid BookDto book) {
        return new ResponseEntity<>(bookApi.create(book), HttpStatus.CREATED);
    }

    /**
     * Получаем все книги
     *
     * @param page      страница
     * @param size      количество сущностей на страницу
     * @param sortParam параметр сортироваки
     */
    @GetMapping
    public ResponseEntity<List<BookDto>> getAll(@RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "50") int size,
                                                @RequestParam(defaultValue = "name") String sortParam) {
        return new ResponseEntity<>(bookApi.getAll(PageRequest.of(page, size, Sort.by(sortParam))), HttpStatus.OK);
    }

    /**
     * Получаем книгу по id
     *
     * @param id идентификатор
     */
    @GetMapping("/{id}")
    public ResponseEntity<BookDto> get(@PathVariable("id") Long id) {
        return new ResponseEntity<>(bookApi.getById(id), HttpStatus.OK);
    }

    /**
     * Обновляем книгу
     *
     * @param id   идентификатор автора
     * @param book обновленные поля книги
     */
    @Async
    @PutMapping("/{id}")
    public ResponseEntity<BookDto> update(@PathVariable Long id,
                                          @RequestBody @Valid BookDto book) {
        return new ResponseEntity<>(bookApi.update(id, book), HttpStatus.OK);
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
