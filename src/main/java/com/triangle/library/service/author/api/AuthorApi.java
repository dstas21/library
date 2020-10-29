package com.triangle.library.service.author.api;

import com.triangle.library.service.author.dto.AuthorDto;
import com.triangle.library.service.author.model.Author;
import com.triangle.library.service.author.service.AuthorService;
import com.triangle.library.service.common.api.AbstractCrudApi;
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
