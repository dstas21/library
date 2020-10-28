package com.triangle.library.service.book.service;

import com.triangle.library.service.book.dto.BookDto;
import com.triangle.library.service.book.model.Book;
import com.triangle.library.service.book.repository.BookRepository;
import com.triangle.library.service.common.service.CrudService;
import com.triangle.library.service.common.service.TransformationService;
import com.triangle.library.service.exception.book.BookDuplicateNameException;
import com.triangle.library.service.exception.book.BookNotFoundException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Реализация сервиса для сущности книга {@link Book}
 */
@Service
public class BookService extends TransformationService<Book, BookDto>
        implements CrudService<BookDto> {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        super(Book.class, BookDto.class);
        this.bookRepository = bookRepository;
    }

    /**
     * Создаем книгу
     *
     * @param dto dto книги
     */
    @Transactional
    @Override
    public BookDto create(BookDto dto) {
        findBookByName(dto.getName());

        return toDto(bookRepository.save(toEntity(dto)));
    }

    /**
     * Получаем список всех книг
     */
    @Override
    public List<BookDto> getAll(Pageable pageable) {
        return toDtoList(bookRepository.findAll(pageable));
    }

    /**
     * Получение книги по id
     *
     * @param id идентификатор
     */
    @Override
    public BookDto getById(Long id) {
        return toDto(findById(id));
    }

    /**
     * Обновляем книгу по id
     *
     * @param id  идентификатор книги
     * @param dto dto автора
     */
    @Override
    public BookDto update(Long id, BookDto dto) {
        findBookByName(dto.getName());

        Book book = toEntity(dto);
        book.setId(id);
        return toDto(bookRepository.save(book));
    }

    /**
     * Удаляем книги по id
     *
     * @param id идентификатор
     */
    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    /**
     * Поиск книги по названию, если нет то выбрасываем ошибку {@link BookDuplicateNameException}
     *
     * @param name название книги
     */
    public Book findBookByName(String name) {
        return bookRepository.findBookByName(name).orElseThrow(() -> new BookDuplicateNameException(name));
    }

    /**
     * Поиск книги по id, если нет то выбрасываем ошибку {@link BookNotFoundException}
     *
     * @param id идентификатор
     */
    public Book findById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
    }
}
