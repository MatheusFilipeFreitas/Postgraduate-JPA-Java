package com.mathffreitas.jpaunipds.service.conference;

import com.mathffreitas.jpaunipds.exceptions.NotFoundException;
import com.mathffreitas.jpaunipds.model.entity.conference.Conference;
import com.mathffreitas.jpaunipds.model.entity.session.Session;
import com.mathffreitas.jpaunipds.repository.common.BaseRepository;
import com.mathffreitas.jpaunipds.repository.conference.ConferenceRepository;
import com.mathffreitas.jpaunipds.repository.session.SessionRepository;
import com.mathffreitas.jpaunipds.service.common.AbstractBaseService;
import com.mathffreitas.jpaunipds.util.PageableUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ConferenceServiceImpl extends AbstractBaseService<Conference> implements ConferenceService {

    private final ConferenceRepository conferenceRepository;
    private final SessionRepository sessionRepository;

    public ConferenceServiceImpl(ConferenceRepository conferenceRepository, SessionRepository sessionRepository) {
        this.conferenceRepository = conferenceRepository;
        this.sessionRepository = sessionRepository;
    }

    @Override
    protected BaseRepository<Conference> getRepository() {
        return conferenceRepository;
    }

    @Override
    protected String getEntityName() {
        return "Conference";
    }

    @Override
    protected void updateEntity(Conference existing, Conference incoming) {
        existing.setName(incoming.getName());
        existing.setAddress(incoming.getAddress());
    }

    @Override
    public Page<Session> findSessions(Long conferenceId, Pageable pageable) {
        if (!conferenceRepository.existsById(conferenceId)) {
            throw new NotFoundException("Conference", conferenceId);
        }
        return sessionRepository.findByConference_Id(conferenceId, PageableUtils.sanitize(pageable));
    }
}
