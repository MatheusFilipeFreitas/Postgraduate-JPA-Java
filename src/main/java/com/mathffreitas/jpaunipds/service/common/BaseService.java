package com.mathffreitas.jpaunipds.service.common;

import com.mathffreitas.jpaunipds.model.entity.common.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BaseService<T extends BaseEntity> {

    Page<T> findAll(Pageable pageable);

    T findById(Long id);

    T create(T entity);

    T update(Long id, T entity);

    void delete(Long id);
}
