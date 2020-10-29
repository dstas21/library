package com.triangle.library.service.book.api;

import com.triangle.library.service.book.dto.BookDto;
import com.triangle.library.service.book.model.Book;
import com.triangle.library.service.book.service.BookService;
import com.triangle.library.service.common.api.AbstractCrudApi;
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
