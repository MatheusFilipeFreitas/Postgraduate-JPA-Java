package com.mathffreitas.jpaunipds.mapper.session;

import com.mathffreitas.jpaunipds.model.dto.session.SessionCreateDto;
import com.mathffreitas.jpaunipds.model.dto.session.SessionDto;
import com.mathffreitas.jpaunipds.model.dto.session.SessionUpdateDto;
import com.mathffreitas.jpaunipds.model.entity.conference.Conference;
import com.mathffreitas.jpaunipds.model.entity.session.Session;
import org.springframework.stereotype.Component;

@Component
public class SessionMapperImpl implements SessionMapper {

    @Override
    public SessionDto toDto(Session entity) {
        if (entity == null) {
            return null;
        }
        SessionDto dto = new SessionDto();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setStartDate(entity.getStartDate());
        dto.setStartTime(entity.getStartTime());
        if (entity.getConference() != null) {
            dto.setConferenceId(entity.getConference().getId());
        }
        return dto;
    }

    @Override
    public Session fromCreateDto(SessionCreateDto dto) {
        if (dto == null) {
            return null;
        }
        Session entity = new Session();
        entity.setTitle(dto.getTitle());
        entity.setStartDate(dto.getStartDate());
        entity.setStartTime(dto.getStartTime());
        entity.setConference(toConferenceReference(dto.getConferenceId()));
        return entity;
    }

    @Override
    public Session fromUpdateDto(SessionUpdateDto dto) {
        if (dto == null) {
            return null;
        }
        Session entity = new Session();
        entity.setTitle(dto.getTitle());
        entity.setStartDate(dto.getStartDate());
        entity.setStartTime(dto.getStartTime());
        entity.setConference(toConferenceReference(dto.getConferenceId()));
        return entity;
    }

    private Conference toConferenceReference(Long conferenceId) {
        if (conferenceId == null) {
            return null;
        }
        Conference conference = new Conference();
        conference.setId(conferenceId);
        return conference;
    }
}
