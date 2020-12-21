package com.triangle.library.dto;

import com.triangle.library.model.Book;
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
