package com.triangle.library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Икслючение не найдена сущность
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

    private Long id;

    public NotFoundException(Long id) {
        super(String.format("Сущность с id = %d не найдена", id));
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
