package com.triangle.library.service;

import com.triangle.library.model.Author;
import com.triangle.library.model.Book;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<Author> get(String bookName) {
        return authorService.getByBook(bookService.getBookByName(bookName));
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

        if (remove) {
            author.getBooks().remove(newBook);
        } else {
            author.getBooks().add(newBook);
        }
        authorService.update(author);
    }
}
