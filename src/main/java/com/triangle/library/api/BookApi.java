package com.triangle.library.api;

import com.triangle.library.mapper.BookMapper;
import com.triangle.library.model.Book;
import com.triangle.library.dto.BookDto;
import com.triangle.library.service.BookService;
import org.springframework.stereotype.Component;

/**
 * Api для сущности книга {@link Book}
 */
@Component
public class BookApi extends AbstractCrudApi<Book, BookDto> {

    public BookApi(BookService service,
                   BookMapper bookMapper) {
        super(service, bookMapper);
    }
}
