package com.triangle.library.service;

import com.triangle.library.exception.ConflictException;
import com.triangle.library.exception.NotFoundException;
import com.triangle.library.model.Book;
import com.triangle.library.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import static com.triangle.library.Utils.generateId;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
class BookServiceTest {

    private static final AtomicInteger BookCounter = new AtomicInteger(0);

    @Autowired
    private BookService bookService;

    @MockBean
    private BookRepository bookRepository;

    @Test
    void create() {
        Book book = createBook();

        bookService.create(book);

        Mockito.verify(bookRepository, Mockito.times(1))
               .findBookByName(book.getName());

        Mockito.verify(bookRepository, Mockito.times(1))
               .save(book);
    }

    @Test()
    void createDuplicate() {
        Book book = createBook();

        Mockito.doThrow(new ConflictException(book.getName()))
               .when(bookRepository)
               .findBookByName(book.getName());

        try {
            bookService.create(book);

            assert false : "Expected ConflictException";
        } catch (ConflictException e) {
            assertThat(e.getField()).isEqualTo(book.getName());
        }

        Mockito.verify(bookRepository, Mockito.times(1))
               .findBookByName(book.getName());

        Mockito.verify(bookRepository, Mockito.times(0))
               .save(book);
    }

    @Test
    void getAll() {
        Book book1 = createBook();
        Book book2 = createBook();

        Mockito.doReturn(new PageImpl(Arrays.asList(book1, book2)))
               .when(bookRepository)
               .findAll(Pageable.unpaged());

        Page<Book> returnedBooks = bookService.getAll(Pageable.unpaged());

        assertThat(returnedBooks).containsOnly(book1, book2);

        Mockito.verify(bookRepository, Mockito.times(1))
               .findAll(Pageable.unpaged());
    }

    @Test
    void getById() {
        Book book = createBookWithId();

        Mockito.doReturn(Optional.of(book))
               .when(bookRepository)
               .findById(book.getId());

        Book returnedBook = bookService.getById(book.getId());

        assertThat(returnedBook.getId()).isEqualTo(book.getId());

        Mockito.verify(bookRepository, Mockito.times(1))
               .findById(book.getId());
    }

    @Test
    void getByNotFoundId() {
        Book book = createBookWithId();

        Long id = generateId();

        Mockito.doThrow(new NotFoundException(id))
               .when(bookRepository)
               .findById(id);

        try {
            bookService.getById(book.getId());

            assert false : "Expected NotFoundException";
        } catch (NotFoundException e) {
            assertThat(e.getId()).isEqualTo(book.getId());
        }

        Mockito.verify(bookRepository, Mockito.times(1))
               .findById(book.getId());
    }

    @Test
    void update() {
        Book update = createBookWithId();

        bookService.update(update);

        Mockito.verify(bookRepository, Mockito.times(1))
               .findBookByName(update.getName());

        Mockito.verify(bookRepository, Mockito.times(1))
               .save(update);
    }

    @Test
    void updateDuplicate() {
        Book update = createBookWithId();

        Mockito.doThrow(new ConflictException(update.getName()))
               .when(bookRepository)
               .findBookByName(update.getName());

        try {
            bookService.update(update);

            assert false : "Expected ConflictException";
        } catch (ConflictException e) {
            assertThat(e.getField()).isEqualTo(update.getName());
        }

        Mockito.verify(bookRepository, Mockito.times(1))
               .findBookByName(update.getName());

        Mockito.verify(bookRepository, Mockito.times(0))
               .save(update);
    }

    @Test
    void delete() {
        Book book = createBookWithId();

        bookService.delete(book.getId());

        Mockito.verify(bookRepository, Mockito.times(1))
               .deleteById(book.getId());
    }

    static Book createBook() {
        Book book = new Book();
        book.setName("name" + BookCounter.incrementAndGet());

        return book;
    }

    static Book createBookWithId() {
        Book book = new Book();
        book.setId(generateId());
        book.setName("name" + BookCounter.incrementAndGet());

        return book;
    }
}