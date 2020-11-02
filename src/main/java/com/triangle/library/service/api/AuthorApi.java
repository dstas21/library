package com.triangle.library.service.api;

import com.triangle.library.service.dto.AuthorDto;
import com.triangle.library.service.model.Author;
import com.triangle.library.service.service.AuthorService;
import org.springframework.stereotype.Component;

/**
 * Api для сущности автор {@link Author}
 */
@Component
public class AuthorApi extends AbstractCrudApi<Author, AuthorDto> {

    public AuthorApi(AuthorService service) {
        super(Author.class, AuthorDto.class, service);
    }
}
