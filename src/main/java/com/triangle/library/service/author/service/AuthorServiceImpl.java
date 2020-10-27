package com.triangle.library.service.author.service;

import com.triangle.library.service.author.dto.AuthorDto;
import com.triangle.library.service.author.model.Author;
import com.triangle.library.service.author.repository.AuthorRepository;
import com.triangle.library.service.common.service.TransformationService;
import com.triangle.library.service.exception.author.AuthorNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Реализация сервиса для сущности автор {@link Author}
 */
@Service
public class AuthorServiceImpl extends TransformationService<Author, AuthorDto> implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        super(Author.class, AuthorDto.class, new ModelMapper());
        this.authorRepository = authorRepository;
    }

    /**
     * Создаем автора
     *
     * @param authorDto dto автора
     */
    @Override
    @Transactional
    public AuthorDto create(AuthorDto authorDto) {
        return toDto(authorRepository.save(toEntity(authorDto)));
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
     * @param id        идентификатор автора
     * @param authorDto dto автора
     */
    @Override
    @Transactional
    public AuthorDto update(Long id, AuthorDto authorDto) {
        Author author = toEntity(authorDto);
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

    /**
     * Поиск автора по id, если нет то выбрасываем ошибку {@link AuthorNotFoundException}
     *
     * @param id идентификатор
     */
    private Author findById(Long id) {
        return authorRepository.findById(id).orElseThrow(() -> new AuthorNotFoundException(id));
    }
}
