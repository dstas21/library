package com.triangle.library.service;

import com.triangle.library.exception.ConflictException;
import com.triangle.library.model.Author;
import com.triangle.library.model.Book;
import com.triangle.library.repository.AuthorRepository;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.triangle.library.jms.AuthorReceiver.CREATE_AUTHOR;
import static com.triangle.library.jms.AuthorReceiver.UPDATE_AUTHOR;


/**
 * Реализация сервиса для сущности автор {@link Author}
 */
@Service
public class AuthorService extends AbstractCrudService<Author> {

    private final AuthorRepository authorRepository;
    private final JmsTemplate jmsTemplate;

    public AuthorService(AuthorRepository authorRepository, JmsTemplate jmsTemplate) {
        super(authorRepository);
        this.authorRepository = authorRepository;
        this.jmsTemplate = jmsTemplate;
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public Author create(Author entity) {
        jmsTemplate.convertAndSend(CREATE_AUTHOR, entity);
        checkDuplicateFirstAndLastName(entity);
        return super.create(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public Author update(Author entity) throws RuntimeException {
        jmsTemplate.convertAndSend(UPDATE_AUTHOR, entity);
        checkDuplicateFirstAndLastName(entity);
        return super.update(entity);
    }

    /**
     * Получение всех авторов по книге
     *
     * @param book книга
     */
    public List<Author> getByBook(Book book) {
        return authorRepository.findAuthorsByBooks(book);
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
                            if (!found.getId().equals(author.getId())) {
                                throw new ConflictException(
                                        author.getFirstName(),
                                        author.getLastName()
                                );
                            }
                        });
    }
}
