package com.mathffreitas.jpaunipds.service.conference;

import com.mathffreitas.jpaunipds.model.entity.conference.Conference;
import com.mathffreitas.jpaunipds.model.entity.session.Session;
import com.mathffreitas.jpaunipds.service.common.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ConferenceService extends BaseService<Conference> {

    Page<Session> findSessions(Long conferenceId, Pageable pageable);
}
