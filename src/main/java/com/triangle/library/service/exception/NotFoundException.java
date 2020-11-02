package com.triangle.library.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Икслючение не найдена сущность
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
    public NotFoundException(Long id) {
        super(String.format("Сущность с id = %d не найдена", id));
    }
}
