package com.mathffreitas.jpaunipds.service.session;

import com.mathffreitas.jpaunipds.exceptions.NotFoundException;
import com.mathffreitas.jpaunipds.model.entity.conference.Conference;
import com.mathffreitas.jpaunipds.model.entity.session.Session;
import com.mathffreitas.jpaunipds.repository.common.BaseRepository;
import com.mathffreitas.jpaunipds.repository.conference.ConferenceRepository;
import com.mathffreitas.jpaunipds.repository.session.SessionRepository;
import com.mathffreitas.jpaunipds.service.common.AbstractBaseService;
import org.springframework.stereotype.Service;

@Service
public class SessionServiceImpl extends AbstractBaseService<Session> implements SessionService {

    private final SessionRepository sessionRepository;
    private final ConferenceRepository conferenceRepository;

    public SessionServiceImpl(SessionRepository sessionRepository, ConferenceRepository conferenceRepository) {
        this.sessionRepository = sessionRepository;
        this.conferenceRepository = conferenceRepository;
    }

    @Override
    protected BaseRepository<Session> getRepository() {
        return sessionRepository;
    }

    @Override
    protected String getEntityName() {
        return "Session";
    }

    @Override
    protected Session prepareForCreate(Session session) {
        session.setConference(resolveConference(session.getConference()));
        return session;
    }

    @Override
    protected void updateEntity(Session existing, Session incoming) {
        existing.setTitle(incoming.getTitle());
        existing.setStartDate(incoming.getStartDate());
        existing.setStartTime(incoming.getStartTime());
        if (incoming.getConference() != null) {
            existing.setConference(resolveConference(incoming.getConference()));
        }
    }

    private Conference resolveConference(Conference conference) {
        if (conference == null || conference.getId() == null) {
            throw new IllegalArgumentException("Conference id is required");
        }
        return conferenceRepository.findById(conference.getId())
                .orElseThrow(() -> new NotFoundException("Conference", conference.getId()));
    }
}
