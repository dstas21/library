package com.triangle.library.service.dto;

import com.triangle.library.service.model.Book;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * DTO для сущности книга {@link Book}
 */
@Data
public class BookDto {

    @NotBlank
    @Size(max = 255)
    private String name;
}
