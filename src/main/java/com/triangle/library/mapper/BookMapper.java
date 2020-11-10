package com.triangle.library.mapper;

import com.triangle.library.model.Book;
import com.triangle.library.dto.BookDto;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring", uses = AuthorMapper.class)
public interface BookMapper extends AbstractMapper<Book, BookDto> {

    @Override
    BookDto toDto(Book entity);

    @Override
    Book toEntity(BookDto dto);

    @Override
    List<BookDto> toDtoList(Page<Book> entitys);
}
