package com.triangle.library.service.common.service;

import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Интерфес сервиса создания, чтения, обновления и удаления (CRUD) сущности
 *
 * @param <D> dto сущности
 */
public interface CrudService<D> {

    D create(D dto);

    List<D> getAll(Pageable pageable);

    D getById(Long id);

    D update(Long id, D dto);

    void delete(Long id);
}
