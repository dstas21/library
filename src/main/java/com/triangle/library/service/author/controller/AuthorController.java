package com.triangle.library.service.author.controller;

import com.triangle.library.service.author.api.AuthorApi;
import com.triangle.library.service.author.dto.AuthorDto;
import com.triangle.library.service.author.model.Author;
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

    private final AuthorApi authorApi;

    public AuthorController(AuthorApi authorApi) {
        this.authorApi = authorApi;
    }

    /**
     * Создаем автора
     *
     * @param author автор
     */
    @Async
    @PostMapping
    public ResponseEntity<AuthorDto> create(@RequestBody @Valid AuthorDto author) {
        return new ResponseEntity<>(authorApi.create(author), HttpStatus.CREATED);
    }

    /**
     * Получаем всех авторов
     *
     * @param page      страница
     * @param size      количество сущностей на страницу
     * @param sortParam параметр сортироваки
     */
    @GetMapping
    public ResponseEntity<List<AuthorDto>> getAll(@RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "50") int size,
                                                  @RequestParam(defaultValue = "firstName") String sortParam) {
        return new ResponseEntity<>(authorApi.getAll(PageRequest.of(page, size, Sort.by(sortParam))), HttpStatus.OK);
    }

    /**
     * Получаем автора по id
     *
     * @param id идентификатор
     */
    @GetMapping("/{id}")
    public ResponseEntity<AuthorDto> get(@PathVariable("id") Long id) {
        return new ResponseEntity<>(authorApi.getById(id), HttpStatus.OK);
    }

    /**
     * Обновляем автора
     *
     * @param id     идентификатор автора
     * @param author обновленные поля автора
     */
    @Async
    @PutMapping("/{id}")
    public ResponseEntity<AuthorDto> update(@PathVariable Long id,
                                            @RequestBody @Valid AuthorDto author) {
        return new ResponseEntity<>(authorApi.update(id, author), HttpStatus.OK);
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
