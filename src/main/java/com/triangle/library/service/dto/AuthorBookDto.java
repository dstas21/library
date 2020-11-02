package com.triangle.library.service.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * DTO для обновления связи автора и книги
 */
@Data
public class AuthorBookDto {

    @NotBlank
    private Long authorId;

    @NotBlank
    private Long bookId;
}
