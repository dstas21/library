package com.triangle.library.service;

import com.triangle.library.exception.ConflictException;
import com.triangle.library.exception.NotFoundException;
import com.triangle.library.model.Author;
import com.triangle.library.repository.AuthorRepository;
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
import java.util.HashSet;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import static com.triangle.library.Utils.generateId;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
class AuthorServiceTest {

    private static final AtomicInteger authorCounter = new AtomicInteger(0);

    @Autowired
    private AuthorService authorService;

    @MockBean
    private AuthorRepository authorRepository;

    @Test
    void create() {
        Author author = createAuthor();

        authorService.create(author);

        Mockito.verify(authorRepository, Mockito.times(1))
               .findAuthorByFirstNameAndLastName(author.getFirstName(), author.getLastName());

        Mockito.verify(authorRepository, Mockito.times(1))
               .save(author);
    }

    @Test()
    void createDuplicate() {
        Author author = createAuthor();

        Mockito.doThrow(new ConflictException(author.getFirstName(), author.getLastName()))
               .when(authorRepository)
               .findAuthorByFirstNameAndLastName(author.getFirstName(), author.getLastName());

        try {
            authorService.create(author);

            assert false : "Expected ConflictException";
        } catch (ConflictException e) {
            assertThat(e.getFirstField()).isEqualTo(author.getFirstName());
            assertThat(e.getSecondField()).isEqualTo(author.getLastName());
        }

        Mockito.verify(authorRepository, Mockito.times(1))
               .findAuthorByFirstNameAndLastName(author.getFirstName(), author.getLastName());

        Mockito.verify(authorRepository, Mockito.times(0))
               .save(author);
    }

    @Test
    void getAll() {
        Author author1 = createAuthor();
        Author author2 = createAuthor();

        Mockito.doReturn(new PageImpl(Arrays.asList(author1, author2)))
               .when(authorRepository)
               .findAll(Pageable.unpaged());

        Page<Author> returnedAuthors = authorService.getAll(Pageable.unpaged());

        assertThat(returnedAuthors).containsOnly(author1, author2);

        Mockito.verify(authorRepository, Mockito.times(1))
               .findAll(Pageable.unpaged());
    }

    @Test
    void getById() {
        Author author = createAuthorWithId();

        Mockito.doReturn(Optional.of(author))
               .when(authorRepository)
               .findById(author.getId());

        Author returnedAuthor = authorService.getById(author.getId());

        assertThat(returnedAuthor.getId()).isEqualTo(author.getId());

        Mockito.verify(authorRepository, Mockito.times(1))
               .findById(author.getId());
    }

    @Test
    void getByNotFoundId() {
        Author author = createAuthorWithId();

        Long id = generateId();

        Mockito.doThrow(new NotFoundException(id))
               .when(authorRepository)
               .findById(id);

        try {
            authorService.getById(author.getId());

            assert false : "Expected NotFoundException";
        } catch (NotFoundException e) {
            assertThat(e.getId()).isEqualTo(author.getId());
        }

        Mockito.verify(authorRepository, Mockito.times(1))
               .findById(author.getId());
    }

    @Test
    void update() {
        Author update = createAuthorWithId();

        authorService.update(update);

        Mockito.verify(authorRepository, Mockito.times(1))
               .findAuthorByFirstNameAndLastName(update.getFirstName(), update.getLastName());

        Mockito.verify(authorRepository, Mockito.times(1))
               .save(update);
    }

    @Test
    void updateDuplicate() {
        Author update = createAuthorWithId();

        Mockito.doThrow(new ConflictException(update.getFirstName(), update.getLastName()))
               .when(authorRepository)
               .findAuthorByFirstNameAndLastName(update.getFirstName(), update.getLastName());

        try {
            authorService.update(update);

            assert false : "Expected ConflictException";
        } catch (ConflictException e) {
            assertThat(e.getFirstField()).isEqualTo(update.getFirstName());
            assertThat(e.getSecondField()).isEqualTo(update.getLastName());
        }

        Mockito.verify(authorRepository, Mockito.times(1))
               .findAuthorByFirstNameAndLastName(update.getFirstName(), update.getLastName());

        Mockito.verify(authorRepository, Mockito.times(0))
               .save(update);
    }

    @Test
    void delete() {
        Author author = createAuthorWithId();

        authorService.delete(author.getId());

        Mockito.verify(authorRepository, Mockito.times(1))
               .deleteById(author.getId());
    }

    static Author createAuthor() {
        Author author = new Author();
        author.setFirstName("firstName" + authorCounter.incrementAndGet());
        author.setLastName("lastName" + authorCounter.get());

        return author;
    }

    static Author createAuthorWithId() {
        Author author = new Author();
        author.setId(generateId());
        author.setFirstName("firstName" + authorCounter.incrementAndGet());
        author.setLastName("lastName" + authorCounter.get());
        author.setBooks(new HashSet<>());

        return author;
    }
}