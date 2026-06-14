package com.mathffreitas.jpaunipds.mapper.conference;

import com.mathffreitas.jpaunipds.model.dto.conference.ConferenceCreateDto;
import com.mathffreitas.jpaunipds.model.dto.conference.ConferenceDto;
import com.mathffreitas.jpaunipds.model.dto.conference.ConferenceUpdateDto;
import com.mathffreitas.jpaunipds.model.entity.conference.Conference;
import org.springframework.stereotype.Component;

@Component
public class ConferenceMapperImpl implements ConferenceMapper {

    @Override
    public ConferenceDto toDto(Conference entity) {
        if (entity == null) {
            return null;
        }
        ConferenceDto dto = new ConferenceDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setAddress(entity.getAddress());
        return dto;
    }

    @Override
    public Conference fromCreateDto(ConferenceCreateDto dto) {
        if (dto == null) {
            return null;
        }
        Conference entity = new Conference();
        entity.setName(dto.getName());
        entity.setAddress(dto.getAddress());
        return entity;
    }

    @Override
    public Conference fromUpdateDto(ConferenceUpdateDto dto) {
        if (dto == null) {
            return null;
        }
        Conference entity = new Conference();
        entity.setName(dto.getName());
        entity.setAddress(dto.getAddress());
        return entity;
    }
}
