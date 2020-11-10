package com.triangle.library.mapper;

import org.springframework.data.domain.Page;

import java.util.List;

public interface AbstractMapper<E, D> {

    D toDto(E entity);

    E toEntity(D dto);

    List<D> toDtoList(Page<E> entitys);
}
