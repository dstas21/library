package com.triangle.library.service.common.api;

import com.triangle.library.service.common.model.BaseEntity;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Добавляет поддержку трансформации из entity в dto и обратно
 *
 * @param <E> сущность
 * @param <D> dto сущности
 */
public abstract class TransformationApi<E extends BaseEntity, D> {

    private final ModelMapper modelMapper;
    private final Class<E> entityType;
    private final Class<D> dtoType;

    protected TransformationApi(Class<E> entityType,
                                Class<D> dtoType) {
        this.modelMapper = new ModelMapper();
        this.entityType = entityType;
        this.dtoType = dtoType;
    }

    /**
     * Получение класса сущности
     */
    public Class<E> getEntityType() {
        return this.entityType;
    }

    /**
     * Получение класса dto сущности
     */
    public Class<D> getDtoType() {
        return this.dtoType;
    }

    /**
     * Мапинг из dto сущности в сущность
     *
     * @param dto dto сущности
     */
    public E toEntity(D dto) {
        return modelMapper.map(dto, getEntityType());
    }

    /**
     * Мапинг из сущности в dto сущности
     *
     * @param entity сущность
     */
    public D toDto(E entity) {
        return modelMapper.map(entity, getDtoType());
    }

    /**
     * Мапинг сущностей в список dto сущностей
     *
     * @param entitys сущности
     */
    public List<D> toDtoList(Page<E> entitys) {
        return entitys.map(this::toDto).stream().collect(Collectors.toList());
    }
}

