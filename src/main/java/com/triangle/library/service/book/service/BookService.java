package com.triangle.library.service.book.service;

import com.triangle.library.service.book.model.Book;
import com.triangle.library.service.book.repository.BookRepository;
import com.triangle.library.service.common.service.AbstractCrudService;
import com.triangle.library.service.exception.book.BookDuplicateNameException;
import com.triangle.library.service.exception.book.BookNotFoundException;
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
     * {@inheritDoc}
     */
    @Override
    protected RuntimeException notFound(Long id) {
        return new BookNotFoundException(id);
    }

    /**
     * Проверяем книгу на уникальность названия
     * Если книга с таким название существует - исключение {@link BookDuplicateNameException}
     *
     * @param book книга
     */
    private void checkDuplicateName(Book book) {
        bookRepository.findBookByName(book.getName())
                      .ifPresent(found -> {
                          if (!found.equals(book)) {
                              throw new BookDuplicateNameException(book.getName());
                          }
                      });
    }
}
