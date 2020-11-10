package com.triangle.library.mapper;

import com.triangle.library.dto.AuthorDto;
import com.triangle.library.model.Author;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring", uses = BookMapper.class)
public interface AuthorMapper extends AbstractMapper<Author, AuthorDto> {

    @Override
    AuthorDto toDto(Author entity);

    @Override
    Author toEntity(AuthorDto dto);

    @Override
    List<AuthorDto> toDtoList(Page<Author> entitys);
}
