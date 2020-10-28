package com.triangle.library.service.author.service;

import com.triangle.library.service.author.dto.AuthorDto;
import com.triangle.library.service.author.model.Author;
import com.triangle.library.service.author.repository.AuthorRepository;
import com.triangle.library.service.book.service.BookService;
import com.triangle.library.service.common.service.CrudService;
import com.triangle.library.service.common.service.TransformationService;
import com.triangle.library.service.exception.author.AuthorNotFoundException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.Collections.singleton;

/**
 * Реализация сервиса для сущности автор {@link Author}
 */
@Service
public class AuthorService extends TransformationService<Author, AuthorDto>
        implements CrudService<AuthorDto> {

    private final AuthorRepository authorRepository;
    private final BookService bookService;

    public AuthorService(AuthorRepository authorRepository,
                         BookService bookService) {
        super(Author.class, AuthorDto.class);
        this.authorRepository = authorRepository;
        this.bookService = bookService;
    }

    /**
     * Создаем автора
     *
     * @param dto dto автора
     */
    @Override
    @Transactional
    public AuthorDto create(AuthorDto dto) {
        return toDto(authorRepository.save(toEntity(dto)));
    }

    /**
     * Получаем список всех авторов
     */
    @Override
    public List<AuthorDto> getAll(Pageable pageable) {
        return toDtoList(authorRepository.findAll(pageable));
    }

    /**
     * Получение автора по id
     *
     * @param id идентификатор
     */
    @Override
    public AuthorDto getById(Long id) {
        return toDto(findById(id));
    }

    /**
     * Обновляем автора по id
     *
     * @param id  идентификатор автора
     * @param dto dto автора
     */
    @Override
    @Transactional
    public AuthorDto update(Long id, AuthorDto dto) {
        Author author = toEntity(dto);
        author.setId(id);
        return toDto(authorRepository.save(author));
    }

    /**
     * Удаляем автора по id
     *
     * @param id идентификатор
     */
    @Override
    public void delete(Long id) {
        authorRepository.deleteById(id);
    }

    public List<AuthorDto> findByBookName(String name) {
        return toDtoList(authorRepository.findAuthorByBooks(singleton(bookService.findBookByName(name))));
    }

    /**
     * Поиск автора по id, если нет то выбрасываем ошибку {@link AuthorNotFoundException}
     *
     * @param id идентификатор
     */
    public Author findById(Long id) {
        return authorRepository.findById(id).orElseThrow(() -> new AuthorNotFoundException(id));
    }
}
