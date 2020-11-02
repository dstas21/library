package com.triangle.library.service.service;

import com.triangle.library.service.model.Author;
import com.triangle.library.service.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

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
     * @param authorId идентификатор автора
     * @param bookId   идентификатор книги
     */
    public void add(Long authorId, Long bookId) {
        updateDependentEntities(authorId, bookId, false);
    }

    /**
     * Получение всех авторов по названию книги
     *
     * @param bookName название книги
     */
    public Page<Author> get(String bookName) {
        return authorService.findByBookName(bookName);
    }

    /**
     * Удаляет книгу у автора
     *
     * @param authorId идентификатор автора
     * @param bookId   идентификатор книги
     */
    public void delete(Long authorId, Long bookId) {
        updateDependentEntities(authorId, bookId, true);
    }

    /**
     * Обновляем связь книги с автором
     *
     * @param authorId идентификатор автора
     * @param bookId   идентификатор книги
     * @param remove   признак обновления, если remove is true то удаляем книгу у автора, иначе добавляем
     */
    private void updateDependentEntities(Long authorId, Long bookId, boolean remove) {
        Author author = authorService.getById(authorId);
        Book newBook = bookService.getById(bookId);

        Set<Book> books = author.getBooks();
        if (remove) {
            books.remove(newBook);
        } else {
            books.add(newBook);
        }

        author.setBooks(books);
        authorService.update(author);
    }
}
