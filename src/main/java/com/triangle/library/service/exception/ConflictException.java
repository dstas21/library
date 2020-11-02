package com.triangle.library.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Исклчение дубликат сущности
 */
@ResponseStatus(code = HttpStatus.CONFLICT)
public class ConflictException extends RuntimeException {

    public ConflictException(String field) {
        super(String.format("Сущность с %s уже существует", field));
    }

    public ConflictException(String firstField, String secondField) {
        super(String.format("Сущность с %s и %s уже существует", firstField, secondField));
    }
}
