package com.mathffreitas.jpaunipds.mapper.session;

import com.mathffreitas.jpaunipds.mapper.common.CreateDtoToEntityMapper;
import com.mathffreitas.jpaunipds.mapper.common.EntityToDtoMapper;
import com.mathffreitas.jpaunipds.mapper.common.UpdateDtoToEntityMapper;
import com.mathffreitas.jpaunipds.model.dto.session.SessionCreateDto;
import com.mathffreitas.jpaunipds.model.dto.session.SessionDto;
import com.mathffreitas.jpaunipds.model.dto.session.SessionUpdateDto;
import com.mathffreitas.jpaunipds.model.entity.session.Session;

public interface SessionMapper extends
        EntityToDtoMapper<Session, SessionDto>,
        CreateDtoToEntityMapper<SessionCreateDto, Session>,
        UpdateDtoToEntityMapper<SessionUpdateDto, Session> {
}
