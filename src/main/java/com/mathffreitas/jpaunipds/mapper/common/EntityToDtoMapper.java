package com.mathffreitas.jpaunipds.mapper.common;

import org.springframework.data.domain.Page;

public interface EntityToDtoMapper<E, D> {

    D toDto(E entity);

    default Page<D> toDtoPage(Page<E> page) {
        return page.map(this::toDto);
    }
}
