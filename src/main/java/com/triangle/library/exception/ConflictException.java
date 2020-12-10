package com.triangle.library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Исклчение дубликат сущности
 */
@ResponseStatus(code = HttpStatus.CONFLICT)
public class ConflictException extends RuntimeException {

    private String field;
    private String firstField;
    private String secondField;

    public ConflictException(String field) {
        super(String.format("Сущность с %s уже существует", field));
        this.field = field;
    }

    public ConflictException(String firstField, String secondField) {
        super(String.format("Сущность с %s и %s уже существует", firstField, secondField));
        this.firstField = firstField;
        this.secondField = secondField;
    }

    public String getField() {
        return field;
    }

    public String getFirstField() {
        return firstField;
    }

    public String getSecondField() {
        return secondField;
    }
}
