package com.mathffreitas.jpaunipds.mapper.common;

public interface UpdateDtoToEntityMapper<U, E> {

    E fromUpdateDto(U dto);
}
