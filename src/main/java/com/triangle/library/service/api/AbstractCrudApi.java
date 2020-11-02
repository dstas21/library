package com.triangle.library.service.api;

import com.triangle.library.service.model.BaseEntity;
import com.triangle.library.service.service.CrudService;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Абстрактная реализация интерфес api создания, чтения, обновления и удаления (CRUD) сущности
 *
 * @param <E> сущность
 * @param <D> dto сущности
 */
public abstract class AbstractCrudApi<E extends BaseEntity, D> extends TransformationApi<E, D> implements CrudApi<D> {

    private final CrudService<E> service;

    public AbstractCrudApi(Class<E> entityType,
                           Class<D> dtoType,
                           CrudService<E> service) {
        super(entityType, dtoType);
        this.service = service;
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public D create(D dto) {
        return toDto(service.create(toEntity(dto)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<D> getAll(Pageable pageable) {
        return toDtoList(service.getAll(pageable));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public D getById(Long id) {
        return toDto(service.getById(id));
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public D update(Long id, D dto) {
        service.getById(id);
        E entity = toEntity(dto);
        entity.setId(id);
        return toDto(service.update(entity));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Long id) {
        service.delete(id);
    }
}
