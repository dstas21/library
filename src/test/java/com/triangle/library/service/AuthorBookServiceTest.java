package com.triangle.library.service;

import com.triangle.library.model.Author;
import com.triangle.library.model.Book;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static com.triangle.library.service.AuthorServiceTest.createAuthorWithId;
import static com.triangle.library.service.BookServiceTest.createBookWithId;

@RunWith(SpringRunner.class)
@SpringBootTest
class AuthorBookServiceTest {

    @Autowired
    private AuthorBookService authorBookService;

    @MockBean
    private AuthorService authorService;

    @MockBean
    private BookService bookService;

    @Test
    void add() {
        Author author = createAuthorWithId();
        Book book = createBookWithId();

        Mockito.doReturn(author)
               .when(authorService)
               .getById(author.getId());

        Mockito.doReturn(book)
               .when(bookService)
               .getById(book.getId());

        authorBookService.add(author.getId(), book.getId());

        Mockito.verify(authorService, Mockito.times(1))
               .getById(author.getId());

        Mockito.verify(bookService, Mockito.times(1))
               .getById(book.getId());

        Mockito.verify(authorService, Mockito.times(1))
               .update(author);
    }

    @Test
    void get() {
        Author author = createAuthorWithId();
        Book book = createBookWithId();

        authorBookService.get(book.getName());

        Mockito.verify(authorService, Mockito.times(1))
               .findByBookName(book.getName());
    }

    @Test
    void delete() {
        Author author = createAuthorWithId();
        Book book = createBookWithId();

        Mockito.doReturn(author)
               .when(authorService)
               .getById(author.getId());

        Mockito.doReturn(book)
               .when(bookService)
               .getById(book.getId());

        authorBookService.add(author.getId(), book.getId());

        Mockito.verify(authorService, Mockito.times(1))
               .getById(author.getId());

        Mockito.verify(bookService, Mockito.times(1))
               .getById(book.getId());

        Mockito.verify(authorService, Mockito.times(1))
               .update(author);
    }
}