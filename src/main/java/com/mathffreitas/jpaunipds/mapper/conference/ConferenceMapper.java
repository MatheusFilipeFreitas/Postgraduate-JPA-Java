package com.mathffreitas.jpaunipds.mapper.conference;

import com.mathffreitas.jpaunipds.mapper.common.CreateDtoToEntityMapper;
import com.mathffreitas.jpaunipds.mapper.common.EntityToDtoMapper;
import com.mathffreitas.jpaunipds.mapper.common.UpdateDtoToEntityMapper;
import com.mathffreitas.jpaunipds.model.dto.conference.ConferenceCreateDto;
import com.mathffreitas.jpaunipds.model.dto.conference.ConferenceDto;
import com.mathffreitas.jpaunipds.model.dto.conference.ConferenceUpdateDto;
import com.mathffreitas.jpaunipds.model.entity.conference.Conference;

public interface ConferenceMapper extends
        EntityToDtoMapper<Conference, ConferenceDto>,
        CreateDtoToEntityMapper<ConferenceCreateDto, Conference>,
        UpdateDtoToEntityMapper<ConferenceUpdateDto, Conference> {
}
