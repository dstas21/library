package com.triangle.library.service.controller;

import com.triangle.library.service.api.AuthorApi;
import com.triangle.library.service.dto.AuthorDto;
import com.triangle.library.service.model.Author;
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
 * Сервис для сущности автор {@link Author}
 */
@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    private final AuthorApi authorApi;

    public AuthorController(AuthorApi authorApi) {
        this.authorApi = authorApi;
    }

    /**
     * Создаем автора
     *
     * @param author автор
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AuthorDto> create(@RequestBody @Valid AuthorDto author) {
        return ResponseEntity.ok(authorApi.create(author));
    }

    /**
     * Получаем всех авторов
     *
     * @param page страница
     * @param size количество сущностей на страницу
     * @param sort параметр сортироваки
     */
    @GetMapping
    public ResponseEntity<List<AuthorDto>> getAll(@RequestParam(name = "page", defaultValue = "0") int page,
                                                  @RequestParam(name = "size", defaultValue = "50") int size,
                                                  @RequestParam(name = "sort", defaultValue = "name") String sort) {
        return ResponseEntity.ok(authorApi.getAll(PageRequest.of(page, size, Sort.by(sort))));
    }

    /**
     * Получаем автора по id
     *
     * @param id идентификатор
     */
    @GetMapping("/{id}")
    public ResponseEntity<AuthorDto> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(authorApi.getById(id));
    }

    /**
     * Обновляем автора
     *
     * @param id     идентификатор автора
     * @param author обновленные поля автора
     */
    @PutMapping("/{id}")
    public ResponseEntity<AuthorDto> update(@PathVariable Long id,
                                            @RequestBody @Valid AuthorDto author) {
        return ResponseEntity.ok(authorApi.update(id, author));
    }

    /**
     * Удаляем автора по id
     *
     * @param id идентификатор
     */
    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable("id") Long id) {
        authorApi.delete(id);
        return HttpStatus.NO_CONTENT;
    }
}
