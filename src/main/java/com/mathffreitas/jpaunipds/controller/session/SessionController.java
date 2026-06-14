package com.mathffreitas.jpaunipds.controller.session;

import com.mathffreitas.jpaunipds.controller.common.BaseController;
import com.mathffreitas.jpaunipds.hateoas.SessionResourceAssembler;
import com.mathffreitas.jpaunipds.mapper.session.SessionMapper;
import com.mathffreitas.jpaunipds.model.dto.session.SessionCreateDto;
import com.mathffreitas.jpaunipds.model.dto.session.SessionDto;
import com.mathffreitas.jpaunipds.model.dto.session.SessionUpdateDto;
import com.mathffreitas.jpaunipds.model.entity.session.Session;
import com.mathffreitas.jpaunipds.service.session.SessionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sessions")
@Tag(name = "Sessions", description = "Session management endpoints")
public class SessionController extends BaseController<
        Session, SessionDto, SessionCreateDto, SessionUpdateDto, SessionService, SessionMapper, SessionResourceAssembler> {

    public SessionController(
            SessionService sessionService,
            SessionMapper sessionMapper,
            SessionResourceAssembler assembler) {
        super(sessionService, sessionMapper, assembler);
    }
}
