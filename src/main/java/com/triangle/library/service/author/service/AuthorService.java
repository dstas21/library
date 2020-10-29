package com.triangle.library.service.author.service;

import com.triangle.library.service.author.model.Author;
import com.triangle.library.service.author.repository.AuthorRepository;
import com.triangle.library.service.common.service.AbstractCrudService;
import com.triangle.library.service.exception.author.AuthorDuplicateFirstAndLastNameException;
import com.triangle.library.service.exception.author.AuthorNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Page<Author> findByBookName(String bookName) {
        return authorRepository.findByBookName(bookName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected RuntimeException notFound(Long id) {
        return new AuthorNotFoundException(id);
    }

    /**
     * Проверяем автора на уникальность имени и фамилии
     * Если автор с таким именем и фамилией существует - исключение {@link AuthorDuplicateFirstAndLastNameException}
     *
     * @param author автор
     */
    private void checkDuplicateFirstAndLastName(Author author) {
        authorRepository.findAuthorByFirstNameAndLastName(author.getFirstName(), author.getLastName())
                        .ifPresent(found -> {
                            if (!found.equals(author)) {
                                throw new AuthorDuplicateFirstAndLastNameException(
                                        author.getFirstName(),
                                        author.getLastName()
                                );
                            }
                        });
    }
}
