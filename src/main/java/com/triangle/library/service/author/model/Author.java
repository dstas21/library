package com.triangle.library.service.author.model;

import com.triangle.library.service.common.model.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Сущность Автор
 */
@Entity
@Table(name = "authors", schema = "library")
@Data
public class Author extends BaseEntity {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;
}
