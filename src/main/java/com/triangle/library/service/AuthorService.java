package com.triangle.library.service;

import com.triangle.library.exception.ConflictException;
import com.triangle.library.model.Author;
import com.triangle.library.repository.AuthorRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

/**
 * Реализация сервиса для сущности автор {@link Author}
 */
@Service
public class AuthorService extends AbstractCrudService<Author> {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        super(authorRepository);
        this.authorRepository = authorRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public Author create(Author entity) {
        checkDuplicateFirstAndLastName(entity);
        return super.create(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public Author update(Author entity) throws RuntimeException {
        checkDuplicateFirstAndLastName(entity);
        return super.update(entity);
    }

    /**
     * Получение всех авторов по названию книги
     *
     * @param bookName название книги
     */
    public List<Author> findByBookName(String bookName) {
        return Collections.emptyList();
    }

    /**
     * Проверяем автора на уникальность имени и фамилии
     * Если автор с таким именем и фамилией существует - исключение {@link ConflictException}
     *
     * @param author автор
     */
    private void checkDuplicateFirstAndLastName(Author author) {
        authorRepository.findAuthorByFirstNameAndLastName(author.getFirstName(), author.getLastName())
                        .ifPresent(found -> {
                            if (!found.equals(author)) {
                                throw new ConflictException(
                                        author.getFirstName(),
                                        author.getLastName()
                                );
                            }
                        });
    }
}
