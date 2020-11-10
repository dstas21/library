package com.triangle.library.api;

import com.triangle.library.dto.AuthorDto;
import com.triangle.library.mapper.AuthorMapper;
import com.triangle.library.model.Author;
import com.triangle.library.service.AuthorService;
import org.springframework.stereotype.Component;

/**
 * Api для сущности автор {@link Author}
 */
@Component
public class AuthorApi extends AbstractCrudApi<Author, AuthorDto> {

    public AuthorApi(AuthorService service,
                     AuthorMapper mapper) {
        super(service, mapper);
    }
}
