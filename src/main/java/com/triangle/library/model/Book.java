package com.triangle.library.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

/**
 * Сущность Книга
 */
@Entity
@Table(name = "books", schema = "library")
@Getter
@Setter
@ToString
public class Book extends BaseEntity {

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "books")
    Set<Author> authors = new HashSet<>();
}
