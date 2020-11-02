package com.triangle.library.service.api;

import com.triangle.library.service.dto.BookDto;
import com.triangle.library.service.model.Book;
import com.triangle.library.service.service.BookService;
import org.springframework.stereotype.Component;

/**
 * Api для сущности книга {@link Book}
 */
@Component
public class BookApi extends AbstractCrudApi<Book, BookDto> {

    public BookApi(BookService service) {
        super(Book.class, BookDto.class, service);
    }
}
