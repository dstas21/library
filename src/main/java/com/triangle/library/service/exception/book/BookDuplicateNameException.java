package com.triangle.library.service.exception.book;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class BookDuplicateNameException extends RuntimeException {
    public BookDuplicateNameException(String name) {
        super(String.format("Книга с название = %s уже существует", name));
    }
}
