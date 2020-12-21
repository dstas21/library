package com.triangle.library.service;

import com.triangle.library.exception.ConflictException;
import com.triangle.library.exception.NotFoundException;
import com.triangle.library.model.Book;
import com.triangle.library.repository.BookRepository;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.triangle.library.jms.BookReceiver.CREATE_BOOK;
import static com.triangle.library.jms.BookReceiver.UPDATE_BOOK;

/**
 * Реализация сервиса для сущности книга {@link Book}
 */
@Service
public class BookService extends AbstractCrudService<Book> {

    private final BookRepository bookRepository;
    private final JmsTemplate jmsTemplate;

    public BookService(BookRepository bookRepository, JmsTemplate jmsTemplate) {
        super(bookRepository);
        this.bookRepository = bookRepository;
        this.jmsTemplate = jmsTemplate;
    }

    /**
     * Получение книги по названию
     */
    public Book getBookByName(String bookName) {
        return bookRepository.findBookByName(bookName)
                             .orElseThrow(() -> new NotFoundException(bookName));
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public Book create(Book book) {
        checkDuplicateName(book);
        jmsTemplate.convertAndSend(CREATE_BOOK, book);
        return bookRepository.save(book);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public Book update(Book book) {
        checkDuplicateName(book);
        jmsTemplate.convertAndSend(UPDATE_BOOK, book);
        return super.update(book);
    }

    /**
     * Проверяем книгу на уникальность названия
     * Если книга с таким название существует - исключение {@link ConflictException}
     *
     * @param book книга
     */
    private void checkDuplicateName(Book book) {
        bookRepository.findBookByName(book.getName())
                      .ifPresent(found -> {
                          if (!found.equals(book)) {
                              throw new ConflictException(book.getName());
                          }
                      });
    }
}
