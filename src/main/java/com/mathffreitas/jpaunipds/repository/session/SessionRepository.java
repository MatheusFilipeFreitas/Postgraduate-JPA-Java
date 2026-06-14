package com.mathffreitas.jpaunipds.repository.session;

import com.mathffreitas.jpaunipds.model.entity.session.Session;
import com.mathffreitas.jpaunipds.repository.common.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SessionRepository extends BaseRepository<Session> {

    Page<Session> findByConference_Id(Long conferenceId, Pageable pageable);
}
