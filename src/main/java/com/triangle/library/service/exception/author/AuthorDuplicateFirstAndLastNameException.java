package com.triangle.library.service.exception.author;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Исклчение дубликата имени и фамилии автора
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class AuthorDuplicateFirstAndLastNameException extends RuntimeException {
    public AuthorDuplicateFirstAndLastNameException(String firstName, String lastName) {
        super(String.format("Автор с именем %s и фамилией %s уже существует", firstName, lastName));
    }
}
