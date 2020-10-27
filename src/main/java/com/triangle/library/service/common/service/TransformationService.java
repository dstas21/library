package com.triangle.library.service.common.service;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Добавляет поддержку трансформации из entity в dto и обратно
 */
public abstract class TransformationService<E, D> {

    private final ModelMapper modelMapper;

    private final Class<E> entityType;
    private final Class<D> dtoType;

    protected TransformationService(Class<E> entityType,
                                    Class<D> dtoType,
                                    ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.entityType = entityType;
        this.dtoType = dtoType;
    }

    public Class<E> getEntityType() {
        return this.entityType;
    }

    public Class<D> getDtoType() {
        return this.dtoType;
    }

    public E toEntity(D dto) {
        return modelMapper.map(dto, getEntityType());
    }

    public D toDto(E entity) {
        return modelMapper.map(entity, getDtoType());
    }

    public List<D> toDtoList(Page<E> entitys) {
        return entitys.map(this::toDto).stream().collect(Collectors.toList());
    }
}
