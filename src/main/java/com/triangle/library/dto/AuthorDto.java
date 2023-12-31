package com.triangle.library.dto;

import com.triangle.library.model.Author;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * DTO для сущности автор {@link Author}
 */
@Data
public class AuthorDto {

    @NotBlank
    @Size(max = 80)
    private String firstName;

    @NotBlank
    @Size(max = 80)
    private String lastName;
}
