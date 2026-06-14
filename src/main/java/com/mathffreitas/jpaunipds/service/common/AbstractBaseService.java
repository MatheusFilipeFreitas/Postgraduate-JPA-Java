package com.mathffreitas.jpaunipds.service.common;

import com.mathffreitas.jpaunipds.exceptions.NotFoundException;
import com.mathffreitas.jpaunipds.model.entity.common.BaseEntity;
import com.mathffreitas.jpaunipds.repository.common.BaseRepository;
import com.mathffreitas.jpaunipds.util.PageableUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public abstract class AbstractBaseService<T extends BaseEntity> implements BaseService<T> {

    protected abstract BaseRepository<T> getRepository();

    protected abstract String getEntityName();

    protected abstract void updateEntity(T existing, T incoming);

    @Override
    public Page<T> findAll(Pageable pageable) {
        return getRepository().findAll(PageableUtils.sanitize(pageable));
    }

    @Override
    public T findById(Long id) {
        return getRepository().findById(id)
                .orElseThrow(() -> new NotFoundException(getEntityName(), id));
    }

    @Override
    @Transactional
    public T create(T entity) {
        return getRepository().save(prepareForCreate(entity));
    }

    protected T prepareForCreate(T entity) {
        return entity;
    }

    @Override
    @Transactional
    public T update(Long id, T entity) {
        T existing = findById(id);
        updateEntity(existing, entity);
        return getRepository().save(existing);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!getRepository().existsById(id)) {
            throw new NotFoundException(getEntityName(), id);
        }
        getRepository().deleteById(id);
    }
}
