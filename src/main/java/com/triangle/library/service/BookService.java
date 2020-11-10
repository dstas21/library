package com.triangle.library.service;

import com.triangle.library.exception.ConflictException;
import com.triangle.library.model.Book;
import com.triangle.library.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Реализация сервиса для сущности книга {@link Book}
 */
@Service
public class BookService extends AbstractCrudService<Book> {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        super(bookRepository);
        this.bookRepository = bookRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public Book create(Book book) {
        checkDuplicateName(book);
        return bookRepository.save(book);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public Book update(Book book) {
        checkDuplicateName(book);
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
