package com.triangle.library.service.author.service;

import com.triangle.library.service.author.dto.AuthorBookDto;
import com.triangle.library.service.author.dto.AuthorDto;
import com.triangle.library.service.author.model.Author;
import com.triangle.library.service.book.model.Book;
import com.triangle.library.service.book.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * Реализация сервиса для связи автора и книг
 */
@Service
public class AuthorBookService {

    private final AuthorService authorService;
    private final BookService bookService;

    public AuthorBookService(AuthorService authorService,
                             BookService bookService) {
        this.authorService = authorService;
        this.bookService = bookService;
    }

    /**
     * Добавляет книгу к автору
     *
     * @param authorBookDto dto обновления связи автора и книги
     */
    public void add(AuthorBookDto authorBookDto) {
        updateDependentEntities(authorBookDto, false);
    }

    public List<AuthorDto> get(String bookName) {
        return authorService.findByBookName(bookName);
    }

    /**
     * удаляет книгу к автору
     *
     * @param authorBookDto dto обновления связи автора и книги
     */
    public void delete(AuthorBookDto authorBookDto) {
        updateDependentEntities(authorBookDto, true);
    }

    private void updateDependentEntities(AuthorBookDto authorBookDto, boolean remove) {
        Author author = authorService.findById(authorBookDto.getAuthorId());
        Book newBook = bookService.findById(authorBookDto.getBookId());

        Set<Book> books = author.getBooks();
        if (remove) {
            books.remove(newBook);
        } else {
            books.add(newBook);
        }

        author.setBooks(books);
        authorService.update(author.getId(), authorService.toDto(author));
    }
}
