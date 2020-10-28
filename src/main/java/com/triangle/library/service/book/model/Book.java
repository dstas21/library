package com.triangle.library.service.book.model;

import com.triangle.library.service.author.model.Author;
import com.triangle.library.service.common.model.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

/**
 * Сущность Книга
 */
@Entity
@Table(name = "books", schema = "library")
@Data
public class Book extends BaseEntity {

    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "books")
    Set<Author> authors = new HashSet<>();
}
