package com.mathffreitas.jpaunipds.mapper.common;

public interface CreateDtoToEntityMapper<C, E> {

    E fromCreateDto(C dto);
}
